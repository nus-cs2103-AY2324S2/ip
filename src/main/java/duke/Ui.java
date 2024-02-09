package duke;

public class Ui {

    private static final String logo = " _  _   __    ____  ____ \n" +
            "( \\/ ) /__\\  (  _ \\(  _ \\\n" +
            " \\  / /(__)\\  )   / )   /\n" +
            " (__)(__)(__)(_)\\_)(_)\\_)\n";
    private static final int dividerLength = 90;
    private static final char dividerChar = 0x2500;

    public Ui() {
        //do nothing
    }

    /**
     * Method to print section dividers to the console.
     */
    public void printDivider(){
        for (int i = 0; i < dividerLength; i++) {
            System.out.print(dividerChar);
        }
        System.out.println();
    }

    /**
     * Method to print the logo and welcome message at program startup.
     */

    public void showWelcome() {
        System.out.println(logo);
        printDivider();
        System.out.println("Ahoy! I be Yarr \nWhat be yer command, me heartie?");
        printDivider();
    }

    /**
     * Method that takes a string and prints it with section dividers above and below.
     *
     * @param message a String representing the message to be printed
     */
    public void printMessage(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }
}
