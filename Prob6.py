def diff(n):
    sumSq = 0
    #calculate the sum of the squares
    for i in range(1, n+1):
        sumSq += i**2
    print ("Sum of Squares up to ", n, ": ", sumSq)

    #calculate the summation
    summation = n*(n+1)/2
    print ("Summation of ", n, ", squared: ", summation**2)

    diff = summation**2-sumSq
    print ("Difference between the two: ", diff)

if __name__ == "__main__":
    diff(100)
