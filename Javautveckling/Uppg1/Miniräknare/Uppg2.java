/*
* class Uppg2
*
* Liten miniräknare:
* Får bara godkänna taluppställningar med två reella tal och en av följande räkneoperander: +, -, * och /.
* Får inte utföra divisioner med 0. Kan hantera vita tecken och decimaltal av svensk och internationell typ. 
* Visar felmeddelande för:
* 
*   Inga tecken (vita tecken borträknade)
*   För få tecken (vita tecken borträknade)
*   Ogiltiga tecken
*   Uppställningen inleds och/eller avslutas inte med reella tal
*   För få operander 
*   För många operander
*   Olagliga divisioner
*   Ogiltiga tal

* Innehåller meotderna
*   void start()
*   void clean(String)
*   void checkChar(String)
*   void checkEnds(String)
*   void operators(String)
*   void stringDivider(String)
*   boolean checkDivision (String)
*   double doubleMaker (String)
*   void calculate (double, char, double)
*   void error (String)
*
* Ylva Fröjmark
*
*/

import java.util.Scanner;
public class Uppg2 {
    
    static int length;
    static boolean calculate;
    

	public static void main (String [] args){

        start(); 

	}

    //Metod som skriver ut start-meddelande och läser in användarens input, anropar sedan nästa metod.
    public static void start (){

        //Nollställ värden från ev. tidigare försök.
        length = 0;
        calculate = true;

        System.out.println ("Vad ska vi räkna på? Ge mig en uppställning med två reella tal och en operator.");
        
    
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        
        clean(input);  

    }
    
    //Metod som sorterar bort vita tecken och byter ev. komma till punkt, samt anropar nästa metod.
    public static void clean (String input){
        
        
        String without = (input.replaceAll(" ", "")).replaceAll(",", ".");

        //Tilldela length strängens storlek.
        length = without.length();
        
        checkChar(without);

    }

    //Metod som säkerställer att strängen är minst tre tecken lång och innehåller endast siffror, punkter och operatorer.
    //Anropar antingen metod för felmeddelande eller skickar godkänd sträng till nästa metod.
    public static void checkChar (String toBeChecked){

        boolean charOk = true;

            if (length == 0){
                error("Du har inte matat in några tecken.");
            }else if (length < 3){
                error("Du måste mata in minst tre tecken: siffra, operator, siffra.");
            }
            else{
                
                for (int i = 0; i < length; i++){

                    if (!(toBeChecked.charAt(i) > 41 && toBeChecked.charAt(i) < 58 )){
                        
                        charOk = false;
                        break;
                    }
                }

                
                if (charOk == false)
                    error("Din uppställning innehåller otillåtna tecken. Den får bara innehålla reella tal och +, -, * eller /.");
                else 
                    checkEnds(toBeChecked); 
            }
        }

    //Metod som säkerställer att strängen inleds och avslutas med reella tal genom att kontrollera inledande och avslutande tecken.
    //Anropar antingen metod för felmeddelande eller skickar godkänd sträng till nästa metod.
    public static void checkEnds (String toBeChecked){

        //Variabler för att förkorta utskrivningen av villkor i kommande if-satser.
        char last = toBeChecked.charAt(length - 1);
        char first = toBeChecked.charAt(0);
        char second = toBeChecked.charAt(1);

        if (((last > 47 && last < 58)) && //Om sista tecknet är en siffra OCH
            ((first > 47 && first < 58) || //om första tecknet är en siffra ELLER
            (first == 45 && (second > 47 && second < 58)))){ //om första tecknet är - och andra tecknet är en siffra
                
                operators(toBeChecked); 
        
        } else  error("Din uppställning måste inledas och avslutas med reella tal.");
        
    }

