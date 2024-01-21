import java.util.Scanner;

public class Program {

    private final String NAME = "Linus";
    private final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private Boolean running;
    private Scanner userInputScanner;

    public Program() {
        this.running = true;
        this.userInputScanner = new Scanner(System.in);
    }

    public void start() {
        this.greeting();
        
        while (this.running) {
            String userInput = this.userInputScanner.nextLine();
            this.readUserInput(userInput);
        }

    }

    private void readUserInput(String input) {
        if (input.toLowerCase().equals("bye")) {
            this.end();
        } else {
            this.textFormat(input);
        }
    }

    private void greeting() {
        String greeting = String.format("Hello I'm %s", this.NAME);
        String request = "What can I do for you?";
        this.textFormat(String.format("%s\n\t%s", greeting, request));
    }

    private void end() {
        String exit = "Goodbye. See you later!";
        this.textFormat(exit);
        this.running = false;
    }

    private void textFormat(String text) {
        System.out.println(String.format("\t%s\n\t%s\n\t%s", 
        this.BORDER,
        text, 
        this.BORDER));
    }
    
}
