import java.util.ArrayList;

public class CommandHandler {
    public static void handleCommand(String userInput, ArrayList<Task> tasks, int taskCounter) throws DukeException {
        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        } else if (userInput.equalsIgnoreCase("list")) {
            listTasks(tasks);
        } else if (userInput.startsWith("mark")) {
            markTaskAsDone(userInput, tasks);
        } else if (userInput.startsWith("unmark")) {
            markTaskAsNotDone(userInput, tasks);
        } else if (userInput.startsWith("todo")) {
            // addTodoTask(userInput, tasks, taskCounter);
            if (userInput.length() <= 5) {
                throw new DukeException("Umm... The todo command is incomplete!");
            }
            String description = userInput.substring(5);
            ToDo.addToDoTask(tasks, taskCounter, description);
        } else if (userInput.startsWith("deadline")) {
            if (userInput.length() <= 9) {
                throw new DukeException("Oops! The deadline command is incomplete.");
            }

            String[] description = userInput.substring(9).split(" /by ");
            if (description.length < 2) {
                throw new DukeException("Oops! Both description and deadline are required for a deadline task.");
            }

            String dueBy = description[1];
            Deadline.addDeadlineTask(tasks, taskCounter, description[0], dueBy);
        } else if (userInput.startsWith("event")) {

            if (userInput.length() <= 6) {
                throw new DukeException("Uh oh! The event command is incomplete.");
            }

            String[] description = userInput.substring(6).split(" /from | /to ");

            if (description.length < 3) {
                throw new DukeException("Uh oh! The event command requires both start and end details.");
            }

            String start = description[1];
            String end = description[2];
            Event.addEventTask(tasks, taskCounter, description[0], start, end);
        } else if (userInput.startsWith("delete")) {
            deleteTask(userInput, tasks);
        } else {
            throw new DukeException("I don't understand what you mean :c");
        }
    }

    private static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here " + (tasks.size() == 1 ? "is the task" : "are the tasks") + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).getStatusIcon());
            }
        }
    }

    private static void markTaskAsDone(String userInput, ArrayList<Task> tasks) throws DukeException {
        int index = getIndexFromUserInput(userInput, "mark") - 1;
        validateIndex(index, tasks);
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).getStatusIcon());
    }

    private static void markTaskAsNotDone(String userInput, ArrayList<Task> tasks) throws DukeException {
        int index = getIndexFromUserInput(userInput, "unmark") - 1;
        validateIndex(index, tasks);
        tasks.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).getStatusIcon());
    }

    private static void deleteTask(String userInput, ArrayList<Task> tasks) throws DukeException {
        int index = getIndexFromUserInput(userInput, "delete") - 1;
        validateIndex(index, tasks);
        Task removedTask = tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.getStatusIcon());
        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() <= 1 ? "task" : "tasks") + " in the list.");
    }

    private static int getIndexFromUserInput(String userInput, String command) throws DukeException {
        try {
            return Integer.parseInt(userInput.substring(command.length()).trim());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Invalid " + command + " command. Please provide a valid task number.");
        }
    }

    private static void validateIndex(int index, ArrayList<Task> tasks) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task not found. Please provide a valid task number.");
        }
    }

}
