package duke.commands;

import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The FindCommand class defines a command to find tasks stored in the Duke
 * application (containing the keyword)
 *
 * @author Ryan NgWH
 */
public class FindCommand extends Command {
    /**
     * Keyword for the find command
     */
    private String keyword;

    /**
     * Creates a find command without any keyword
     */
    public FindCommand() {
        super(false);
        this.keyword = "";
    }

    /**
     * Creates a find command with a keyword
     *
     * @param keyword Keyword for the find command
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the find command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String tasks;
        if (this.keyword.equals("")) {
            tasks = taskList.getTasks();
        } else {
            tasks = taskList.getTasks(keyword);
        }

        // Print tasks
        System.out.println(tasks);
    }

    /**
     * Indicates whether some other object is "equal to" this command
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof FindCommand) {
            FindCommand command = (FindCommand) obj;

            if (this.keyword != null && command.keyword != null) {
                return super.equals(command) && this.keyword.equals(command.keyword);
            } else if (this.keyword == null && command.keyword == null) {
                return super.equals(command);
            }
        }

        return false;
    }
}
