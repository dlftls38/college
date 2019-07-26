def gugudan1():
    for i in range(2,10):
        for j in range(1,10):
            if j==3 or j==6 or j==9:
                print(i, "x", j,"=", str(i*j).rjust(2))
            else:
                print(i, "x", j,"=", str(i*j).rjust(2), end = "  ")
        print()

def gugudan2():
    for i in range(0,2):
        for j in range(1,10):
            for k in range(2+4*i,6+4*i):
                if k==5 or k==9:
                    print(k, "x", j,"=", str(k*j).rjust(2))
                else:
                    print(k, "x", j,"=", str(k*j).rjust(2), end = "  ")
        print()
                
         
            
