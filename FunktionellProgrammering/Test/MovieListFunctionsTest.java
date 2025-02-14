import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class MovieListFunctionsTest {

    Movie m1 = new Movie("573a1396f29313caabce5419", "Kenji Mizoguchi: The Life of a Film Director", 1975, asList("Documentary", "Biography"), null, asList("Takako Irie", "Daisuke Itè", "Kyèko Kagawa", "Matsutarè Kawaguchi"), 7.3, asList("Japanese"), 132);
    Movie m2 = new Movie("573a1396f29313caabce54ea", "Dersu Uzala", 1975, asList("Adventure", "Biography", "Drama"), null, asList("Yuriy Solomin", "Maksim Munzuk", "Mikhail Bychkov", "Vladimir Khrulev"), 8.3, asList("Russian", "Chinese"), 144);
    Movie m3 = new Movie("573a1396f29313caabce5522", "The Common Man", 1975, asList("Drama"), null, asList("Jean Carmet", "Pierre Tornade", "Jean Bouise", "Michel Peyrelon"), 7.4, asList("French"), 100);
    Movie m4 = new Movie("573a1396f29313caabce553b", "Wrong Move", 1975, asList("Drama"), null, asList("Rèdiger Vogler", "Hans Christian Blech", "Hanna Schygulla", "Nastassja Kinski"), 7.1, asList("German"), 103);
    Movie m5 = new Movie("573a1396f29313caabce5548", "White Collar Blues", 1975, asList("Comedy"), null, asList("Paolo Villaggio", "Anna Mazzamauro", "Gigi Reder", "Giuseppe Anatrelli"), 8.0, asList("Italian", "Japanese", "French", "German"), 108);
    Movie m6 = new Movie("574a1396f29313caabce553b", "Wrong Move", 1975, asList("Fan fiction"), null, asList("Gigi Reder"), 8.3, asList("Rövarspråket"), 7000);
    List<Movie> movieList = asList(m1, m2, m3, m4, m5, m6);
    MovieListFunctions mlf = new MovieListFunctions();

    @Test
    void moviesInList() {
        assertEquals(6, MovieListFunctions.moviesInList(movieList));
    }

    @Test
    void lengthOfLongestMovie() {
        assertEquals(7000, MovieListFunctions.lengthOfLongestMovie(movieList));
    }

    @Test
    void distinctGenresInList() {
        assertEquals(6, MovieListFunctions.distinctGenresInList(movieList));
    }

    @Test
    void actorsInHighestRankedMovies() {
        List<String> result = MovieListFunctions.actorsInHighestRankedMovies(movieList);
        List<String> expected = asList("Yuriy Solomin", "Maksim Munzuk", "Mikhail Bychkov", "Vladimir Khrulev", "Gigi Reder");

        assertEquals(expected.size(), result.size());
        assertNotEquals(500, result.size());
        assertEquals(expected.getFirst(), result.getFirst());
        assertEquals(expected.getLast(), result.getLast());
    }

    @Test
    void movieWithFewestListedActors() {
        assertEquals(MovieListFunctions.movieWithFewestListedActors(movieList), "Wrong Move");
        assertNotEquals(MovieListFunctions.movieWithFewestListedActors(movieList), "Dersu Uzala");
    }

    @Test
    void numberOfActorsInMultipleMovies() {
        assertEquals(1, MovieListFunctions.numberOfActorsInMultipleMovies(movieList));
    }

    @Test
    void actedInMostMovies() {
        assertEquals(MovieListFunctions.actedInMostMovies(movieList), "Gigi Reder");
        assertNotEquals(MovieListFunctions.actedInMostMovies(movieList), "Yuriy Solomin");
    }

    @Test
    void distinctLanguagesInList() {
        assertEquals(7, MovieListFunctions.distinctLanguagesInList(movieList));
    }

    @Test
    void containsTitleDuplicates() {
        assertTrue(MovieListFunctions.containsTitleDuplicates(movieList));
    }
}