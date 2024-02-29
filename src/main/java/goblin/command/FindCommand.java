package goblin.command;

import goblin.Ui;
import goblin.Storage;
import goblin.exception.OrkException;
import goblin.TaskList;


public class FindCommand extends Command {
    protected String description;

    public FindCommand(String description) {
        super();
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof FindCommand) {
            FindCommand obj = (FindCommand) o;
            return obj.getDescription().equals(description);
        } else {
            return false;
        }
    }
}
