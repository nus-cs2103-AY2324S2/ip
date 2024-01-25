import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String Greeting = "____________________________________________________________\n" +
                " Hello! I'm VICTOR\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String Ending =
                "____________________________________________________________\n"+
                        " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        String barrier = "____________________________________________________________";
        Boolean exit = true;
        ArrayList<String> todoList = new ArrayList<String>();
        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        System.out.println(Greeting);
        while (exit) {
            Scanner myObj = new Scanner(System.in);
            String userInput = myObj.nextLine();
            String[] inputList = userInput.split(" ", 2);
            if (userInput.equals("bye")) {
                exit = false;
            } else if (userInput.equals("list")) {
                System.out.println(barrier);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    if (checkList.get(i)) {
                        System.out.println(i+1 + ".[X] " + todoList.get(i));
                    } else {
                        System.out.println(i+1 + ".[ ] " + todoList.get(i));
                    }
                }
                System.out.println(barrier);
            } else if (inputList[0].equals("mark")) {
                int position = Integer.parseInt(inputList[1]);
                System.out.println(barrier);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + todoList.get(position-1));
                checkList.set(position-1, true);
                System.out.println(barrier);
            } else if (inputList[0].equals("unmark")) {
                int position = Integer.parseInt(inputList[1]);
                System.out.println(barrier);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + todoList.get(position-1));
                checkList.set(position-1, false);
                System.out.println(barrier);
            } else if (inputList[0].equals("todo")) {
                todoList.add(userInput);
                checkList.add(false);
                System.out.println(barrier);
                System.out.println("added: " + userInput);
                System.out.println("Now you have " + todoList.size() + "in the list.");
                System.out.println(barrier);
                System.out.println(inputList[1]);
            } else if (inputList[0].equals("deadline")) {
                String[] differentParts = inputList[1].split("/");
                System.out.println(differentParts[0]);
                System.out.println(differentParts[1]);
                todoList.add(userInput);
                checkList.add(false);
                System.out.println(barrier);
                System.out.println("added: " + userInput);
                System.out.println("Now you have " + todoList.size() + "in the list.");
                System.out.println(barrier);
            } else if (inputList[0].equals("event")) {
                String[] differentParts = inputList[1].split("/");
                System.out.println(differentParts[0]);
                System.out.println(differentParts[1]);
                System.out.println(differentParts[2]);
                todoList.add(userInput);
                checkList.add(false);
                System.out.println(barrier);
                System.out.println("added: " + userInput);
                System.out.println("Now you have " + todoList.size() + "in the list.");
                System.out.println(barrier);
            }
        }
        System.out.println(Ending);

    }
}
