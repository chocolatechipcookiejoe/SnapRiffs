def largestPrime(number):
    testfactor = 2
    while True:
        if number % testfactor == 0:
            print(number/testfactor)
            if not isPrime(number/testfactor):
                return largestPrime(number/testfactor)
            else:
                return number/testfactor
            break
        testfactor += 1
def isPrime(n):
    for i in range(2, int(n**0.5+1)):
        if n%i == 0:
            return False
    return True

if __name__ == "__main__":
    largestPrime(600851475143)
