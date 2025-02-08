/*
 * Testklass MainGymTest
 *
 * Ylva Fröjmark
 * 2024-10-17
 *
 * Instansvaribler:
 * Path tempDir <- @TempDir
 * String inFile
 * LocalDate date <- ska kunna testa grönt oavsett nät det körs
 * String validPayDate <- -||-
 * MainGym mainGym = new MainGym(true) <- skapar upp instans av MainGym med boolean-värde för isTest
 * GymMember firstMember <- ex-medlem
 * GymMember secondMember = new GymMember <- medlem
 *
 * Testmetoder:
 * void turnFileToMembersList() <-testar filinläsning
 * void checkVisitor() <- testar inläsning och korrekt hantering
 * void fileVisit() <- testar inläsning till fil
 */

package BestGymEver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainGymTest {
    @TempDir
    Path tempDir;
    String inFile = "Inlamningsuppgifter/data_inlamningsuppg2.txt";
    LocalDate date = LocalDate.now().minusMonths(7);
    String validPayDate = date.toString();
    MainGym mainGym = new MainGym(true);
    GymMember firstMember = new GymMember("5211271800", "Ada Lovelace", "2018-03-12");
    GymMember secondMember = new GymMember("1411091900", "Hedy Lamarr", validPayDate);

    @Test //passed
    void turnFileToMembersList() {

        List<GymMember> members = mainGym.turnFileToMembersList(inFile);
        assertTrue(members.size() == 14);

        assertTrue(members.get(0).getName().equals("Alhambra Aromes"));
        assertFalse(members.get(0).getName().equals("Ada Lovelace"));

    }

    @Test //passed
    void checkVisitor() {

        List<GymMember> members = new ArrayList<>();
        members.add(firstMember);
        members.add(secondMember);

        assertTrue(mainGym.checkVisitor(members, "Ada Lovelace").trim().equals("inte längre medlem."));
        assertFalse(mainGym.checkVisitor(members, "Ada Lovelace").trim().equals("medlem."));
        assertTrue(mainGym.checkVisitor(members, "1411091900").trim().equals("medlem.\nBesöket registreras."));
        assertFalse(mainGym.checkVisitor(members, "1411091900").equals("inte medlem."));
        assertTrue(mainGym.checkVisitor(members, "Scroll-Mia").equals("inte medlem."));
        assertFalse(mainGym.checkVisitor(members, "Scroll-Mia").equals("inte längre medlem."));
        assertFalse(mainGym.checkVisitor(members, "Scroll-Mia").equals("medlem.\nBesöket registreras."));

    }

    @Test //passed
    void fileVisit() throws IOException {

        Path outPath = tempDir.resolve("Recent Visits.txt");
        mainGym.fileVisit(outPath, firstMember);
        mainGym.fileVisit(outPath, secondMember);

        List<String> lines = Files.readAllLines(outPath);
        assertEquals(4, lines.size());
        assertTrue(mainGym.fileVisit(outPath, secondMember).equals("Besöket registreras."));

        try (BufferedReader br = new BufferedReader(new FileReader(outPath.toFile()))) {

            assertTrue(br.readLine().equals("5211271800, Ada Lovelace"));
            assertFalse(br.readLine().equals("5211271800, Ada Lovelace"));
            assertTrue(br.readLine().equals("1411091900, Hedy Lamarr"));
            assertTrue(br.readLine().equals(LocalDate.now().toString()));

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}