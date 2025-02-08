/*
 * Klass MainGym
 *
 * Ylva Fröjmark
 * 2024-10-17
 *
 * Instansvariabler:
 * Path p <- Fil att skriva till
 * String memberFile
 * boolean isTest <- för körning av testprogram
 *
 * Metoder:
 * main
 * Konstruktor (boolean isTest) <- bryter statisk kontext, anropar huvudprogram
 * void mainProgram() <- huvudprogram, tar användarinput (om ej test), anropar övriga, skriver ut data.
 * List<GymMember> turnFileToMembersList( String fileName) <- läser och listar medlemar från fil
 * String checkVisitor(List<GymMember> members, String visitorInfo) <- kontrollerar medlemstatus och initierar listning av besök
 * String fileVisit(Path p, GymMember gm) <- listar medlemars besök åt PT
 */

package BestGymEver;

import java.nio.file.Path;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.APPEND;

public class MainGym {
    protected Path p = Paths.get("Inlamningsuppgifter/src/BestGymEver/Recent Visits.txt");
    protected String memberFile = "Inlamningsuppgifter/data_inlamningsuppg2.txt";
    protected boolean isTest;

    public static void main(String[] args) {
        MainGym gym = new MainGym(false);
    }

    public MainGym(boolean isTest) {
        this.isTest = isTest;
        this.mainProgram();
    }

    public void mainProgram() {
        List<GymMember> members = this.turnFileToMembersList(memberFile);
        String visitorInfo = "Ada Lovelace";

        System.out.println("Besökarens namn eller personnummer?");

        if (!isTest) {
            Scanner sc = new Scanner(System.in);
            visitorInfo = sc.nextLine().trim();
        }

        String status = this.checkVisitor(members, visitorInfo);

        System.out.println("Besökaren är " + status);
    }

    public List<GymMember> turnFileToMembersList( String fileName){

        List<GymMember> members = new ArrayList<>();
        String socialSecNumb, name, payedLast;

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName))).useDelimiter(",\\s*|\\n")) {
            while (sc.hasNext()) {
                socialSecNumb = sc.next().trim();   //Kommer ej i utskrift
                name = sc.next().trim(); //Kommer ej i utskrift
                payedLast = sc.next().toString().trim();

                members.add(new GymMember(socialSecNumb, name, payedLast));
            }

        }catch (FileNotFoundException e){
            System.out.println("Programmet har inte tillgång till medlemslistan.");
            e.printStackTrace();
        }
        return members;
    }

    public String checkVisitor(List<GymMember> members, String visitorInfo){

        MembershipStatus status = MembershipStatus.NOT_MEMBER;
        String fileVisit = ".";

        for (GymMember m : members) {
            if (m.getName().equalsIgnoreCase(visitorInfo) || m.getSocialSecurityNumber().equals(visitorInfo)){

                status = m.getStatus();

                if (status == MembershipStatus.MEMBER){
                    fileVisit = ".\n" + fileVisit(p, m);
                }
            }
        }
        return status.string + fileVisit;
    }

    public String fileVisit(Path p, GymMember gm) {

        try {
            if (!Files.exists(p)){
                Files.createFile(p);
            }
        }catch (IOException e){
            System.out.println("Kunde inte skapa fil.");
            e.printStackTrace();
        }

        try(BufferedWriter bw = Files.newBufferedWriter(p, APPEND)){
            bw.write(gm.getSocialSecurityNumber() + ", " + gm.getName() + "\n" + LocalDate.now().toString() + "\n");


        }catch (IOException e){
            System.out.println("Kunde inte skriva till fil.");
            e.printStackTrace();
        }

        return "Besöket registreras.";
    }
}