package catchat;

/**
 * Parser class deals with making sense of the user command
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor for Parser
     *
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.ui = new Ui();
        this.taskList = taskList;
    }

    /**
     * Executes user input
     *
     * @param input user input
     * @return boolean
     */
    public void executeUserInput(String input) {
        switch (CommandType) {
        case

        //        if (input.equals("list")) { // show list
        //            taskList.printList();
        //            return CommandType.LIST;
        //
        //        } else if (input.startsWith("mark done")) { // mark as done
        //            try {
        //                int index = Integer.parseInt(input.substring(9).trim()) - 1;
        //                taskList.markDone(index);
        //                return CommandType.MARK_DONE;
        //            } catch (NumberFormatException e) {
        //                ui.printInvalidTaskIndex();
        //            }
        //
        //        } else if (input.startsWith("mark undone")) { // mark as undone
        //            try {
        //                int index = Integer.parseInt(input.substring(11).trim()) - 1;
        //                taskList.markUndone(index);
        //                return CommandType.MARK_UNDONE;
        //            } catch (NumberFormatException e) {
        //                ui.printInvalidTaskIndex();
        //            }
        //
        //        } else if (input.startsWith("find")) { // tasks that contain keyword
        //            try {
        //                String keyword = input.substring(5).trim();
        //                taskList.findTasks(keyword);
        //                return CommandType.FIND;
        //            } catch (StringIndexOutOfBoundsException e) {
        //                ui.printInvalidKeyword();
        //            }
        //
        //        } else { // else adds task
        //            taskList.addTask(input);
        //            return CommandType.ADD;
        //        }
        //        return CommandType.INVALID_COMMAND;
    }

    /**
     * Gets the command type
     *
     * @param input
     * @return
     */
    public CommandType getCommandType(String input) {
        if (input.equals("bye")) {
            return CommandType.EXIT;
        }

        if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark done")) {
            return CommandType.MARK_DONE;
        } else if (input.startsWith("mark undone")) {
            return CommandType.MARK_UNDONE;
        } else if (input.startsWith("find")) {
            return CommandType.FIND;
        } else if (input.startsWith("help")) {
            return CommandType.HELP;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else {
            // Assuming any other input should be treated as adding a task
            return CommandType.ADD;
        }
    }

    /**
     * Enum for CommandType
     */
    public enum CommandType {
        EXIT,
        LIST,
        MARK_DONE,
        MARK_UNDONE,
        FIND,
        ADD,
        HELP,
        DELETE
    }
}
