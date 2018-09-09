def fibonacci(n):
    i = 0
    j = 1
    print(j)
    fbnc(i, j, n)


def fbnc(i, j, n):
    k = i + j
    if k < n:
        print(k)
        fbnc(j, k, n)


fibonacci(100)
