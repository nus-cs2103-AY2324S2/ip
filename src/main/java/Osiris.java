import java.util.ArrayList;
import java.util.Scanner;

public class Osiris {

    public static final String NAME = "Osiris";

    private final UserInputs userInputs = new UserInputs();

    public void startChat(){
        Scanner scanner = new Scanner(System.in);
        boolean terminateChat = false;

        this.printSeparator();
        this.outputIntroductions();
        this.printSeparator();

        while (!terminateChat){

            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                terminateChat = true;
            } else if (userInput.equals("list")) {
                this.printUserInputs();
            } else {
                this.storeUserInput(userInput);
            }
        }

        this.printSeparator();
        this.outputGoodbyes();
        this.printSeparator();
    }

    public void outputIntroductions() {
        String introductions = String.format("Hello! I'm %s. \nWhat can I do for you?", Osiris.NAME);
        System.out.println(introductions);
    }

    public void outputGoodbyes() {
        String goodbyes = "Bye. Hope to see you again soon!";
        System.out.println(goodbyes);
    }

    private void echoUserInput(String input) {
        this.printSeparator();
        System.out.println("     " + input);
        this.printSeparator();
    }

    private void storeUserInput(String userInput) {
        this.userInputs.add(userInput);

        this.printSeparator();
        System.out.println("     Added: " + userInput);
        this.printSeparator();
    }

    private void printUserInputs(){
        ArrayList<String> toPrint = this.userInputs.getUserInputs();

        this.printSeparator();
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + toPrint.get(i));
        }
        this.printSeparator();
    }

    private void printSeparator() {
        System.out.println("----------------------------------------");
    }

}
