import java.util.Scanner;

public class Solaire {

    
    public void startConversation() {
        greet();

        while(true) {
            String input = acceptInput();
            
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        waveBye();
    }

    private void greet() {
        String greetingMessage = "--------------------------------------------------\n"
        + "Oh hello there. I'm Solaire of Astora.\n"
        + "The sun is a wondrous body. Like a magnificent father!\n"
        + "If only I could be so grossly incandescent!\n"
        + "--------------------------------------------------\n";

        System.out.println(greetingMessage);
    }

    private void waveBye() {
        String farewellMessage = "Farewell!" +
        "--------------------------------------------------\n";
        System.out.println(farewellMessage);
    }

    private String acceptInput() {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine().toLowerCase();

        return input;
    }


}
