#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<netdb.h>
#include<signal.h>
#include<fcntl.h>

#define BYTES 1024
#define CLIENT_MAX 1000

void error(char *);

int main(int argc, char* argv[])
{	
	int server_socket, client_socket[CLIENT_MAX], n, i;
	char *root, port_number[6];
	struct sockaddr_in client_address;
	socklen_t addresslen;

	if (argc < 2) {
         fprintf(stderr,"ERROR, no port_number provided\n");
         exit(1);
     }

	root = getenv("PWD");//current directory path

	strcpy(port_number,argv[1]);

	// Setting all elements to -1: signifies there is no client connected
	for (i=0; i<CLIENT_MAX; i++)
		client_socket[i]=-1;
	struct addrinfo hints, *result, *p;//hints - give information which i want to set network type to getaddrinfo
	//result - network address information from dns server
	// getaddrinfo for host
	memset (&hints, 0, sizeof(hints));
	hints.ai_family = AF_INET;//IPv4 internet protocol
	hints.ai_socktype = SOCK_STREAM;//TCP protocol
	hints.ai_flags = AI_PASSIVE;//to get localhost information
	if (getaddrinfo( NULL, port_number, &hints, &result) != 0)//get network ip address
	{
		perror ("getaddrinfo() error");
		exit(1);
	}
	// socket and bind
	for (p = result; p!=NULL; p=p->ai_next)
	{
		server_socket = socket (p->ai_family, p->ai_socktype, 0);//make server_socket as AF_INET, TCP
		if (server_socket == -1) continue;
		if (bind(server_socket, p->ai_addr, p->ai_addrlen) == 0) break;//bind server_socket with server address, server address.length
	}
	if (p==NULL)
	{

		perror ("socket() or bind() error");
		exit(1);
	}

	freeaddrinfo(result);//to prevent memory leak

	// listen for incoming connections
	if ( listen (server_socket, 100000) != 0 )//check client's entering request
	{
		perror("listen() error");
		exit(1);
	}

	// accept, send what client requests
	while (1)
	{
		addresslen = sizeof(client_address);
		client_socket[n] = accept (server_socket, (struct sockaddr *) &client_address, &addresslen);//make client's socket, permit client's entering request

		if (client_socket[n]<0)
			error ("accept() error");
		else
		{
			if ( fork()==0 )//make process
			{
				char message[100000], *request[3], data[BYTES], path[100000];

				int fd, recv_len, data_len;

				memset( (void*)message, (int)'\0', 100000 );//set message null

				recv_len=recv(client_socket[n], message, 100000, 0);//get message from client

				if (recv_len<0)    // receive error
					fprintf(stderr,("recv() error\n"));
				else if (recv_len==0)    // receive socket closed
					fprintf(stderr,"Client disconnected upexpectedly.\n");
				else    // message received
				{
					printf("%s", message);//print GET blah blah
					request[0] = strtok (message, " \t\n");//parse what client request

					if ( strncmp(request[0], "GET\0", 4)==0 )
					{
						request[1] = strtok (NULL, " \t");//parse what client request
						request[2] = strtok (NULL, " \t\n");//parse what client request

						if ( strncmp( request[2], "HTTP/1.0", 8)!=0 && strncmp( request[2], "HTTP/1.1", 8)!=0 )
						{
							write(client_socket[n], "HTTP/1.0 400 Bad Request\n", 25);
						}
						else
						{
							if ( strncmp(request[1], "/\0", 2)==0 )
								request[1] = "/index.html";        //set default page

							strcpy(path, root);
							strcpy(&path[strlen(root)], request[1]);

							if ( (fd=open( path, O_RDONLY ))!=-1 )    //found file client request
							{
								send(client_socket[n], "HTTP/1.0 200 OK\n\n", 17, 0);//send client ok
								while ( (data_len=read(fd, data, BYTES))>0 ){//read data
									write (client_socket[n], data, data_len);//write data
								}
							}
							else
							{
							    write(client_socket[n], "HTTP/1.0 404 Not Found\n", 23); //can't find file client wants
							}
						}
					}
				}

				//close socket
				shutdown (client_socket[n], SHUT_RDWR);//stop sending, receiving
				close(client_socket[n]);
				client_socket[n]=-1;
				exit(0);
			}
		}

		while (client_socket[n]!=-1) n = (n+1)%CLIENT_MAX;//if n is over 1024(CLIENT_MAX) make it < 1024
	}

	return 0;
}
