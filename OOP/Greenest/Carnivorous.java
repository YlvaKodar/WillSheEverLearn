/*
 * Carnivorous, subklass Plant
 *
 * Konstrukor:
 * public Carnivorous (String name, double height) <- super()
 *
 * Metod:
 * public void feedMe() (@override) <- beräknar dos och anropar superklassens setTotalDosage()
 *
 * Ylva Fröjmark
 * 24-09-30
 */

package Greenest;

public class Carnivorous extends Plant{

    public Carnivorous(String name, double height){
        super(name, height, Plantfood.PROTEIN_MIX, 0.1, 0.2);
    }
    //Polymorfism
    @Override
    public void feedMe(){
        this.setTotalDosage(this.getDosageBaseQuantityinLiter() + (this.getDosagePerMeterInLiter() * this.getHeight()));
    }
}
