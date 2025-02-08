package Uppg2;/*
 * FileAnalyzer
 * 
 * Klass med en metod: int fileAnalyzer(String).
 * Läser in en textfil och returnerar antalet tecken.
 *
 * Ylva Fröjmark
 * 2024-09-12
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class FileAnalyzer{

    public static int fileAnalyzer(String file){

        int charCounter = 0;

                try (Scanner reader = new Scanner(new File (file));){
                    
                    while (reader.hasNextLine()){ //Så länge filen har rader kvar

                        String str = reader.nextLine(); //Lagra texten i en sträng
                        charCounter += str.length(); //Addera antal tecken i strängen till charCounter

                    }
       
            } catch(IOException e){
                e.printStackTrace();
            }

            return charCounter;
    }
}