def mult(m,n):
    ans = 0
    while n> 0:
        n=n-1
        ans +=m
    return ans

def fastmult(m,n):
    if n>0:
        if n%2==0:
            return fastmult(m+m,n//2)
        else:
            return m + fastmult(m,n-1)
    else:
        return 0

def fastmult2(m,n):
    ans = 0
    while n>0:
        if n%2==0:
            m,n = m+m,n//2
        else:
            n=n-1
            ans+=m
    return ans

def russian_mult(m,n):
    if n>=1:
        if n%2 ==0:
            return russian_mult(m+m,n//2)
        else:
            return m+russian_mult(m+m,n//2)
    else:
        return 0

def russian_mult2(m,n):
    ans = 0
    while n>=1:
        if n%2 == 0:
            m+=m
            n=n//2
        else:
            ans+=m
            m+=m
            n=n//2
    return ans
    
        
