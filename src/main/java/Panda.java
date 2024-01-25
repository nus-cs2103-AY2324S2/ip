import java.util.Scanner;

public class Panda {

    private static boolean running = false;
    private static String[] tlist;
    private static int idx = 0;

    private static void startUp() {
        System.out.println(
            "Hello! I'm Panda\n" + 
            "What can I do for you?"
        );
        running = true;
        tlist = new String[100];
    }

    private static void shutDown() {
        System.out.println(
            "Bye. Hope to see you again soon!"
        );
    }

    private static void printTlist() {
        for(int i = 0; i < idx; i++) {
            System.out.println((i + 1) + ". " + tlist[i]);
        }
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
                if(userInput.equals(("list"))) {
                    Panda.printTlist();
                }
                else {
                    tlist[idx] = userInput;
                    idx = idx + 1;
                    System.out.println("added: " + userInput);
                }
            }
        }
    }

    public static void main(String[] args) {
        Panda.startUp();
        Panda.comm();
        Panda.shutDown();
    }
}
