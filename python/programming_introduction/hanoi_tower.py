def tower(n,fro,to,tmp):
    global counter
    if n > 1:
        tower(n-1,fro,tmp,to)
        # print("Move from",fro,"to",to)
        counter += 1
        tower(n-1,tmp,to,fro)
    else:
        # print("Move from",fro,"to",to)
        counter +=1

counter = 0
tower(1,"A","B","C")
print("The number of moves:",counter)
