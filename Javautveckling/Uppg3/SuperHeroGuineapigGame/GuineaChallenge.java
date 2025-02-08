package Uppg3.SuperHeroGuineapigGame;

import java.util.ArrayList;
import java.util.Scanner;


class GuineaChallenge{

    private static int timeLeft = 15, successBy = 0, hero = 0, bodyCount = 3;
    private static ArrayList<Guineapig> pigTeam = new ArrayList <Guineapig>();
    private static String[] actionEnemy = {"Attack!", "Outwit the bastard.", "Run, bounce or cartwheel their way out of this.", "Use their special power."};
    private static String[] actionObstacle = {"Use brute force!", "Use their brains.", "Leave it.", "Use their special power."}; 
    Scanner input = new Scanner(System.in);
    private static boolean lazerEyes, invisibility, nightVision, canFly, success, theEndIsHere;


    public static void start(){
        System.out.println("Welcome to Super Guineapig Challenge 2024!");
        createPigTeam();
    }
    
    public static void createPigTeam(){
        theEndIsHere = false;

        System.out.println( "\nCreate your champions:");
        for (int i = 0; i < 3; i++){

            System.out.println("\nChampion nr " + (i + 1) + ":");
            pigTeam.add(new Guineapig());
        }

        for (int i = 0; i < pigTeam.size(); i++){

            System.out.println("\nGuineapig " + (i + 1));
            System.out.println (pigTeam.get(i).toString());
        }
        chapter1();
    }

    public static void chapter1 (){
        success = false;

        System.out.println("\nAnd so we begin: \nThe place: the Saltzbrückensburg Castle's Royal World History Museum." + 
                            "\nThe time: the darkest hours of the midwinter night. Your team has been called in by an anonymous" + 
                            "\nsource to stop an on-going heist; a great opportunity for you to settle the score with" +
                            "\nyour arch enemies the Rodent Rascals, the culprits of the crime. The gang has lured you all" + 
                            "\nthe way down to the winding tunnels underneath the great building, when it dawns on you:" + 
                            "\nthis isn't just a robbery, it's a trap! Apparently the Rascals mean to get away with the loot," + 
                            "\ntrigger the alarm and trap you in a desecrated castle for the police to find. You have 15 minutes" +
                            "\nto get out of here so you better start working.\n" + 
                            "\nFirstly, you need to find your way out of this tunnel, which seems to be a dead end. But you just heard" + 
                            "\na shrill ratty giggle and the unmistakable sound of a heavy metal door slammed shut behind you. Trapped!" +
                            "\nAnd though you suspect there's another way out, you can't see it.");


        chooseYourAdventure(actionObstacle, 30, 30, 0, 1);
        chapter2();
    }

    public static void chapter2(){
        success = false;
        //howWeDoin();
        if (theEndIsHere == false){  
            System.out.print("\nSo " + pigTeam.get(hero).getName());

            switch (successBy) {
                case 1:
                    System.out.print(" broke the door open with raw force");
                    break;
                case 2:
                    System.out.print(" found the secret passway and led you all safely through it");
                    break;
                case 3:
                    System.out.print(" Can't happen.");
                    break;
                case 4:
                    System.out.print(" lazer eyed the metal door to lava");

            }

            System.out.print(" and you find yourselves in a black-as-death tunnel." +
                      "\nSomebody sabotaged the old electric system of flickery incandescent bulbs that previously" +
                      "\ncast a gloomy light over the brick walls. You have a vague idea of the right direction," +
                      "\nbut you also know your enemy: as likely as not, the darkness is full of medieval fox shears" + 
                    "\nand pits lined with Mesopotanian spears. And quicksand, why not.");

            chooseYourAdventure(actionObstacle, 0, 40, 0, 2);
            chapter3();
        }
    }

    static void chapter3 (){
        success = false;
        if (theEndIsHere == false){  
            System.out.print("\nBut " + pigTeam.get(hero).getName());

            switch (successBy) {
                case 1:
                    System.out.print("Can't happen.");
                    break;
                case 2:
                    System.out.print(" managed to get the electricity going with no lightsource what so ever! What a hero.");
                    break;
                case 3:
                    System.out.print(" got through the intricate system of traps, and managed to return with some kind of" +
                    "\nantiquey looking oil torch.");
                    break;
                case 4:
                    System.out.print(" led their team safely through the intricate system of traps!");
            }

            System.out.println("\nSoon you'll be out of these wretched tunnels. But ... oh no! To enormous sewer rats" +
                                "\nguard the exit, and I think they've spotted you ...");

            chooseYourAdventure(actionEnemy, 30, 60, 30, 3);
            chapter4();
        }
    }
    
