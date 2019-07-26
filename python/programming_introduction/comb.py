def comb(n,r):
    if r != 0 and r != n:
        return comb(n-1,r-1) + comb(n-1,r)
    else:
        return 1

def pascal(n,r):
    table = [[]]*(n-r+1)
    table[0] = [1]*(r+1)
    for i in range(1,n-r+1):
        table[i] = [1]
    for i in range(1,n-r+1):
        for j in range(1,r+1):
            newvalue = table[i][j-1] + table[i-1][j]
            table[i].append(newvalue)
    return table[n-4][r]
