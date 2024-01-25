import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Homie");
        System.out.println("What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();  // Read user command
        ArrayList<Task> myList = new ArrayList<>(); // Create an ArrayList object

        int index = 1;
        String[] tempArr;
        Task currTask;

        while (!command.equals("bye")) {

            if (command.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (Task s : myList) {
                    System.out.println(index + "." + s);
                    index++;
                }
                System.out.println(line + "\n");
                index = 1; // Reset index to 1
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                tempArr = command.split(" ");
                switch (tempArr[0]) {
                    case ("todo"):
                        System.out.println(line);
                        Todo currTodo = new Todo(command.substring(5));
                        myList.add(currTodo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(currTodo);
                        System.out.println("Now you have " + myList.size() + " tasks in the list.");
                        System.out.println(line);
                        break;

                    case ("deadline"):
                        System.out.println(line);
                        tempArr = (command.substring(9)).split("/");
                        Deadline currDeadline = new Deadline(tempArr[0], tempArr[1].substring(3));
                        myList.add(currDeadline);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(currDeadline);
                        System.out.println("Now you have " + myList.size() + " tasks in the list.");
                        System.out.println(line);
                        break;

                    case ("event"):
                        System.out.println(line);
                        tempArr = (command.substring(6)).split("/");
                        Event currEvent = new Event(tempArr[0], tempArr[1].substring(5), tempArr[2].substring(3));
                        myList.add(currEvent);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(currEvent);
                        System.out.println("Now you have " + myList.size() + " tasks in the list.");
                        System.out.println(line);
                        break;
                }
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.contains("mark")) {
                tempArr = command.split(" ");
                currTask = myList.get(Integer.parseInt(tempArr[1]) - 1);
                switch (tempArr[0]) {
                    case ("mark"):
                        System.out.println(line);
                        currTask.setDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(currTask);
                        System.out.println(line);
                        break;

                    case ("unmark"):
                        System.out.println(line);
                        currTask.setNotDone();
                        System.out.println("Ok, I've marked this task as not done yet:");
                        System.out.println(currTask);
                        System.out.println(line);
                        break;

                    default:
                        System.out.println("Wrong Command");
                }
                command = scanner.nextLine(); // Read next command
                continue;
            }

            myList.add(new Task(command)); // add command to list
            System.out.println(line + "\nadded: " + command + "\n" + line);  // Echo added
            command = scanner.nextLine(); // Read next command
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
