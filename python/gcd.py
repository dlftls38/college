def gcd(m,n):
    while n != 0:
        m,n = n,m%n
    return m


def gcd2(m,n):
    if n != 0:
        return gcd2(n,m%n)
    else:
        return m

def gcd3(m,n):
    if n == 0:
        return m
    elif m == 0:
        return n
    elif m%2==0 and n%2==0:
        return 2*gcd3(m//2,n//2)
    elif m%2==0 and n%2==1:
        return gcd3(m//2,n)
    elif m%2==1 and n%2==0:
        return gcd3(m,n//2)
    elif m%2==1 and n%2==1 and m<=n:
        return gcd3(m,(n-m)//2)
    elif m%2==1 and n%2==1 and m>n:
        return gcd3(n,(m-n)//2)

def gcd4(m,n):
    k=1
    while not(m==0 or n==0):
        if m%2==0 and n%2==0:
            m,n=m//2,n//2
            k=k*2
        elif m%2==0 and n%2==1:
            m=m//2
        elif m%2==1 and n%2==0:
            n=n//2
        elif m%2==1 and n%2==1 and m<=n:
            n=(n-m)//2
        elif m%2==1 and n%2==1 and m>n:
            m,n=n,(m-n)//2
    if m==0:
        return n*k
    else:
        return m*k
