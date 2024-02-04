package Commands;

import Irwyn.Tasks.TaskList;
import Irwyn.Tasks.ToDo;
import Misc.StorageManager;
import Misc.Ui;

public class ToDoCommand extends Command {
    String todoDescription;
    ToDoCommand (String input) {
        super(false);
        this.todoDescription = input.replaceFirst("todo ", "");
    }
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        // create the actual task
        ToDo todo = new ToDo(todoDescription);

        // add to the storage in Task & save into HDD
        taskList.addTask(todo);
        storageManager.save(taskList.getTasks());

        // add to the reply
        ui.reply(todo.replyString(taskList.getTasksSize()));
    }
}