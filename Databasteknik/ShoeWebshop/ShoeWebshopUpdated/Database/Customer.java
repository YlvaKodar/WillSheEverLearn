package ShoeWebshop.ShoeWebshopUpdated.Database;

public class Customer {

    private int id;
    private String name;
    private String streetAddress;
    private String areaCode;
    private String city;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String printableContact(){
        return name + "<br/>" + streetAddress + "<br/>" +
                areaCode + " " + city +
               "<br/><br/>Confirmation email to " + email;
    }

    @Override
    public String toString() {
        return name + "\n" + streetAddress + "\n" +
                areaCode + " " + city;
    }
}
