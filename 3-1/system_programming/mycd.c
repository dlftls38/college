#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h> 
#include <stdlib.h>
#include <time.h>
#include <pwd.h>
#include <grp.h>
void permission(mode_t);
void directory_information();

int main(){
	char current_path[2019]={0};
	char recent_path[2019]={0};
	char spare[2019]={0};
	getcwd(current_path,2019);
	printf("%s\n",current_path);
	directory_information();


	while(1){
		char input[2019]={0};
		int i=0;
		while(1){
			input[i]=getchar();
			if(input[i++] == '\n'){
				input[i-1] = 0;
				break;
			}
		}

		if(input[0]=='c' && input[1]=='d'){
			if(strcmp(input,"cd")==0 || strcmp(input,"cd ~")==0 || strcmp(input+3,"$HOME")==0){
				int count=0;
				getcwd(recent_path,2019);
				for(i=0;i<strlen(current_path);i++){
					if(current_path[i]=='/'){
						count++;
					}
				}
				for(i=0;i<count-2;i++){
					chdir("..");
				}
				getcwd(current_path,2019);
				printf("%s\n",current_path);
				directory_information();
			}
			else if(strcmp(input,"cd -")==0){
				if(recent_path[0]==0){
					printf("Error : bash: cd: OLDPWD not set\n");
				}
				else{
					chdir(recent_path);
					strcpy(recent_path,current_path);
					getcwd(current_path,2019);
					printf("%s\n",current_path);
					directory_information();
				}
			}
			else if(input[2] != ' '){
				printf("Error : Wrong instruction\n");
			}
			else{
				char path[2019]={0};
				strcat(path,input+3);
				strcpy(spare,recent_path);
				getcwd(recent_path,2019);
				if(chdir(path)>=0){
					getcwd(current_path,2019);
					printf("%s\n",current_path);
					directory_information();
				}
				else{
					strcpy(recent_path,spare);
					printf("Error : No such file or directory\n");
				}
			}
		}
		else if(strcmp(input,"ls")==0){
			directory_information();
		}
		else{
			printf("Error : Wrong instruction\n");
		}
	}
}

void permission(mode_t file_mode){

	if(S_ISDIR(file_mode)) printf("d");
	else printf("-");

	if(file_mode & S_IRUSR) printf("r");
	else printf("-");

	if(file_mode & S_IWUSR) printf("w");
	else printf("-");
		
	if(file_mode & S_IXUSR)	printf("x");
	else printf("-"); 
		
	if(file_mode & S_IRGRP) printf("r");
	else printf("-");
	
	if(file_mode & S_IWGRP) printf("w");
	else printf("-");
		
	if(file_mode & S_IXGRP) printf("x");
	else printf("-"); 
		
	if(file_mode & S_IROTH) printf("r");
	else printf("-");
		
	if(file_mode & S_IWOTH) printf("w");
	else printf("-");
		
	if(file_mode & S_IXOTH) printf("x");
	else printf("-");

	printf(" ");
}

void directory_information(){
	DIR *directory_pointer; 
	struct dirent *directory_entry; 
	int get_stat;
    struct stat file_information;
    char *file_name;
    struct passwd *passwd;
    struct group  *group;
	struct tm *time_information;
	mode_t file_mode;
	char buf[80];

	if((directory_pointer = opendir(".")) == NULL) 
	exit(1); 

	while( directory_entry = readdir(directory_pointer)) {
	
	if ((get_stat = stat(directory_entry->d_name, &file_information)) == -1)
    {
        perror("Error : ");
        exit(0);
    }
    	if(directory_entry->d_ino != 0) 
		printf("%s ", directory_entry->d_name);	

		file_mode = file_information.st_mode;
		permission(file_mode);
		printf("%ldbytes ", file_information.st_size);

		printf("%ld ", file_information.st_nlink);

		passwd = getpwuid(file_information.st_uid);
	    group  = getgrgid(file_information.st_gid);
		printf("%s ", passwd->pw_name);
		printf("%s ", group->gr_name);

		time_information = localtime (&(file_information.st_atime));
		strftime(buf, 100, "%m/%d %H:%M", time_information); 
		printf("%s\n", buf);

	} 

	closedir(directory_pointer); 
}