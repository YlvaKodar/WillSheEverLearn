package Uppg2;/*
 * Main
 * 
 * Klass med endast main-metod.
 * Anropar metodeder i klassen Dice, skriver ut resultatet i terminalen samt i textfil.
 *
 * Ylva Fröjmark
 * 2024-09-12
 */
import java.io.FileWriter;
import java.io.IOException;

public class Main{

    public static void main (String [] args){

        //Metodanrop
        Dice.createFile();
        char[] dieRolls = Dice.createArray(); 
        int[] stats = Dice.analyseArray(dieRolls);

        //String- och String[]-variabler för att förenkla utskrift.
        String headLine = "Results of a thousand die rolls:";
        String[] dies = {"\nones: ", "\ntwos: ", "\nthrees: ", "\nfours: ", "\nfives: ", "\nsixes: "};

        //Skriver ut från arrayer, till terminal och till textfil.
        try (FileWriter writer = new FileWriter("Die roll statistics.txt");) {
           
            System.out.print(headLine);
            writer.write(headLine);

            for (int i = 0; i < 6; i++){
                writer.write(dies[i] + stats[i]);
                System.out.print(dies[i] + stats[i]);
            }
            
        } catch (IOException e) {     
            e.printStackTrace();
        }

        System.out.println("\nFilen \"Die roll statistics.txt\" innehåller " + 
                            FileAnalyzer.fileAnalyzer("Die roll statistics.txt") +
                            " tecken.");

    }
}