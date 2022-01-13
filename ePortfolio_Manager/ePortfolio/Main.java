package ePortfolio;

/** Represents the Main class.
 * @author Basil Yusuf
 * @version 1.1
 * @since 1.0
*/

public class Main{
    /** 
     * @param args main function
     */
    public static void main(String []args)
    {
        String fileName;
        
        if(args.length == 0){
            System.out.println("No Command line file. Taking file: ePortfolio.txt");
            fileName = "ePortfolio.txt";
        }else{
            fileName = args[0];
        }
        Portfolio p = new Portfolio(fileName);
        p.readFromFile(fileName);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
}