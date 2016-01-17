def isPalindrome (n):
    i = 2
    while 10**i < n:    #Find the number of digits in the number n, store in i
        i += 1
    i -= 1    #Edit i to reflect actual digits
    j = 0
    while i >= j:
        if int(n%10**(i+1)/(10**i)) != int(n%10**(j+1)/(10**j)):  #Figure out whether the digit at i is the same as
            #debug print ("i:", i, "\ni digit", int(n%10**(i+1)/(10**i)))
            #debug print ("j:", j, "\nj digit", int(n%10**(j+1)/(10**j)))
            return False        #the digit at j, if not, return false
        i -= 1
        j += 1
        
    return True

def main(n):
    if isPalindrome(n):
        print(n, " is a palindrome.")
    else:
        print(n, " is not a palindrome.")

if __name__ == "__main__":
    done = False
    number = 0
    for i in reversed(range(101, 1000)):
        for j in reversed(range(101, 1000)):
            if isPalindrome(i*j):
                if number < i*j:
                    number = i*j
    print (number)
