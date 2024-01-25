import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        //Chatbot greets user
        System.out.println("Hello! I'm Chatteroo\n" + "What can I do for you?\n");

        //Chatbot stores user commands in a fixed array
        String[] listStore = new String[100];
        int listCount = 0;

        //Chatbot echos user commands
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            // print list of stored tasks if user inputs "list"
            if (input.equals("list")) {
                for (int i = 0; i < listCount; i++) {
                    int bulletNum = i + 1;
                    System.out.println(bulletNum + ". " + listStore[i]);
                }
                System.out.println();
            } else { // add user inputs to list
                listStore[listCount] = input;
                listCount++;
                System.out.println("added: " + input + "\n");
            }
//            System.out.println("added: " + input + "\n");
            input = sc.nextLine();
        }
        //Chatbot exits
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
