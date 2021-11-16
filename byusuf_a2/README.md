---------------------Name: Basil Iqbal Yusuf------------------------
---------------------Class: Object Oriented Programming-------------
---------------------Course: 2430-----------------------------------
---------------------Student ID: 1158677----------------------------
---------------------Email: byusuf@uoguelph.ca----------------------

General goal:
Create and manage an ePortfolio

Test Plan: 
1. Try all the command line outputs(1-9)
2. Take the value of the type of investment to buy.(1-2)
3. Take all the relevant values for the type of investment given to buy. (Symbol, Name, Price, Quantity)
4. If the user enters an investment that is already in the investment, take the value of new price and quantity.
5. Buy one more investment in Stocks, and in Funds. This is just to make sure everything is functioning properly in this list.
6. Sell all share/units and see if it is updating gain and book value.
7. Sell partially and see if it is updating book value.
8. Test getGain by updating the remaining 3 investment prices to (0, same price, 
   larger number than current price of investment). Use calculator to check if the displayed prices are correct.
9. Now, for search there can be 8 different cases.
    i)   S, K, P
    ii)  S, 0, 0
    iii) 0, K, 0
    iv)  0, 0, P
    v)   S, K, 0
    vi)  0, K, P
    vii) S, 0, P
    viii)0, 0, 0
10. Here, S => Symbol, K => Keywords, P => Prices, 0 => empty. Input can be empty by pressing enter. Test all the possible cases in a big list.
11. Provided a text file that can be used for loading and reading. Or else a file is already provided that can be used for testing. 
12. Working of hash maps. After every buy, sell, and loading of files command hash map is updated for keywords. The hash map reduces the range
    for searching which will generally help in reducing search times in large programs.
13. To test hash maps, just load the existing investments from the file ePortfolio.txt and see the hash key and values generated for
    every keyword. This is later used in search to get a better performance by giving it a range.

EDGE CASES:
-Enter negative values for the price and quantity with the buy/sell function
-Use SEARCH to search for the all types of investments for both lists using the variations states at #5 (this should generally mean that your search method is working well)
-try all the combinations for price ranges for the search function, such as x-y, x, x-, and -x where x and y  can be any real number

THINGS TO IMPROVE:
-If I had more time, I would try to make my program more readable. 
-I will plan more and code less. This is a general rule I'd try to adapt in my next assignment.
-Otherwise, I think I did everything well in the given assignment. 

Compilation and Execution:
->Make sure you have ePortfolio folder in your current directory
->Then, write this command: javac ePortfolio/*.java
->Then, for executing: java java ePortfolio.Main ePortfolio.txt  

User Guide:
1. There is a command loop which takes an input for maintaining ePortfolio
2. There is user-friendly prompts as one goes deeper.
3. Follow the prompts to maintain your stocks and funds properly.
4. Conclusion: Follow the prompts and make your choices as you select commands.