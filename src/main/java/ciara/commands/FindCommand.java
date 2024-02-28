package ciara.commands;

import ciara.storage.TaskList;
import ciara.ui.Ui;

/**
 * The FindCommand class defines a command to find tasks stored in the Ciara
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
     * Visibility of the tasks to find
     */
    private boolean isArchived;

    /**
     * Creates a find command without any keyword
     *
     * @param isArchived Visibility of the tasks to find
     */
    public FindCommand(boolean isArchived) {
        super(false);
        this.keyword = "";
        this.isArchived = isArchived;
    }

    /**
     * Creates a find command with a keyword
     *
     * @param keyword    Keyword for the find command
     * @param isArchived Visibility of the tasks to find
     *
     */
    public FindCommand(String keyword, boolean isArchived) {
        super(false);
        this.keyword = keyword;
        this.isArchived = isArchived;
    }

    /**
     * Executes the command
     *
     * @param taskList Tasklist used for the command
     *
     * @return String containing the output of the command
     */
    @Override
    public String execute(TaskList taskList) {
        String tasks;
        if (this.keyword.equals("")) {
            tasks = taskList.getTasks(this.isArchived);
        } else {
            tasks = taskList.getTasks(keyword, this.isArchived);
        }

        return tasks;
    }

    /**
     * Executes the find command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String successMessage = this.execute(taskList);

        // Print tasks
        System.out.println(successMessage);
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
                return super.equals(command)
                        && this.keyword.equals(command.keyword)
                        && this.isArchived == command.isArchived;
            } else if (this.keyword == null && command.keyword == null) {
                return super.equals(command) && this.isArchived == command.isArchived;
            }
        }

        return false;
    }
}
