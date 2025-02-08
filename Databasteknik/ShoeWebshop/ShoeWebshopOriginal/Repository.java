package ShoeWebshop.ShoeWebshopOriginal;



import ShoeWebshop.ShoeWebshopOriginal.Database.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Repository {

    //Callable statement
    public Shopper loggIn(String email, String password) throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"), p.getProperty("password"));

             CallableStatement cS = con.prepareCall("CALL checkInlog(?, ?, ?, ?, ?)");
             ){

            cS.setString(1, email);
            cS.setString(2, password);
            cS.registerOutParameter(3, Types.INTEGER);
            cS.registerOutParameter(4, Types.VARCHAR);
            cS.registerOutParameter(5, Types.INTEGER);
            cS.executeQuery();

            Shopper shopper = new Shopper();

            shopper.getCustomer().setId(cS.getInt(3));
            shopper.getCustomer().setName(cS.getString(4));
            if (cS.getInt(5) != 0) {
                shopper.getActiveOrder().setId(cS.getInt(5));
                System.out.println("Repo Logged in Active Order: " + cS.getString(5));
            }

            return shopper;

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Callable statement
    public List<Shoe> filterSearchDisplay(String colour, String category, String brand) throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"), p.getProperty("password"));
             CallableStatement cS = con.prepareCall("CALL filterShoeModel(?, ?, ?)");){

            cS.setString(1, colour);
            cS.setString(2, category);
            cS.setString(3, brand);
            cS.execute();

            ResultSet rs = cS.getResultSet();
            List<Shoe> shoes = new ArrayList<Shoe>();

            if (rs != null){
                if (!rs.isBeforeFirst()){   //om falskt har pekaren läst alla rader utan att hitta ett resultat.
                    JOptionPane.showMessageDialog(null, "Sorry! No matches found!");
                    System.out.println("no data found");
                }else{
                    while (rs.next()){
                        Shoe temp = new Shoe();
                        temp.setName(rs.getString("name"));
                        temp.setTheme(rs.getString("theme"));
                        temp.setPrice(rs.getDouble("min(price)"));

                        shoes.add(temp);
                    }
                }
            }else
                System.out.println("ResultSet is null.");

            return shoes;

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Callable statement
    public void callAddToCart(Shopper shopper, int shoeId) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"), p.getProperty("password"));
             CallableStatement cS = con.prepareCall("CALL addToCart(?, ?, ?, ?)");){

            System.out.println("repo addToCart: Order id IN: " + shopper.getActiveOrder().getId());

            cS.setInt(1, shopper.getCustomer().getId());
            cS.registerOutParameter(2, Types.INTEGER);

            if (shopper.getActiveOrder().getId() == null){
                cS.setNull(2, Types.INTEGER);
            }else cS.setInt(2, shopper.getActiveOrder().getId());

            cS.setInt(3, shoeId);
            cS.registerOutParameter(4, Types.BOOLEAN);
            cS.execute();

            if (shopper.getActiveOrder().getId() == null){
                shopper.getActiveOrder().setId(cS.getInt(2));
                System.out.println("repo addToCart: Order id AFTER: " + shopper.getActiveOrder().getId());
            }

            if (cS.getBoolean(4)){
                JOptionPane.showMessageDialog(null, "Successfully added to cart!");
            }else{
                JOptionPane.showMessageDialog(null, "Sorry! Shoe is currently out of stock.");
            }

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Prepared statement
    public List<Shoe> getDisplayedShoe(String name, String theme) throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"), p.getProperty("password"));
             PreparedStatement pS = con.prepareStatement("select distinct Shoe_id, name, theme, brand, size, price from ShoeInStockAllIncluded where name = ? and theme = ?");){

            pS.setString(1, name);
            pS.setString(2, theme);
            pS.execute();

            ResultSet rs = pS.getResultSet();
            List<Shoe> shoes = new ArrayList<Shoe>();

            if (rs != null){
                if (!rs.isBeforeFirst()){   //Om jag får tillbaka ett ResultSet men det inte skriver ut pga select inte gav resultat.
                    System.out.println("no data found");
                }else{
                    while (rs.next()){
                        Shoe temp = new Shoe();
                        temp.setId(rs.getInt("Shoe_id"));
                        temp.setName(rs.getString("name"));
                        temp.setTheme(rs.getString("theme"));
                        temp.setBrand(rs.getString("brand"));
                        temp.setSize(rs.getInt("size"));
                        temp.setPrice(rs.getDouble("price"));

                        shoes.add(temp);
                    }
                }
            }else
                System.out.println("ResultSet är null.");

            return shoes;

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Prepared statement
    public void setShopperDetails(Customer shopper) throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("username"), p.getProperty("password"));

             PreparedStatement stmt = con.prepareStatement("Select * from Customer where id = ?");
             ){

            stmt.setInt(1, shopper.getId());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                shopper.setId(rs.getInt("id"));
                shopper.setName(rs.getString("name"));
                shopper.setStreetAddress(rs.getString("streetAddress"));
                shopper.setAreaCode(rs.getString("areaCode"));
                shopper.setCity(rs.getString("city"));
                shopper.setEmail(rs.getString("email"));
                shopper.setPassword(rs.getString("password"));
            }

            System.out.println("repo setShopperDetails: " + shopper.toString());

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Prepared statement
    public void setWebOrder(WebOrder shopperOrder) throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("username"), p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("Select * from WebOrder where id = ?");
        ){

            System.out.println("repo setWebOrder IN: " + shopperOrder.getId());

            stmt.setInt(1, shopperOrder.getId());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();


            while (rs.next()) {
                shopperOrder.setId(rs.getInt("id"));
                shopperOrder.setCustomerId(rs.getInt("customerId"));
                shopperOrder.setOrderDate(String.valueOf(rs.getDate("orderDate")));
                shopperOrder.setTotalPrice(rs.getDouble("totalPrice"));
                shopperOrder.setActive(rs.getString("order_status"));
            }

            System.out.println("repo setWebOrder AFTER: " + shopperOrder.toString());

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Callable statement
    public void updateShopperCart(Shopper shopper) throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("username"), p.getProperty("password"));
             CallableStatement stmt = con.prepareCall("Call getShoesFromOrderTable(?)");
        ){

            stmt.setInt(1, shopper.getActiveOrder().getId());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            System.out.println("repo updateShopperCart orderId IN: " + shopper.getActiveOrder().getId());

            shopper.clearShoppingCart();

            while (rs.next()) {
                Shoe temp = new Shoe();

                temp.setId(rs.getInt("shoe.id"));
                temp.setName(rs.getString("shoe.name"));
                temp.setTheme(rs.getString("shoe.theme"));
                temp.setSize(rs.getInt("shoe.shoeSize"));
                temp.setPrice(rs.getDouble("shoe.price"));
                temp.setBrand(rs.getString("brand.name"));

                System.out.println("repo updateShopperCart Cart AFTER: " + temp.toString());

                shopper.addToShoppingCart(temp);
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Statement
    public List<Brand> getBrand() throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("username"), p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from Brand");) {

            List<Brand> brands = new ArrayList<>();

            while (rs.next()) {
                Brand temp = new Brand();

                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));

                brands.add(temp);
            }

            return brands;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Statement
    public List<Colour> getColour() throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("username"), p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from Colour");) {

            List<Colour> colours = new ArrayList<>();

            while (rs.next()) {
                Colour temp = new Colour();

                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));

                colours.add(temp);
            }

            return colours;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Statement
    public List<Category> getCategory() throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("username"), p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from Category");) {

            List<Category> categories = new ArrayList<>();

            while (rs.next()) {
                Category temp = new Category();

                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));

                categories.add(temp);
            }

            return categories;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Callable statement
    public boolean markOrderAsPayed(Shopper shopper) throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/ShoeWebShop/Setting.properties"));

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("username"), p.getProperty("password"));
             CallableStatement cS = con.prepareCall("CALL setWebOrderAsPayed(?, ?, ?)");
        ){

            cS.setInt(1, shopper.getActiveOrder().getId());
            cS.setInt(2, shopper.getCustomer().getId());
            cS.registerOutParameter(3, Types.BOOLEAN);

            cS.execute();

            return cS.getBoolean(3);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
