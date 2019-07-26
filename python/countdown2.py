import time

def countdown2(n):
    if n>0:
        if n<10 and n%2==0:
            print("발사임박!")
            time.sleep(1)
            countdown2(n-1)
        else:
            print(n)
            time.sleep(1)
            countdown2(n-1)
    else:
        print("발사!")
        
