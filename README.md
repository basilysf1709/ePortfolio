# ePortfolio GUI Application

**Name:** Basil Yusuf

**Course:** Object Oriented Programming (CIS*2430)

**Date:**  30th Novemeber, 2021

**Version:** 1.2 (Updated from 1.1)

## Description of the Problem

An investor needs to manage a portfolio of different investments ( which are: Mutual Funds, or Stocks) so that the person can keep track of the actions for buying, or selling investments, searching through the portfolio for given investments, updating prices, and calculating the total gain of the Portfolio. This application provides a simple and user-friendly Graphical User Interface and keeps track of two types of investmnets - mutual funds and stocks.

## Assumptions and Limitations

This application only keeps track of two types of investments. For example, this program does not cover Cryptocurrency ePortfolio.

It is limited by the range of double

It has an assumption that the investor is able to have a machine that can run java 11

## User Guide

The application is inside the folder of the package name `ePortfolio` which can be found in the same folder of this file

**How to Run the Code** 
*Note: To run and test this programme effectively, you must have Java 11 installed on your computer*.
- Open a terminal or a command prompt on your computer.
- Go to the same folder as this README file's path.
- To compile the application and generate the '.class' files, type the command precisely as it is given here: 'javac ePortfolio/*.java'.
- Next, type 'java ePortfolio.Main' or 'java ePortfolio/Main Filename' on the command prompt. The latter will read data from a file that has been properly formatted. 'Filename' is the name of the file to be loaded, together with its format specifier, such as 'ePorfolio.txt', otherwise print an error message and proceed.

After sucessfully running the code, the user will be presented with a home screen which says:

___Choose a command from the “Commands” menu to buy or sell 
an investment, update prices for all investments, get gain for the 
portfolio, search for relevant investments, or quit the program___

This application has been designed to be as user friendly as possible.

## Continuation of User guide: 

1. The user can now go to the top left of the screen and press "commands"
2. The user will be presented with six options:
    1. *buy*
    2. *sell*
    3. *update*
    4. *getGain*
    5. *search*
    6. *quit*

1. *buy* - own a new investment or add more quantity to an existing investment.
    *prompts*
    1. Dropdown menu to select Type(Stock, or Mutual Fund)
    2. TextField to type other inputs
    3. Two buttons are provided to reset, or buy the investment
    *buttons*
        1. buy: buys the investment
        2. reset: clears everything in the text field

2. *sell* - reduce some or all of the existing investment. 
    *prompts*
    1. TextField to type every input
    2. Two buttons are provided to reset, or buy the investment
    *buttons*
        1. sell: sells the investment
        2. reset: clears everything in the text field

3. *update* - refresh the prices of all existing investments. This will print the available investment and prompt the user to enter the updated prices.
    *prompts*
    1. TextField to type price as input
    2. Non-editable TextField for looking at all investments present
    3. Three buttons are provided to go next, go back, or to save the new price of the investment.
    *buttons*
        1. next: Goes to the next investment
        2. prev: Goes to the previous investment
        3. save: Saves the new price of the investment

4. *getGain* - this computes the total gain of the portfolio by accumulation the gains of all the individual investments. It calculates the total if the user were to sell every investment he has at this point.
    *Screen*
    1. Non-editabel TextField respresenting the total gain
    2. Shows all the individual gains for all the investments.

5. *search* -  find all the investments based on the user's selection (symbol, price range and keywords) and display them. 
    *prompts*
    1. TextField to type price as input
    2. Two buttons are provided to reset, or search the investment
    *buttons*
        1. search: searches the investment
        2. reset: clears everything in the text field

6. *quit*- This will quit the application.
    -> Closes the application


## Test Plan: 

The application provided here is robust. To test its functionality, the following test cases can be used.

1. Check if the application can handle bad inputs with a **defensive programming** test. Enter a value that isn't expected by the prompt.
  Enter text values where ints are anticipated, or texts where doubles are expected in this test.
  As a result, the application should re-prompt for input until it receives the correct data.

  Enter a negative figure for price or quantity (for example, -20).
  As a result, the programme will display an error message and remind you again until you enter the proper information.

  Enter a 'Filename' that does not exist as a test.
  As a result, the application will produce an error message and proceed. It will save the output to a file called 'ePortfolio.txt'.

2. **Buy test** - ensuring that the buy functionality works properly.
Test: Performing a trial run - select buy into the command drop down menu.

- For `Stocks,` choose stocks from dropdown combobox.
- For the `symbol` attribute, type TSL.
- For the `name` attribute, type Tesla Inc.
- For the `quantity` attribute, type 500.
- For the `price` attribute, type 110.08.

Result:
- The 'bookValue' should be displayed as 55049.99 by the application.

Next,
- In the command loop, type buy.
- For `Stocks,` choose stock from the dropdown menu.
- For the `symbol` attribute, type TSL.

Result:
- The software should not change about the user's name. Instead, it should just request the price and quantity, and then publish the book value.

Mutual fund functionality could be subjected to similar tests.

