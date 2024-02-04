package toothless.parser;

import toothless.exception.ToothlessException;
import toothless.task.TaskList;
import toothless.ui.Ui;

public class Parser {
    private boolean isRunning = true;
    
    private void makeExit() {
        this.isRunning = false;
    }
    
    public boolean isStillRunning() {
        return isRunning;
    }
     
    public void parseInput(String userInput, TaskList tasks, Ui ui) throws ToothlessException {
        if (userInput.equals("bye")) {
            ui.printMessage("Bye. Purr-lease chat again soon!");
            makeExit();
        } else if (userInput.equals("list")) {
            tasks.printList();
        } else if (userInput.startsWith("mark ") || userInput.equals("mark")) {
            int listIndex = validateListInput(userInput, "mark", tasks.getSize());
            tasks.markTask(listIndex);
        } else if (userInput.startsWith("unmark ") || userInput.equals("unmark")) {
            int listIndex = validateListInput(userInput, "unmark", tasks.getSize());
            tasks.unmarkTask(listIndex);
        } else if (userInput.startsWith("todo ") || userInput.equals("todo")) {
            String taskDescription = validateToDoInput(userInput);
            tasks.addToDoToList(taskDescription);
        } else if (userInput.startsWith("deadline ") || userInput.equals("deadline")) {
            String[] deadlineAttributes = validateDeadlineInput(userInput);
            tasks.addDeadlineToList(deadlineAttributes[0], deadlineAttributes[1]);
        } else if (userInput.startsWith("event ") || userInput.equals("event")) {
            String[] eventAttributes = validateEventInput(userInput);
            tasks.addEventToList(eventAttributes[0], eventAttributes[1], eventAttributes[2]);
        } else if (userInput.startsWith("delete ") || userInput.equals("delete")) {
            int listIndex = validateListInput(userInput, "delete", tasks.getSize());
            tasks.deleteTask(listIndex);
        } else {
            throw new ToothlessException("Sorry, I don't understand what that means D:");
        }
    }

    public int validateListInput(String listInput, String command, int taskListSize) throws ToothlessException {
        // split string by spaces
        String[] markInputSplit = listInput.strip().split("\\s+");
        try {
            if (markInputSplit.length > 2) {
                throw new ToothlessException(
                        String.format("Sorry, purr-lease only include one numeric argument after %s.", command));
            } else if (markInputSplit.length < 2 || markInputSplit[1].isBlank()) {
                throw new ToothlessException(String.format("Sorry, purr-lease state a list index to %s.", command));
            }
            // try parsing integer
            int listIndex = Integer.parseInt(markInputSplit[1]);
            // check index bounds
            if (listIndex < 1 || listIndex > taskListSize) {
                throw new ToothlessException("Apurrlogies, there's no task at that index.");
            }
            return listIndex;
        } catch (NumberFormatException e) {
            throw new ToothlessException(String.format("Sorry, purr-lease use a numeric list index to %s.", command));
        }
    }

    public String validateToDoInput(String toDoInput) throws ToothlessException {
        String taskDescription = toDoInput.replace("todo ", "").strip();
        if (taskDescription.isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        }
        return taskDescription;
    }

    public String[] validateDeadlineInput(String deadlineInput) throws ToothlessException {
        String[] deadlineAttributes = deadlineInput.replace("deadline ", "")
                .strip().split("\\s+/by\\s+");

        if (deadlineAttributes.length != 2) {
            throw new ToothlessException("Sorry, purr-lease use the format: " +
                    "deadline [description] /by [yyyy-mm-dd hh:mm].");
        } else if (deadlineAttributes[0].isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        } else if (deadlineAttributes[1].isBlank()) {
            throw new ToothlessException("Apurrlogies, the /by field cannot be empty.");
        }

        return deadlineAttributes;
    }

    public String[] validateEventInput(String eventInput) throws ToothlessException {
        String[] eventAttributes = new String[3];
        String[] tempAttributes = eventInput.replace("event ", "")
                .strip().split("\\s+/from\\s+|\\s+/to\\s+");

        int fromIndex = eventInput.indexOf("/from");
        int toIndex = eventInput.indexOf("/to");

        if (tempAttributes.length != 3) {
            throw new ToothlessException("Sorry, purr-lease use the format: " +
                    "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]");
        } else if (fromIndex == -1 || toIndex == -1) {
            throw new ToothlessException("Sorry, purr-lease remember to include the /from and /to fields.");
        } else if (tempAttributes[0].isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        } else if (tempAttributes[1].isBlank() || tempAttributes[2].isBlank()) {
            throw new ToothlessException(("Apurrlogies, the /from and /to fields cannot be empty."));
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
}
