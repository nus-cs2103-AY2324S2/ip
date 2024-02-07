package view;

/**
 * The SingleLine class provides a method to display a horizontal line made of a repeating character.
 * Extends the UI class.
 */
public class SingleLine extends UI {

    /**
     * Displays a horizontal line made of a repeating character.
     */
    public static void display() {
        String character = "═";
        String line = character.repeat(100);

        System.out.println(line);
    }
}
