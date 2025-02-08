package Uppg3.Elevator;

public class Elevator{

    private int level; 

    Elevator (int level){
        if (level >= -2 && level <= 10)
            this.level = level;
        else
            throw new IllegalArgumentException("Ogiltig våning.");
    }

    void goTo (int newLevel){
        
        if (newLevel >= -2 && newLevel <= 10){

            String message = (newLevel > level) ? "åker upp till" : ((newLevel < level) ? "åker ner till" : "står redan på");

            System.out.println("Hissen " + message + " våning " + newLevel + ".");

            level = newLevel;

        }else{
        throw new IllegalArgumentException("Ogiltig våning.");
        }
    }

    int where(){
        return this.level;
    }
}