package ePortfolio;

/** Represents the Stock class.
 * @author Basil Yusuf
 * @version 1.1
 * @since 1.0
*/

public class Stock extends Investment {
    
    /** 
     * @param type initializing type
     * @param symbol initializing symbol
     * @param name initializing name
     * @param bookValue initializing book value
     * @param price initializing price
     * @param quantity initializing quantity
     */
    public void Stock(String type, String symbol, String name, double bookValue, double price, int quantity){
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bookValue = bookValue;
    }
    
    
    /** 
     * @return String
     */
    public String toString()
    {
        return "\nType: " + this.type + " \nName: " + this.name + "\nSymbol: " + this.symbol + "\nPrice: " + this.price + "\nQuantity: " + this.quantity + " units\nBook Value: " + this.bookValue + "\n";
    }
    
    /** 
     * @param s equals method
     * @return boolean true/false
     */
    public boolean equals(Stock s) {
        return (s.getSymbol().equalsIgnoreCase(this.symbol) && s.getName().equalsIgnoreCase(this.name) && s.getQuantity() == quantity
                && s.getPrice() == this.price && s.getBookValue() == this.bookValue);
    }
}