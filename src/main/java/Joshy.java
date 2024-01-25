import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Joshy {
    public static void main(String[] args) {

        // instantiate new task-list
        List<Task> taskList = new ArrayList<>();

        // Introduction
        System.out.println("   ______________________________________________");
        System.out.println("   Hello! I'm Joshy");
        System.out.println("   What can I do for you?");
        System.out.println("   ______________________________________________");

        // Read the text entered by user line by line
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // runs the program until user inputs "bye"
        while (!Objects.equals(input, "bye")) {
            System.out.println("   ______________________________________________");

            // delimited using " "
            String[] tokens = input.split(" ", 2);
            String command = tokens[0];

            switch (command) {

                // list out items in task-list
                case "list":
                    System.out.println("   Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task currTask = taskList.get(i);
                        String currDesc = currTask.getStatus();
                        System.out.println("   " + (i + 1) + ". " + currDesc);
                    }
                    break;

                // mark a task
                case "mark": {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    Task currTask = taskList.get(index);
                    currTask.markTask();

                    System.out.println("   Nice! I've marked this task as done:");
                    System.out.println("      " + currTask.getStatus());
                    break;
                }

                // unmark a task
                case "unmark": {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    Task currTask = taskList.get(index);
                    currTask.unmarkTask();

                    System.out.println("   OK, I've marked this task as not done yet:");
                    System.out.println("      " + currTask.getStatus());
                    break;
                }

                // create new to-do task
                case "todo": {
                    String description = tokens[1];
                    Task newTodoTask = new Todo(description);
                    taskList.add(newTodoTask);

                    System.out.println("   Got it. I've added this task:");
                    System.out.println("      " + newTodoTask.getStatus());
                    System.out.println("   Now you have " + taskList.size() + " tasks in the list.");
                    break;
                }

                // create new deadline task
                case "deadline": {
                    // delimiting string
                    String information = tokens[1];
                    String[] descTokens = information.split(" /by ");
                    String description = descTokens[0];
                    String endDate = descTokens[1];

                    // create new task
                    Task newDeadlineTask = new Deadline(description, endDate);
                    taskList.add(newDeadlineTask);

                    System.out.println("   Got it. I've added this task:");
                    System.out.println("      " + newDeadlineTask.getStatus());
                    System.out.println("   Now you have " + taskList.size() + " tasks in the list.");
                    break;
                }

                // create new event task
                case "event": {
                    // delimiting string
                    String information = tokens[1];
                    String[] descTokens = information.split(" /from ");
                    String description = descTokens[0];
                    String dates = descTokens[1];
                    String[] dateTokens = dates.split(" /to ");
                    String startDate = dateTokens[0];
                    String endDate = dateTokens[1];

                    // create new task
                    Task newEventTask = new Event(description, startDate, endDate);
                    taskList.add(newEventTask);

                    System.out.println("   Got it. I've added this task:");
                    System.out.println("      " + newEventTask.getStatus());
                    System.out.println("   Now you have " + taskList.size() + " tasks in the list.");
                    break;
                }

                // add general task to task-list
                default:
                    Task newTask = new Task(input);
                    taskList.add(newTask);

                    System.out.println("   added: " + input);
                    break;
            }

            System.out.println("   ______________________________________________");

            // scan next line
            input = sc.nextLine();
        }

        // Bye message
        System.out.println("   ______________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("   ______________________________________________");
    }
}
