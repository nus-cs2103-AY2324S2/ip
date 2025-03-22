package view;

/**
 * The EncaseLines class provides a method to display a string enclosed by horizontal lines.
 */
public class EncaseLines {

    /**
     * Displays a string enclosed by horizontal lines for emphasis.
     *
     * @param string The string to be displayed.
     */
    public static void display(String string) {
        SingleLine.display();
        System.out.println(string);
        SingleLine.display();
    }
}
