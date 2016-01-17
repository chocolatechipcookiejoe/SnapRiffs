def main():
    sum = 0
    for i in range(1, 1000):
        if i%3 == 0:
            sum += i
        if i%5 == 0 and i%15 != 0:
            sum += i
        
    print(sum)

if __name__ == "__main__":
    main();
