package MovieFinderByRating;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieMatcher {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Movie[] movies = {
                new Movie("Sharknado", 1),
                new Movie("Cats", 2),
                new Movie("Transformers", 2),
                new Movie("The Matrix Reloaded", 3),
                new Movie("Avatar", 4),
                new Movie("Inception", 5),
                new Movie("The Dark Knight", 5)
        };

        //Ask for user input for movie rating
        System.out.println("Enter desired movie rating (1 to 5):");

        int userMovieRating = scanner.nextInt();

        //use binary to find matching movies

        ArrayList<Movie> movieByRatings = findMoviesByRating(movies, userMovieRating);

        if(movieByRatings.isEmpty()){
            System.out.println("No matching movies found for that rating.");
        } else {
            System.out.println("Possible matches: ");
            for(Movie movie : movieByRatings){
                System.out.println(" - " + movie.title);
            }
        }

        scanner.close();
    }

    /**
     * @description Binary search find *one* match, then expand left and right
     * to find *all* movies with the same rating.
     * @param movies : ascending sorted array by rating
     * @param userMovieRating : the user input rating
     * @return ArrayList<MovieFinderByRating.Movie> : All Movies with the same rating.
     * */

    static ArrayList<Movie> findMoviesByRating(Movie[] movies, int userMovieRating) {
        ArrayList<Movie> result = new ArrayList<>();

        int low = 0;
        int high = movies.length - 1;
        int foundIndex = -1; //-1 means no match

        //Standard binary search to find one match
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("Checking for: " + movies[mid].title + " (Rating: " + movies[mid].rating + ")");


            if (movies[mid].rating == userMovieRating) {
                foundIndex = mid;
                break; //One match found

            } else if (movies[mid].rating < userMovieRating) {
                low = mid + 1; //go right
            } else {
                high = mid - 1; //go left
            }
        }
        //if no match found, return empty list
        if(foundIndex == -1) {
            return result;
        }

        //Explore left and right from the found index
        int left = foundIndex;
        int right = foundIndex + 1;

        //Move left to find other matches
        while(left >= 0 && movies[left].rating == userMovieRating){
            result.add(movies[left]);
            left--;
        }

        //Move right to find other matches
        //while(right < movies.length && movies[right].rating == userMovieRating) //this one for exact match
        //now we modified to get all the movies with rating equal or above the user input rating

        while(right < movies.length && movies[right].rating >= userMovieRating){
            result.add(movies[right]);
            right++;
        }

        return result; //no match

    }
}