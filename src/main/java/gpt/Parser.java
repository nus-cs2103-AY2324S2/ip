package gpt;

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
    public static void parseCommand(String command, TaskList tl, Ui ui, Storage storage) throws GptException {
        if (command.equals("list")) {
            for (int i = 1; i <= tl.size(); i++) {
                System.out.println(i + ". " + tl.get(i - 1).toString());
            }
        } else if (command.startsWith("todo")) {
            processTodoCommand(command, tl);
        } else if (command.startsWith("deadline")) {
            processDeadlineCommand(command, tl);
        } else if (command.startsWith("event")) {
            processEventCommand(command, tl);
        } else if (command.startsWith("delete")) {
            processDeleteCommand(command, tl);
        } else if (command.startsWith("unmark")) {
            String[] splitInput = command.split("\\s+");
            if (splitInput[0].equals("unmark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
                tl.unmarkTask(Integer.valueOf(splitInput[1]) - 1);
            }
        } else if (command.startsWith("mark")) {
            String[] splitInput = command.split("\\s+");
            if (splitInput[0].equals("mark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
                tl.markTask(Integer.valueOf(splitInput[1]) - 1);
            }
        } else if (command.startsWith("find")) {
            processFindCommand(command, tl);
        } else if (command.equals("save")) {
            storage.saveTasks(tl);
        } else {
            throw new GptException("HEY YOU mESsEd UP!!! Your input don't make sense to me :-(");
        }
    }

    private static void processDeleteCommand(String command, TaskList tl) throws GptException {
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
    }
    private static void processTodoCommand(String command, TaskList tl) throws GptException {
        if (command.length() < 5) {
            throw new GptException("HEY YOU mESsEd UP!!! The description of a todo cannot be empty.");
        }
        String todoDescription = command.substring(5).trim();

        System.out.println("Got it. I've added this task:");
        Task todoTask = new Task(todoDescription, TaskType.T, false);
        tl.addTask(todoTask);
        System.out.println("  " + todoTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }
    private static void processDeadlineCommand(String command, TaskList tl) throws GptException {
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
        tl.addTask(deadlineTask);
        System.out.println("  " + deadlineTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }
    private static void processEventCommand(String command, TaskList tl) throws GptException {
        if (!command.contains("/from") || !command.contains("/to") || command.length() < 6) {
            throw new GptException("name, start date or end date missing for deadline task");
        }
        String[] splitInput = command.split("/from|/to");
        String eventName = splitInput[0].substring(6).trim();
        String eventStartDate = splitInput[1].trim();
        String eventEndDate = splitInput[2].trim();
        System.out.println("Got it. I've added this task:");
        Task eventTask = new Task(eventName, TaskType.E, false, eventStartDate, eventEndDate);
        tl.addTask(eventTask);
        System.out.println("  " + eventTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }
    private static void processFindCommand(String command, TaskList taskList) throws GptException {
        String keyword = command.substring(5).trim(); // assuming "find" is followed by a space
        TaskList matchingTasks = taskList.findTasks(keyword);

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= matchingTasks.size(); i++) {
            System.out.println(i + ". " + matchingTasks.get(i - 1).toString());
        }
    }

}
