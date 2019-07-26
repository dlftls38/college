CC = gcc
OBJS = server.o
TARGET = server
 
.SUFFIXES : .c .o
 
all : $(TARGET)
 
$(TARGET) : $(OBJS)
	  $(CC) -o $@ $(OBJS)
 
clean:
	rm -f $(OBJS) $(TARGET)
 
server.o : server.c