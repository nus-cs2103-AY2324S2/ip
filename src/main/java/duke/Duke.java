package duke;

import java.nio.file.Paths;
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;  // Import the Scanner class
import java.nio.file.Path;

public class Duke {
    public static String currentDir = System.getProperty("user.dir");
    public static final Path filePath = Paths.get(currentDir, "src", "main", "java", "duke", "data", "data.txt");

    public static void main(String[] args){
        Storage storageFile = new Storage(filePath);
        ArrayList<Task> myList = new ArrayList<>(); // Create an ArrayList object

        if (storageFile.isFileCreated()) {
            myList = storageFile.loadTasksFromFile();
        } else {
            storageFile.createStorageFile();
        }

        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Homie");
        System.out.println("What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();  // Read user command

        int index = 1;
        String[] tempArr;
        Task currTask;

        while (!command.equals("bye")) {

            try {
                Duke.checkCommand(command);
            }
            catch (DukeException e) {
                System.err.println(e.getMessage());
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (Task s : myList) {
                    System.out.println(index + "." + s);
                    index++;
                }
                System.out.println(line);
                index = 1; // Reset index to 1
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.startsWith("delete")) {
                tempArr = command.split(" ");
                currTask = myList.get(Integer.parseInt(tempArr[1]) - 1);
                myList.remove(Integer.parseInt(tempArr[1]) - 1);
                System.out.println(line);
                System.out.println("Noted. I've removed this task:");
                System.out.println(currTask);
                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                System.out.println(line);
                storageFile.updateStorageFile(myList);
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                tempArr = command.split(" ");
                switch (tempArr[0]) {
                    case ("todo"):
                        try {
                            System.out.println(line);
                            Todo currTodo = new Todo(command.substring(5));
                            myList.add(currTodo);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(currTodo);
                            System.out.println("Now you have " + myList.size() + " tasks in the list.");
                            System.out.println(line);
                            storageFile.updateStorageFile(myList);
                        } catch (TodoException e) {
                            System.err.println(e.getMessage());
                        }
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
                        storageFile.updateStorageFile(myList);
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
                        storageFile.updateStorageFile(myList);
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
                        storageFile.updateStorageFile(myList);
                        break;

                    case ("unmark"):
                        System.out.println(line);
                        currTask.setNotDone();
                        System.out.println("Ok, I've marked this task as not done yet:");
                        System.out.println(currTask);
                        System.out.println(line);
                        storageFile.updateStorageFile(myList);
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

    public static void checkCommand (String command) throws DukeException {
        String line = "____________________________________________________________";
        if (!(command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event") || command.startsWith("list") || command.startsWith("bye") || command.startsWith("delete"))) {
            throw new DukeException("\n" + line + "\nOPPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
        }
    }

}