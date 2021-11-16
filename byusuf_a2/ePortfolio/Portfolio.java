package ePortfolio;
import java.util.*;
import java.io.*;

/** Represents the Portfolio class.
 * @author Basil Yusuf
 * @version 1.1
 * @since 1.0
*/
public class Portfolio {
    ArrayList<Investment> investList = new ArrayList<Investment>();
    HashMap<String, ArrayList<Integer>> matches = new HashMap<String, ArrayList<Integer>>();

    
    /** 
     * @param s for adding stock
     */
    public void addStock(Stock s){
        this.investList.add(s);
    }
    
    /** 
     * @param f for adding fund
     */
    public void addFund(MutualFund f){
        this.investList.add(f);
    }
    
    /** 
     * @param index the index of stock to be removed
     */
    public void removeStock(int index){
        this.investList.remove(index); 
    }
    
    /** 
     * @param index the index of fund to be removed
     */
    public void removeFund(int index){
        this.investList.remove(index);
    }
    
    /** 
     * @param s string to be searched
     * @return int index
     */
    int findStock(String s)
    {
        int i = 0;
        if(this.investList.isEmpty())
            return -1;
        else{
            for(Investment invest: this.investList)
            {
                if(s.equalsIgnoreCase(invest.getSymbol()) && this.investList.get(i).getType().equalsIgnoreCase("stock"))
                {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    
    /** 
     * @param s string to be searched
     * @return int index
     */
    int findFund(String s)
    {
        int i = 0;
        if(this.investList.isEmpty())
            return -1;
        else{
            for(Investment invest: this.investList)
            {
                if(s.equalsIgnoreCase(invest.getSymbol()) && this.investList.get(i).getType().equalsIgnoreCase("mutualfund"))
                {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }
    /**
     * updates the key for hash maps
     */
    public void changeKey(){
        ArrayList<Integer> h = new ArrayList<Integer>();
        String str[], name;
        int flag = 0;
        for(int i = 0; i < this.investList.size(); i++){
            name = this.investList.get(i).getName();
            str = name.split("[ ]+");
            for(int j = 0; j < str.length; j++){
                flag = 0;
                if(this.matches.isEmpty()){
                    h = new ArrayList<Integer>();
                    h.add(i);
                    this.matches.put(str[j],h);
                }else{
                    for(String st : this.matches.keySet()){
                        if(st.equalsIgnoreCase(str[j])){
                            if (!this.matches.get(st).contains(i)) 
                                this.matches.get(st).add(i);
                            flag = 1;
                        }
                    }

                    if(flag != 1)
                    {
                        h = new ArrayList<Integer>();
                        h.add(i);
                        this.matches.put(str[j],h);
                    }
                }
            }
        }
    }

    /**
     * Removes all the element in the hashmap
     */
    public void removeKey(){
        this.matches.clear();
    }

    /**
     * prints the state of the hash map
     */
    public void stateHash(){
        System.out.println("State of the hash map which contains the keywords: ");
        for(String word : this.matches.keySet()){
            System.out.println(word + ": "+this.matches.get(word));
        }
        System.out.println();
    }
    /**
    * This method is used for buying stocks/funds.
    */
    public void buy(){
        Stock stock = new Stock();
        MutualFund f = new MutualFund();
        Scanner scan = new Scanner(System.in);
        final double comm = 9.99;
        double price;
        int quantity;
        String symbol; 
        String name;
        System.out.println("Choose the type:(type this: stock or fund)\n1.Stock\n2.Mutual Fund");
        String type = scan.nextLine();
        if(type.equalsIgnoreCase("stock") || type.equals("1"))
        {
            System.out.println("Enter Symbol to check if stock already exists: ");
            symbol = scan.nextLine();
            if(symbol.contains(" ")){
                System.out.println(" Symbol should have only one token!");
                return;
            }
            if(this.findStock(symbol) != -1 && this.investList.get(findStock(symbol)).getType().equalsIgnoreCase("stock")){
                System.out.println("This symbol exists: ");
                System.out.println("Enter new price: ");
                price = scan.nextDouble();
                if(price < 0.00){
                    System.out.println("Prices can not be negative");
                    return;
                }
                System.out.println("Enter the quantity to add: ");
                quantity = scan.nextInt();
                if(quantity <= 0){
                    System.out.println("Quantity can not be 0 or negative");
                    return;
                }
                double oldBookValue = this.investList.get(this.findStock(symbol)).getBookValue();
                int oldQuan = this.investList.get(this.findStock(symbol)).getQuantity();
                this.investList.get(this.findStock(symbol)).setPrice(price);
                this.investList.get(this.findStock(symbol)).setQuantity(quantity + oldQuan);
                this.investList.get(this.findStock(symbol)).setBookValue((price * quantity) + comm + oldBookValue);
            }
            else{       
                System.out.println("This symbol does not exist: (Adding it to system)");
                System.out.println("Enter name: ");
                name = scan.nextLine(); 
                if(name.isEmpty()){
                    System.out.println("Invalid name");
                    return;
                }     
                System.out.println("Enter price: ");
                price = scan.nextDouble();
                if(price < 0.00){
                    System.out.println("Prices can not be negative");
                    return;
                }
                System.out.println("Enter quantity: ");
                quantity = scan.nextInt();
                if(quantity <= 0){
                    System.out.println("Quantity can not be 0 or negative");
                    return;
                }
                stock.setType("stock");
                stock.setSymbol(symbol);
                stock.setPrice(price);
                stock.setQuantity(quantity);
                stock.setName(name);
                stock.setBookValue((price*quantity) + comm);
                this.addStock(stock);
            }
        }
        else if(type.equalsIgnoreCase("mutualfund") || type.equalsIgnoreCase("2")){
            System.out.println("Enter Symbol to check if fund exists in the system: ");
            symbol = scan.nextLine();
            if(symbol.contains(" ")){
                System.out.println(" Symbol should have only one token!");
                return;
            }
            if(this.findFund(symbol) != -1 && this.investList.get(findFund(symbol)).getType().equalsIgnoreCase("mutualfund")){
                System.out.println("This symbol for fund exists: ");
                System.out.println("Enter new price: ");
                price = scan.nextDouble();
                if(price < 0.00){
                    System.out.println("Prices can not be negative");
                    return;
                }
                System.out.println("Enter the quantity to add: ");
                quantity = scan.nextInt();
                if(quantity <= 0){
                    System.out.println("Quantity can not be 0 or negative");
                    return;
                }
                double oldBookValue = this.investList.get(this.findFund(symbol)).getBookValue();
                int oldQuan = this.investList.get(this.findFund(symbol)).getQuantity();
                this.investList.get(this.findFund(symbol)).setPrice(price);
                this.investList.get(this.findFund(symbol)).setQuantity(quantity + oldQuan);
                this.investList.get(this.findFund(symbol)).setBookValue((price * quantity) + comm + oldBookValue);
            }
            else{       
                System.out.println("This symbol does not exist: (Adding it to system)");
                System.out.println("Enter name: ");
                name = scan.nextLine(); 
                if(name.isEmpty()){
                    System.out.println("Invalid name");
                    return;
                }      
                System.out.println("Enter price: ");
                price = scan.nextDouble();
                if(price < 0.00){
                    System.out.println("Prices can not be negative");
                    return;
                }
                System.out.println("Enter quantity: ");
                quantity = scan.nextInt();
                if(quantity <= 0){
                    System.out.println("Quantity can not be 0 or negative");
                    return;
                }
                f.setType("mutualfund");
                f.setSymbol(symbol);
                f.setPrice(price);
                f.setQuantity(quantity);
                f.setName(name);
                f.setBookValue((price*quantity) + comm);
                this.addFund(f);
            }
        }
        else{
            System.out.println("Invalid input!");
        }
    }

    /**
    * This method is used for selling.
    */
    public void sell(){
        Scanner scan = new Scanner(System.in);
        double price;
        int quantity;
        String symbol;
        String type;
        System.out.println("You have chosen the sell command.");
        System.out.println("Please provide the type: (stock or fund)(1 or 2)");
        type = scan.nextLine();
        if(type.equalsIgnoreCase("stock") || type.equals("1")){
            type = "stock";
        }else if(type.equalsIgnoreCase("fund") || type.equals("2")){
            type = "mutualfund";
        }else{
            System.out.println("Invalid input type");
            return;
        }
        System.out.println("Please provide a symbol: ");
        symbol = scan.nextLine();
        if(symbol.contains(" ")){
            System.out.println(" Symbol should have only one token!");
            return;
        }
        System.out.println("Please provide the quantity you want to sell: ");
        quantity = scan.nextInt();       
        if(quantity <= 0){
            System.out.println("Quantity can not be 0 or negative");
            return;
        }
        System.out.println("Please provide the price: ");
        price = scan.nextDouble();
        if(price < 0.00){
            System.out.println("Prices can not be negative");
            return;
        }
        if(this.findStock(symbol) != -1 && this.investList.get(findStock(symbol)).getType().equals(type)){
            System.out.println("Selling stock: ");
            if(this.investList.get(this.findStock(symbol)).getQuantity() > quantity){
                int index = this.findStock(symbol);
                int quan = this.investList.get(this.findStock(symbol)).getQuantity();
                System.out.println("Selling stock successful");
                this.investList.get(index).setQuantity(quan - quantity);
                System.out.println("New Quantity: " + (quan - quantity));
                double curr  = this.investList.get(index).getBookValue();
                this.investList.get(index).setPrice(price);;
                this.investList.get(index).setBookValue(curr - (curr * ((double)(quantity)/quan)));
            }
            else if(this.investList.get(this.findStock(symbol)).getQuantity() == quantity){
                System.out.println("Sold Stock completely");
                int index = this.findStock(symbol);
                this.removeStock(index);
            }
            else{
                System.out.println("Selling of of stock unsuccessful.\n");
            }
        }
        else if(this.findFund(symbol) != -1 && this.investList.get(findFund(symbol)).getType().equals(type))
        {
            System.out.println("Selling Fund: ");
            if(this.investList.get(this.findFund(symbol)).getQuantity() > quantity){
                int index = this.findFund(symbol);
                int quan = this.investList.get(this.findFund(symbol)).getQuantity();
                System.out.println("Selling fund successful");
                this.investList.get(index).setQuantity(quan - quantity);
                System.out.println("New Quantity: " + (quan - quantity));
                double curr  = this.investList.get(index).getBookValue();
                this.investList.get(index).setPrice(price);;
                this.investList.get(index).setBookValue(curr - (curr * ((double)(quantity)/quan)));
            }
            else if(this.investList.get(this.findFund(symbol)).getQuantity() == quantity){
                System.out.println("Sold fund completely");
                int index = this.findFund(symbol);
                this.removeFund(index);
            }
            else{
                System.out.println("Couldn't sell fund.\n");
            }
        }
        else{
            System.out.println("Nothing found in Stocks and funds for " + symbol);
        }
    }

    /**
    * This method is used for updating price.
    */
    public void update(){
        int i = 0;
        Scanner scan = new Scanner(System.in);
        String update;
        System.out.println("You have chosen the update command.");
        for(Investment invest : this.investList){
            int j;
            System.out.println(invest.toString());
            System.out.println("Do you want to update price for this?[y/n]");
            update = scan.nextLine();

            if(update.isEmpty()){
                System.out.println("Invalid update");
                continue;
            }

            if(update.equalsIgnoreCase("yes") || update.equalsIgnoreCase("y")){
                j = 1;
            }
            else if(update.equalsIgnoreCase("no") || update.equalsIgnoreCase("n")){
                j = 0;
            }
            else{
                System.out.println("Invalid");
                return;
            }
            if(j == 1){
                System.out.println("Updated Price: ");
                Scanner hereScan = new Scanner(System.in);
                double here = hereScan.nextDouble();
                if(here < 0.00){
                    System.out.println("Prices can not be negative");
                    return;
                }
                this.investList.get(i).setPrice(here);
            }
            i++;
        }
    }

    
    /** 
     * @return double gain
     */
    public double getGain(){
        double sum = 0;
        int i = 0;
        for(Investment invest : this.investList){
            if(this.investList.get(i).getType().equalsIgnoreCase("stock")){
                sum += (invest.getPrice() * invest.getQuantity()) - 9.99 - invest.getBookValue();
            }
            else{
                sum += (invest.getPrice() * invest.getQuantity()) - 45 - invest.getBookValue();
            }
            i++;
        }
        return sum;
    }

    /**
    * This method is used for printing.
    */
    public void print(){
        for(int i = 0; i < this.investList.size(); i++){
            System.out.println(this.investList.get(i).toString());
        }
    }

    
    /** 
     * @param fileName name of the file
     */
    public void printToFile(String fileName) {
        try {
            PrintWriter fw = new PrintWriter(fileName, "UTF-8");
            for(int i = 0; i < this.investList.size(); i++){
                fw.println(this.investList.get(i).toStringFile());
            }
            fw.close();
        }
        catch (Exception e){
            System.out.println("Failed to write! ");
        }
    }

    
    /** 
     * @param fileName takes the file name
     */
    public void readFromFile(String fileName){
        try {
            Stock stock = new Stock();
            MutualFund fund = new MutualFund();
            FileInputStream f = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(f));
            String s;
            String str[] = {""};
            int line = 1;
            boolean bool = true;
            while((s = br.readLine()) != null)
            {
                line = line % 8 == 0 ? 1 : line;
                if(line != 7)
                    str = s.split("\"");
                
                if(line == 1)
                {
                    stock = new Stock();
                    fund = new MutualFund();
                    bool = str[1].equalsIgnoreCase("stock") ? true : false;
                }

                if(bool == true){
                    switch(line){
                        case 1:
                            stock.setType(str[1]);
                            break;
                        case 2:
                            stock.setSymbol(str[1]);
                            break;
                        case 3:
                            stock.setName(str[1]);
                            break;
                        case 4:
                            stock.setQuantity(Integer.parseInt(str[1]));
                            break;
                        case 5:
                            stock.setPrice(Double.parseDouble(str[1]));
                            break;
                        case 6:
                            stock.setBookValue(Double.parseDouble(str[1]));
                            break;
                    }
                    if(line == 6){
                        this.addStock(stock);
                    }

                }else if(bool == false){
                    switch(line){
                        case 1:
                            fund.setType(str[1]);
                            break;
                        case 2:
                            fund.setSymbol(str[1]);
                            break;
                        case 3:
                            fund.setName(str[1]);
                            break;
                        case 4:
                            fund.setQuantity(Integer.parseInt(str[1]));
                            break;
                        case 5:
                            fund.setPrice(Double.parseDouble(str[1]));
                            break;
                        case 6:
                            fund.setBookValue(Double.parseDouble(str[1]));
                            break;
                    }
                    if(line == 6){
                        this.addFund(fund);
                    }
                }
                line++;
            }
            br.close();
        } catch (IOException e)
        {
            System.out.println("Could not open file");
        }
    }
    
    /** 
     * @param s takes a string and pares into array list double for further manipulation
     * @return ArrayList returns an array list
     */
    public ArrayList<Double> priceParser(String s){
        //this function parses the price
        String str[];
        ArrayList<Double> d = new ArrayList<Double>();
        String check = s.replaceAll("\\s+","");
        if(check.contains("-") == false)
        {
            d.add(Double.parseDouble(check));
            return d;
        }
        else if(check.indexOf('-') == 0)
        {
            String strNew = check.replace("-", "");
            d.add(0.0);
            d.add(Double.parseDouble(strNew));
            return d;
        }
        str = check.split("-");
        d.add(Double.parseDouble(str[0]));

        if(str.length > 1){
            d.add(Double.parseDouble(str[1]));
        }else{
            d.add(0.0);
        }
        return d;
    }

    
    /** 
     * @param s takes symbol string
     * @param i takes investment object
     * @return boolean returns true/false
     */
    public boolean symbolSearch(String s, Investment i){
        if(i.getSymbol().equalsIgnoreCase(s)){
            return true;
        }
        return false; 
    }

    
    /** 
     * @param d takes parsed array list
     * @param p takes double p
     * @return boolean returns true/false
     */
    public boolean priceSearch(ArrayList<Double> d, double p){

        //this function does the price search
        if(d.size() == 1){
            if(Double.compare(p, d.get(0)) == 0){
                return true;
            }
        }else if(d.size() == 2){
            if(Double.compare(0.0,d.get(0)) == 0 && Double.compare(p,d.get(1)) <= 0){
                return true;
            }
            if(Double.compare(p,d.get(0)) >= 0 && Double.compare(0.0, d.get(1)) == 0){
                return true;
            }
            if(Double.compare(p, d.get(0)) >= 0 && Double.compare(p, d.get(1)) <= 0){
                return true;
            }
        }
        return false;
    }

    
    /** 
     * @param s takes name string
     * @param s1 takes another string
     * @return boolean returns true/false
     */
    public boolean nameSearch(String s, String s1){

        // this function searches everything in the name
        int count = 0;
        String str[] = s.split("[ ]+");
        String str1[] = s1.split("[ ]+");
        if(str.length == 0){
            return false;
        }
        else{
            for(String name : str){
                for(String check : str1){
                    if(name.equalsIgnoreCase(check)) count++;
                }
            }
        }
        if(count == str.length)
            return true;
        return false;
    }

    /**
    * This method is used for searching.
    */
    public void search() {
        ArrayList<Double> d = new ArrayList<Double>();
        Scanner scan = new Scanner(System.in);
        String symbol, line, name, str[];
        double price;
        int lower = 0; 
        int upper = 10000;
        int prev = 0;
        int prev1 = 0;

        // The search function 
        // implements the case for every case being empty or not
        // the proper explanation of it is in the read me file
        System.out.println("Enter the keywords for search:");
        name = scan.nextLine();
        str = name.split("[ ]+");
        System.out.println("Enter the symbol: ");
        symbol = scan.nextLine();
        if(symbol.contains(" ")){
            System.out.println(" Symbol should have only one token!");
            return;
        }
        System.out.println("Enter the price range: ");
        line = scan.nextLine();
        if(!line.isEmpty()) d = priceParser(line);

        if(!name.isEmpty() && !symbol.isEmpty() && !line.isEmpty()){
            for(int j = 0; j < str.length; j++){
                for(String st : this.matches.keySet()){
                    if(str[j].equalsIgnoreCase(st)){
                        System.out.println(st + ": " + this.matches.get(st));
                        prev = this.matches.get(st).get(0);
                        prev1 = this.matches.get(st).get(this.matches.get(st).size()-1);
                        lower = (lower > prev) ? lower : prev;
                        upper = (upper < prev1) ? upper : prev1;
                        if(upper < lower){
                            int temp = lower;
                            lower = upper;
                            upper = temp;
                        }
                    }
                }
            }
            System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");
            for(int i = lower; i <= upper; i++) {
                if(symbolSearch(symbol, this.investList.get(i))){
                    price = this.investList.get(i).getPrice();
                    if(priceSearch(d, price) && nameSearch(name, this.investList.get(i).getName())){
                        System.out.println(this.investList.get(i));
                    }
                }
            }
        }
        else if(name.isEmpty() && !symbol.isEmpty() && !line.isEmpty()){
            for(int i = 0; i < this.investList.size(); i++){
                if(symbolSearch(symbol, this.investList.get(i))){
                    price = this.investList.get(i).getPrice();
                    if(priceSearch(d, price)){
                        System.out.println(this.investList.get(i));
                    }
                }
            }
        }
        else if(!name.isEmpty() && symbol.isEmpty() && !line.isEmpty()){
            for(int j = 0; j < str.length; j++){
                for(String st : this.matches.keySet()){
                    if(str[j].equalsIgnoreCase(st)){
                        System.out.println(st + ": " + this.matches.get(st));
                        prev = this.matches.get(st).get(0);
                        prev1 = this.matches.get(st).get(this.matches.get(st).size()-1);
                        lower = (lower > prev) ? lower : prev;
                        upper = (upper < prev1) ? upper : prev1;
                        if(upper < lower){
                            int temp = lower;
                            lower = upper;
                            upper = temp;
                        }
                    }
                }
            }
            System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");

            for(int i = lower; i <= upper; i++) {
                price = this.investList.get(i).getPrice();
                if(priceSearch(d, price) && nameSearch(name, this.investList.get(i).getName())){
                    System.out.println(this.investList.get(i));
                }
            }
        }
        else if(!name.isEmpty() && !symbol.isEmpty() && line.isEmpty()){
            for(int j = 0; j < str.length; j++){
                for(String st : this.matches.keySet()){
                    if(str[j].equalsIgnoreCase(st)){
                        System.out.println(st + ": " + this.matches.get(st));
                        prev = this.matches.get(st).get(0);
                        prev1 = this.matches.get(st).get(this.matches.get(st).size()-1);
                        lower = (lower > prev) ? lower : prev;
                        upper = (upper < prev1) ? upper : prev1;
                        if(upper < lower){
                            int temp = lower;
                            lower = upper;
                            upper = temp;
                        }
                    }
                }
            }
            System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");
            for(int i = lower; i <= upper; i++) {
                price = this.investList.get(i).getPrice();
                if(symbolSearch(symbol, this.investList.get(i)) && nameSearch(name, this.investList.get(i).getName())){
                    System.out.println(this.investList.get(i));
                }
            }
        }
        else if(!name.isEmpty() && symbol.isEmpty() && line.isEmpty()){
            for(int j = 0; j < str.length; j++){
                for(String st : this.matches.keySet()){
                    if(str[j].equalsIgnoreCase(st)){
                        System.out.println(st + ": " + this.matches.get(st));
                        prev = this.matches.get(st).get(0);
                        prev1 = this.matches.get(st).get(this.matches.get(st).size()-1);
                        lower = (lower > prev) ? lower : prev;
                        upper = (upper < prev1) ? upper : prev1;
                        if(upper < lower){
                            int temp = lower;
                            lower = upper;
                            upper = temp;
                        }
                    }
                }
            }
            System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");

            for(int i = lower; i <= upper; i++) {
                if(nameSearch(name, this.investList.get(i).getName())){
                    System.out.println(this.investList.get(i));
                }
            }
        }
        else if(name.isEmpty() && !symbol.isEmpty() && line.isEmpty()){
            for(int i = 0; i < this.investList.size(); i++){
                if(symbolSearch(symbol, this.investList.get(i))){
                    System.out.println(this.investList.get(i));
                }
            }
        }
        else if(name.isEmpty() && symbol.isEmpty() && !line.isEmpty()){
            for(int i = 0; i < this.investList.size(); i++){
                price = this.investList.get(i).getPrice();
                if(priceSearch(d, price)){
                    System.out.println(this.investList.get(i));
                }
            }
        }
        else if(name.isEmpty() && symbol.isEmpty() && line.isEmpty()){
            this.print();
        }
    }
}
