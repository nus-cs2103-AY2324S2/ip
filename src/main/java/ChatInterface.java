import java.util.Scanner;

public class ChatInterface {

    private final Osiris chatBot = new Osiris();

    public void startChat(){
        Scanner scanner = new Scanner(System.in);
        boolean terminateChat = false;

        this.printSeparator();
        chatBot.outputIntroductions();
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
        chatBot.outputGoodbyes();
        this.printSeparator();
    }

    private void echoUserInput(String input) {
        this.printSeparator();
        System.out.println("     " + input);
        this.printSeparator();
    }
    private void printSeparator() {
        System.out.println("----------------------------------------");
    }
}
