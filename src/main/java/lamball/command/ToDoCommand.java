package lamball.command;

import lamball.TaskList;

public class ToDoCommand extends Command{
    private String toDo;
    private boolean isInit;
    public ToDoCommand(String toDo, TaskList tasks, boolean isInit) {
        super(tasks);
        this.toDo = toDo;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        taskList.toDo(toDo, isInit);
        return true;
    }
}
