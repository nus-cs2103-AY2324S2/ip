package harper.utils;

import harper.tasks.Task;

public class UiStub extends Ui {
    public boolean isPrintSuccessfulAddCalled;

    public UiStub() {
        isPrintSuccessfulAddCalled = false;
    }

    @Override
    public void printSuccessfulAdd(TaskList taskList, Task task) {
        this.isPrintSuccessfulAddCalled = true;
    }
}
