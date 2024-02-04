package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;
import tasklist.tasks.Task;

public class ListCommand implements Command {

  // public ListCommand() {
  // }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) {
    System.out.println("Here are your current tasks:");
    for (int i = 0; i < taskList.size(); i++) {
      int itemNumber = i + 1;
      Task listItem = taskList.getTask(i);
      System.out.println(itemNumber + ". " + listItem);
    }
  }
}