    static void chapter4(){

        success = false;
        //howWeDoin();
        if (theEndIsHere == false){  
            System.out.print("\nAnd can you believe it! " + pigTeam.get(hero).getName());

            switch (successBy) {
                case 1:
                    System.out.print(" beat them to a pulp alright.");
                    break;
                case 2:
                    System.out.print(" actually smoothtalked those bastards to let you pass!");
                    break;
                case 3:
                    System.out.print(" really bounced those bastards senseless.");
                    break;
                case 4:
                    System.out.print(" invisibilitied their way around them and knocked them out from behind!");

            }

            System.out.println("\nFinally you're in the sparkling great hall of the castle, with it's gold coated pillars" +
                                "\nand mother of pearl walls! But what fresh hell is this? It shouldn't be possible," + 
                                "\nbut the enormous taxidermy mammoth mounted under the great chandelier have somehow" + 
                                "\n come to life, and it's not happy about it ... No doubt the work of evil Rodent Rascal" +
                                "\nscientist Chemical Kate. Well, you'll deal with her later, but for know, the charging" + 
                                "\nmammoth takes precedence.");


            chooseYourAdventure(actionEnemy, 60, 0, 60, 4);
            theBeginningOfTheEnd();
        }
    }

    static void theBeginningOfTheEnd(){
        success = false;

        if (theEndIsHere == false){  
            System.out.print("\nThis was truly epic. " + pigTeam.get(hero).getName());

            switch (successBy) {
                case 1:
                    System.out.print(" showed unknown strength in defeating this monster, and songs will be sung" +
                                    "\nabout this glorious battle.");
                    break;
                case 2:
                    System.out.print(" Can't happen.");
                    break;
                case 3:
                    System.out.print(" shall never again be called an unreliable rubber maniac. This you solemnly" +
                                    "\nswear over the exhausted and severely light-headed mammothy monster laying" +
                                    "\npanting on the marble floor.");
                    break;
                case 4:
                    System.out.print(" took to the sky like a hawk on speed and before anyone knew it the enormous" +
                                    "\nchandelier fell from the aboves and trapped the mammoth in a gilded cage.");
            }

            System.out.println("\n\nYou seem to have done it! You truly deserv an epic ending, but it's way past bedtime" +
                                "\n for this little wannabe coder who btw already spent too much time on her homework" +
                                "\nand she simply hasn't got any more to give. Yet, you won!");
            theEnd();

        }
    }


    static void chooseYourAdventure(String[] action, int strength, int smarts, int agility, int power){
        while (success == false){
            chooseHero();
            chooseAction(action, strength, smarts, agility, power);
            System.out.println("Time left: " + timeLeft + " minutes.");
            howWeDoin();
        }
    }

    static void howWeDoin(){
        for (int i = 0; i < pigTeam.size(); i++){
            if (pigTeam.get(i).getHealth() <= 0){
                --bodyCount;
                cruelDeath(pigTeam.get(i));
                pigTeam.remove(i);
            }
        }
        if (bodyCount < 1){
            theEnd();
            
        }else{
            if (timeLeft < 1)
                causeForAlarm();
        }
    }

    static void causeForAlarm(){
        System.out.println("But ... oh darn! The air is filled with a deafening ringing! The Rascals" +
                            "\nset the alarm and fled with the loot, and the police will be here any seccond." +
                            "\nBetter get ready to do some serious time. Ohwell what canya do." );
        theEnd();
    }

    static void theEnd(){
        theEndIsHere = true;
        System.out.println("\nThe end. \n\nWanna go again? \n1. Do I! \n2. Gawd no!");

        int choice = Guineapig.inputInt(1, 3);

        switch (choice) {
            case 1:
                createPigTeam();
                break;
        
            case 2:
                System.out.println("Ait bye!");
        }
    }

    static void cruelDeath(Guineapig piggy){

        String fate = bodyCount == 2 ? "\nOy vey! This is too gruesome! Poor " + piggy.getName() + "'s fluffy little body" +
                    "\nis suddenly all covered in dark blood, and the little heart that used to" +
                    "\ntick-tick-tick so merrily is dead still. Weep, cruel world, for thou hast lost" +
                    "\nthy bravest soul."
                    : (bodyCount == 1 ? "\nThis be truly a sorrowful night! " + piggy.getName() + " has been brutaly ripped" +
                    "\nfrom the realm of the living. \"Woe, destruction, ruin, and decay;" +
                    "\nThe worst is death, and death will have his day.\""
                    : "\nAnd with that our last hero left this world and all it's joys and sorrows." +
                    "\n\"My sick heart shows that I must yield my body to the earth, and, by my fall, the conquest to my foe!\"");
         
        System.out.println(fate);
    }

