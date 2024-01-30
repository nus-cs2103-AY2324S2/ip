package solaire.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scn;

    public Ui(Scanner scn) {
        this.scn = scn;
    }

    public String acceptInput() {
        String input = this.scn.nextLine().toLowerCase();
        return input;
    }

    public void greet() {
        String greetingMessage = "Oh hello there. I'm Solaire of Astora.\n"
                + "The sun is a wondrous body. Like a magnificent father!\n"
                + "If only I could be so grossly incandescent!\n";

        System.out.print(greetingMessage);
        lineBreak();
    }

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
