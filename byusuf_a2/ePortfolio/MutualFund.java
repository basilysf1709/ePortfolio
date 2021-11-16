package ePortfolio;

/** Represents the MutualFund class.
 * @author Basil Yusuf
 * @version 1.1
 * @since 1.0
*/

public class MutualFund extends Investment {
    
    /** 
     * @param type initializing type
     * @param symbol initializing symbol
     * @param name initializing name
     * @param bookValue initializing book value
     * @param price initializing price
     * @param quantity initializing quantity
     */
    public void MutualFund(String type, String symbol, String name, double bookValue, double price, int quantity){
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bookValue = bookValue;
    }

    
    /** 
     * @return String to string function
     */
    public String toString()
    {
        return "\nType: " + this.type + "\nName: " + this.name + "\nSymbol: " + this.symbol + "\nPrice: " + this.price + "\nQuantity: " + this.quantity + " shares\nBook Value: " + this.bookValue + "\n";
    }
    
    /** 
     * @param mf object of mutual fund
     * @return boolean true/false
     */
    public boolean equals(MutualFund mf) {
        return (mf.getSymbol().equalsIgnoreCase(symbol) && mf.getName().equalsIgnoreCase(name) && mf.getQuantity() == quantity
                && mf.getPrice() == price && mf.getBookValue() == bookValue);
    }
}

