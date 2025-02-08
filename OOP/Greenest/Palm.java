/*
 * Palm, subklass till Plant
 *
 * Konstrukor:
 * public Palm (String name, double height) <- super()
 *
 * Metod:
 * public void feedMe() (@override) <- beräknar dos och anropar superklassens setTotalDosage()
 *
 * Ylva Fröjmark
 * 24-09-30
 */

package Greenest;

public class Palm extends Plant{

    public Palm( String name, double height){
        super(name, height, Plantfood.TAP_WATER, 0,0.5);
    }
    //Polymorfism
    @Override
    public void feedMe(){
        this.setTotalDosage(this.getDosagePerMeterInLiter() * this.getHeight());
    }
}
