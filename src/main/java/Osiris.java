import java.util.Scanner;

public class Osiris {

    public static final String NAME = "Osiris";

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
            } else {
                this.echoUserInput(userInput);
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
        System.out.print("     ");
        this.printSeparator();
        System.out.println("     " + input);
        System.out.print("     ");
        this.printSeparator();
    }

    private void printSeparator() {
        System.out.println("----------------------------------------");
    }

}
