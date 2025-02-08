/*
* Class Uppg1
*
* Pris för fönsterglas
* Hämtar in uppgifter om pris och mått från användaren i dialogrutor. Beräknar kostnad, 
* drar av eventuella rabatter och publicerar relevant info i dialogrutor. Kan hantera uppgifter
* i svensk och internationell decimalform. Avrundar till två decimaler. Kan hantera ogiltiga inmatningar.
* 
* Innehåller meotderna
* 	void initiate ()
* 	String requestData (String)
* 	void convert (String, String, String)
*	void calculate (double, double, double)
* 	double roundUp (double)
* 	void output ()
* 
* Ylva Fröjmark
* 24-09-08 
*/

import javax.swing.JOptionPane;

public class Uppg1 {
	
	static double width, hight, price, totalPrice, priceDiscount;
	static int discount = 0;
	static boolean entitledToDiscount = true;
	static String widthStr, hightStr, priceStr;
	public static void main (String[] args){

		initiate();
    }

	//Metod som initierar inhämtning av data och skickar vidare med metodanrop.
	public static void initiate (){

        widthStr = requestData("Ange fönstrets bredd i meter.");

        hightStr = requestData("Ange fönstrets höjd i meter.");
        
        priceStr = requestData("Ange pris per kvadratmeter.");

		convert(widthStr, hightStr, priceStr);

	}

	//Metod som hämtar in användarens data i dialogrutor.
	public static String requestData (String request){
        
		return JOptionPane.showInputDialog(request).replaceAll(",", ".").replaceAll(" ", "");
	
	}

	//Metod som konverterar inputen till double-värden eller, vid felaktiga inmatningar,
	//visar felmeddelanden och initierar ny inmatning.
	public static void convert (String widthStr, String hightStr, String priceStr){
	
		boolean inputCorrect = true;

		try{
			width = Double.parseDouble(widthStr);
			hight = Double.parseDouble(hightStr);
			price = Double.parseDouble(priceStr);

		} catch (Exception e){

			JOptionPane.showMessageDialog(null, "Error: Du måste ange numeriska värden.");
			initiate();
			inputCorrect = false;
		}

		if (inputCorrect)
			calculate(width, hight, price);
			
	}

	//Metod som beräknar pris och rabatter och skickar vidare värdena med metodanrop.
	public static void calculate (double width, double hight, double price){

		totalPrice = width * hight * price;

        if (totalPrice >= 5000)
            discount = 25;
        else if (totalPrice >= 2500)
            discount = 10;
        else if (totalPrice >= 1500) 
            discount = 5;
		else
			entitledToDiscount = false;


		priceDiscount = (totalPrice - (totalPrice * discount * 0.01));

		totalPrice = roundUp(totalPrice);
		priceDiscount = roundUp(priceDiscount);

		output();

	}

	//Metod som avrundar priset.
	public static double roundUp (double dbl){

		return Math.round(dbl * 100.0) / 100.0;
	}

	//Metod som publicerar resultaten i dialogruta.
	public static void output (){
		
		String output = "Bredd: " + width + " meter\nHöjd: " + hight + " meter\nPris per kvadratmeter: " + price + " kr\nKostnad: " + totalPrice + " kr"; 

		if (entitledToDiscount)
			output = output + "\nRabatt: " + discount + " %\nRabatterat pris: " + priceDiscount + "kr";
    

		JOptionPane.showMessageDialog(null, output);

	}
}
