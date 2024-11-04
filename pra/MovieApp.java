import java.util.*;

class MovieDatabase {
    private HashMap<Integer, Director> directors = new HashMap<>();
    private List<Movie> movies = new ArrayList<>();

    // Method to add a movie to the database
    public void addMovie(Movie movie) {
        movies.add(movie);
        Director director = directors.get(movie.getDirectorId());
        if (director != null) {
            director.getMovies().add(movie); // Add movie to the director's movie list
        }
    }

    // Method to add a director to the database
    public void addDirector(Director director) {
        directors.put(director.getDirectorId(), director);
    }

    // Retrieves a list of all movie titles directed by a specific director
    public List<String> getMoviesByDirector(int directorId) throws DirectorNotFoundException {
        Director director = directors.get(directorId);
        if (director == null) {
            throw new DirectorNotFoundException("No Director found with ID: " + directorId);
        }

        List<String> movieTitles = new ArrayList<>();
        for (Movie movie : director.getMovies()) {
            movieTitles.add(movie.getTitle());
        }
        return movieTitles;
    }

    // Returns a Set of Directors of a specific nationality
    public Set<Director> getDirectorsByNationality(String nationality) throws NoDirectorsFoundException {
        Set<Director> result = new HashSet<>();
        for (Director director : directors.values()) {
            if (director.getNationality().equalsIgnoreCase(nationality)) {
                result.add(director);
            }
        }
        if (result.isEmpty()) {
            throw new NoDirectorsFoundException("No Directors found with nationality: " + nationality);
        }
        return result;
    }

    // Converts the HashMap of directors to a List and returns it
    public List<Director> convertDirectorMapToList() {
        return new ArrayList<>(directors.values());
    }
}

class Movie {
    private int movieId;
    private String title;
    private int releaseYear;
    private int directorId;

    public Movie(int movieId, String title, int releaseYear, int directorId) {
        this.movieId = movieId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.directorId = directorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getDirectorId() {
        return directorId;
    }

    @Override
    public String toString() {
        return "Movie ID: " + movieId + ", Title: " + title + ", Year: " + releaseYear;
    }
}

class Director {
    private int directorId;
    private String name;
    private String nationality;
    private List<Movie> movies = new ArrayList<>();

    public Director(int directorId, String name, String nationality) {
        this.directorId = directorId;
        this.name = name;
        this.nationality = nationality;
    }

    public int getDirectorId() {
        return directorId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "Director ID: " + directorId + ", Name: " + name + ", Nationality: " + nationality;
    }
}

// Custom exceptions
class DirectorNotFoundException extends Exception {
    public DirectorNotFoundException(String message) {
        super(message);
    }
}

class NoDirectorsFoundException extends Exception {
    public NoDirectorsFoundException(String message) {
        super(message);
    }
}

// Testing the implementation
public class MovieApp {
    public static void main(String[] args) {
        MovieDatabase movieDatabase = new MovieDatabase();

        // Adding Directors
        Director director1 = new Director(1, "Steven Spielberg", "American");
        Director director2 = new Director(2, "Christopher Nolan", "British");
        movieDatabase.addDirector(director1);
        movieDatabase.addDirector(director2);

        // Adding Movies
        Movie movie1 = new Movie(1, "Jaws", 1975, 1);
        Movie movie2 = new Movie(2, "Inception", 2010, 2);
        Movie movie3 = new Movie(3, "Interstellar", 2014, 2);
        movieDatabase.addMovie(movie1);
        movieDatabase.addMovie(movie2);
        movieDatabase.addMovie(movie3);

        try {
            // 1. getMoviesByDirector(2)
            System.out.println("Movies by Director 2: " + movieDatabase.getMoviesByDirector(2));

            // 2. getDirectorsByNationality("British")
            System.out.println(
                    "Directors with British nationality: " + movieDatabase.getDirectorsByNationality("British"));

            // 3. convertDirectorMapToList()
            System.out.println("All Directors: " + movieDatabase.convertDirectorMapToList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
