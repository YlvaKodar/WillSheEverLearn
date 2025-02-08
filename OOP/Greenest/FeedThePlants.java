package Greenest;/*
 * Greenest.FeedThePlants, klass med huvudprogram, implementerar Communicate
 * Skapar och listar plant-instanser, hämtar info om västskebehov
 * och kommunicerar med användaren via dialogrutor.
 *
 * Importer
 * java.util.LinkedList
 * java.util.List
 * import javax.swing.JOptionPane
 *
 * Instansvariabler:
 * private List<Plant> plantList
 * private final String outputQuestion
 * private final String inputError
 * private final String feedMore
 *
 * Konstrukor:
 * Konstruerar instans för att bryta den statiska kontexten, skapar och listar växter, anropar askForInput
 *
 * Metoder:
 * main-metod <- skapar instans av Greenest.FeedThePlants genom konstruktoranrop
 * public void askForInput(String question)(@override) <- hämtar användarinput om vilken växt som ska vattnas
 * public void processInput(String input)(@override) <- kontrollerar att inputen är giltig
 * public void respond(String response)(@override) <- ger användaren info om växtens behov
 * public void goAgain(String option) (@override) <- frågar om fler växter ska vattnas, anropar i så fall askForInput()
 *
 * Ylva Fröjmark
 * 24-09-30
 */



import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class FeedThePlants implements Greenest.Communicate {

    private List<Plant> plantList = new LinkedList<>();
    private final String outputQuestion = "Vilken växt ska få vätska?";
    private final String inputError = " återfinns inte bland våra gäster. \nKontrollera att namnet är rättstavat och prova igen.\n\n";
    private final String feedMore = "Vill du vattna fler växter?";

    public static void main (String[] args){

        FeedThePlants feed = new FeedThePlants();

    }
    public FeedThePlants(){

        Plant plant1 = new Cactus("Igge", 0.2);
        plantList.add(plant1);
        plant1.feedMe();
        Palm plant2 = new Palm("Laura", 5.0);
        plantList.add(plant2);
        plant2.feedMe();
        Plant plant3 = new Carnivorous("Meatloaf", 0.70);
        plantList.add(plant3);
        plant3.feedMe();
        Plant plant4 = new Palm("Olof", 1.0);
        plantList.add(plant4);
        plant4.feedMe();

        askForInput(outputQuestion);
    }
    //Polymorfism
    @Override
    public void askForInput(String question) {
        String input = JOptionPane.showInputDialog(question);
        processInput(input.trim());
    }
    @Override
    public void processInput(String input) {
        String response = null;

        for (Plant plant : plantList){
            if (plant.getName().compareToIgnoreCase(input) == 0){
                response = plant.toString();
                break;
            }
        }

        if (!(response == null)){
            respond(response);
        }else
            askForInput("\"" + input + "\"" + inputError + outputQuestion);
    }
    @Override
    public void respond(String response) {
        JOptionPane.showMessageDialog(null, response);
        goAgain(feedMore);
    }
    @Override
    public void goAgain(String option) {
        int choice = JOptionPane.showConfirmDialog(null, option, null, JOptionPane.YES_NO_OPTION);
        if (choice == 0)
            askForInput(outputQuestion);
    }
}
