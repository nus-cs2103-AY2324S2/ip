package toothless;

import toothless.tasks.Task;

public class UiStub1 extends Ui{

    @Override
    public String showMarkedTask(Task task) {
        return "Task marked as done: " + task;
    }
}
