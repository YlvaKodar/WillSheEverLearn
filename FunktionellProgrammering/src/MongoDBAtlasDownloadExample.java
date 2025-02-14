//Kod från https://github.com/sigrunolafsdottir/JavaFuncProg/tree/master/src/mogodbDemo/atlas
import com.mongodb.client.*;
import org.bson.Document;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;


public class MongoDBAtlasDownloadExample {
    public MongoDBAtlasDownloadExample() {

        //Skriv in rätt url!
        //String uri = "mongodb+srv://<your-username>:<your-password>@<yourCluster>.mongodb.net/?retryWrites=true&w=majority"; //Sigruns
        String uri = "mongodb+srv://tomte:tomtebobarn@cluster0.hzy3c.mongodb.net/?retryWrites=true&w=majority"; //Min som Sigruns slut
        //String uri = "mongodb+srv://tomte:tomtebobarn@cluster0.hzy3c.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"; //Min som jag fick den

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            // Skriver ut alla filmer
            for (Movie movie : movieList) {
                System.out.println(movie);
            }

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften

            // Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal.
            System.out.println(MovieListFunctions.moviesInList(movieList));
            // Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
            System.out.println(MovieListFunctions.lengthOfLongestMovie(movieList));
            // Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
            System.out.println(MovieListFunctions.distinctGenresInList(movieList));
            // Vilka skådisar spelade i de filmer som hade högst imdb-rating? Returnera en
            // List<String> med deras namn.
            MovieListFunctions.actorsInHighestRankedMovies(movieList).forEach(System.out::println);
            // Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
            System.out.println(MovieListFunctions.movieWithFewestListedActors(movieList));
            // Hur många skådisar var med i mer än 1 film? Returnera ett tal.
            System.out.println(MovieListFunctions.numberOfActorsInMultipleMovies(movieList));
            // Vad hette den skådis som var med i flest filmer? Returnera en String
            System.out.println(MovieListFunctions.actedInMostMovies(movieList));
            // Hur många UNIKA språk har filmerna? Returnera ett tal.
            System.out.println(MovieListFunctions.distinctLanguagesInList(movieList));
            // Finns det någon titel som mer än en film har? Returnera en bool.
            System.out.println(MovieListFunctions.containsTitleDuplicates(movieList));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}
