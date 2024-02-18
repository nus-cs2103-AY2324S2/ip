package gpt;

import java.util.ArrayList;

/**
 * Parses the user input and executes the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param command The user input.
     * @param tl The TaskList to be modified.
     * @param ui The Ui to be used.
     * @param storage The Storage to be used.
     * @throws GptException If the user input is invalid.
     */
    public static String parseCommand(String command, TaskList tl, Ui ui, Storage storage) throws GptException {
        if (command.equals("bye")) {
            return ui.getByeMsg();
        } else if (command.equals("list")) {
            return processListCommand(tl);
        } else if (command.startsWith("todo")) {
            return processTodoCommand(command, tl);
        } else if (command.startsWith("deadline")) {
            return processDeadlineCommand(command, tl);
        } else if (command.startsWith("event")) {
            return processEventCommand(command, tl);
        } else if (command.startsWith("delete")) {
            return processDeleteCommand(command, tl);
        } else if (command.startsWith("unmark")) {
            return processUnmarkCommand(command, tl);
        } else if (command.startsWith("mark")) {
            return processMarkCommand(command, tl);
        } else if (command.startsWith("find")) {
            return processFindCommand(command, tl);
        } else if (command.equals("save")) {
            return processSaveCommand(tl, storage);
        } else {
            throw new GptException("HEY YOU mESsEd UP!!! Your input don't make sense to me :-(");
        }
    }

    private static String processUnmarkCommand(String command, TaskList tl) {
        String[] splitInput = command.split("\\s+");
        if (splitInput[0].equals("unmark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
            tl.unmarkTask(Integer.valueOf(splitInput[1]) - 1);
        }
        return "Task unmarked!";
    }

    private static String processMarkCommand(String command, TaskList tl) {
        String[] splitInput = command.split("\\s+");
        if (splitInput[0].equals("mark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
            tl.markTask(Integer.valueOf(splitInput[1]) - 1);
        }
        return "Task marked as done!";
    }

    private static String processListCommand(TaskList tl) {
        for (int i = 1; i <= tl.getTasks().size(); i++) {
            System.out.println(i + ". " + tl.getTasks().get(i - 1).toString());
        }
        return getListAsString(tl.getTasks());
    }

    private static String processDeleteCommand(String command, TaskList tl) throws GptException {
        String[] splitInput = command.split("\\s+");
        if (splitInput.length < 2) {
            throw new GptException("OIII!!! Please specify the task number to delete.");
        }
        int taskNumber = Integer.parseInt(splitInput[1]);
        if (taskNumber <= 0 || taskNumber > tl.size()) {
            throw new GptException("OOPS!!! Task number is out of range.");
        }

        Task deletedTask = tl.deleteTask(taskNumber - 1);
        System.out.println("Noted. I've removed this task:\n" + deletedTask);
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + tl.size() + " tasks in the list.";

    }
    private static String processTodoCommand(String command, TaskList tl) throws GptException {
        if (command.length() < 5) {
            throw new GptException("HEY YOU mESsEd UP!!! The description of a todo cannot be empty.");
        }
        String todoDescription = command.substring(5).trim();

        System.out.println("Got it. I've added this task:");
        Task todoTask = new Task(todoDescription, TaskType.T, false);
        System.out.println(tl.size());
        System.out.println(todoTask);
        tl.addTask(todoTask);
        System.out.println("  " + todoTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
        return "Got it. I've added this task:" + "  " + todoTask.toString() + "\nNow you have "
                + tl.size() + " tasks in the list.";

    }

    private static String processSaveCommand(TaskList tl, Storage storage) {
        System.out.println(tl.size());
        storage.saveTasks(tl);
        return "Tasks saved!";
    }

    /**
     * Processes the "deadline" command.
     *
     * @param command The "deadline" command.
     * @param tl The TaskList to be modified.
     * @throws GptException If the name or deadline date is missing.
     */
    private static String processDeadlineCommand(String command, TaskList tl) throws GptException {
        if (command.length() < 9) {
            throw new GptException("Name or deadline date missing for deadline task");
        }
        if (!command.contains("/by")) {
            throw new GptException("Format is wrong, please use /by to indicate deadline");
        }
        String[] splitInput = command.split("/by");
        String deadlineName = splitInput[0].substring(9).trim();
        String deadlineDate = splitInput[1].trim();
        if (deadlineDate.isEmpty() || deadlineName.isEmpty()) {
            throw new GptException("Name or deadline date missing for deadline task");
        }
        System.out.println("Got it. I've added this task:");
        Task deadlineTask = new Task(deadlineName, TaskType.D, false, deadlineDate); //TODO
        System.out.println("  " + deadlineTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
        tl.addTask(deadlineTask);
        return "Got it. I've added this task:" + "  " + deadlineTask.toString()
                + "\nNow you have " + tl.size() + " tasks in the list.";

    }

    /**
     * Processes the "event" command.
     *
     * @param command The "event" command.
     * @param tl The TaskList to be modified.
     * @throws GptException If the name, start date or end date is missing.
     */
    private static String processEventCommand(String command, TaskList tl) throws GptException {
        if (!command.contains("/from") || !command.contains("/to") || command.length() < 6) {
            throw new GptException("name, start date or end date missing for deadline task");
        }
        String[] splitInput = command.split("/from|/to");
        String eventName = splitInput[0].substring(6).trim();
        String eventStartDate = splitInput[1].trim();
        String eventEndDate = splitInput[2].trim();
        Task eventTask = new Task(eventName, TaskType.E, false, eventStartDate, eventEndDate);
        tl.addTask(eventTask);
        return "Got it. I've added this task:" + "  " + eventTask.toString()
                + "\nNow you have " + tl.size() + " tasks in the list.";
    }

    /**
     * Processes the "find" command.
     *
     * @param command The "find" command.
     * @param taskList The TaskList to be searched.
     * @throws GptException If the keyword is missing.
     */
    private static String processFindCommand(String command, TaskList taskList) throws GptException {
        String keyword = command.substring(5).trim(); // assuming "find" is followed by a space
        TaskList matchingTasks = taskList.findTasks(keyword);

        System.out.println("Here are the matching tasks in your list:");

        for (int i = 1; i <= matchingTasks.size(); i++) {
            System.out.println(i + ". " + matchingTasks.get(i - 1).toString());
        }
        return getListAsString(matchingTasks.getTasks());
    }

    private static String getListAsString(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= tasks.size(); i++) {
            stringBuilder.append(i).append(". ").append(tasks.get(i - 1).toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}


