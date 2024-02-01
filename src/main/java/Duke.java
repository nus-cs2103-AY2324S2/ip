import helperpackage.Deadline;
import helperpackage.DukeException;
import helperpackage.Event;
import helperpackage.Task;
import helperpackage.ToDo;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________________");

        System.out.println("Hello! I'm NextGenerationJarvis.");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        /** Used to store Tasks */
        ArrayList<Task> taskList = new ArrayList<>();

        // loop only exits if input is "bye"
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println("\n________________________________________");

            // Level-2: if the input is "list"
            if (userInput.toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 1; i <= taskList.size(); i++) {
                    Task t = taskList.get(i - 1);
                    System.out.println(i + ". " + t.toString());
                }

            } else {
                StringTokenizer st = new StringTokenizer(userInput);
                String cmd = st.nextToken().toLowerCase();

                // Level-3: mark & unmark
                if (cmd.equals("mark") || (cmd.equals("unmark"))) {
                    try {
                        changeStatus(taskList, cmd, st);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found. :(");
    
                    } catch (NumberFormatException e) {
                        System.out.println("Input is not an integer. :(");
    
                    } catch (NoSuchElementException e) {
                        System.out.println("Missing task number. :(");
                    }
                    
                // Level-4: ToDo, Deadline, Event
                } else if (cmd.equals("todo") || cmd.equals("event") || cmd.equals("deadline")) {
                    try {
                        addTask(taskList, cmd, userInput);

                    } catch (DukeException e) {
                        System.out.println(e.getMessage());

                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Invalid input. :(");
                    }

                // Level-5: Throw exception for other inputs
                } else {
                    try {
                        throw new DukeException("OOPS!! Pls try again. :)");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("________________________________________\n");
            userInput = scanner.nextLine();
        }    

        System.out.println("\n________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________\n");

        scanner.close();
    }

    // Level-3: mark & unmark
    public static void changeStatus(ArrayList<Task> taskList, String cmd, StringTokenizer st) throws IndexOutOfBoundsException,
            NumberFormatException, NoSuchElementException {
        
        int index = Integer.parseInt(st.nextToken());
        Task t = taskList.get(index - 1);

        if (cmd.equals("mark")) {
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        
        } else if (cmd.equals("unmark")) {
            t.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(" " + t.toString());
    }

    // Level-4: ToDo, Deadline, Event
    public static void addTask(ArrayList<Task> taskList, String cmd, String userInput) throws DukeException {
        int firstSpaceIndex = userInput.indexOf(" ");
        String description = userInput.substring(firstSpaceIndex + 1);
        Task t = new Task(" ");

        if (cmd.equals("todo")) {
            description = description.strip();
            if (description.equals("") || firstSpaceIndex == -1) {
                throw new DukeException("Invalid todo input. :(");
            } else {
                t = new ToDo(description);
            }

        } else if (cmd.equals("deadline")) {
            t = new Deadline(description);

        } else if (cmd.equals("event")) {
            t = new Event(description);
        }

        taskList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
