package duke.commands;

import duke.tasks.Task;
import duke.utils.TaskList;
import duke.exceptions.TaskModificationException;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * This class implements the modify task command that modifies tasks in the bot tasklist.
 */
public class ModifyTaskCommand extends Command {
    
    public enum ModificationTypes {MARK, UNMARK, DELETE}
    private ModificationTypes modType;
    private String indexInput;

    /**
     * Basic constructor, takes in type of modificaiton and the user input for index to be modified.
     * 
     * @param modType Modification type based on enum ModificationTypes.
     * @param indexInput user input to be parsed into index.
     */
    public ModifyTaskCommand(ModificationTypes modType, String indexInput) {
        super(false);
        this.modType = modType;
        this.indexInput = indexInput;
    }

    /** 
     * Method for executing modify task command, modifies tasks in list based on index and type.
     * 
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) 
    throws IndexOutOfBoundsException, NumberFormatException, TaskModificationException {
        
        String[] inputSplit = indexInput.split(" ");
        if (inputSplit.length < 1) {
            throw new TaskModificationException("Input is missing task number\nList is of current length: \" + tasks.size()");
        } 

        int index = Integer.parseInt(indexInput.split(" ")[1]);

        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid Index " + index + " for current list\nList is of current length: " + tasks.size());
        }

        switch (this.modType) {
        case MARK:
            Task t1 = tasks.get(index);
            t1.doTask();
            ui.botPrint("Good job on finishing your task!:\n  " + t1);
            break;
        case UNMARK:
            Task t2 = tasks.get(index);
            t2.undoTask();
            ui.botPrint("I've marked this task as undone:\n  " + t2);
            break;
        case DELETE:
            Task t3 = tasks.get(index);
            tasks.remove(t3);
            ui.botPrint("I've removed this task:\n  " + t3);
            break;
        }        
    } 
}
