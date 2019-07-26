def fib(n):
    if n > 1:
        return fib(n-1) + fib(n-2)
    else:
        return n

def fib2(n):
    k = 1
    old, new = 0, 1
    while k < n:
        k+=1
        old, new = new, old + new
    return new

def fib3(n):
    old, new = 0, 1
    for _ in range(1,n):
        old, new = new, old + new
    return new

def fibseq(n):
    fibs = [0,1]
    for i in range(2,n+1):
        fibs.append(fibs[i-1]+fibs[i-2])
    return fibs
