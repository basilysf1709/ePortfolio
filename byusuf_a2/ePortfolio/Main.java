package ePortfolio;

import java.util.*;

/** Represents the Main class.
 * @author Basil Yusuf
 * @version 1.1
 * @since 1.0
*/

public class Main{

    
    /** 
     * @param scan
     * @return String returns string
     */
    static String inputLoops(Scanner scan)
    {
        String s;
        System.out.println("Write a command: (1 to 9)(ex: 1, buy, Buy, BUY for buy):");
        System.out.println("1. buy");
        System.out.println("2. sell");            
        System.out.println("3. update");
        System.out.println("4. getGain");
        System.out.println("5. search");
        System.out.println("6. print");
        System.out.println("7. load to text file");
        System.out.println("8. read from text file");
        System.out.println("9. quit");
        s = scan.nextLine();
        
        return s;
    }

    
    /** 
     * @param s string
     * @return int for checking
     */
    static int checks(String s)
    {
        if(s.equalsIgnoreCase("buy")  || s.equals("1"))
        {
            return 1;
        }
        if(s.equalsIgnoreCase("sell")  || s.equals("2"))
        {
            return 2;
        }
        if(s.equalsIgnoreCase("update")  || s.equals("3"))
        {
            return 3;
        }
        if(s.equalsIgnoreCase("getgain")  || s.equals("4"))
        {
            return 4;
        }
        if(s.equalsIgnoreCase("search")  || s.equals("5"))
        {
            return 5;
        }
        if(s.equalsIgnoreCase("print")  || s.equals("6"))
        {
            return 6;
        }
        if(s.equalsIgnoreCase("load") || s.equals("7")){
            return 7;
        }
        if(s.equalsIgnoreCase("read") || s.equals("8")){
            return 8;
        }
        if(s.equalsIgnoreCase("quit")  || s.equals("9"))
        {
            return 9;
        }
        return 0;
    }
    
    /** 
     * @param args main function
     */
    public static void main(String []args)
    {
        String fileName;
        int flag = 0;
        Scanner scan = new Scanner(System.in);
        Portfolio p = new Portfolio();
        if(args.length == 0){
            System.out.println("No Command line file. Taking file: ePortfolio.txt");
            fileName = "ePortfolio.txt";
        }else{
            fileName = args[0];
        }

        while(flag != 1)
        {
            String s = inputLoops(scan);
            if(checks(s) == 1)
            {
                p.buy();
                p.removeKey();
                p.changeKey();
                p.stateHash();
            }
            else if(checks(s) == 2)
            {
                p.sell();
                p.removeKey();
                p.changeKey();
                p.stateHash();
            }
            else if(checks(s) == 3)
            {
                p.update();
            }
            else if(checks(s) == 4)
            {
                System.out.println("The total gain for all the investments are: " + p.getGain());
            }
            else if(checks(s) == 5)
            {
                p.search();
            }
            else if(checks(s) == 6)
            {
                p.print();
                p.stateHash();
            }
            else if(checks(s) == 7)
            {
                p.printToFile(fileName);
                p.stateHash();
            }
            else if(checks(s) == 8)
            {
                p.readFromFile(fileName);
                p.removeKey();
                p.changeKey();
                p.stateHash();
            }
            else if(checks(s) == 9)
            {
                flag = 1;
            }
            else
            {
                System.out.println("Invalid input try again");
            }
        }
    }
}