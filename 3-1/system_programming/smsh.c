#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>

#define BSIZE 1024
#define WRITESIZE 1024
#define FPERM 0644

int instruction_number;
int amp;
int current_instruction;
char previous_directory[BSIZE];
char current_directory[BSIZE];
char buffers[BSIZE];
char **tokens_pointer;
char *comm[10]={";", "|", "<", ">>", ">|", ">", "&"};
int history_last;
char *history[BSIZE];
int sharing_pipe[2];
int io_pipe[2];
char history_zero[BSIZE];
int info_fd;
int command_num;
int pid;
int pid_pipe[2];
char pid_list[50][50];
int pid_tail=1;
int pid2;
int pid3;

void parsing();
void redirect();
void piping();
void cd();
void history_print();
void run();
void cwd_from_child();
void history_from_child();
void pid_from_child();

int main(){
	pipe(sharing_pipe);
	pipe(io_pipe);
	int flag=fcntl(io_pipe[0],F_GETFL,0);
	fcntl(io_pipe[0],F_SETFL,flag | O_NONBLOCK);
	pipe(pid_pipe);
	int flag2=fcntl(pid_pipe[0],F_GETFL,0);
	fcntl(pid_pipe[0],F_SETFL,flag2 | O_NONBLOCK);
	/* read command line until “end of file” */
	strcpy(current_directory,getcwd(NULL,BSIZE));
	while (read(STDIN_FILENO, buffers, BSIZE)){
		if(buffers[0]!=0 && (history_last==0 || strcmp(history[history_last-1],buffers)!=0)){
			history[history_last++]=strdup(buffers);
		}
		char *tokens[BSIZE]={0};
		tokens_pointer=tokens;
		current_instruction=0;
		parsing();
		// for(int i=0;tokens_pointer[i]!=NULL;i++){
		// 	printf("checking tokens = %s\n",tokens_pointer[i]);
		// }
		if ((pid3=fork()) == 0){
			for(instruction_number=0;;instruction_number++){
				if(tokens_pointer[instruction_number]==NULL){
					if(current_instruction<instruction_number){
						run();
						pid_from_child();
					}
					write(sharing_pipe[1],current_directory,WRITESIZE);
					write(sharing_pipe[1],previous_directory,WRITESIZE);
					write(sharing_pipe[1],history_zero,WRITESIZE);
					remove("info");
					for(int i=1;i<pid_tail;i++){
						write(pid_pipe[1],pid_list[i],30);
					}
					exit(0);
				}
				command_num=7;
				for(int j=0;j<7;j++){
					if(strcmp(tokens_pointer[instruction_number],comm[j])==0){
						command_num=j;
						break;
					}
				}
				if(command_num<7){
					run();
					pid_from_child();
				}
			}
		}
		/* parent continues over here, 
		 * wait for child to exit if required
		 */
		waitpid(pid3,NULL,0);
		pid_tail=1;
		while(1){
			char pid_data[50]={0};
			read(pid_pipe[0],pid_data,30);
			if(pid_data[0]==0){
				break;
			}
			else{
				strcpy(pid_list[pid_tail++],pid_data);
			}
		}
		cwd_from_child();
		history_from_child();
		if(strcmp(history_zero,"on")==0){
			memset(history_zero,0,BSIZE);
			for(int i=0;i<history_last;i++){
				history[i]=0;
			}
			history_last=0;
		}
		memset(buffers,0,BSIZE);
	} // end of while loop
}
void parsing(){
	/* parse command line */
	int i=0;
	char *token = strtok(buffers," \n");
	char *sample[BSIZE]={0};
	while(token !=NULL){
		sample[i++]=token;
		token = strtok(NULL," \n");
	}
	int n=0;
	for(int j=0;j<i;j++){
		int l=0;
		int m=0;
		char temp[BSIZE]={0};
		char temp2[BSIZE]={0};
		for(int k=0;k<strlen(sample[j]);k++){
			if(sample[j][k]==';' || sample[j][k]=='&' || sample[j][k]=='|' || sample[j][k]=='<' || sample[j][k]=='>'){
				temp2[m++]=sample[j][k];
				if(l>0){
					tokens_pointer[n++]=strdup(temp);
					l=0;
					memset(temp,0,BSIZE);
				}
				if(sample[j][k+1]==0){
					tokens_pointer[n++]=strdup(temp2);
				}
			}
			else{
				temp[l++]=sample[j][k];
				if(m>0){
					tokens_pointer[n++]=strdup(temp2);
					m=0;
					memset(temp2,0,BSIZE);
				}
				if(sample[j][k+1]==0){
					tokens_pointer[n++]=strdup(temp);
				}
			}
		}
	}
}
void cd(){
	if(instruction_number - current_instruction>2){
		printf("%d %d\n",instruction_number,current_instruction);
		printf("too many arguments\n");
	}
	else{
		char argument[BSIZE]={0};
		strcpy(argument,tokens_pointer[instruction_number-1]);
		if(strcmp(argument,"cd")==0 || strcmp(argument,"$HOME")==0 || strcmp(argument,"~")==0){
			chdir(getenv("HOME"));
			strcpy(previous_directory,current_directory);
		}
		else if(strcmp(argument,"-")==0){
			if(previous_directory[0]==0){
				printf("OLDPWD not set\n");
			}
			else{
				chdir(previous_directory);
				strcpy(previous_directory,current_directory);
			}
		}
		else{
			if(chdir(argument)==0){
				strcpy(previous_directory,current_directory);
			}
			else{
				printf("wrong directory\n");
			}
		}
		strcpy(current_directory,getcwd(NULL,BSIZE));
	}
}
void history_print(){
	char argument[BSIZE]={0};
	strcpy(argument,tokens_pointer[instruction_number-1]);
	int num = atoi(argument);
	if(instruction_number - current_instruction>3){
		printf("too many arguments\n");
	}
	else if(instruction_number - current_instruction==3){
		if(strcmp(tokens_pointer[instruction_number-2],"-w")==0){
			int newfile = creat(tokens_pointer[instruction_number-1],FPERM);
			for(int i=0;i<history_last;i++){
				char temp[BSIZE]={0};
				strcpy(temp,history[i]);
				write(newfile,temp,WRITESIZE);
			}
		}
		else{
			printf("wrong arguments\n");
		}
	}
	else if(instruction_number - current_instruction==2){
		if(strcmp(tokens_pointer[instruction_number-1],"-c")==0){
			strcpy(history_zero,"on");
		}
		else if(num!=0){
			if(num>history_last)
				num=history_last;
			for(int i=history_last-num;i<history_last;i++){
				char temp[BSIZE]={" "};
				char index[BSIZE]={0};
				sprintf(index, "%d", i+1);
				strcat(temp,index);
				strcat(temp,"  ");
				strcat(temp,history[i]);
				write(io_pipe[1],temp,WRITESIZE);
			}
		}
		else{
			printf("wrong arguments\n");
		}
	}
	else{
		for(int i=0;i<history_last;i++){
			char temp[BSIZE]={" "};
			char index[BSIZE]={0};
			sprintf(index, "%d", i+1);
			strcat(temp,index);
			strcat(temp,"  ");
			strcat(temp,history[i]);
			write(io_pipe[1],temp,WRITESIZE);
		}
	}
}
void run(){
	char last_instruction[BSIZE]={0};
	if(current_instruction>0){
		strcpy(last_instruction,tokens_pointer[current_instruction-1]);
	}
	if((command_num==2 && (tokens_pointer[instruction_number+1]==NULL || access(tokens_pointer[instruction_number+1], 0)==-1)) || ((command_num==3 || command_num==4 || command_num==5) && tokens_pointer[instruction_number+1]==NULL)){
		printf("syntax error near unexpected input\n");
		instruction_number=300;
		current_instruction=301;
	}
	else{
		if(command_num==2 || command_num==3 || command_num==4 || command_num==5){
			for(int j=0;j<7;j++){
				if(tokens_pointer[instruction_number+2]==NULL){
					command_num=7;
					break;
				}
				if(strcmp(tokens_pointer[instruction_number+2],comm[j])==0){
					command_num=j;
					break;
				}
			}
		}
		if(strcmp(tokens_pointer[current_instruction],"cd")==0){
			if((pid2=fork())==0){
				if(command_num==6){
					if((pid=fork())==0){
						cd(tokens_pointer[current_instruction+1]);
						if(strcmp(last_instruction,"|")!=0)
							if(tokens_pointer[instruction_number]==NULL || strcmp(tokens_pointer[instruction_number],"&")!=0 && strcmp(tokens_pointer[instruction_number],"|")!=0){
								write(sharing_pipe[1],current_directory,WRITESIZE);
								write(sharing_pipe[1],previous_directory,WRITESIZE);	
							}
						exit(0);
					}
					printf("[%d] %d\n",pid_tail,pid);
					char a[BSIZE]={0};
					sprintf(a,"%d",pid);
					write(pid_pipe[1],a,10);
					wait(NULL);
					printf("[%d]+  Done                    ",pid_tail);
					for(int i=current_instruction;i<instruction_number;i++){
						printf("%s ",tokens_pointer[i]);
					}
					printf("\n");
					write(pid_pipe[1],a,10);
					exit(0);
				}
				else{
					cd(tokens_pointer[current_instruction+1]);
					if(strcmp(last_instruction,"|")!=0)
						if(tokens_pointer[instruction_number]==NULL || strcmp(tokens_pointer[instruction_number],"&")!=0 && strcmp(tokens_pointer[instruction_number],"|")!=0){
							write(sharing_pipe[1],current_directory,WRITESIZE);
							write(sharing_pipe[1],previous_directory,WRITESIZE);	
						}
					exit(0);
				}
			}
		}
		else if(strcmp(tokens_pointer[current_instruction],"history")==0){
			if((pid2=fork())==0){
				if(command_num==6){
					if((pid=fork())==0){

						if(tokens_pointer[instruction_number]==NULL || strcmp(tokens_pointer[instruction_number],"&")!=0 && strcmp(tokens_pointer[instruction_number],"|")!=0)
							if(strcmp(tokens_pointer[current_instruction],"history")==0 && strcmp(last_instruction,"|")!=0)
								write(sharing_pipe[1],history_zero,WRITESIZE);
						exit(0);
					}
					printf("[%d] %d\n",pid_tail,pid);
					char a[BSIZE]={0};
					sprintf(a,"%d",pid);
					write(pid_pipe[1],a,30);
					wait(NULL);
					printf("[%d]+  Done                    ",pid_tail);
					for(int i=current_instruction;i<instruction_number;i++){
						printf("%s ",tokens_pointer[i]);
					}
					printf("\n");
					write(pid_pipe[1],a,10);
					exit(0);
				}
				else{
					history_print();
					if(tokens_pointer[instruction_number]==NULL || strcmp(tokens_pointer[instruction_number],"&")!=0 && strcmp(tokens_pointer[instruction_number],"|")!=0)
						if(strcmp(tokens_pointer[current_instruction],"history")==0 && strcmp(last_instruction,"|")!=0)
							write(sharing_pipe[1],history_zero,WRITESIZE);
					exit(0);
				}
			}
		}
		else{
			if((pid2=fork())==0){
				if(command_num==6){
					if((pid=fork())==0){
						char *command[BSIZE]={0};
						int j=0;
						for(j=0;current_instruction<instruction_number;j++,current_instruction++){
							command[j]=tokens_pointer[current_instruction];
						}
						if(strcmp(last_instruction,"|")==0 && tokens_pointer[instruction_number]!=NULL && strcmp(tokens_pointer[instruction_number],"<")==0){
							int redirection_file = open(tokens_pointer[instruction_number+1], O_RDONLY, FPERM);
							if(strcmp(command[0],"ls")!=0)
								if(strcmp(command[0],"grep")!=0 || j<3)
									command[j++]=tokens_pointer[instruction_number+1];	
						}
						else if(strcmp(last_instruction,"|")==0){
							if(strcmp(command[0],"ls")!=0)
								if(strcmp(command[0],"grep")!=0 || j<3)
									command[j++]=strdup("info");
						}
						else if(tokens_pointer[instruction_number]!=NULL && strcmp(tokens_pointer[instruction_number],"<")==0){
							int redirection_file = open(tokens_pointer[instruction_number+1], O_RDONLY, FPERM);
							if(strcmp(command[0],"ls")!=0 && strcmp(command[0],"echo")!=0)
								if(strcmp(command[0],"grep")!=0 || j<3){
									command[j++]=tokens_pointer[instruction_number+1];
								}
						}
						
						if(strcmp(command[0],"grep")==0){
							command[j++]=strdup("-a");
						}
						// for(int i=0;command[i]!=0;i++){
						// 	printf("! %s\n",command[i]);
						// }

						if((strcmp(command[0],"ls")==0 || strcmp(command[0],"more")==0) && (tokens_pointer[instruction_number]== NULL || tokens_pointer[instruction_number]!= NULL &&
							(strcmp(tokens_pointer[instruction_number],"&")==0 || strcmp(tokens_pointer[instruction_number],"<")==0 || 
								strcmp(tokens_pointer[instruction_number],";")==0))){
							
						}
						else{
							close(STDOUT_FILENO);
							dup(io_pipe[1]);
							close(io_pipe[1]);
						}
						if(execvp(command[0],command)==-1){
							printf("wrong instruction\n");
						}
						exit(1);
					}
					printf("[%d] %d\n",pid_tail,pid);
					char a[BSIZE]={0};
					sprintf(a,"%d",pid);
					write(pid_pipe[1],a,30);
					int status;
					waitpid(pid,&status,0);
					if(status==0)
						printf("[%d]+  Done                    ",pid_tail);
					else
						printf("[%d]+  Exit 127                ",pid_tail);
					for(int i=current_instruction;i<instruction_number;i++){
						printf("%s ",tokens_pointer[i]);
					}
					printf("\n");
					write(pid_pipe[1],a,30);
					exit(0);
				}
				else{
					char *command[BSIZE]={0};
					int j=0;
					for(j=0;current_instruction<instruction_number;j++,current_instruction++){
						command[j]=tokens_pointer[current_instruction];
					}
					if(strcmp(last_instruction,"|")==0 && tokens_pointer[instruction_number]!=NULL && strcmp(tokens_pointer[instruction_number],"<")==0){
						int redirection_file = open(tokens_pointer[instruction_number+1], O_RDONLY, FPERM);
						if(strcmp(command[0],"ls")!=0)
							if(strcmp(command[0],"grep")!=0 || j<3)
								command[j++]=tokens_pointer[instruction_number+1];	
					}
					else if(strcmp(last_instruction,"|")==0){
						if(strcmp(command[0],"ls")!=0)
							if(strcmp(command[0],"grep")!=0 || j<3)
								command[j++]=strdup("info");
					}
					else if(tokens_pointer[instruction_number]!=NULL && strcmp(tokens_pointer[instruction_number],"<")==0){
						int redirection_file = open(tokens_pointer[instruction_number+1], O_RDONLY, FPERM);
						if(strcmp(command[0],"ls")!=0 && strcmp(command[0],"echo")!=0)
							if(strcmp(command[0],"grep")!=0 || j<3){
								command[j++]=tokens_pointer[instruction_number+1];
							}
					}
					
					if(strcmp(command[0],"grep")==0){
						command[j++]=strdup("-a");
					}
					// for(int i=0;command[i]!=0;i++){
					// 	printf("! %s\n",command[i]);
					// }

					if((strcmp(command[0],"ls")==0 || strcmp(command[0],"more")==0) && (tokens_pointer[instruction_number]== NULL || tokens_pointer[instruction_number]!= NULL &&
						(strcmp(tokens_pointer[instruction_number],"&")==0 || strcmp(tokens_pointer[instruction_number],"<")==0 || 
							strcmp(tokens_pointer[instruction_number],";")==0))){
						
					}
					else{
						close(STDOUT_FILENO);
						dup(io_pipe[1]);
						close(io_pipe[1]);
					}
					if(execvp(command[0],command)==-1){
						printf("wrong instruction\n");
					}
					exit(1);
				}
			}
		}
		if (tokens_pointer[instruction_number]==NULL || strcmp(tokens_pointer[instruction_number],"&")!=0)
			waitpid(pid2,NULL,0);
		if(strcmp(last_instruction,"|")!=0 && strcmp(tokens_pointer[current_instruction],"cd")==0)
			if(tokens_pointer[instruction_number]==NULL || strcmp(tokens_pointer[instruction_number],"&")!=0 && strcmp(tokens_pointer[instruction_number],"|")!=0){
				cwd_from_child();
			}
		if(tokens_pointer[instruction_number]==NULL || strcmp(tokens_pointer[instruction_number],"&")!=0 && strcmp(tokens_pointer[instruction_number],"|")!=0)
			if(strcmp(tokens_pointer[current_instruction],"history")==0)
				history_from_child();
		if(command_num==1){
			info_fd = creat("info",0644);
			while(1){
				char io_data[BSIZE]={0};
				read(io_pipe[0],io_data,BSIZE);
				if(io_data[0]==0){
					break;
				}
				else{
					write(info_fd,io_data,strlen(io_data));
				}
			}
		}
		else if(tokens_pointer[instruction_number]!=NULL && (strcmp(tokens_pointer[instruction_number],">>")==0 || strcmp(tokens_pointer[instruction_number],">")==0 || strcmp(tokens_pointer[instruction_number],">|")==0)){
			int redirection_file;
			if(strcmp(tokens_pointer[instruction_number],">>")==0){
				redirection_file=open(tokens_pointer[instruction_number+1], O_WRONLY | O_CREAT | O_APPEND, FPERM);
			}
			else if(strcmp(tokens_pointer[instruction_number],">|")==0){
				redirection_file=open(tokens_pointer[instruction_number+1], O_WRONLY | O_CREAT | O_TRUNC, FPERM);
			}
			else{
				redirection_file=open(tokens_pointer[instruction_number+1], O_WRONLY | O_CREAT | O_EXCL, FPERM);
			}
			if(redirection_file==-1){
				printf("the file is already exist\n");
				while(1){
					char io_data[BSIZE]={0};
					read(io_pipe[0],io_data,BSIZE);
					if(io_data[0]==0){
						break;
					}
				}
			}
			else
				while(1){
					char io_data[BSIZE]={0};
					read(io_pipe[0],io_data,BSIZE);
					if(io_data[0]==0){
						break;
					}
					else{
						write(redirection_file,io_data,strlen(io_data));
					}
				}
		}
		else{
			while(1){
				char io_data[BSIZE]={0};
				read(io_pipe[0],io_data,BSIZE);
				if(io_data[0]==0){
					break;
				}
				else{
					write(STDOUT_FILENO,io_data,WRITESIZE);
				}
			}
		}
		if(tokens_pointer[instruction_number]!=NULL && (strcmp(tokens_pointer[instruction_number],"<")==0 || strcmp(tokens_pointer[instruction_number],">>")==0 || strcmp(tokens_pointer[instruction_number],">")==0 || strcmp(tokens_pointer[instruction_number],">|")==0)){
			instruction_number+=2;
		}
		current_instruction=instruction_number+1;
	}
}
void cwd_from_child(){
	char sharing_data[BSIZE]={0};
	read(sharing_pipe[0], sharing_data, BSIZE);
	strcpy(current_directory,sharing_data);
	chdir(current_directory);
	read(sharing_pipe[0], sharing_data, BSIZE);
	strcpy(previous_directory,sharing_data);
}
void history_from_child(){
	char sharing_data[BSIZE]={0};
	read(sharing_pipe[0], sharing_data, BSIZE);
	strcpy(history_zero,sharing_data);
}
void pid_from_child(){
	while(1){
		char pid_data[50]={0};
		read(pid_pipe[0],pid_data,30);
		if(pid_data[0]==0){
			break;
		}
		else{
			int check=0;
			for(int i=1;i<pid_tail;i++){
				if(strcmp(pid_list[i],pid_data)==0){
					memset(pid_list[i],0,30);
					check=1;
				}
			}
			if(check==0){
				strcpy(pid_list[pid_tail++],pid_data);
			}
			for(int i=1;i<pid_tail;i++){
				if(pid_list[i][0]!=0){
					check=2;
				}
			}
			if(check!=2){
				pid_tail=1;
			}
		}
	}
}