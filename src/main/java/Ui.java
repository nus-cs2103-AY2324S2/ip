import java.util.NoSuchElementException;
import java.util.Scanner;
public class Ui {
    private final Scanner scanner;
    Ui() {
        scanner = new Scanner(System.in);
    }

    public String scanInput() throws MikeException {
        try {
            return scanner.nextLine().strip();
        } catch (NoSuchElementException e) {
            throw new MikeException("That is the weirdest thing you've ever said.");
        }
    }

    public static void display(Object object) {
        System.out.println(object);
    }

    public static void displayError(String errorMessage) {
        display(errorMessage);
    }
    public void displayWelcome() {
        displayLogo();
        displayGreeting();
        displayLine();
    }

    /**
     * Displays a farewell message with Wazowski pizazz.
     */
    public static void farewell() {
        String farewell =
                " Where are you going? We'll talk.\n" +
                        " We'll have a latte.";
        display(farewell);
    }

    /**
     * Prints the Wazowski logo as ASCII art.
     */
    private void displayLogo() {
        String logo =
                "                   .\n" +
                "          &#  #*&/,,(@%*. (#\n" +
                "           %#((,.      #%,*,\n" +
                "         %%%(/,.. *(     /,.**\n" +
                "      .%%%%#/**,%(@@&@    /.,,/\n" +
                "      %%%%#(((/*(#*(%&    ..,,,*(\n" +
                "    *#%%%##(/,#(/**,,...,...,,,**/\n" +
                "   .#%%%%%##(((//#(//*......,,,**//\n" +
                "   &#%%%%%%###(///*,,..,,,,,,,***/#*\n" +
                "   &#%%%%%%#####@@@@@@(,,**,****/(#*\n" +
                "  *&%#%%&&&%%%@@@@@@@@@@&**/////(.&/*\n" +
                "  #&, #%&&&&&&%(((/*****((((((##.  #(\n" +
                "  ##    #&&&&&&&&&&%%%%%%###%%(    (*\n" +
                "  .#/     /%%%%%%%%%%%%%%%%#       //\n" +
                "   #(     /##            *#,       (/\n" +
                "   %*     /#(             #,       //\n" +
                "   %(/.    %(            .%&      //*/\n" +
                "   %/,&,   &(            #%/      (#//,\n" +
                "   /(*     ##*           #%       &*#%\n" +
                "     @/@*  (#*.          #(.      /%%\n" +
                "      (/(/##(...........##(//&#(&/.\n" +
                "     (@%(&@@,#,..........,*@@&&@&*,\n";
        display(logo);
    }

    private void displayGreeting() {
        String greeting =
                " Hello! I'm Mike WAZOWSKI.\n" +
                " What can I do for you?";
        display(greeting);
    }

    public static void displayLine() {
        String horizontalLine = "==========================================";
        display(horizontalLine);
    }

}
