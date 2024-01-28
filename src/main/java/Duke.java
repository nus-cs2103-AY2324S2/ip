import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * The Duke class represent a simple task management program.
 * Users can add, mark as done, unmark, list, delete and exit tasks
 * Supports three types of tasks: Todo, Deadline, and Event
 * Provides a command-line interface for user interaction
 * 
 * @author Kailin Teo
 * 
 */

public class Duke {
    public static void main(String[] args) {
<<<<<<< HEAD

        // Displaying Duke logo and initial message
=======
          // Displaying Duke logo and initial message
>>>>>>> branch-Level-7
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("What can I do for you?");

        // Create an ArrayList to store tasks
<<<<<<< HEAD
        //ArrayList<Task> myList = new ArrayList<>();
        ArrayList<Task> myList = SaveTask.loadTasks();
        
        if (myList == null) {
            myList = new ArrayList<>();
        }

        // Initialize Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.print(" ");

        // Processing loop based on the Command
        while (true) {
            // Read user input
=======
        ArrayList<Task> myList = SaveTask.loadTasks();

        if (myList == null) {
            myList = new ArrayList<>();
        }

         // Initialize Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.print(" ");

          // Processing loop based on the Command
        while (true) {
             // Read user input
>>>>>>> branch-Level-7
            String input = sc.nextLine();

            // Split input into "command" and "parameters"
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String restOfInputs = parts.length > 1 ? parts[1] : "";

<<<<<<< HEAD
            // Processing different commands
            if (command.equals("mark")) {
                /*
=======
             // Processing different commands
            if (command.equals("mark")) {
                  /*
>>>>>>> branch-Level-7
                * Marks a specified task as done
                * 
                * @param taskNUmver the index of the task to be marked as done
                * @throws INdexOutOfBoundsException if the task numr is out of bounds
                */

                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsDone();

                    System.out.println("------------------------------------------------------------");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(task);
                    System.out.println("------------------------------------------------------------");

                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("unmark")) {
<<<<<<< HEAD
                /*
=======
                 /*
>>>>>>> branch-Level-7
                 * Marks a specified task as not done
                 * 
                 * @param task number the index of the task to be marked as not done
                 * @throws IndexOutOfBoundsException if the task number is out of bounds
                 */

                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsNotDone();

                    System.out.println("------------------------------------------------------------");
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    System.out.println(task);
                    System.out.println("------------------------------------------------------------");

                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("list")) {
<<<<<<< HEAD
                /*
                 * Displays the list of tasks in the MyList
                 */

=======
                 /*
                 * Displays the list of tasks in the MyList
                 */
                
>>>>>>> branch-Level-7
                System.out.println("------------------------------------------------------------");
                System.out.println("Here are the tasks in your list: ");

                for (Task task : myList) {
                    System.out.println((myList.indexOf(task) + 1) + "." + task);
                }
                System.out.println("------------------------------------------------------------");

            } else if (command.equals("bye")) {
                /*
<<<<<<< HEAD
                 * Save the new task in MyList before exitting the Duke Program
                 */
=======
                * Save the new task in MyList before exitting the Duke Program
                */
>>>>>>> branch-Level-7
                
                SaveTask.saveTasks(myList);
                break;

            } else if (command.equals("delete")) {
<<<<<<< HEAD
                /*
=======
                /**
>>>>>>> branch-Level-7
                 * Deletes a specified task from the list
                 * 
                 * @param task numer the index of the task to be deleted
                 * @throws INdexOutOfBoundsException if the task number is out of bounds
<<<<<<< HEAD
                 */
=======
                 **/
>>>>>>> branch-Level-7

                int removed_item = Integer.parseInt(parts[1]) - 1;

                System.out.println("------------------------------------------------------------");
                System.out.println("Noted. I've removed this task: ");
                System.out.println(myList.get(removed_item));

                myList.remove(removed_item);

                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                System.out.println("------------------------------------------------------------");

            } else {
                
                try {
                    // Todo is called
                    if (command.equals("todo")) {
<<<<<<< HEAD
                        /*
=======
                        /**
>>>>>>> branch-Level-7
                         * Adds a Todo task to the list.
                         * 
                         * @param item The description of the Todo task
                         * @throws DukeException if the description is empty
<<<<<<< HEAD
                         */
=======
                         **/
>>>>>>> branch-Level-7

                        String item = restOfInputs;
                        try {
                            if (item.isEmpty()) {
                                
                                throw new DukeException("Todo cannot be empty, please indicate what you plan to do.");

                            } else {

                                Todo newTodo = new Todo(item);
                                myList.add(newTodo);

                                System.out.println("------------------------------------------------------------");
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(newTodo);
                                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                System.out.println("------------------------------------------------------------");
                            }
                        } catch (DukeException e) {
                            System.out.println("------------------------------------------------------------");
                            System.out.println(e.getMessage());
                            System.out.println("------------------------------------------------------------");
                        }
                    }

                    // Deadline is called
                    else if (command.equals("deadline")) {
<<<<<<< HEAD
                        /*
=======
                        /**
>>>>>>> branch-Level-7
                         * Adds a Deadline task to the list
                         * 
                         * @param item The description of the deadline task
                         * @param time The due date and time of the deadline task
<<<<<<< HEAD
                         */
                        String item = restOfInputs;
                        try {
                            if (item.isEmpty()) {
                                
                                throw new DukeException("Deadline cannot be empty, please indicate the deadline of the plan");

                            } else {

=======
                         **/

                        String item = restOfInputs;
                        try {
                            if (item.isEmpty()) {
                                
                                throw new DukeException("Deadline cannot be empty! please indicate the deadline of the task");

                            } else {
>>>>>>> branch-Level-7
                                String[] item_time = restOfInputs.split("/by");
                                String items = item_time[0];
                                String time = item_time[1];
        
                                Deadline newDeadline = new Deadline(items, time);
                                myList.add(newDeadline);
                                System.out.println("------------------------------------------------------------");
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(newDeadline);
                                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                System.out.println("------------------------------------------------------------");
                            }
<<<<<<< HEAD

=======
>>>>>>> branch-Level-7
                        } catch (DukeException e) {
                            System.out.println("------------------------------------------------------------");
                            System.out.println(e.getMessage());
                            System.out.println("------------------------------------------------------------");
                        }
                    }

                    // Event is called
                    else if (command.equals("event")) {
<<<<<<< HEAD
                        /*
=======
                        /**
>>>>>>> branch-Level-7
                         * Adds an Event task to the list
                         * 
                         * @param item The description of the Event task
                         * @param from The start time of the Event task
                         * @para to The end time of the Event task
                         */

                        String item = restOfInputs;
                        try {
                            if (item.isEmpty()) {
                                
<<<<<<< HEAD
                                throw new DukeException("Event cannot be empty, please indicate the plan, start and end time!");

                            } else {

                                String[] item_time = restOfInputs.split("/from");
                                String items = item_time[0];
                                String time = item_time[1];
        
                                String[] from_to = time.split("/to");
                                String from = from_to[0];
                                String to = from_to[1];
        
                                Event newEvent = new Event(items, from, to);
                                myList.add(newEvent);
        
                                System.out.println("------------------------------------------------------------");
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(newEvent);
                                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                System.out.println("------------------------------------------------------------");
                            }

=======
                                throw new DukeException("Event cannot be empty! please indicate the task, start and end time");

                            } else {
                                String[] item_time = restOfInputs.split("/from");
                                String items = item_time[0];
                                String time = item_time[1];
        
                                String[] from_to = time.split("/to");
                                String from = from_to[0];
                                String to = from_to[1];
        
                                Event newEvent = new Event(items, from, to);
                                myList.add(newEvent);
        
                                System.out.println("------------------------------------------------------------");
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(newEvent);
                                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                System.out.println("------------------------------------------------------------");
                            }

>>>>>>> branch-Level-7
                        } catch (DukeException e) {
                            System.out.println("------------------------------------------------------------");
                            System.out.println(e.getMessage());
                            System.out.println("------------------------------------------------------------");
                        }
<<<<<<< HEAD

                    }

                    else {
                        /*
=======
                    }

                    else {
                        /**
>>>>>>> branch-Level-7
                         * Handle unknow commands and throws an exception
                         * 
                         * @throws DukeException if the command is not recognized
                         */
<<<<<<< HEAD
=======

>>>>>>> branch-Level-7
                        throw new DukeException("SORRY! but are you sure you enter the correct command? please check!");
                    }

                } catch (DukeException e) {
<<<<<<< HEAD
                    /*
=======
                    /**
>>>>>>> branch-Level-7
                     * Handles Duke-specific exceptions and prints an error message
                     * 
                     * @param e The DukeException object
                     */

                    System.out.println("------------------------------------------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("------------------------------------------------------------");
                }
            }

            System.out.print(" ");
        }
<<<<<<< HEAD
        /*
=======
        /**
>>>>>>> branch-Level-7
         * Final message before exiting
         */

        System.out.println("------------------------------------------------------------");
        System.out.println("Bye! Hope to see you again!!");
    }
}

