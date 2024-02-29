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
    public void execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        Boolean isFind = false;
        if (description.isEmpty()) {
          throw new OrkException("What do you wan to find?");
        } else {
            System.out.println("Here are the matching tasks:");
            for (int i = 0; i < TaskList.list.size(); i++) {
                if (list.getTask(i).getDescription().contains(description)) {
                    System.out.println("\t " + (i + 1) + "." + list.getTask(i).notPrint());
                    isFind = true;
                }
            }
            if (!isFind) {
                System.out.println("No matching task");
            }
        }
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
     * @return
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
