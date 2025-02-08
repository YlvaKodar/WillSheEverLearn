package ShoeWebshop.ShoeWebshopOriginal;

import ShoeWebshop.ShoeWebshopOriginal.Database.Shoe;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Head {

    Repository repo = new Repository();
    AppDatabase db = new AppDatabase();
    Shopper shopper = new Shopper();
    ShopFront shopFront;

    Head() throws IOException {
        if (loggIn(true)){
            updateAppDatabase();
            shopFront = new ShopFront(db, shopper.getCustomer().getName());
            addFilterBoxActionListeners();
            addSearchResultButtonActionListeners();
            addDisplayShoeButtonActionListeners();
            addShowCartButtonActionListener();
            addCartButtonActionListeners();
        }
    }

    public boolean loggIn(boolean test) throws IOException {
        boolean loggedIn = false;
        String email = "Ada@Lovelace";
        String password = "Ada";

        do {
            if (!test){
                email = JOptionPane.showInputDialog("Enter your email");
                password = JOptionPane.showInputDialog("Enter your password");
            }

            shopper = repo.loggIn(email, password);

            if (shopper.getCustomer().getId() == 0) {
                int choice = JOptionPane.showConfirmDialog(null, "Invalid email or password. Try again?",
                        null, JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) {
                    break;
                }
            }

        } while (shopper.getCustomer().getId()  < 1);

        if (shopper.getCustomer().getId()  > 0) {
            loggedIn = true;
        }

        repo.setShopperDetails(shopper.getCustomer());

        System.out.println("Head " + shopper.getCustomer().getId() + " logged in");

        if (shopper.getActiveOrder().getId() != null) {
            repo.setWebOrder(shopper.getActiveOrder());
        }

        return loggedIn;
    }

    public void updateAppDatabase() throws IOException {
        db.categoryList = repo.getCategory();
        db.brandList = repo.getBrand();
        db.colourList = repo.getColour();
        db.shoeModelSearchResult = repo.filterSearchDisplay(db.chosenBoxColour, db.chosenBoxCategory, db.chosenBoxBrand);
    }

    public void addFilterBoxActionListeners(){
        shopFront.colourBox.addActionListener(e->{
            if (shopFront.colourBox.getSelectedIndex() == 0) {
                db.chosenBoxColour = "%";
            }else {
                db.chosenBoxColour = Objects.requireNonNull(shopFront.colourBox.getSelectedItem()).toString().trim();
            }
            try {
                searchWithFilter();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        shopFront.categoryBox.addActionListener(e->{
            if (shopFront.categoryBox.getSelectedIndex() == 0) {
                db.chosenBoxCategory = "%";
            }else {
                db.chosenBoxCategory = Objects.requireNonNull(shopFront.categoryBox.getSelectedItem()).toString().trim();
            }
            try {
                searchWithFilter();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        shopFront.brandBox.addActionListener(e->{
            if (shopFront.brandBox.getSelectedIndex() == 0) {
                db.chosenBoxBrand = "%";
            }else {
                db.chosenBoxBrand = Objects.requireNonNull(shopFront.brandBox.getSelectedItem()).toString().trim();
            }
            try {
                searchWithFilter();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void addSizeBoxActionListeners(){
        shopFront.sizeBox.addActionListener(e->{
            if (shopFront.sizeBox.getSelectedIndex() == 0){
                shopFront.addToCartButton.setEnabled(false);
            }else{
                shopFront.addToCartButton.setEnabled(true);
                db.selectedShoeSize = Integer.parseInt(Objects.requireNonNull(shopFront.sizeBox.getSelectedItem()).toString());
            }
        });
    }

    public void addShowCartButtonActionListener(){
        shopFront.showCart.addActionListener(e->{
            try {
                if (shopper.getActiveOrder().getId() != null) {
                    repo.updateShopperCart(shopper);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            shopFront.updateDisplayCart(shopper.getShoppingCart());
            shopFront.cardLayout.show(shopFront.display, "Cart");
        });
    }

    public void addSearchResultButtonActionListeners(){
        for (JButton button : shopFront.searchResult){
            button.addActionListener(e->{
                for (Shoe model : db.shoeModelSearchResult){
                    String checkModel = model.getName() + " " + model.getTheme();
                    if (checkModel.equals(button.getText())) {
                        db.displayedModel = model;
                        break;
                    }
                }

                try {
                    db.availableSizes = repo.getDisplayedShoe(db.displayedModel.getName(), db.displayedModel.getTheme());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                shopFront.updateDisplayShoe();
                addSizeBoxActionListeners();
                shopFront.cardLayout.show(shopFront.display, "Shoe");
            });
        }
    }

    public void addDisplayShoeButtonActionListeners(){
        shopFront.backToSearchButton.addActionListener(e->{
            shopFront.cardLayout.show(shopFront.display, "Search");
            db.resetAvailableSizes();
        });

        shopFront.addToCartButton.addActionListener(e->{
            for (Shoe displayed : db.availableSizes){

                if (displayed.getSize() == db.selectedShoeSize) {
                    try {
                        repo.callAddToCart(shopper, displayed.getId());
                        if (!shopper.getActiveOrder().isActive()){
                            repo.setWebOrder(shopper.getActiveOrder());
                        }
                        repo.updateShopperCart(shopper);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
            }
        });
    }

    public void addCartButtonActionListeners(){
        shopFront.keepLookingButton.addActionListener(e->{
           shopFront.cardLayout.show(shopFront.display, "Search");
        });

        shopFront.pay.addActionListener(e->{
            try {
                if (repo.markOrderAsPayed(shopper)){
                    shopFront.updateDisplayPayed(shopper.getCustomer());
                    shopFront.cardLayout.show(shopFront.display, "Payed");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Payment not successful.");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void searchWithFilter() throws IOException {
        db.shoeModelSearchResult = repo.filterSearchDisplay(db.chosenBoxColour, db.chosenBoxCategory, db.chosenBoxBrand);
        shopFront.updateDisplaySearch();
        addSearchResultButtonActionListeners();
    }

    public static void main(String[] args) throws IOException {
        Head head = new Head();
    }
}
