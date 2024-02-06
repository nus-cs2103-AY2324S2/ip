package solaire.ui;

import java.util.Scanner;

/**
 * Ui class to handle user interface.
 * It contains methods to greet the user and accept input from the user.
 */
public class Ui {
    private Scanner scn;

    public Ui(Scanner scn) {
        this.scn = scn;
    }

    /**
     * Accepts input from the user.
     *
     * @return String input from the user in lower case.
     */
    public String acceptInput() {
        String input = this.scn.nextLine().toLowerCase();
        return input;
    }

    /**
     * Greets the user with an intro message.
     */
    public void greet() {
        String greetingMessage =
                "Oh hello there. I'm Solaire of Astora.\n" + "The sun is a wondrous body. Like a " + "magnificent "
                        + "father!\n" + "If only I could be so grossly incandescent!\n";

        System.out.print(greetingMessage);
        lineBreak();
    }

    /**
     * Ends the chat session of Solaire.
     */
    public void waveBye() {
        String farewellMessage = "Farewell!\n";
        System.out.print(farewellMessage);
        lineBreak();
        scn.close();
    }

    private void lineBreak() {
        System.out.print("--------------------------------------------------\n");
    }
}
