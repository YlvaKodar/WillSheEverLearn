/*
 * Cactus, subklass till Plant
 *
 * Konstrukor:
 * public Cactus (String name, double hieght) <- super()
 *
 * Metod:
 * public void feedMe() (@override) <- beräknar dos och anropar superklassens setTotalDosage()
 *
 * Ylva Fröjmark
 * 24-09-30
 */

package Greenest;

public class Cactus extends Plant{

    public Cactus(String name, double height){
        super(name, height, Plantfood.MINERAL_WATER, 0.02, 0);
    }
    //Polymorfism
    @Override
    public void feedMe(){
        this.setTotalDosage(this.getDosageBaseQuantityinLiter());
    }
}