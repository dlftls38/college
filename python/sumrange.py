def sumrange(m,n):
    if m<n:
        return n+sumrange(m,n-1)
    elif m==n:
        return m
    else:
        return 0
    return sumrange(m,n)

def sumrange2(m,n):
    sum=0
    while m<=n:
        sum = n + sum
        n = n-1
    return sum

#교수님이 한 방법
def sumrange3(m,n):
    if m <= n:
        return m + sumrange(m+1,n)
    else:
        return 0

def sumrange4(m,n):
    sum = 0
    while m <= n:
        sum = sum + m
        m = m + 1
    return sum
    
        
            
        
