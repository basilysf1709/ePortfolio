package ePortfolio;

/** Represents the Investment class.
 * @author Basil Yusuf
 * @version 1.1
 * @since 1.0
*/

public class Investment {
    /**
    * This variable is used for type.
    */
    protected String type;
    /**
    * This variable is used for symbol.
    */
    protected String symbol;
    /**
    * This variable is used for name.
    */
    protected String name;
    /**
    * This variable is used for book value.
    */
    protected double bookValue;
    /**
    * This variable is used for price.
    */
    protected double price;
    /**
    * This variable is used for quantity.
    */
    protected int quantity;

    
    /** 
     * @param type initializing type
     * @param symbol initializing symbol
     * @param name initializing name
     * @param bookValue initializing book value
     * @param price initializing price
     * @param quantity initializing quantity
     */
    public void Investment(String type, String symbol, String name, double bookValue, double price, int quantity) {
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bookValue = bookValue;
    }

    
    /** 
     * @param type setter for type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /** 
     * @return String getter for type
     */
    public String getType() {
        return this.type;
    }
    
    /** 
     * @param symbol setter for symbol
     */
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    
    /** 
     * @return String getter for symbol
     */
    public String getSymbol(){
        return this.symbol;
    }
    
    /** 
     * @param name setter for name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /** 
     * @return String getter for name
     */
    public String getName(){
        return this.name;
    }
    
    /** 
     * @param price setter for price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /** 
     * @return double getter for price
     */
    public double getPrice() {
        return this.price;
    }
    
    /** 
     * @param quantity setter for quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /** 
     * @return int getter for quantity
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /** 
     * @param bookValue setter for book value
     */
    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }
    
    /** 
     * @return double getter for book value
     */
    public double getBookValue() {
        return this.bookValue;
    }
    
    /** 
     * @return String prints to file 
     */
    public String toStringFile(){
        return "type = \"" + this.type + "\"\nsymbol = \"" + this.symbol + "\"\nname = \"" +  this.name + "\"\nquantity = \"" + this.quantity + "\"\nprice = \"" + this.price  + "\"\nbookValue = \"" + this.bookValue + "\"\n";
    }
}

