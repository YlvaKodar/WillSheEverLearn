package ShoeWebshop.ShoeWebshopUpdated;
import ShoeWebshop.ShoeWebshopUpdated.Database.Brand;
import ShoeWebshop.ShoeWebshopUpdated.Database.Category;
import ShoeWebshop.ShoeWebshopUpdated.Database.Colour;
import ShoeWebshop.ShoeWebshopUpdated.Database.Shoe;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase {

    List<Colour> colourList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    List<Brand> brandList = new ArrayList<>();
    List<Shoe> shoeModelSearchResult = new ArrayList<>();
    List<Shoe> availableSizes = new ArrayList<>();

    Shoe displayedModel = new Shoe();

    String chosenBoxCategory = "%";
    String chosenBoxColour = "%";
    String chosenBoxBrand = "%";

    int selectedShoeSize;

    public void resetAvailableSizes() {
        availableSizes.clear();
    }
}
