#include <stdio.h>
#include <signal.h> 
#include <stdlib.h>
#include <unistd.h>

void catchint (int signo) 
{ 
	printf ("\nCATCHINT: signo=%d\n", signo); 
	printf("CATCHINT: returning\n\n"); 
}
void catchint2 (int signo) 
{ 
	printf ("\nwowowowowowow%d\n", signo); 
	printf("nwowowowowowow\n\n"); 
} 

int main() 
{ 
	static struct sigaction act,act2; 

	act.sa_handler = catchint; 
	act2.sa_handler = catchint2; 
	sigfillset(&(act.sa_mask)); 
	sigaction(SIGINT, &act, NULL); 
	sigaction(SIGINT, &act2, NULL); 
	
	printf ("sleep call #1\n"); 
	sleep (1); 
	printf ("sleep call #2\n"); 
	sleep (1); 
	printf ("sleep call #3\n"); 
	sleep (1); 
	printf ("sleep call #4\n"); 
	sleep (1); 
	printf ("Exiting\n"); 
	exit (0); 
} 

