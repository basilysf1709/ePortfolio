package ePortfolio;

/** Represents the Stock class.
 * @author Basil Yusuf
 * @version 1.1
 * @since 1.0
*/

public class Stock extends Investment {

    /**
     * declaration stock
     */
    public Stock() {
        // declaration here
    }

    /** 
     * @param copy initializing stock
     * @throws Exception if price is negative
     */
    public Stock(Stock copy) throws Exception {
        if(price < 0){
            this.type = copy.type;
            this.symbol = copy.symbol;
            this.name = copy.name;
            this.price = copy.price;
            this.quantity = copy.quantity;
            this.bookValue = copy.bookValue;
        }else{
            throw new Exception("Price can not be negative");
        }
    }

    
    /** 
     * @param type initializing type
     * @param symbol initializing symbol
     * @param name initializing name
     * @param bookValue initializing book value
     * @param price initializing price
     * @param quantity initializing quantity
     * @throws Exception if price is negative
     */
    public Stock(String type, String symbol, String name, double bookValue, double price, int quantity)throws Exception{
        if(price < 0){
            this.type = type;
            this.symbol = symbol;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.bookValue = bookValue;
        }else{
            throw new Exception("Price can not be negative");
        }
    }
    
    @Override
    /** 
     * @return String
     */
    public String toString()
    {
        return "\nType: " + this.type + " \nName: " + this.name + "\nSymbol: " + this.symbol + "\nPrice: " + this.price + "\nQuantity: " + this.quantity + " units\nBook Value: " + this.bookValue + "\n";
    }

    @Override
    public String toStringScreen()
    {
        return "<html><br>Type: " + this.type + " <br>Name: " + this.name + "<br>Symbol: " + this.symbol + "<br>Price: " + this.price + "<br>Quantity: " + this.quantity + " units<br>Book Value: " + this.bookValue + "<br></html>";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Investment) {
            return super.equals(obj) && symbol.equals(((Investment) obj).getSymbol());
        }
        return false;
    }
}