package junjie;

/**
 * The Ui class handles the user interface of the application.
 * It is responsible for printing messages to the user.
 */
public class Ui {

    /**
     * Prints the given string surrounded by a border of dashes.
     * This method adds a decorative border to the printed output for visual emphasis.
     *
     * @param str The string to be printed.
     */
    public void print(String str) {
        String separator = "-------------------------";

        System.out.println(separator);
        System.out.println(str);
        System.out.println(separator);
    }
}
