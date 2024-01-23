import exception.DukeException;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean isRunning;
    private ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.isRunning = true;

        String introMessage = " /\\_/\\\n" +
                "\t( o.o )\n" +
                "\t > ^ <\n" +
                "\tNya-ice to meet you! I'm Toothless :D\n" +
                "\tWhat can I do for you?";
        printMessage(introMessage);

        while (this.isRunning) {
            String userInput = scanner.nextLine();
            parseInput(userInput);
        }

        printMessage("Bye. Purr-lease chat again soon!");
    }

    public void parseInput(String userInput) {
        try {
            if (userInput.equals("bye")) {
                this.isRunning = false;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.startsWith("mark ") || userInput.equals("mark")) {
                int listIndex = validateListInput(userInput, "mark");
                markTask(listIndex);
            } else if (userInput.startsWith("unmark ") || userInput.equals("unmark")) {
                int listIndex = validateListInput(userInput, "unmark");
                unmarkTask(listIndex);
            } else if (userInput.startsWith("todo ") || userInput.equals("todo")) {
                String taskDescription = validateToDoInput(userInput);
                addToDoToList(taskDescription);
            } else if (userInput.startsWith("deadline ") || userInput.equals("deadline")) {
                String[] deadlineAttributes = validateDeadlineInput(userInput);
                addDeadlineToList(deadlineAttributes[0], deadlineAttributes[1]);
            } else if (userInput.startsWith("event ") || userInput.equals("event")) {
                String[] eventAttributes = validateEventInput(userInput);
                addEventToList(eventAttributes[0], eventAttributes[1], eventAttributes[2]);
            } else if (userInput.startsWith("delete ") || userInput.equals("delete")) {
                int listIndex = validateListInput(userInput, "delete");
                deleteTask(listIndex);
            } else {
                throw new DukeException("Sorry, I don't understand what that means D:");
            }
        } catch (DukeException e) {
            printMessage(e.getMessage());
        }
    }

    public int validateListInput(String listInput, String command) throws DukeException {
        // split string by spaces
        String[] markInputSplit = listInput.strip().split("\\s+");
        try {
            if (markInputSplit.length > 2) {
                throw new DukeException(
                        String.format("Sorry, purr-lease only include one numeric argument after %s.", command));
            } else if (markInputSplit.length < 2 || markInputSplit[1].isBlank()) {
                throw new DukeException(String.format("Sorry, purr-lease state a list index to %s.", command));
            }
            // try parsing integer
            int listIndex = Integer.parseInt(markInputSplit[1]);
            // check index bounds
            if (listIndex < 1 || listIndex > taskList.size()) {
                throw new DukeException("Apurrlogies, there's no task at that index.");
            }
            return listIndex;
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Sorry, purr-lease use a numeric list index to %s.", command));
        }
    }

    public String validateToDoInput(String toDoInput) throws DukeException {
        String taskDescription = toDoInput.replace("todo ", "").strip();
        if (taskDescription.isBlank()) {
            throw new DukeException("Apurrlogies, the task description cannot be empty.");
        }
        return taskDescription;
    }

    public String[] validateDeadlineInput(String deadlineInput) throws DukeException {
        String[] deadlineAttributes = deadlineInput.replace("deadline ", "")
                .strip().split("\\s+/by\\s+");
        if (deadlineAttributes.length != 2) {
            throw new DukeException("Sorry, purr-lease use the format: deadline [description] /by [deadline].");
        } else if (deadlineAttributes[0].isBlank()) {
            throw new DukeException("Apurrlogies, the task description cannot be empty.");
        } else if (deadlineAttributes[1].isBlank()) {
            throw new DukeException("Apurrlogies, the /by field cannot be empty.");
        }
        return deadlineAttributes;
    }

    public String[] validateEventInput(String eventInput) throws DukeException {
        String[] eventAttributes = new String[3];
        String[] tempAttributes = eventInput.replace("event ", "")
                .strip().split("\\s+/from\\s+|\\s+/to\\s+");

        int fromIndex = eventInput.indexOf("/from");
        int toIndex = eventInput.indexOf("/to");

        if (tempAttributes.length != 3) {
            throw new DukeException("Sorry, purr-lease use the format: event [description] /from [datetime] /to [datetime]");
        } else if (fromIndex == -1 || toIndex == -1) {
            throw new DukeException("Sorry, purr-lease remember to include the /from and /to fields.");
        } else if (tempAttributes[0].isBlank()) {
            throw new DukeException("Apurrlogies, the task description cannot be empty.");
        } else if (tempAttributes[1].isBlank() || tempAttributes[2].isBlank()) {
            throw new DukeException(("Apurrlogies, the /from and /to fields cannot be empty."));
        }

        eventAttributes[0] = tempAttributes[0];

        if (fromIndex < toIndex) {
            eventAttributes[1] = tempAttributes[1];
            eventAttributes[2] = tempAttributes[2];
        } else {
            eventAttributes[1] = tempAttributes[2];
            eventAttributes[2] = tempAttributes[1];
        }

        return eventAttributes;
    }

    public void markTask(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        printMessage("Ameowzing! I've marked this task as done:\n\t" + task);
    }

    public void unmarkTask(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkAsDone();
        printMessage("OK, I've marked this task as not done yet:\n\t" + task);
    }
    
    public void deleteTask(int index) {
        Task deletedTask = taskList.get(index - 1); 
        taskList.remove(index - 1);
        printDeletedTask(deletedTask);
    }

    public void addToDoToList(String taskDescription) {
        Task newTask = new ToDo(taskDescription);
        taskList.add(newTask);
        printNewTask(newTask);
    }

    public void addDeadlineToList(String taskDescription, String by) {
        Task newTask = new Deadline(taskDescription, by);
        taskList.add(newTask);
        printNewTask(newTask);
    }

    public void addEventToList(String taskDescription, String from, String to) {
        Task newTask = new Event(taskDescription, from, to);
        taskList.add(newTask);
        printNewTask(newTask);
    }

    public void printNewTask(Task newTask) {
        String message = 
                String.format("Got it. I've added this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                newTask, taskList.size());
        printMessage(message);
    }
    
    public void printDeletedTask(Task deletedTask) {
        String message = 
                String.format("Noted. I've remeowved this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                deletedTask, taskList.size());
        printMessage(message);
    }

    public void printList() {
        String listString = "";
        for (int i = 1; i <= taskList.size(); i++) {
            if (i > 1) {
                listString += "\t";
            }
            listString += i + ". " + taskList.get(i - 1);
            if (i < taskList.size()) {
                listString += "\n";
            }
        }
        printMessage(listString);
    }

    public void printMessage(String message) {
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println(line);
    }
}
