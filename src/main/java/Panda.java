import java.util.Scanner;

public class Panda {

    private static boolean running = false;
    private static Task[] tlist;
    private static int idx = 0;

    private static void startUp() {
        System.out.println(
            "Hello! I'm Panda\n" + 
            "What can I do for you?"
        );
        running = true;
        tlist = new Task[100];
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
        Scanner myObj  = new Scanner(System.in);
        while(running) {
            System.out.print("> ");
            String userInput = myObj.nextLine();
            if(userInput.equals("bye")) {
                running = false;
                continue;
            }
            if(userInput.equals(("list"))) {
                Panda.printTlist();
                continue;
            }
            if(userInput.length() > 5 && userInput.substring(0, 5).equals("mark ")) {
                int target = Integer.parseInt(userInput.substring(5)) - 1;
                tlist[target].mark();
                System.out.println("Nice! I've marked this task as done:\n  " + tlist[target]);
                continue;
            }
            if(userInput.length() > 7 && userInput.substring(0, 7).equals("unmark ")) {
                int target = Integer.parseInt(userInput.substring(7)) - 1;
                tlist[target].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tlist[target]);
                continue;
            }
            tlist[idx] = new Task(userInput);
            idx = idx + 1;
            System.out.println("added: " + userInput);
        }
        myObj.close();
    }

    public static void main(String[] args) {
        Panda.startUp();
        Panda.comm();
        Panda.shutDown();
    }
}
