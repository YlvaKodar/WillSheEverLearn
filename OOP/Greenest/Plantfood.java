/*
 * Plantfood, enum
 * String-enum, lagrar möjliga vätske-alternativ för växter
 *
 * Ylva Fröjmark
 * 24-09-30
 */

package Greenest;

public enum Plantfood {
    TAP_WATER  ("kranvatten"),
    MINERAL_WATER ("mineralvatten"),
    PROTEIN_MIX ("proteindryck");

    public final String plantFood;

    Plantfood(String s){
        plantFood = s;
    }

    public String getPlantFood() {
        return plantFood;
    }
}