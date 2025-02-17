

import java.util.*;
import java.util.stream.Collectors;

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

    public static List<String> actorsInHighestRankedMovies(List<Movie> list) {

        List<Map.Entry<Double, List<Movie>>> list1 = list.stream().collect(Collectors.groupingBy(Movie::getImdbRating)).entrySet().stream().
                filter(e -> e.getKey() == (list.stream().max(Comparator.comparing(Movie::getImdbRating)).stream().mapToDouble(Movie::getImdbRating).sum())
                        ).toList();

        return list1.stream().flatMap(e -> e.getValue().stream()).flatMap(e -> e.getCast().stream()).toList();
        //return list.stream().max(Comparator.comparing(Movie::getImdbRating)).map(Movie::getCast).orElse(new ArrayList<>());
    }

    public static String movieWithFewestListedActors(List<Movie> list) {
        return list.stream().sorted(Comparator.comparing(m -> m.getCast().size())).map(Movie::getTitle).toList().getFirst();
    }

    public static int numberOfActorsInMultipleMovies(List<Movie> list) {
        //Borde ge map med nyckel 1 och value antal gånger stringen finns i listan.
        Map<String, Long> frequency = list.stream().flatMap(m -> m.getCast().stream()).collect(Collectors.groupingBy(s -> s, Collectors.counting()));

                                                                                                                                    //DESSA BEHÖVS
        return (int) list.stream().flatMap(m -> m.getCast().stream()).collect(Collectors.groupingBy(s -> s, Collectors.counting())).values().stream()
                .filter(a -> a > 1).count();

    }

    public static String actedInMostMovies(List<Movie> list) {
        //namn nyckel, frekvens value
        Map<String, Long> map1 = list.stream().flatMap(m -> m.getCast().stream()).collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        //frekvens nyckel, namn value
        Map<Long, String> map2 = list.stream().flatMap(m -> m.getCast().stream()).collect(Collectors.groupingBy(s -> s, Collectors.counting())).
                entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (a1, a2) -> a1 + ", " + a2));

        return map2.entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getValue).stream().collect(Collectors.joining(", "));
    }

    public static int distinctLanguagesInList(List<Movie> list) {
        return (int) list.stream().flatMap(value -> value.getLanguages().stream()).distinct().count();
    }

    public static boolean containsTitleDuplicates(List<Movie> list) {
        return list.stream().map(Movie::getTitle).collect(Collectors.groupingBy(s -> s, Collectors.counting())).values().stream().anyMatch(a -> a > 1);
    }
}
