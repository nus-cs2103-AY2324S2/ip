/* (C)2024 */
package taskList.commands;

import taskList.Storage;
import taskList.TaskList;
import taskList.Ui;
import taskList.tasks.Task;

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
