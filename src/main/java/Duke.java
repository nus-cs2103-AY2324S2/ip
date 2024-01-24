import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Hao Wen\n" + "What can I do for you?");
        //System.out.println("Bye. Hope to see you again soon!");

        MyList myList = new MyList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String userInput = br.readLine();
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }

                if (userInput.equals("list")) {
                    System.out.println(myList.getItems());
                } else {
                    System.out.println(myList.addItem(userInput));
                }
            }
            System.out.println("Bye. Hope to see you again soon!");

        } catch (IOException e) {
            System.err.println("Error");
            //e.printStackTrace();
        }
    }
}
