import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieListFunctions {

    public static long moviesInList(List<Movie> list) {
        return list.stream().count();
    }

    public static int lengthOfLongestMovie(List<Movie> list) {
        return list.stream().map(Movie::getRuntime).max(Integer::compareTo).orElse(0);
    }

    public static int distinctGenresInList(List<Movie> list) {
        return (int) list.stream().flatMap(value -> value.getGenres().stream()).distinct().count();
    }

    //Klarade så länge det fanns bara en highest ranked. Ej nu.
    public static List<String> actorsInHighestRankedMovies(List<Movie> list) {
        return list.stream().max(Comparator.comparing(Movie::getImdbRating)).map(Movie::getCast).orElse(new ArrayList<>());
    }

    public static String movieWithFewestListedActors(List<Movie> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static int numberOfActorsInMultipleMovies(List<Movie> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static String actedInMostMovies(List<Movie> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static int distinctLanguagesInList(List<Movie> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static boolean containsTitleDuplicates(List<Movie> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
