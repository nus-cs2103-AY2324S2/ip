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
        ArrayList<Task> todoList = new ArrayList<Task>();
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
                    System.out.println(i+1 + ".[" + todoList.get(i).getStatusIcon() + "] " + todoList.get(i).getDes());
                }
                System.out.println(barrier);
            } else if (inputList[0].equals("mark")) {
                int position = Integer.parseInt(inputList[1]);
                System.out.println(barrier);
                System.out.println("Nice! I've marked this task as done:");
                todoList.get(position-1).markAsDone();
                System.out.println("[X] " + todoList.get(position-1).getDes());
                System.out.println(barrier);
            } else if (inputList[0].equals("unmark")) {
                int position = Integer.parseInt(inputList[1]);
                System.out.println(barrier);
                System.out.println("OK, I've marked this task as not done yet:");
                todoList.get(position-1).unmarkAsDone();
                System.out.println("[ ] " + todoList.get(position-1).getDes());
                System.out.println(barrier);
            } else if (inputList[0].equals("todo")) {
                Task userTask = new Task(inputList[1],"T");
                todoList.add(userTask);
                System.out.println(barrier);
                System.out.println("[T][ ] " + inputList[1]);
                System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                System.out.println(barrier);
                System.out.println(inputList[1]);
            } else if (inputList[0].equals("deadline")) {
                String[] differentParts = inputList[1].split("/");
                System.out.println(differentParts[0]);
                System.out.println(differentParts[1]);
                Task userTask = new Task(inputList[1],"D");
                todoList.add(userTask);
                System.out.println(barrier);
                System.out.println("[D][ ] " + differentParts[0]);
                System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                System.out.println(barrier);
            } else if (inputList[0].equals("event")) {
                String[] differentParts = inputList[1].split("/");
                System.out.println(differentParts[0]);
                System.out.println(differentParts[1]);
                System.out.println(differentParts[2]);
                Task userTask = new Task(inputList[1],"E");
                todoList.add(userTask);
                System.out.println(barrier);
                System.out.println("[E][ ] " + differentParts[0]);
                System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                System.out.println(barrier);
            }
            else {
                Task userTask = new Task(inputList[0] + " " + inputList[1],"E");
                todoList.add(userTask);
                System.out.println(barrier);
                System.out.println("added: " + inputList[0] + " " + inputList[1]);
                System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                System.out.println(barrier);
            }
        }
        System.out.println(Ending);

    }
}
