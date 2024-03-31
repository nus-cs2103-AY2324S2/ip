package goblin.command;

import goblin.Ui;
import goblin.Storage;
import goblin.exception.OrkException;
import goblin.TaskList;

//@@Chow Yi Yin nusliuyifan-reused
//{slightly modified from a past student's work(github name: Chow Yi Yin)
public class FindCommand extends Command {
    protected String description;

    /**
     * create a new FindCommand object
     * @param description what to find
     */
    public FindCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * find some tasks
     * @param list list of tasks
     * @param ui handle ui
     * @param storage handle storage
     * @throws OrkException when description is empty or not complete
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        boolean isFind = false;
        String message = "Here are the matching tasks:";
        if (description.isEmpty()) {
          throw new OrkException("What do you wan to find?");
        } else {
            for (int i = 0; i < TaskList.list.size(); i++) {
                if (list.getTask(i).getDescription().contains(description)) {
                    message = message + "\n" + "\t " + (i + 1) + "." + list.getTask(i).notPrint();
                    isFind = true;
                }
            }
            if (!isFind) {
                message = "No matching task";
            }
        }
        return message;
    }

    /**
     * get description of what to find
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * equal method
     * @param object object to compare with
     * @return a boolean
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object instanceof FindCommand) {
            FindCommand obj = (FindCommand) object;
            return obj.getDescription().equals(description);
        } else {
            return false;
        }
    }
}
