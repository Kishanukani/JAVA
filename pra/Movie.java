import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Movie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // MovieDatabase moidata = new MovieDatabase(null, null);
        int n = sc.nextInt();
        List<Director> directors = new ArrayList<>();
        HashMap<Integer, Director> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Movi> movies = new ArrayList<>();
            // System.err.println();
            System.out.println("enter did");
            int a = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Dname");

            String b = sc.nextLine();
            System.out.println("Enter nat");

            String c = sc.nextLine();
            System.out.println("enter no of mov");

            int k = sc.nextInt();
            for (int j = 0; j < k; j++) {
                System.out.println("Enter Mid");

                int h = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Mname");

                String p = sc.nextLine();
                System.out.println("Enter Myear");

                int q = sc.nextInt();
                System.out.println("Enter Did");
                int r = sc.nextInt();
                movies.add(new Movi(h, p, q, r));
            }
            Director der = new Director(a, b, c, movies);
            directors.add(der);
            // moidata=new MovieDatabase(, movies)
            map.put(a, der);
        }
        // sc.nextLine();
        System.out.println("Enter TEhe nat to find");
        int a = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        getMoviesByDirector(directors, a);
        getDirectorsByNationality(directors, str);

        for (Map.Entry<Integer, Director> e : map.entrySet()) {
            // Director d = e.getValue();
            // List<Movi> m = d.getMovies();
            // System.out.println(m.toString());
            System.out.println(e.getKey() + "+");
            for (Movi m : e.getValue().getMovies()) {
                // System.out.println(m/);
            }
        }

        sc.close();

    }

    public static void getMoviesByDirector(List<Director> directors, int a) {
        try {
            // int c = 0;
            boolean flag = false;
            for (Director d : directors) {
                if (d.getDirectorId() == a) {
                    flag = true;
                    for (Movi m : d.getMovies()) {
                        System.out.print(m.getTitle());
                    }
                }
            }
            if (!flag) {
                throw new DirectorNotFoundException("No Director FOund");
            }
        } catch (DirectorNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void getDirectorsByNationality(List<Director> directors, String str) {
        try {
            boolean flag = false;
            for (Director d : directors) {
                if (d.getNationality().equalsIgnoreCase(str)) {
                    flag = true;
                    System.out.println(d.getDirectorId() + "," + d.getDirectorName());
                }
            }
            if (!flag) {
                throw new DirectorNotFoundException("Director not found");
            }

        } catch (DirectorNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class DirectorNotFoundException extends Exception {
    public DirectorNotFoundException(String str) {
        super(str);
    }
}

/**
 * MovieDatabase
 */
class MovieDatabase {

    private HashMap<Integer, Director> directors;
    private List<Movi> movies;

    public HashMap<Integer, Director> getDirectors() {
        return directors;
    }

    public void setDirectors(HashMap<Integer, Director> directors) {
        this.directors = directors;
    }

    public List<Movi> getMovies() {
        return movies;
    }

    public void setMovies(List<Movi> movies) {
        this.movies = movies;
    }

    public MovieDatabase(HashMap<Integer, Director> directors, List<Movi> movies) {
        this.directors = directors;
        this.movies = movies;
    }

}

class Movi {
    private int movieId;
    private String title;
    private int releaseYear;
    private int directorId;

    public Movi(int movieId, String title, int releaseYear, int directorId) {
        this.movieId = movieId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.directorId = directorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

}

class Director {
    private int directorId;
    private String directorName;
    private String nationality;
    private List<Movi> movies;

    public Director(int directorId, String directorName, String nationality, List<Movi> movies) {
        this.directorId = directorId;
        this.directorName = directorName;
        this.nationality = nationality;
        this.movies = movies;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Movi> getMovies() {
        return movies;
    }

    public void setMovies(List<Movi> movies) {
        this.movies = movies;
    }

}