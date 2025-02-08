package Uppg3.Elevator;

public class Main{
 
     public static void main (String[] args){


        Elevator hiss1 = new Elevator(5);
        Elevator hiss2 = new Elevator(0);

        System.out.println("Hiss 1 är på våning " + hiss1.where() + ".");
        hiss1.goTo(10);
        hiss1.goTo(-2);
        System.out.println("Hiss 1 är nu på våning " + hiss1.where() + ".");

        System.out.println("Hiss 2 är på våning " + hiss2.where() + ".");
        hiss2.goTo(0);
        System.out.println("Hiss 2 är nu på våning " + hiss2.where() + ".");
     }
}