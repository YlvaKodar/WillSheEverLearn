package ShoeWebshop.ShoeWebshopOriginal.Database;

public class OrderTable {

    private int id;
    private int webOrderId;
    private int shoeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWebOrderId() {
        return webOrderId;
    }

    public void setWebOrderId(int webOrderId) {
        this.webOrderId = webOrderId;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }

    @Override
    public String toString() {
        return "OrderTable{" +
                "id=" + id +
                ", webOrderId=" + webOrderId +
                ", shoeId=" + shoeId +
                '}';
    }
}
