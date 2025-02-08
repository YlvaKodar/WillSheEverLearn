package ShoeWebshop.ShoeWebshopOriginal;

import ShoeWebshop.ShoeWebshopOriginal.Database.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShopFront extends JFrame {

    AppDatabase db;

    //ShopWindow
    JPanel mainPanel = new JPanel(new BorderLayout());

    //CustomerPanel
    JPanel customerPanel;
    JLabel customerLabel;
    JPanel shopByPanel = new JPanel();
    JComboBox<String> colourBox;
    JComboBox<String> categoryBox;
    JComboBox<String> brandBox;
    JButton showCart = new JButton("Show Cart");

    //CardLayout används för byte av skärm
    CardLayout cardLayout = new CardLayout();
    JPanel display = new JPanel();

    //displaySearch
    JPanel displaySearch = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    //gbc.insets = new Insets(5, 5, 5, 5);
    JScrollPane skrollMia = new JScrollPane(display);
    List<JButton> searchResult = new ArrayList<>();

    //DisplayShoe
    JPanel displayShoe = new JPanel(new BorderLayout());
    JPanel shoePanel;
    JPanel shoePic;
    JLabel shoeInfo;
    JComboBox<String> sizeBox;
    JPanel buttonPanel = new JPanel();
    JButton addToCartButton = new JButton("Add To Cart");
    JButton backToSearchButton = new JButton("Back");

    //DisplayCart
    JPanel displayCart = new JPanel(new BorderLayout());
    JPanel cartPanel;
    JPanel cartButtonPanel;
    JButton keepLookingButton = new JButton("Keep looking!");
    JButton pay = new JButton("Pay and be done.");

    //DisplayPayed
    JPanel displayPayed = new JPanel(new BorderLayout());
    JPanel payedPanel;
    JLabel payedInfo;
    JPanel payedButtonPanel;


    //Thank you Viktor!
    Border outerPadding = BorderFactory.createEmptyBorder(10, 20, 10, 20);
    Border buttonsPadding = BorderFactory.createEmptyBorder(20, 0, 20, 0);

    ShopFront(AppDatabase db, String shopperName){
        this.db = db;
        this.add(mainPanel);
        customerLabel = new JLabel("Welcome " + shopperName + "! Your shoe needs are important to us.");

        mainPanel.add(setupCustomerPanel(), BorderLayout.NORTH);
        mainPanel.add(setupDisplayPanel(), BorderLayout.CENTER);
        skrollMia.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(skrollMia, BorderLayout.EAST);

        setSize(1000, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel setupCustomerPanel(){

        customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBorder(outerPadding);
        customerLabel.setBorder(outerPadding);
        customerPanel.add(customerLabel, BorderLayout.NORTH);

        fillShopByBoxes();
        shopByPanel.add(colourBox);
        shopByPanel.add(categoryBox);
        shopByPanel.add(brandBox);

        showCart.setBorder(buttonsPadding);
        showCart.setBorder(new LineBorder(Color.PINK));
        showCart.setBackground(Color.WHITE);
        showCart.setForeground(Color.PINK);
        shopByPanel.add(showCart);
        customerPanel.add(shopByPanel, BorderLayout.CENTER);

        return customerPanel;
    }

    public JPanel setupDisplayPanel(){

        display.setSize(900, 700);
        display.setBackground(Color.PINK);
        display.setBorder(outerPadding);
        display.setLayout(cardLayout);

        //displaySearch Default update
        updateDisplaySearch();

        //displayCart
        cartButtonPanel = new JPanel(new BorderLayout());
        cartButtonPanel.setBorder(outerPadding);
        cartButtonPanel.setBackground(Color.WHITE);
        cartButtonPanel.add(pay, BorderLayout.EAST);
        cartButtonPanel.add(keepLookingButton, BorderLayout.WEST);

        //displayPayed
        payedPanel = new JPanel(new BorderLayout());
        payedButtonPanel = new JPanel();
        payedButtonPanel.setBorder(outerPadding);

        display.add(displaySearch, "Search");
        display.add(displayShoe, "Shoe");
        display.add(displayCart, "Cart");
        display.add(displayPayed, "Payed");
        cardLayout.show(display, "Search");

        return display;
    }

    public void fillShopByBoxes(){
        int i = 1;
        int colourNb = db.colourList.size() + 1;
        String[] colourChoices = new String[colourNb];
        colourChoices[0] = "Shop by colour";
        for (Colour colour : db.colourList){
            colourChoices[i++] = colour.getName();
        }
        colourBox = new JComboBox<>(colourChoices);

        i = 1;
        int categoryNb = db.categoryList.size() + 1;
        String[] categoryChoices = new String[categoryNb];
        categoryChoices[0] = "Shop by category";
        for (Category category : db.categoryList){
            categoryChoices[i++] = category.getName();
        }
        categoryBox = new JComboBox<>(categoryChoices);

        i = 1;
        int brandNb = db.brandList.size() + 1;
        String[] brandChoices = new String[brandNb];
        brandChoices[0] = "Shop by brand";
        for (Brand brand : db.brandList){
            brandChoices[i++] = brand.getName();
        }
        brandBox = new JComboBox<>(brandChoices);
    }

    public void updateDisplaySearch(){

        int x = 0;
        int y = 0;

        displaySearch.removeAll();
        displaySearch.revalidate();
        displaySearch.repaint();
        searchResult.clear();

        for (Shoe shoe : db.shoeModelSearchResult){
            JPanel picPanel = new JPanel(new BorderLayout());
            picPanel.setBorder(new LineBorder(Color.white, 5));
            picPanel.setPreferredSize(new Dimension(220, 220));
            picPanel.setMinimumSize(new Dimension(220, 220));
            picPanel.setMaximumSize(new Dimension(220, 220));

            JButton button = new JButton();
            button.setText(shoe.getName() + " " + shoe.getTheme());
            button.setForeground(Color.white);
            button.setBackground(Color.PINK);
            button.setBorder(BorderFactory.createLineBorder(Color.white, 1));
            picPanel.add(button, BorderLayout.SOUTH);
            searchResult.add(button);

            gbc.gridx = x;
            gbc.gridy = y;

            gbc.fill = GridBagConstraints.NONE;
            displaySearch.add(picPanel, gbc);

            if (x >= 3 ){
                y++;
                x = 0;
            }else
                x++;
        }

    }

    public void updateDisplayShoe(){

        displayShoe.removeAll();
        displayShoe.revalidate();
        displayShoe.repaint();

        fillSizeBox();

        shoePic = new JPanel();
        shoePic.setPreferredSize(new Dimension(400, 400));
        shoePic.setMinimumSize(new Dimension(400, 400));
        shoePic.setMaximumSize(new Dimension(400, 400));
        shoePic.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));

        shoeInfo = new JLabel(db.availableSizes.getFirst().toString()); //Så länge

        shoePanel = new JPanel(new BorderLayout());
        shoePanel.add(shoePic, BorderLayout.NORTH);
        shoePanel.add(shoeInfo, BorderLayout.WEST);
        shoePanel.add(sizeBox, BorderLayout.EAST);
        displayShoe.add(shoePanel, BorderLayout.NORTH);
        buttonPanel.add(backToSearchButton);
        buttonPanel.add(addToCartButton);
        addToCartButton.setEnabled(false);
        displayShoe.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateDisplayCart(List<Shoe> shoppingCart){
        displayCart.removeAll();
        displayCart.revalidate();
        displayCart.repaint();

        cartPanel = new JPanel(new GridLayout(0, 1));
        cartPanel.setBorder(outerPadding);

        if (shoppingCart.isEmpty()){
            JLabel emptyCartLabel = new JLabel("Empty cart. Please feel pressed to overconsume.");
            cartPanel.add(emptyCartLabel);
            pay.setEnabled(false);
            pay.setVisible(false);
        }else {
            for (Shoe item : shoppingCart) {
                JLabel itemLabel = new JLabel((shoppingCart.indexOf(item) + 1) + ": " + item.toString());
                cartPanel.add(itemLabel);
            }
            pay.setEnabled(true);
            pay.setVisible(true);
        }

        displayCart.add(cartPanel, BorderLayout.CENTER);
        displayCart.add(cartButtonPanel, BorderLayout.SOUTH);
    }

    public void updateDisplayPayed(Customer shopper){
        displayPayed.removeAll();
        displayPayed.revalidate();
        displayPayed.repaint();

        payedInfo = new JLabel();
        payedInfo.setBorder(outerPadding);
        payedInfo.setBackground(Color.white);
        payedInfo.setText("<html>You successfully bought lots of stuff. <br/>All will be sent to:<br/><br/>" +
                shopper.printableContact() + "</html>");

        System.out.println(payedInfo.getText());

        displayPayed.add(payedInfo, BorderLayout.CENTER);
        displayPayed.add(payedButtonPanel, BorderLayout.SOUTH);
    }

    public void fillSizeBox(){
        int i = 1;

        int shoeNb = db.availableSizes.size() + 1;
        String[] displayedShoeSizes = new String[shoeNb];
        displayedShoeSizes[0] = "Available sizes";
        for (Shoe shoe : db.availableSizes){
            displayedShoeSizes[i++] = String.valueOf(shoe.getSize());
        }
        sizeBox = new JComboBox<>(displayedShoeSizes);
    }
}