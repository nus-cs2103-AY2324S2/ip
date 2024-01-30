import exception.DukeException;
import task.TaskList;

import java.util.Scanner;

public class Duke {
    private boolean isRunning;
    private TaskList taskList;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.isRunning = true;

        printLine();
        String introMessage = " /\\_/\\\n" +
                "\t( o.o )\n" +
                "\t > ^ <\n" +
                "\tNya-ice to meet you! I'm Toothless :D\n" +
                "\tWhat can I do for you?";
        printMessage(introMessage);
        printLine();

        try {
            this.taskList = new TaskList();
        } catch (DukeException e) {
            printMessage(e.getMessage());
            printMessage("Exiting as tasklist.txt is corrupted.");
            this.isRunning = false;
        }

        while (this.isRunning) {
            try {
                String userInput = scanner.nextLine();
                printLine();
                parseInput(userInput);
            } catch (DukeException e) {
                printMessage(e.getMessage());
            } finally {
                printLine();
            }
        }
    }

    public void parseInput(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            printMessage("Bye. Purr-lease chat again soon!");
            this.isRunning = false;
        } else if (userInput.equals("list")) {
            taskList.printList();
        } else if (userInput.startsWith("mark ") || userInput.equals("mark")) {
            int listIndex = validateListInput(userInput, "mark");
            taskList.markTask(listIndex);
        } else if (userInput.startsWith("unmark ") || userInput.equals("unmark")) {
            int listIndex = validateListInput(userInput, "unmark");
            taskList.unmarkTask(listIndex);
        } else if (userInput.startsWith("todo ") || userInput.equals("todo")) {
            String taskDescription = validateToDoInput(userInput);
            taskList.addToDoToList(taskDescription);
        } else if (userInput.startsWith("deadline ") || userInput.equals("deadline")) {
            String[] deadlineAttributes = validateDeadlineInput(userInput);
            taskList.addDeadlineToList(deadlineAttributes[0], deadlineAttributes[1]);
        } else if (userInput.startsWith("event ") || userInput.equals("event")) {
            String[] eventAttributes = validateEventInput(userInput);
            taskList.addEventToList(eventAttributes[0], eventAttributes[1], eventAttributes[2]);
        } else if (userInput.startsWith("delete ") || userInput.equals("delete")) {
            int listIndex = validateListInput(userInput, "delete");
            taskList.deleteTask(listIndex);
        } else {
            throw new DukeException("Sorry, I don't understand what that means D:");
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
    
    public void printMessage(String message) {
        System.out.println("\t" + message);
    }
    
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}
