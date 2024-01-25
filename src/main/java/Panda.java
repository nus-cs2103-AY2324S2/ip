import java.util.Scanner;

public class Panda {

    private static boolean running = false;

    private static void startUp() {
        System.out.println(
            "Hello! I'm Panda\n" + 
            "What can I do for you?"
        );
        running = true;
    }

    private static void shutDown() {
        System.out.println(
            "Bye. Hope to see you again soon!"
        );
        running = true;
    }

    private static void comm() {
        while(running) {
            Scanner myObj  = new Scanner(System.in);
            System.out.print("> ");
            String userInput = myObj.nextLine();
            if(userInput.equals("bye")) {
                running = false;
                myObj.close();
            }
            else {
                System.out.println(userInput);
            }
        }
    }

    public static void main(String[] args) {
        Panda.startUp();
        Panda.comm();
        Panda.shutDown();
    }
}