    static void chooseHero(){
        
        System.out.println("\nWho's gonna tackle this?");
        int choice;

        for (int i = 0; i < pigTeam.size(); i++){
            System.out.println ((i+1) + ": " + pigTeam.get(i).getName());            
        }

        choice = (Guineapig.inputInt(1, bodyCount+1) -1);

        hero = choice;
        nightVision = pigTeam.get(hero).isNightVision();
        canFly = pigTeam.get(hero).isFly();
        lazerEyes = pigTeam.get(hero).isLazerEyes();
        invisibility = pigTeam.get(hero).isInvisibility();
       
    }
    
    static void chooseAction(String[] action, int strength, int smarts, int agility, int power){

        System.out.println("\nWhat's " + pigTeam.get(hero).getName() + " gonna do?" );
        int choice;

        for (int i = 0; i < action.length; i++){
            System.out.println((i+1) + ": " + action[i]);            
        }

        choice = (Guineapig.inputInt(1, 5));

        switch (choice) {
            case 1:
                tryStrength(strength);
                break;        
            case 2:
                trySmarts(smarts);
                break;
            case 3:
                tryAgility(agility);
                break;
            case 4:
                trySpecialPower(power);
        }

        successBy = choice;
    }
    
    
    static void tryStrength (int die){
        
        if (die == 0){
            System.out.println("But muscles won't help you here!");
        }else{

            int challenge = Guineapig.randomizer(1, die);
            System.out.println("Die: " + challenge);

            if (pigTeam.get(hero).getStrength() >= challenge){
                success = true;
                System.out.println("Oh yeah! Sometimes strength is all you need.");
            
            }else if (pigTeam.get(hero).getStrength() >= (challenge/2)){
                success = true;
                System.out.println("Well, " + pigTeam.get(hero).getName() + " is strong enough to make some damage," +
                "\nbut not without getting hurt themselves.");
                pigTeam.get(hero).takeDamage();
            }else{
                pigTeam.get(hero).takeDamage();
                System.out.println("Too bad. " + pigTeam.get(hero).getName() + " took a hit themselves, no damage done elsewere.");
            }
        }
            --timeLeft;

    }
    static void trySmarts(int die){      
        if (die == 0){
            System.out.println("Sure, their're smart, but what good's that to anyone now?");
        }else{
            int challenge = Guineapig.randomizer(1, die);
            System.out.println("Die: " + challenge);

            if (pigTeam.get(hero).getSmarts() >= challenge){
                System.out.println("Good job, " + pigTeam.get(hero).getName() + "! Brains over brawn anytime!");
                success = true;
            
            }else if (pigTeam.get(hero).getStrength() >= (challenge/2)){
                System.out.println(pigTeam.get(hero).getName() + "'s smarts got you out of a sticky situation, but it took it's toll on them.");
                success = true;
                pigTeam.get(hero).takeDamage();
            }else{
                System.out.println("But apparently you can't think your way out of everything. Or at least " + pigTeam.get(hero).getName() + " can't.");
                pigTeam.get(hero).takeDamage();
            }
        }
        --timeLeft;
    }
    static void tryAgility(int die){
        if (die != 0){
            int challenge = Guineapig.randomizer(1, die);
            System.out.println("Die: " + challenge);

            if (pigTeam.get(hero).getSmarts() >= challenge){
                System.out.println("What do you know! " + pigTeam.get(hero).getName() + " isn't all talk! Good job!");
                success = true;
            
            }else if (pigTeam.get(hero).getStrength() >= (challenge/2)){
                System.out.println("Wow, " + pigTeam.get(hero).getName() + ", you did it! But you seem to have hurt yourself in the process ...");
                success = true;
                pigTeam.get(hero).takeDamage();
            }else{
                System.out.println("Oh no. No luck there. Just a holelot'a pain.");
                pigTeam.get(hero).takeDamage();
            }
        }else
            System.out.println("Ok, " + pigTeam.get(hero).getName() + ". Seems like a wasted round to me, but what do I know.");
            
        --timeLeft;

    }
    
    static void trySpecialPower(int power){        
        boolean powerNeeded = false;

            switch (power) {
                case 1:
                    powerNeeded = lazerEyes;
                    break;
                case 2:
                    powerNeeded = nightVision;
                    break;
                case 3:
                    powerNeeded = invisibility;
                    break;
                case 4:
                    powerNeeded = canFly;
                    
            }
        
        if (powerNeeded == true){
            System.out.println(pigTeam.get(hero).getName() + " tries to use their " + pigTeam.get(hero).getSecondPower() + " ...");

            if (Guineapig.randomizer(1, 11) > 8 ){
                System.out.println("... but fails! Oh woe is " + pigTeam.get(hero).getName() +"!");  
                pigTeam.get(hero).takeDamage();
            }else{
                System.out.println(" ... and YES! SUCCESS!");
                success = true;
            }
        }else{
            System.out.println("But alas! " + pigTeam.get(hero).getName() + "'s powers are useless here!");
            pigTeam.get(hero).takeDamage();
        }
        --timeLeft;
    }
}
