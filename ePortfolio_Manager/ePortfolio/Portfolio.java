package ePortfolio;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Represents the Portfolio class.
 * @author Basil Yusuf
 * @version 1.2
 * @since 1.0
*/

public class Portfolio extends JFrame{
    /**
     * Variable is used to create a panel
     */
    private JPanel panel = new JPanel();
    /**
     * Variable is used to create an option panel
     */
    private JPanel optionPanel = new JPanel();
    /**
     * Variable is used to create a menu
     */
    private JMenu menu;
    /**
     * Variable is used to create a bar
     */
    private JMenuBar bar;
    /**
     * Variable is used to create a filename
     */
    private String fileName;
    /**
     * Variable is used to create a frame
     */
    private JFrame frame = new JFrame();
    /**
     * Variable is used to create an array list to store investments
     */
    ArrayList<Investment> investList = new ArrayList<>();
    /**
     * Variable is used to create a hashmap
     */
    HashMap<String, ArrayList<Integer>> matches = new HashMap<>();

    /** 
     * @param fileName fileName
     */
    public Portfolio(String fileName){
        super();
        showLayout();
        this.fileName = fileName;
    }

    private void showLayout() {
        setTitle("ePortfolio");
        setLayout(new GridLayout(2, 1));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu = new JMenu("Commands");

        JMenuItem buy = new JMenuItem("Buy");
        menu.add(buy);
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                optionPanel.removeAll();
                optionPanel.revalidate();
                optionPanel.repaint();

                panel.setLayout(new GridLayout(1, 2));
                JPanel subpanel1 = new JPanel();
                subpanel1.setLayout(new BoxLayout(subpanel1, BoxLayout.Y_AXIS));

                JPanel subpanel2 = new JPanel();
                subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Buying an investment: ");
                label.setFont(new Font("Serif", Font.PLAIN, 36));
                JPanel title = new JPanel();
                title.add(label);

                JLabel label1 = new JLabel("Type :      ");
                label1.setFont(new Font("Serif", Font.PLAIN, 24));
                String[] typeList = {"Stock", "Fund"};
                JComboBox<String> type = new JComboBox<>(typeList);
                type.setFont(new Font("Serif", Font.PLAIN, 16));
                type.setBackground(Color.WHITE);
                JPanel form1 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form1.add(label1);
                form1.add(type);

                JLabel label2 = new JLabel("Symbol :   ");
                label2.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label2Field = new JTextField(24);
                JPanel form2 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form2.add(label2);
                form2.add(label2Field);

                JLabel label3 = new JLabel("Name :     ");
                label3.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label3Field = new JTextField(24);
                JPanel form3 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form3.add(label3);
                form3.add(label3Field);

                JLabel label4 = new JLabel("Quantity :   ");
                label4.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label4Field = new JTextField(24);
                JPanel form4 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form4.add(label4);
                form4.add(label4Field);

                JLabel label5 = new JLabel("Price :     ");
                final JTextField label5Field = new JTextField(24);
                label5.setFont(new Font("Serif", Font.PLAIN, 24));
                JPanel form5 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form5.add(label5);
                form5.add(label5Field);

                JButton reset = new JButton("Reset");
                reset.setAlignmentX(Component.CENTER_ALIGNMENT);
                reset.setBackground(Color.WHITE);
                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        label2Field.setText(null);
                        label3Field.setText(null);
                        label4Field.setText(null);
                        label5Field.setText(null);
                    }
                });

                JButton buyButton = new JButton("Buy");
                buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                buyButton.setBackground(Color.WHITE);
                buyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JLabel validityCheck = new JLabel();
                        Stock stock = new Stock();
                        MutualFund f = new MutualFund();
                        final double comm = 9.99;

                        if(label2Field.getText().isEmpty() || label3Field.getText().isEmpty() || label4Field.getText().isEmpty() || label5Field.getText().isEmpty()){
                            JOptionPane.showMessageDialog(frame, "Inputs can not be empty!",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String symbol = label2Field.getText(); 
                        String name = label3Field.getText();
                        int quantity;
                        double price;
                        try {
                            quantity = Integer.parseInt(label4Field.getText());
                        } catch (NumberFormatException except) {
                            JOptionPane.showMessageDialog(frame, "Please ensure quantity is a number and not a string",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try {
                            price = Double.parseDouble(label5Field.getText());
                        }catch(NumberFormatException except){
                            JOptionPane.showMessageDialog(frame, "Please ensure price is a number and not a string",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(((String)type.getSelectedItem()).equalsIgnoreCase("Stock")){
                            if(symbol.contains(" ")){
                                JOptionPane.showMessageDialog(frame, "Please ensure symbol is one word and has no spaces",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if(findStock(symbol) != -1 && investList.get(findStock(symbol)).getType().equalsIgnoreCase("stock")){
                                validityCheck.setText("This symbol for stock exists");// System.out.println("This symbol exists: ");
                                if(quantity <= 0){
                                    JOptionPane.showMessageDialog(frame, "Please ensure quantity is not negative or empty",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                if(price <= 0){
                                    JOptionPane.showMessageDialog(frame, "Please ensure price is not negative or empty",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                double oldBookValue = investList.get(findStock(symbol)).getBookValue();
                                int oldQuan = investList.get(findStock(symbol)).getQuantity();
                                investList.get(findStock(symbol)).setPrice(price);
                                investList.get(findStock(symbol)).setQuantity(quantity + oldQuan);
                                investList.get(findStock(symbol)).setBookValue((price * quantity) + comm + oldBookValue);
                            }
                            else{       
                                validityCheck.setText("This symbol does not exist: Adding it to system");// System.out.println("This symbol does not exist: (Adding it to system)"); 
                                if(quantity <= 0){
                                    JOptionPane.showMessageDialog(frame, "Please ensure quantity is not negative",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if(price < 0.00){
                                    JOptionPane.showMessageDialog(frame, "Please ensure price is not negative, or empty",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                stock.setType("stock");
                                stock.setSymbol(symbol);
                                stock.setPrice(price);
                                stock.setQuantity(quantity);
                                stock.setName(name);
                                stock.setBookValue((price*quantity) + comm);
                                addStock(stock);
                            }
                        }
                        else if(((String)type.getSelectedItem()).equalsIgnoreCase("Fund")){
                            if(symbol.contains(" ")){
                                JOptionPane.showMessageDialog(frame, "Please ensure symbol only has one token and no spaces",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if(findFund(symbol) != -1 && investList.get(findFund(symbol)).getType().equalsIgnoreCase("mutualfund")){
                                validityCheck.setText("This symbol for fund exists");// System.out.println("This symbol for fund exists: ");
                                if(quantity <= 0){
                                    JOptionPane.showMessageDialog(frame, "Please ensure quantity is not negative or empty",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if(price <= 0){
                                    JOptionPane.showMessageDialog(frame, "Please ensure price is not negative or empty",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                double oldBookValue = investList.get(findFund(symbol)).getBookValue();
                                int oldQuan = investList.get(findFund(symbol)).getQuantity();
                                investList.get(findFund(symbol)).setPrice(price);
                                investList.get(findFund(symbol)).setQuantity(quantity + oldQuan);
                                investList.get(findFund(symbol)).setBookValue((price * quantity) + comm + oldBookValue);
                            }
                            else{       
                                validityCheck.setText("This symbol does not exist adding it to system");// System.out.println("This symbol does not exist: (Adding it to system)");
                                if(quantity <= 0 || String.valueOf(quantity).isEmpty()){
                                    JOptionPane.showMessageDialog(frame, "Please ensure quantity is not negative or empty",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if(price <= 0 || String.valueOf(price).isEmpty()){
                                    JOptionPane.showMessageDialog(frame, "Please ensure price is not negative or empty",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                f.setType("mutualfund");
                                f.setSymbol(symbol);
                                f.setPrice(price);
                                f.setQuantity(quantity);
                                f.setName(name);
                                f.setBookValue((price*quantity) + comm);
                                addFund(f);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "Please ensure inputs are valid",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                        }


                        optionPanel.removeAll();
                        optionPanel.revalidate();
                        optionPanel.repaint();
                        //optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                        JLabel msg = new JLabel("Messages: ");
                        msg.setFont(new Font("Serif", Font.PLAIN, 24));

                        optionPanel.add(msg);

                        JPanel displayPanel = new JPanel();
                        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
                        
                        validityCheck.setFont(new Font("Serif", Font.PLAIN, 20));
                        displayPanel.add(validityCheck);
                        JLabel displayTitle = new JLabel("<html><br>Displaying all the investments.</html>");
                        displayTitle.setFont(new Font("Serif", Font.PLAIN, 20));
                        displayPanel.add(displayTitle);
                        for(int i = 0; i < investList.size(); i++){
                            JLabel display = new JLabel(investList.get(i).toStringScreen());
                            display.setFont(new Font("Serif", Font.PLAIN, 16));
                            displayPanel.add(display);
                        }
                        JScrollPane scrolledText = new JScrollPane(displayPanel);
                        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        optionPanel.add(scrolledText);
                    }
                });

                subpanel1.add(title);
                subpanel1.add(form1);
                subpanel1.add(form2);
                subpanel1.add(form3);
                subpanel1.add(form4);
                subpanel1.add(form5);

                int pad = 20;

                subpanel2.add(Box.createRigidArea(new Dimension(0, 100)));
                subpanel2.add(reset);
                subpanel2.add(Box.createRigidArea(new Dimension(0, pad)));
                subpanel2.add(buyButton);

                panel.add(subpanel1);
                panel.add(subpanel2);

                //next panel : optionPanel 

                optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                JLabel msg = new JLabel("Messages: ");
                msg.setFont(new Font("Serif", Font.PLAIN, 24));

                JScrollPane scrolledText = new JScrollPane();
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                optionPanel.add(msg);
                optionPanel.add(scrolledText);

                setVisible(true);
            }
        });

        JMenuItem sell = new JMenuItem("Sell");
        menu.add(sell);
        sell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                optionPanel.removeAll();
                optionPanel.revalidate();
                optionPanel.repaint();

                panel.setLayout(new GridLayout(1, 2));
                JPanel subpanel1 = new JPanel();
                subpanel1.setLayout(new BoxLayout(subpanel1, BoxLayout.Y_AXIS));

                JPanel subpanel2 = new JPanel();
                subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Selling an investment: ");
                label.setFont(new Font("Serif", Font.PLAIN, 36));
                JPanel title = new JPanel();
                title.add(label);
                
                JLabel label2 = new JLabel("Symbol :   ");
                label2.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label2Field = new JTextField(24);
                JPanel form2 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form2.add(label2);
                form2.add(label2Field);

                JLabel label4 = new JLabel("Quantity :   ");
                label4.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label4Field = new JTextField(24);
                JPanel form4 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form4.add(label4);
                form4.add(label4Field);

                JLabel label5 = new JLabel("Price :     ");
                final JTextField label5Field = new JTextField(24);
                label5.setFont(new Font("Serif", Font.PLAIN, 24));
                JPanel form5 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form5.add(label5);
                form5.add(label5Field);

                JButton reset = new JButton("Reset");
                reset.setAlignmentX(Component.CENTER_ALIGNMENT);
                reset.setBackground(Color.WHITE);
                JButton sellButton = new JButton("Sell");
                sellButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                sellButton.setBackground(Color.WHITE);

                subpanel1.add(title);
                subpanel1.add(form2);
                subpanel1.add(form4);
                subpanel1.add(form5);

                int pad = 20;

                subpanel2.add(Box.createRigidArea(new Dimension(0, 100)));
                subpanel2.add(reset);
                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        label2Field.setText(null);
                        label4Field.setText(null);
                        label5Field.setText(null);
                    }
                });


                subpanel2.add(Box.createRigidArea(new Dimension(0, pad)));
                subpanel2.add(sellButton);
                JLabel message = new JLabel();
                sellButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(label2Field.getText().isEmpty() || label4Field.getText().isEmpty() || label5Field.getText().isEmpty()){
                            JOptionPane.showMessageDialog(frame, "Inputs can not be empty!",
                                    "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String symbol = label2Field.getText();
                        int quantity;
                        double price;
                        try {
                            quantity = Integer.parseInt(label4Field.getText());
                        }catch(NumberFormatException except){
                            JOptionPane.showMessageDialog(frame, "Please ensure quantity is a number and not a string",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try {
                            price = Double.parseDouble(label5Field.getText());
                        }catch(NumberFormatException except){
                            JOptionPane.showMessageDialog(frame, "Please ensure price is a number and not a string",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if(symbol.contains(" ")){
                            JOptionPane.showMessageDialog(frame, "Please ensure symbol is one word and does not contain space",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }   
                        if(quantity <= 0){
                            JOptionPane.showMessageDialog(frame, "Please ensure quantity is not negative",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(price < 0.00){
                            JOptionPane.showMessageDialog(frame, "Please ensure price is not negative",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        // if(type.equalsIgnoreCase("stock")){
                        //     type = "stock";
                        // }else if(type.equalsIgnoreCase("fund")){
                        //     type = "mutualfund";
                        // }else{
                        //     System.out.println("Invalid input type");
                        //     return;
                        // }

                        if(findStock(symbol) != -1 /*&& this.investList.get(findStock(symbol)).getType().equals(type)*/){
                            if(investList.get(findStock(symbol)).getQuantity() > quantity){
                                int index = findStock(symbol);
                                int quan = investList.get(findStock(symbol)).getQuantity();
                                investList.get(index).setQuantity(quan - quantity);
                                
                                message.setText("<html>Selling of Stock successful<br>" + "New Quanity: " + (quan - quantity) + "</html>");

                                double curr  = investList.get(index).getBookValue();
                                investList.get(index).setPrice(price);
                                investList.get(index).setBookValue(curr - (curr * ((double)(quantity)/quan)));
                            }
                            else if(investList.get(findStock(symbol)).getQuantity() == quantity){
                                message.setText("Sold Stock completely");

                                int index = findStock(symbol);
                                removeStock(index);
                            }
                            else{
                                message.setText("Selling of stock unsuccessful");
                            }
                        }
                        else if(findFund(symbol) != -1 /*&& investList.get(findFund(symbol)).getType().equals(type)*/)
                        {
                            System.out.println("Selling Fund: ");
                            if(investList.get(findFund(symbol)).getQuantity() > quantity){
                                int index = findFund(symbol);
                                int quan = investList.get(findFund(symbol)).getQuantity();
                                investList.get(index).setQuantity(quan - quantity);
                                
                                message.setText("<html>Selling of Stock successful<br>" + "New Quanity: " + (quan - quantity) + "</html>");

                                double curr  = investList.get(index).getBookValue();
                                investList.get(index).setPrice(price);
                                investList.get(index).setBookValue(curr - (curr * ((double)(quantity)/quan)));
                            }
                            else if(investList.get(findFund(symbol)).getQuantity() == quantity){
                                message.setText("Sold fund completely");

                                int index = findFund(symbol);
                                removeFund(index);
                            }
                            else{
                                message.setText("Selling of fund unsuccessful");
                            }
                        }
                        else{
                            message.setText("Nothing found in Stocks and funds for " + symbol);
                        }

                        optionPanel.removeAll();
                        optionPanel.revalidate();
                        optionPanel.repaint();
                        
                        JLabel msg = new JLabel("Messages: ");
                        msg.setFont(new Font("Serif", Font.PLAIN, 24));

                        optionPanel.add(msg);

                        JPanel displayPanel = new JPanel();
                        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
                        
                        message.setFont(new Font("Serif", Font.PLAIN, 20));
                        displayPanel.add(message);
                        JLabel displayTitle = new JLabel("<html><br>Displaying all the investments.</html>");
                        displayTitle.setFont(new Font("Serif", Font.PLAIN, 20));
                        displayPanel.add(displayTitle);
                        for(int i = 0; i < investList.size(); i++){
                            JLabel display = new JLabel(investList.get(i).toStringScreen());
                            display.setFont(new Font("Serif", Font.PLAIN, 16));
                            displayPanel.add(display);
                        }
                        JScrollPane scrolledText = new JScrollPane(displayPanel);
                        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        optionPanel.add(scrolledText);
                    }
                });


                panel.add(subpanel1);
                panel.add(subpanel2);

                optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                JLabel msg = new JLabel("Messages: ");
                msg.setFont(new Font("Serif", Font.PLAIN, 24));

                message.setFont(new Font("Serif", Font.PLAIN, 16));
                JScrollPane scrolledText = new JScrollPane(message);
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                optionPanel.add(msg);
                optionPanel.add(scrolledText);

                setVisible(true);
            }
        });

        JMenuItem update = new JMenuItem("Update");
        menu.add(update);

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JLabel messages = new JLabel();

                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                optionPanel.removeAll();
                optionPanel.revalidate();
                optionPanel.repaint();

                panel.setLayout(new GridLayout(1, 2));
                JPanel subpanel1 = new JPanel();
                subpanel1.setLayout(new BoxLayout(subpanel1, BoxLayout.Y_AXIS));

                JPanel subpanel2 = new JPanel();
                subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Updating investments: ");
                label.setFont(new Font("Serif", Font.PLAIN, 36));
                JPanel title = new JPanel();
                title.add(label);
                
                JLabel label2 = new JLabel("Symbol :   ");
                label2.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label2Field = new JTextField(24);
                JPanel form2 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form2.add(label2);
                form2.add(label2Field);

                JLabel label3 = new JLabel("Name :     ");
                label3.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label3Field = new JTextField(24);
                JPanel form3 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form3.add(label3);
                form3.add(label3Field);

                JLabel label5 = new JLabel("Price :     ");
                final JTextField label5Field = new JTextField(24);
                label5.setFont(new Font("Serif", Font.PLAIN, 24));
                JPanel form5 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form5.add(label5);
                form5.add(label5Field);

                JButton prevButton = new JButton("Prev");
                prevButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                prevButton.setBackground(Color.WHITE);

                JButton nextButton = new JButton("Next");
                nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                nextButton.setBackground(Color.WHITE);

                JButton saveButton = new JButton("Save");
                saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                saveButton.setBackground(Color.WHITE);

                label2Field.setText(investList.get(0).getSymbol());
                label2Field.setEditable(false);
                label3Field.setText(investList.get(0).getName());
                label3Field.setEditable(false);

                final class MyListener implements ActionListener {
                    int count = 0;
                    public void actionPerformed(ActionEvent e) {
                        String buttonString = e.getActionCommand();

                        try{
                            if(buttonString.equals("Prev"))
                            {
                                count--;
                                label2Field.setText(investList.get(count).getSymbol());
                                label2Field.setEditable(false);
                                label3Field.setText(investList.get(count).getName());
                                label3Field.setEditable(false);
                                messages.setText("");
                            }
                            else if(buttonString.equals("Next")){
                                count++;
                                label2Field.setText(investList.get(count).getSymbol());
                                label2Field.setEditable(false);
                                label3Field.setText(investList.get(count).getName());
                                label3Field.setEditable(false);
                                messages.setText("");
                            }
                            else if(buttonString.equals("Save")){
                                if(label5Field.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(frame, "Price can not be empty",
                                        "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if(Double.parseDouble(label5Field.getText()) <= 0){
                                    JOptionPane.showMessageDialog(frame, "Price can not be negative",
                                        "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                try{
                                    investList.get(count).setPrice(Double.parseDouble(label5Field.getText()));
                                }catch(NumberFormatException except){
                                    JOptionPane.showMessageDialog(frame, "Enter a number, not a string",
                                        "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                messages.setText("Saved new price");//System.out.println("Saved new price");
                            }
                            else{
                                label2Field.setText(investList.get(count).getSymbol());
                                label2Field.setEditable(false);
                                label3Field.setText(investList.get(count).getName());
                                label3Field.setEditable(false);
                            }
                        }catch(Exception happen){
                            JOptionPane.showMessageDialog(frame, "Index went out of bounds. Resetting to the start",
                                "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                            count = 0;
                            label2Field.setText(investList.get(0).getSymbol());
                            label2Field.setEditable(false);
                            label3Field.setText(investList.get(0).getName());
                            label3Field.setEditable(false);
                        }
                    }
                }


                MyListener s = new MyListener();
                prevButton.addActionListener(s);
                nextButton.addActionListener(s);
                saveButton.addActionListener(s);

                

                subpanel1.add(title);
                subpanel1.add(form2);
                subpanel1.add(form3);
                subpanel1.add(form5);

                int pad = 20;

                subpanel2.add(Box.createRigidArea(new Dimension(0, 100)));
                subpanel2.add(prevButton);
                subpanel2.add(Box.createRigidArea(new Dimension(0, pad)));
                subpanel2.add(nextButton);
                subpanel2.add(Box.createRigidArea(new Dimension(0, pad)));
                subpanel2.add(saveButton);

                panel.add(subpanel1);
                panel.add(subpanel2);

                //next panel : optionPanel 

                optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                JLabel msg = new JLabel("Messages: ");
                msg.setFont(new Font("Serif", Font.PLAIN, 24));

                JPanel displayText = new JPanel(new BorderLayout());
                messages.setFont(new Font("Serif", Font.PLAIN, 24));
                displayText.add(messages, BorderLayout.CENTER);
                JScrollPane scrolledText = new JScrollPane(displayText);
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                optionPanel.add(msg);
                optionPanel.add(scrolledText);

                setVisible(true);
            }
        });

        JMenuItem gain = new JMenuItem("getGain");
        menu.add(gain);

        gain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                optionPanel.removeAll();
                optionPanel.revalidate();
                optionPanel.repaint();

                panel.setLayout(new GridLayout(1, 2));
                JPanel subpanel1 = new JPanel();
                subpanel1.setLayout(new BoxLayout(subpanel1, BoxLayout.Y_AXIS));

                JPanel subpanel2 = new JPanel();
                subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Getting Total Gain: ");
                label.setFont(new Font("Serif", Font.PLAIN, 36));
                JPanel title = new JPanel();
                title.add(label);
                
                JLabel label2 = new JLabel("Total Gain :   ");
                label2.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label2Field = new JTextField(24);
                JPanel form2 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                label2Field.setText("" + getGain());
                label2Field.setEditable(false);
                form2.add(label2);
                form2.add(label2Field);

                subpanel1.add(title);
                subpanel1.add(form2);

                panel.add(subpanel1);

                //next panel : optionPanel 

                optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                JPanel gainDisplay = new JPanel();
                gainDisplay.setLayout(new BoxLayout(gainDisplay, BoxLayout.Y_AXIS));
                JLabel msg = new JLabel("Individual Gains: ");
                msg.setFont(new Font("Serif", Font.PLAIN, 24));
                JLabel gainDisplayText = new JLabel(individualGain());
                gainDisplayText.setFont(new Font("Serif", Font.PLAIN, 16));
                gainDisplay.add(gainDisplayText);
                

                JScrollPane scrolledText = new JScrollPane(gainDisplay);
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                optionPanel.add(msg);
                optionPanel.add(scrolledText);

                setVisible(true);
            }
        });

        JMenuItem search = new JMenuItem("Search");
        menu.add(search);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                optionPanel.removeAll();
                optionPanel.revalidate();
                optionPanel.repaint();

                panel.setLayout(new GridLayout(1, 2));
                JPanel subpanel1 = new JPanel();
                subpanel1.setLayout(new BoxLayout(subpanel1, BoxLayout.Y_AXIS));

                JPanel subpanel2 = new JPanel();
                subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Searching investments: ");
                label.setFont(new Font("Serif", Font.PLAIN, 36));
                JPanel title = new JPanel();
                title.add(label);
                
                JLabel label2 = new JLabel("Symbol :   ");
                label2.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label2Field = new JTextField(24);
                JPanel form2 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form2.add(label2);
                form2.add(label2Field);

                JLabel label3 = new JLabel("<html>Name<br>keywords    </html>");
                label3.setFont(new Font("Serif", Font.PLAIN, 24));
                final JTextField label3Field = new JTextField(24);
                JPanel form3 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form3.add(label3);
                form3.add(label3Field);

                JLabel label5 = new JLabel("Low Price :     ");
                final JTextField label5Field = new JTextField(24);
                label5.setFont(new Font("Serif", Font.PLAIN, 24));
                JPanel form5 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form5.add(label5);
                form5.add(label5Field);

                JLabel label6 = new JLabel("High Price :     ");
                final JTextField label6Field = new JTextField(24);
                label6.setFont(new Font("Serif", Font.PLAIN, 24));
                JPanel form6 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
                form6.add(label6);
                form6.add(label6Field);

                JButton reset = new JButton("Reset");
                reset.setAlignmentX(Component.CENTER_ALIGNMENT);
                reset.setBackground(Color.WHITE);
                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        label2Field.setText(null);
                        label3Field.setText(null);
                        label5Field.setText(null);
                        label6Field.setText(null);
                    }
                });
                JButton Search = new JButton("Search");
                Search.setAlignmentX(Component.CENTER_ALIGNMENT);
                Search.setBackground(Color.WHITE);
                Search.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JPanel panelSearch = new JPanel();
                        panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.Y_AXIS));
                        JLabel panelSearchTitle;
                        JLabel displaySearch;
                        ArrayList<Double> d = new ArrayList<>();
                        String symbol = label2Field.getText();
                        String name = label3Field.getText();
                        String line;
                        String[] str;
                        double price;
                        int lower = 0; 
                        int upper = investList.size() - 1;
                        int prev = 0;
                        int prev1 = 0;
                        removeKey();
                        changeKey();
                
                        // The search function 
                        // implements the case for every case being empty or not
                        // the proper explanation of it is in the read me file
                        // System.out.println("Enter the keywords for search:");

                        str = name.split("[ ]+");
                        if(symbol.contains(" ")){
                            JOptionPane.showMessageDialog(frame, "Symbol should be one token and it should not have spaces",
                                        "Portfolio Manager", JOptionPane.ERROR_MESSAGE);
                                    return;
                        }
                        line = label5Field.getText() + "" + label6Field.getText();
                        if(!line.isEmpty()){ 
                            String line1 = label5Field.getText() + "-" + label6Field.getText();
                            d = priceParser(line1);
                        }
                
                        if(!name.isEmpty() && !symbol.isEmpty() && !line.isEmpty()){
                            for(int j = 0; j < str.length; j++){
                                for(String st : matches.keySet()){
                                    if(str[j].equalsIgnoreCase(st)){
                                        //System.out.println(st + ": " + matches.get(st));
                                        prev = matches.get(st).get(0);
                                        prev1 = matches.get(st).get(matches.get(st).size()-1);
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
                            //System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");
                            panelSearchTitle = new JLabel("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");//System.out.println(investList.get(i));
                            panelSearchTitle.setFont(new Font("Serif", Font.PLAIN, 16));
                            panelSearch.add(panelSearchTitle);
                            for(int i = lower; i <= upper; i++) {
                                if(symbolSearch(symbol, investList.get(i))){
                                    price = investList.get(i).getPrice();
                                    if(priceSearch(d, price) && nameSearch(name, investList.get(i).getName())){
                                        displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                        displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                        panelSearch.add(displaySearch);
                                    }
                                }
                            }
                        }
                        else if(name.isEmpty() && !symbol.isEmpty() && !line.isEmpty()){
                            for(int i = 0; i < investList.size(); i++){
                                if(symbolSearch(symbol, investList.get(i))){
                                    price = investList.get(i).getPrice();
                                    if(priceSearch(d, price)){
                                        displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                        displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                        panelSearch.add(displaySearch);
                                        //System.out.println(investList.get(i));
                                    }
                                }
                            }
                        }
                        else if(!name.isEmpty() && symbol.isEmpty() && !line.isEmpty()){
                            for(int j = 0; j < str.length; j++){
                                for(String st : matches.keySet()){
                                    if(str[j].equalsIgnoreCase(st)){
                                        System.out.println(st + ": " + matches.get(st));
                                        prev = matches.get(st).get(0);
                                        prev1 = matches.get(st).get(matches.get(st).size()-1);
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
                            //System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");
                            panelSearchTitle = new JLabel("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");//System.out.println(investList.get(i));
                            panelSearchTitle.setFont(new Font("Serif", Font.PLAIN, 16));
                            panelSearch.add(panelSearchTitle);
                            for(int i = lower; i <= upper; i++) {
                                price = investList.get(i).getPrice();
                                if(priceSearch(d, price) && nameSearch(name, investList.get(i).getName())){
                                    displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                    displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                    panelSearch.add(displaySearch);
                                    //System.out.println(investList.get(i));
                                }
                            }
                        }
                        else if(!name.isEmpty() && !symbol.isEmpty() && line.isEmpty()){
                            for(int j = 0; j < str.length; j++){
                                for(String st : matches.keySet()){
                                    if(str[j].equalsIgnoreCase(st)){
                                        System.out.println(st + ": " + matches.get(st));
                                        prev = matches.get(st).get(0);
                                        prev1 = matches.get(st).get(matches.get(st).size()-1);
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
                            //System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");
                            panelSearchTitle = new JLabel("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");//System.out.println(investList.get(i));
                            panelSearchTitle.setFont(new Font("Serif", Font.PLAIN, 16));
                            panelSearch.add(panelSearchTitle);
                            for(int i = lower; i <= upper; i++) {
                                price = investList.get(i).getPrice();
                                if(symbolSearch(symbol, investList.get(i)) && nameSearch(name, investList.get(i).getName())){
                                    //System.out.println(investList.get(i));
                                    displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                    displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                    panelSearch.add(displaySearch);
                                }
                            }
                        }
                        else if(!name.isEmpty() && symbol.isEmpty() && line.isEmpty()){
                            for(int j = 0; j < str.length; j++){
                                for(String st : matches.keySet()){
                                    if(str[j].equalsIgnoreCase(st)){
                                        System.out.println(st + ": " + matches.get(st));
                                        prev = matches.get(st).get(0);
                                        prev1 = matches.get(st).get(matches.get(st).size()-1);
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
                            //System.out.println("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");
                            panelSearchTitle = new JLabel("Searching time reduced using hash maps: Range: (" + lower + "," + upper + ")");//System.out.println(investList.get(i));
                            panelSearchTitle.setFont(new Font("Serif", Font.PLAIN, 16));
                            panelSearch.add(panelSearchTitle);
                            for(int i = lower; i <= upper; i++) {
                                if(nameSearch(name, investList.get(i).getName())){
                                    displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                    displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                    panelSearch.add(displaySearch);
                                    //System.out.println(investList.get(i));
                                }
                            }
                        }
                        else if(name.isEmpty() && !symbol.isEmpty() && line.isEmpty()){
                            for(int i = 0; i < investList.size(); i++){
                                if(symbolSearch(symbol, investList.get(i))){
                                    displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                    displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                    panelSearch.add(displaySearch);
                                    //System.out.println(investList.get(i));
                                }
                            }
                        }
                        else if(name.isEmpty() && symbol.isEmpty() && !line.isEmpty()){
                            for(int i = 0; i < investList.size(); i++){
                                price = investList.get(i).getPrice();
                                if(priceSearch(d, price)){
                                    displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                    displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                    panelSearch.add(displaySearch);
                                    // System.out.println(investList.get(i));
                                }
                            }
                        }
                        else if(name.isEmpty() && symbol.isEmpty() && line.isEmpty()){
                            // print();
                            for(int i = 0; i < investList.size(); i++)
                            {
                                displaySearch = new JLabel(investList.get(i).toStringScreen());//System.out.println(investList.get(i));
                                displaySearch.setFont(new Font("Serif", Font.PLAIN, 16));
                                panelSearch.add(displaySearch);
                            }
                        }

                        optionPanel.removeAll();
                        optionPanel.revalidate();
                        optionPanel.repaint();
                        
                        JLabel msg = new JLabel("Messages: ");
                        msg.setFont(new Font("Serif", Font.PLAIN, 24));
                        optionPanel.add(msg);

                        JScrollPane scrolledText = new JScrollPane(panelSearch);
                        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        optionPanel.add(scrolledText);
                    }
                });

                subpanel1.add(title);
                subpanel1.add(form2);
                subpanel1.add(form3);
                subpanel1.add(form5);
                subpanel1.add(form6);

                //Why is coding so hard on trhis computer for me it's like ' I am typing everything basil basil public public public public publuic public publicc

                int pad = 20;

                subpanel2.add(Box.createRigidArea(new Dimension(0, 100)));
                subpanel2.add(reset);
                subpanel2.add(Box.createRigidArea(new Dimension(0, pad)));
                subpanel2.add(Search);

                panel.add(subpanel1);
                panel.add(subpanel2);

                //next panel : optionPanel 

                optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                JLabel msg = new JLabel("Messages: ");
                msg.setFont(new Font("Serif", Font.PLAIN, 24));


                JScrollPane scrolledText = new JScrollPane();
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                optionPanel.add(msg);
                optionPanel.add(scrolledText);

                setVisible(true);
            }
        });
        
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printToFile(fileName);
                System.exit(0);
            }
        });
        menu.add(quit);

        bar = new JMenuBar();
        bar.add(menu);
        setJMenuBar(bar);

        JLabel label = new JLabel("Welcome to ePortfolio.", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        JLabel label1 = new JLabel("<html>Choose a commands from the \"Commands\" menu to buy or sell<br>" + 
        "an investment, update prices for all investments, get gain for the<br>" +  
        "portfolio, search for relevant investments, or quit the program.</html>", SwingConstants.CENTER);
        label1.setFont(new Font("Serif", Font.PLAIN, 16));

        panel.setLayout(new BorderLayout());
        optionPanel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        optionPanel.add(label1, BorderLayout.NORTH);


        add(panel);
        add(optionPanel);
    }

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
        ArrayList<Integer> h = new ArrayList<>();
        String[] str;
        String name;
        int flag = 0;
        for(int i = 0; i < this.investList.size(); i++){
            name = this.investList.get(i).getName();
            str = name.split("[ ]+");
            for(int j = 0; j < str.length; j++){
                flag = 0;
                if(this.matches.isEmpty()){
                    h = new ArrayList<>();
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
                        h = new ArrayList<>();
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
     * @return String
     */
    public String individualGain(){
        String sum = "<html>";
        int i = 0;
        for(Investment invest : this.investList){
            if(this.investList.get(i).getType().equalsIgnoreCase("stock")){
                sum += "The Gain for " + (invest.getSymbol()) + " " + (invest.getPrice() * invest.getQuantity() - 9.99 - invest.getBookValue()) + "<br>";
            }
            else{
                sum += "The Gain for " + (invest.getSymbol()) + " " + (invest.getPrice() * invest.getQuantity() - 45 - invest.getBookValue()) + "<br>";
            }
            i++;
        }
        sum += "</html>";
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
            String[] str = {""};
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
        String[] str;
        ArrayList<Double> d = new ArrayList<>();
        String check = s.replaceAll("\\s+","");

        if(check.indexOf('-') == 0)
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
        return (i.getSymbol().equalsIgnoreCase(s));
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
        String[] str = s.split("[ ]+");
        String[] str1 = s1.split("[ ]+");
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
        return (count == str.length);
    }
}
