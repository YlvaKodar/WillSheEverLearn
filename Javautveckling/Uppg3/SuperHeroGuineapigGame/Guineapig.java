package Uppg3.SuperHeroGuineapigGame;

import java.util.Random;
import java.util.Scanner;

class Guineapig{

    static Scanner input = new Scanner(System.in);
      
    private String name, colour, favouriteSnack, superpower, secondPower, alignment, weakness;
    private int strength, extraStrength = 0, health, extraHealth = 0, smarts, extraSmarts = 0, agility, extraAgility = 0;
    private boolean fly, nightVision, lazerEyes, invisibility, gluttony, acrophobia, sleepy;

    Guineapig(){

        System.out.println("What's their name?");
        this.name = input.nextLine();
        
        System.out.println("Colour?");
        this.colour = input.nextLine().toLowerCase();

        setFavouriteSnack();
        setSuperpower();
        setSecondPower();
        setWeakness();
        setAlignment();
        setHealth();
        setStrength();
        setAgility();
        setSmarts();

    }

    public String getSecondPower() {
        return secondPower;
    }



    public void setFavouriteSnack() {

        System.out.println("What's " + getName() + "'s favourite snack? \n1: peanuts \n2: Mars bars \n3: punsch pralines \n4: edamame beans");
 
            int snack = inputInt(1, 5); 

                switch (snack){
                    case 1:
                        this.favouriteSnack = "peanuts";
                        break;
                    case 2:
                        this.favouriteSnack = "Mars bars";
                        break;
                    case 3:
                        this.favouriteSnack = "punsch pralines";
                        break;
                    case 4:
                        this.favouriteSnack = "edamame beans"; 

        }
    }

    public void setSuperpower() {
        
        String power = "0";
        System.out.println("What's " + getName() +
        "'s primary superpower? \n1: super jumpy rubber limbs \n2: super strenght \n3: super resilience \n4: incredible brain powers");

        int firstAbility = inputInt(1, 5);

        switch (firstAbility){
            case 1:
            power = "super jumpy rubber limbs";
                this.setExtraAgility(7);
                break;
            case 2:
            power = "super strenght";
                this.setExtraStrength(7);
                break;
            case 3:
            power = "super resilience";
                this.setExtraHealth(5);
                break;
            case 4:
            power = "incredible brain powers";
                this.setExtraSmarts(7);               
        }
    
        this.superpower = power;
    
    }
    public void setSecondPower() {
        
        int secondAbility = randomizer(1, 5);
        String power = " ";
        
        switch (secondAbility){
            case 1:
            power = "power of invisibility";
                this.setInvisibility(true);
                break;
            case 2:
            power = "night vision";
                this.setNightVision(true);
                break;
            case 3:
                power = "beautiful golden wings";
                this.setFly(true); 
                break;
            case 4:
            power = "lazer eyes";
                this.setLazerEyes(true);
                break;
        }      
        this.secondPower = power;
    }

    public void setWeakness() {
        
        int weak = randomizer(1, 5);
        
        switch (weak){
            case 1:
                this.weakness = "afraid of hights";
                break;
            case 2:
                this.weakness = "unable to control their urge for yummies";
                break;
            case 3:
                this.weakness = "just so darn sleepy";
                break;
            case 4:
                this.weakness = "too clumsy for words";
        }
    }
    public void setAlignment() {
        
        String alignm;
        int law = randomizer(1, 4);
        int ethics = randomizer(1, 3);

        String l = law == 1 ? "lawfully " : (law == 2 ? "chaotic " : "very neutral when it comes to laws and ethics");
        String e = ethics == 1 ? "good" : "evil";

        if (law == 3) {
            alignm = l; 
        }else
            alignm = l + e;
        
        this.alignment = alignm;
    }
    public void setHealth() {
        this.health = (randomizer(3, 5) + getExtraHealth());
    }
    public void setStrength() {
        this.strength = (randomizer(5, 11) + getExtraStrength());
    }
    public void setAgility() {
        this.agility = (randomizer(5, 11) + getExtraAgility());
    }  
    public void setSmarts() {
        this.smarts = (randomizer(5, 11) + getExtraSmarts());
    }
    public void setExtraStrength(int strength) {
        this.extraStrength = strength;
    }
    public void setExtraHealth(int extraHealth) {
        this.extraHealth = extraHealth;
    }
    public void setExtraAgility(int extraAgility) {
        this.extraAgility = extraAgility;
    }
    public void setExtraSmarts(int extraSmarts) {
        this.extraSmarts = extraSmarts;
    }
    public void setFly(boolean fly) {
        this.fly = fly;
    }
    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }
    public void setGluttony(boolean gluttony) {
        this.gluttony = gluttony;
    }
    public void setLazerEyes(boolean lazerEyes) {
        this.lazerEyes = lazerEyes;
    }
    public void setInvisibility(boolean invisibility) {
        this.invisibility = invisibility;
    }
    public void setAcrophobia(boolean acrophobia) {
        this.acrophobia = acrophobia;
    }
    public boolean isFly() {
        return fly;
    }
    public void setSleepy(boolean sleepy) {
        this.sleepy = sleepy;
    }
    public boolean isNightVision() {
        return nightVision;
    }
    public boolean isLazerEyes() {
        return lazerEyes;
    }
    public boolean isInvisibility() {
        return invisibility;
    }
    public boolean isGluttony() {
        return gluttony;
    }
    public boolean isAcrophobia() {
        return acrophobia;
    }
    public boolean isSleepy() {
        return sleepy;
    }
    public String getName() {
        return name;
    }
    public String getColour() {
        return colour;
    }
    public String getFavouriteSnack() {
        return favouriteSnack;
    }
    public String getSuperpower() {
        return superpower;
    }
    public String getAlignment() {
        return alignment;
    }
    public int getStrength() {
        return strength;
    }
    public int getExtraStrength() {
        return extraStrength;
    }
    public int getHealth() {
        return health;
    }
    public int getExtraHealth() {
        return extraHealth;
    }
    public int getSmarts() {
        return smarts;
    }
    public int getExtraSmarts() {
        return extraSmarts;
    }
    public int getAgility() {
        return agility;
    }
    public int getExtraAgility() {
        return extraAgility;
    }
    public String getWeakness() {
        return weakness;
    }

    public void takeDamage(){
        --health;
        System.out.println("Ouch! Health -1. New health: " + health);
    }
    
    public static int randomizer (int bottom, int top){

        Random random = new Random();

        return random.nextInt(bottom, top);

    }
    
    public static int inputInt(int bottom, int top){

        int digit = randomizer(bottom, top );

            try {
                while (true){
                    int intPut = Integer.parseInt(input.nextLine());
                    if (intPut >= bottom && intPut < top){

                        digit = intPut;
                        break;

                    } else {
                        System.out.println("I need you to give me a digit from " + bottom + " to " + (top -1) +".");
                    }
                }
                    
            } catch (NumberFormatException e) {
                System.out.println("You failed to make a choice so I chose for you.");
            }

        return digit;
    }
    
    @Override
    public String toString() {
        return this.name + " is a " + this.colour + " little guineapig whose favourite snack is " + this.favouriteSnack + 
                ".\n" + this.name + " has " + this.superpower + " and " + this.secondPower + ", but unfortunatly they are also " + weakness + ".\n" 
                + this.name + " have been called " + this.alignment + ", whatever that means. \nStrength: " + this.strength
                + "\nHealth: " + health + "\nSmarts: " + smarts + "\nAgility: " + agility;
    }
    
}