3. **Test for Sell** - this is a check to see if the sell capability is working properly.
  Test: Performing a test run - In the commands dropdown menu, select `sell` - Select Stock for `Stocks` - Enter TSL for the `symbol` attribute - Enter 200 for the `quantity` attribute - Enter 142.23 for the `price` attribute
  As a result, the following should be displayed by the programme:
  New quantity has been changed to 300.

  Then, in the commands menu, select `sell`, enter APPL for the `symbol` attribute, 300 for the `quantity` attribute, and 142.23 for the `price` attribute.
  As a result, the following should be displayed by the programme:
  The total amount due is 71105.01.
  The profit is 16055.02 dollars.
  The current book value is 0.0.
  The quantity has been updated to 0

  The investment should also be removed from the list by the software.

4. **Test for Update**: Verify that the update function works properly.
  Test: Performing a test run - Create a few investments as shown above - In the drop down menu, select `update`.
  As a result, the user should be prompted to enter new pricing for each investment and update them for the items.
- You may double-check this by running update again and looking at the printed contents.

5. **Test for get gain**—verify that the Get gain feature works properly.
  Test: Conducting a trial run - Create a few investments as shown above -  In the commands dropdown menu, select `getGain`.
  Result: If the `ePortofolio.txt` file is loaded, the programme should output the total gain from all the investments if we sold them all at once.

6. **search**: Now, for search there can be 8 different cases.
    i)   S, K, P
    ii)  S, 0, 0
    iii) 0, K, 0
    iv)  0, 0, P
    v)   S, K, 0
    vi)  0, K, P
    vii) S, 0, P
    viii)0, 0, 0

    -> Here, S => Symbol, K => Keywords, P => Prices, 0 => empty. Input can be empty by pressing enter. Test all the possible cases in a big list.

    **explanation example**:- see if the search function works properly.
        Test: Performing a symbol search (assuming data from 'ePortfolio.txt' has been loaded)
        - Make a few investments, as shown above.
        - In the commands dropdown menu, select `search`.
**Continuation of search examples**

Test: **price range**
    Low Price: 10
    High Price: (no input)
Output: 
-   type = "stock"
    symbol = "AAPL"
    name = "Apple Inc."
    quantity = "950"
    price = "5000.0"
    bookValue = "147306.981"

    type = "mutualfund"
    symbol = "SSETX"
    name = "BNY Mellon Growth Fund Class I"
    quantity = "450"
    price = "12.0"
    bookValue = "24042.89"

    type = "mutualfund"
    symbol = "SNY"
    name = "Sony"
    quantity = "10"
    price = "20.0"
    bookValue = "15.0"

    type = "stock"
    symbol = "APLC"
    name = "John's Apple Corp"
    quantity = "153"
    price = "900.0"
    bookValue = "155.98"

    type = "mutualfund"
    symbol = "DLDO"
    name = "Dinosaurs R Us"
    quantity = "202"
    price = "4224.91"
    bookValue = "203.89"

    type = "mutualfund"
    symbol = "HRSL"
    name = "Amy's Hair Salon"
    quantity = "1112"
    price = "722.64"
    bookValue = "46.46"

    type = "stock"
    symbol = "AGR"
    name = "apple growth"
    quantity = "1112"
    price = "722.64"
    bookValue = "46.46"

    type = "mutualfund"
    symbol = "GRA"
    name = "growth of apple"
    quantity = "1112"
    price = "722.64"
    bookValue = "46.46"
Test: **symbol**
    Symbol: AAPL
Output:
-   type = "stock"
    symbol = "AAPL"
    name = "Apple Inc."
    quantity = "950"
    price = "5000.0"
    bookValue = "147306.981"
Test: **keywords with HashMap reduced time**
    keywords: apple
-   type = "stock"
    symbol = "AAPL"
    name = "Apple Inc."
    quantity = "950"
    price = "5000.0"
    bookValue = "147306.981"

    type = "stock"
    symbol = "APLC"
    name = "John's Apple Corp"
    quantity = "153"
    price = "900.0"
    bookValue = "155.98"

    type = "stock"
    symbol = "AGR"
    name = "apple growth"
    quantity = "1112"
    price = "722.64"
    bookValue = "46.46"

    type = "mutualfund"
    symbol = "GRA"
    name = "growth of apple"
    quantity = "1112"
    price = "722.64"
    bookValue = "46.46"

-**Above examples are a few test cases.**

7. **Test for quit**
A test run is a process of putting something to the test.
- In the commands Drop down menu, select `quit`, `Q`, or `Quit`.
Result:
- This should bring the programme to a halt.

8. **File I/O Test** - Verify that data is loaded from and output to a file.

Test:
- Check the contents of the file before and after executing the application. Any search, update, getgain, and sell operations should include entries from the file while the programme is executing.

### Enhancements over the previous version

Because of inheritance, the code for this application is less redundant. Furthermore, thanks to the usage of a HashMap data structure, the search is noticeably faster. Built a GUI. The GUI is robust and user-friendly with proper error handling.

### Future Enhancements

The code is modular in nature. It has the ability, however, to be built up from even smaller components in a true object-oriented way utilising the more powerful tools provided by the Java language. As a result, in future generations of this code, little restructing would make it more portable.

The search method is capable of processing smaller inputs from an ordinary person, but if the inputs are too large, it may prove inefficient and time consuming. As a result, this algorithm can be replaced by a more efficient algorithm in future versions.
