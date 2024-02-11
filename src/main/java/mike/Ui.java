package mike;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Ui is the class responsible for the user interface, i.e., user input and response.
 * @author ningc
 */
public class Ui {
    private static final String logo = "                   .\n"
            + "          &#  #*&/,,(@%*. (#\n"
            + "           %#((,.      #%,*,\n"
            + "         %%%(/,.. *(     /,.**\n"
            + "      .%%%%#/**,%(@@&@    /.,,/\n"
            + "      %%%%#(((/*(#*(%&    ..,,,*(\n"
            + "    *#%%%##(/,#(/**,,...,...,,,**/\n"
            + "   .#%%%%%##(((//#(//*......,,,**//\n"
            + "   &#%%%%%%###(///*,,..,,,,,,,***/#*\n"
            + "   &#%%%%%%#####@@@@@@(,,**,****/(#*\n"
            + "  *&%#%%&&&%%%@@@@@@@@@@&**/////(.&/*\n"
            + "  #&, #%&&&&&&%(((/*****((((((##.  #(\n"
            + "  ##    #&&&&&&&&&&%%%%%%###%%(    (*\n"
            + "  .#/     /%%%%%%%%%%%%%%%%#       //\n"
            + "   #(     /##            *#,       (/\n"
            + "   %*     /#(             #,       //\n"
            + "   %(/.    %(            .%&      //*/\n"
            + "   %/,&,   &(            #%/      (#//,\n"
            + "   /(*     ##*           #%       &*#%\n"
            + "     @/@*  (#*.          #(.      /%%\n"
            + "      (/(/##(...........##(//&#(&/.\n"
            + "     (@%(&@@,#,..........,*@@&&@&*,\n";
    private static final String horizontalLine = "==========================================";
    private final Scanner scanner;

    Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     *  Returns the user command input.
     * @return the user command as a String.
     * @throws MikeException if user command input is empty.
     */
    public String scanInput() throws MikeException {
        try {
            return scanner.nextLine().strip();
        } catch (NoSuchElementException e) {
            throw new MikeException("That is the weirdest thing you've ever said.");
        }
    }

    /**
     * Displays the object to the user.
     * @param object to be displayed.
     */
    public static void display(Object object) {
        System.out.println(object);
    }

    /**
     * Displays errorMessage to the user.
     * @param errorMessage to be displayed.
     */
    public static void displayError(String errorMessage) {
        display(errorMessage);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void displayWelcome() {
        displayLogo();
        displayGreeting();
        displayLine();
    }

    /**
     * Prints the Wazowski logo as ASCII art.
     */
    private void displayLogo() {
        display(logo);
    }

    private void displayGreeting() {
        String greeting =
                " Hello! I'm mike WAZOWSKI.\n"
                + " What can I do for you?";
        display(greeting);
    }

    /**
     * Displays a horizontal line to the user.
     */
    public static void displayLine() {
        display(horizontalLine);
    }

    public void close() {
        scanner.close();
    }

}
