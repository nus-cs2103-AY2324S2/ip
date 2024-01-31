package parser;

import storage.Task;

public class Token {
    private Command cmd;
    private Task task;
    private int selectedItem;

    public Token(Command cmd) {
        this.cmd = cmd;
    }

    public Token(Command cmd, Task task) {
        this.cmd = cmd;
        this.task = task;
    }

    public Token(Command cmd, int selectedItem) {
        this.cmd = cmd;
        this.selectedItem = selectedItem;
    }

    public Command getCmd() {
        return this.cmd;
    }

    public int getSelectedItem() {
        return this.selectedItem;
    }

    public Task getTask() {
        return this.task;
    }

    public void setAsSaved() {
        this.cmd = Command.SAVED;
    }

}