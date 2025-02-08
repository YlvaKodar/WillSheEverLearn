package Uppg2;/*
 * Dice
 * Innehåller tre metoder för simulering och analys av tärningskast.
 *
 * Metoder:
 * void createFile ()
 * char[] createArray()
 * int[] analyseArray(char[])
 *
 * Ylva Fröjmark
 * 2024-09-12
 */


import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

public class Dice {

    //Skapar en textfil med 1000 slumpmässiga tal mellan 1 och 6.
    public static void createFile (){

        try (FileWriter writer = new FileWriter("Die rolls.txt");){

            for (int i = 0; i < 1000; i++){

                int dieRolles = (int) (Math.random() * 6) + 1;
                writer.write(String.valueOf(dieRolles) + " ");

            }
                     
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    //Läser in textfilen och lagrar alla tal i en array. Returnerar arrayen.
    public static char[] createArray(){

        char[] dieRolls = new char[1000];

        try (BufferedReader reader = new BufferedReader(new FileReader("Die rolls.txt"));){

                for (int i = 0; i < 1000; i++){

                    dieRolls[i] = (char) reader.read();
                    reader.read();
                    
                }

        } catch(IOException e){
            e.printStackTrace();
        }

        return dieRolls;
    }

    //Tar emot en char-array och räknar förekomsten av siffrorna 1--6. Returnerar resultatet i en int-array.
    public static int[] analyseArray(char[] dieRolls){

        int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0;

            for (int i = 0; i < dieRolls.length; i++){

                if (dieRolls[i] == '1')
                    one++;
                if (dieRolls[i] == '2')  
                    two++;
                if (dieRolls[i] == '3')  
                    three++;
                if (dieRolls[i] == '4')  
                    four++;
                if (dieRolls[i] == '5')  
                    five++;
                if (dieRolls[i] == '6')  
                    six++;
            }

            int[] dieStats = {one, two, three, four, five, six};

        return dieStats;
    }
}

