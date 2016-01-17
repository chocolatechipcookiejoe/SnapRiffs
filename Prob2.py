def main():
    fib1 = 1
    fib2 = 2
    fibsum = 2

    while fib2 <= 4000000:
        tmp = fib1
        fib1 = fib2
        fib2 = fib1 + tmp
        if fib2%2 == 0:
            fibsum += fib2
    print(fibsum)

if __name__=="__main__":
    main()
