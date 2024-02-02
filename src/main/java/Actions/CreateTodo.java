package Actions;

import ChatBot.Duke;
import TaskList.TaskList;
import Tasks.Todo;

public class CreateTodo implements Action {
    private String desc;
    public CreateTodo(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(Duke bot) {
        Todo task = new Todo(desc);
        bot.getTaskList().addToList(task);
        System.out.println("Task successfully added!");
    }
}