    //Metod som kontrollerar att strängen innehåller rätt antal operatorer.
    //Anropar antingen metod för felmeddelande eller skickar godkänd sträng till nästa metod.
    public static void operators (String toBeChecked){

        
        int opCount = 0, negCount = 0, inRow = 0;
        boolean ok = true;
       
        //Variabel för att förenkla utskrift av villkor i if-sats.
        char c;
        

        //Räkna antalet operatorer; en räknare för minus och en för övriga.
        for (int i = 0; i < length; i++){

            c = toBeChecked.charAt(i);

            if (c == 42 || c == 43 || c == 47)
                opCount++;
            
            if (c == 45)
                negCount++;
        }

        //Kontrollera att operator följs av max 1 minustecken.
        for (int i = 0; i < length; i++){
           
            c = toBeChecked.charAt(i);

            //Räkna antal operatorer i följd.
            if (c == 42 || c == 43 || c == 45 || c == 47){
                ++inRow;
            }else inRow = 0;


            //Om operander i följd påträffats ... 
            if (inRow > 1){
                
                //Om den sista av två operatorer i följd inte är minus alt. om det är tre operatorer i följd ...
                if (c != 45 || inRow > 2)    
                    ok = false;
                
                
            }

        }

        //Kontrollerar att minustecken förekommer på max två ställen (-10--10 är ok, -10-10-10 är inte ok).
        if (negCount> 2 && ok == true){
            ok = false;

            for (int i = 1; i < toBeChecked.length(); i++){
                
                c = toBeChecked.charAt(i);
                
                if (c == 45){
                    inRow++;
                } else inRow = 0;

                if (inRow > 1){
                    ok = true; 
                    break;
                }

            }
        }
        
       
        if (opCount > 1 || negCount > 3 || opCount >= 1 && negCount >=2 || ok == false){  //Om ett förstort antal operator påträffats ...
            
            error("Din uppställning får bara innehålla en räkneoperator."); 
    
        } else if (opCount + negCount < 1) {    //Om inga operatorer påträffats ...
        
            error("Din uppställing måste innehålla en räkneoperator.");    

        }else stringDivider(toBeChecked); 

    }

    //Metod som delar upp strängen och placerar operanderna i double-variabler (med ytterliggare metodantop).
    //Anropar antingen metod för felmeddelande eller skickar godkänd sträng till nästa metod.
    public static void stringDivider(String unDivided){

        
        char op = (unDivided.contains("*") ? '*' : (unDivided.contains("+") ? '+' : (unDivided.contains("/") ? '/' : '-' )));
        int i = unDivided.indexOf(op, 1);
      
        boolean illegalDiv = false;

        //Placera operanderna i substrängar.
        String firstNumber = unDivided.substring(0, i);
        String lastNumber = unDivided.substring(i+1);

        //Om operatorn är / och en av operanderna är 0, tilldela illegalDiv true. Annars, placera operanderna i
        //double-variabler och anropa nästa metod.
        if (op == 47)
            illegalDiv = (checkDivision(firstNumber) == false || checkDivision(lastNumber) == false) ? true : false; 
            
        if (illegalDiv == true){
            error("Du får inte dividera med 0.");
        }else {
            
            double number1 = doubleMaker(firstNumber);
            double number2 = doubleMaker(lastNumber);
  
            calculate(number1, op, number2);
        }

    }

    //Metod som undersöker om operander kan godkännas för division.
    public static boolean checkDivision (String number){
       
        boolean ok = false;
        
        for (int i = 0; i < number.length(); i++){
           
            if (number.charAt(i) != '0' && number.charAt(i) != '.' && number.charAt(i) != '-'){
                ok = true;
                break;
            }
        }
        
        return ok;
    }

    //Metod som omvandlar strängar med giltiga operander till double-värden. Returnerar värdet eller anropar
    //error() vid ogiltiga värden.
    public static double doubleMaker (String number){
        
        int decDots = 0; 

        //Räkna antalet punkter i strängen (får finnas max en).
        for (int i = 0; i < number.length(); i++){

            if (number.charAt(i) == 46)
                decDots++;
        }

        if (decDots > 1){
            error("De angivna talen är inte giltiga.");
            number = "0";
        }    

        return Double.parseDouble(number);

    }

    //Utför beräkning av godkända uttryck och skriver ut svaret med hjälp av switch/case.
    public static void calculate (double num1, char op, double num2){

        if (calculate ==true){
            
            int opCase = (op == 42 ? 1 : (op == 43 ? 2 : (op == 45 ? 3 : 4)));
            double answer = 0;

                //Utför endast önskad uträkning och tilldela answer svaret.
                switch (opCase){
                    case 1: 
                        answer = num1 * num2;
                        break;
                    case 2:
                        answer = num1 + num2;
                        break;
                    case 3: 
                        answer = num1 - num2;
                        break;
                    case 4:
                        answer = num1 / num2;
                }

                System.out.println(answer);
            }

    }
    //Metod som skriver ut felmeddelande och initierar nytt försök genom att anropa start().
    public static void error (String typeOfError){
        
        System.out.println ("Nu blev det fel. " + typeOfError + "\nProva igen.");

        //Förhindrar att ogiltiga beräkningar skrivs ut (divisioner med 0).
        calculate = false;
        
        start();
    }
}