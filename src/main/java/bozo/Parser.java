package bozo;

/**
 * Represents a parser that interprets user input and executes the corresponding commands.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding commands.
     *
     * @param input The user input.
     * @param list The list of tasks.
     * @throws BozoException If the user input is invalid.
     */
    public String parseCommand(String input, TaskList list) throws BozoException {
        StringBuilder output = new StringBuilder();

        assert list != null : "List should not be null";
        assert input != null : "Input should not be null";
        if (input.equals("bye")) {
            output.append("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            // Display the list of tasks
            Ui.showLine();
            System.out.println("Here are the tasks in your list:\n");
            output.append("Here are the tasks in your list:\n");
            if (list.getSize() == 0) {
                System.out.println("No tasks! You're a free man! :DD");
                output.append("No tasks! You're a free man! :DD");
            } else {
                int counter = 1;
                for (Task task : list) {
                    System.out.println(counter + ". " + task.toString());
                    output.append(counter + ". " + task.toString()).append("\n");
                    counter++;
                }
            }
            Ui.showLine();
        } else if (input.startsWith("mark")) {
            // Mark a task as done
            Ui.showLine();
            String taskStr = input.substring(input.indexOf(" ") + 1);
            int taskNum = Integer.parseInt(taskStr);
            if (taskNum > 0 && taskNum < list.getSize() + 1) {
                Task t = list.getTask(taskNum - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + t);
                output.append("Nice! I've marked this task as done:\n");
                output.append("  " + t);
                Ui.showLine();
            } else {
                output.append("Error: bozo.Task does not exist!");
                throw new BozoException("Error: bozo.Task does not exist!");
            }
        } else if (input.startsWith("find")) {
            // Find a task
            Ui.showLine();
            String keyword = input.substring(input.indexOf(" ") + 1);
            int counter = 1;
            System.out.println("Here are the matching tasks in your list:");
            output.append("Here are the matching tasks in your list:\n");
            for (Task task : list) {
                if (task.toString().contains(keyword)) {
                    System.out.println(counter + ". " + task.toString());
                    output.append(counter + ". " + task.toString()).append("\n");
                    counter++;
                }
            }
            if (counter == 1) {
                System.out.println("No matching tasks! :O");
                output.append("No matching tasks! :O");
            }
            Ui.showLine();
        } else if (input.startsWith("unmark")) {
            // Mark a task as not done
            Ui.showLine();
            String taskStr = input.substring(input.indexOf(" ") + 1);
            int taskNum = Integer.parseInt(taskStr);
            if (taskNum > 0 && taskNum < list.getSize() + 1) {
                Task t = list.getTask(taskNum - 1);
                t.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + t);
                output.append("OK, I've marked this task as not done yet:\n");
                output.append("  " + t);
                Ui.showLine();
            } else {
                output.append("Error: bozo.Task does not exist!");
                throw new BozoException("Error: bozo.Task does not exist!");
            }
        } else if (input.startsWith("delete")) {
            // Remove a task
            Ui.showLine();
            if (list.getSize() == 0) {
                System.out.println("You have no tasks to delete! :O");
                output.append("You have no tasks to delete! :O");
            } else {
                String taskStr = input.substring(input.indexOf(" ") + 1);
                int taskNum = Integer.parseInt(taskStr);
                Task t = list.removeTask(taskNum - 1);
                System.out.println("Noted: I've removed this task:");
                System.out.println("  " + t);
                output.append("Noted: I've removed this task:\n");
                output.append("  " + t);
            }
            Ui.showLine();
        } else {
            // Add a task
            Ui.showLine();
            if (input.startsWith("todo")) {
                if (input.length() < 6) {
                    output.append("I want that too but ur todo can't be empty :-((");
                    throw new BozoException("I want that too but ur todo can't be empty :-((");
                } else {
                    Todo td = new Todo(input.substring(input.indexOf(" ") + 1));
                    list.addTask(td);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + td);
                    System.out.println("Now you have " + list.getSize() + " tasks in the list.");
                    output.append("Got it. I've added this task:\n");
                    output.append("  " + td).append("\n");
                    output.append("Now you have " + list.getSize() + " tasks in the list.");
                }
            } else if (input.startsWith("deadline")) {
                if (input.length() < 10) {
                    output.append("I want that too but ur deadline can't be empty :-((");
                    throw new BozoException("I want that too but ur deadline can't be empty :-((");
                } else {
                    int i = input.indexOf("/by");
                    Deadline d = new Deadline(input.substring(input.indexOf(" ") + 1, i - 1),
                              input.substring(i + 4));
                    list.addTask(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + d);
                    System.out.println("Now you have " + list.getSize() + " tasks in the list.");
                    output.append("Got it. I've added this task:\n");
                    output.append("  " + d).append("\n");
                    output.append("Now you have " + list.getSize() + " tasks in the list.");
                }
            } else if (input.startsWith("event")) {
                if (input.length() < 7) {
                    output.append("I want that too but ur event can't be empty :-((");
                    throw new BozoException("I want that too but ur event can't be empty :-((");
                } else {
                    int indexOfFrom = input.indexOf("/from");
                    int indexOfTo = input.indexOf("/to");
                    Event e = new Event(input.substring(input.indexOf(" ") + 1, indexOfFrom - 1),
                            input.substring(indexOfFrom + 6, indexOfTo - 1),
                            input.substring(indexOfTo + 4));
                    list.addTask(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e);
                    System.out.println("Now you have " + list.getSize() + " tasks in the list.");
                    output.append("Got it. I've added this task:\n");
                    output.append("  " + e).append("\n");
                    output.append("Now you have " + list.getSize() + " tasks in the list.");
                }
            } else {
                output.append("Oops! I don't know what you are saying :(");
                throw new BozoException("Oops! I don't know what you are saying :(");
            }
            Ui.showLine();
        }
        return output.toString();
    }
}
