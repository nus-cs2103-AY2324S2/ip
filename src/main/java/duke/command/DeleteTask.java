package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

/**
 * Represents the Command of deleting a task in a list.
 */
public class DeleteTask extends Command{
    private int index;

    /**
     * Initializes a Command to remove a task on the given index.
     *
     * @param type the type of the Command which is delete.
     * @param index the index of the task to be removed.
     */
    public DeleteTask(Parser.Cmd type, int index){
        super(type);
        this.index = index;
    }

    /**
     * Removes the task on the index field in the given list.
     *
     * @param taskList the given taskList from which the task is removed.
     */
    @Override
    public void run(TaskList taskList){
        taskList.deleteList(this.index);
    }
}
