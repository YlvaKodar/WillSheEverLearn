/*
 * Plant, abstrakt klass
 *
 * Instansvariabler:
 * private String name
 * private double height
 * private Plantfood plantfood <- enum
 * private double dosageBaseQuantityinLiter
 * private double dosagePerMeterInLiter
 * private double totalDosage
 *
 * Konstrukor:
 * public Plant (String name, double height, Plantfood plantfood, double dosageBaseQuantityinLiter, double dosagePerMeterInLiter)
 *
 * Metoder:
 * Setters och getters för alla instansvariabler
 * public abstract void feedMe()
 * public String toString() (@override)
 *
 * Ylva Fröjmark
 * 24-09-30
 */
package Greenest;

public abstract class Plant {


    private String name;
    private double height;
    private Plantfood plantfood;
    private double dosageBaseQuantityinLiter;
    private double dosagePerMeterInLiter;
    private double totalDosage;

    public abstract void feedMe();

    public Plant(String name, double height, Plantfood plantfood, double dosageBaseQuantityinLiter, double dosagePerMeterInLiter){
        this.name = name;
        this.height = height;
        this.plantfood = plantfood;
        this.dosageBaseQuantityinLiter = dosageBaseQuantityinLiter;
        this.dosagePerMeterInLiter = dosagePerMeterInLiter;
    }
    //Inkapsling: kommer endast åt privata variabler via getters och setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public Plantfood getPlantfood() {
        return plantfood;
    }
    public void setPlantfood(Plantfood plantfood) {
        this.plantfood = plantfood;
    }
    public double getDosageBaseQuantityinLiter() {
        return dosageBaseQuantityinLiter;
    }
    public void setDosageBaseQuantityinLiter(double dosageBaseQuantityinLiter) {
        this.dosageBaseQuantityinLiter = dosageBaseQuantityinLiter;
    }
    public double getDosagePerMeterInLiter() {
        return dosagePerMeterInLiter;
    }
    public void setDosagePerMeterInLiter(double dosagePerMeterInLiter) {
        this.dosagePerMeterInLiter = dosagePerMeterInLiter;
    }
    public double getTotalDosage() {
        return totalDosage;
    }
    public void setTotalDosage(double totalDosage) {
        this.totalDosage = totalDosage;
    }
    //Polymorfism
    @Override
    public String toString(){
        return  name + " behöver " + totalDosage + " liter " + plantfood.getPlantFood() + ".";
    }
}
