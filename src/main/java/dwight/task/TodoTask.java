package dwight.task;

import dwight.exceptions.DwightException;

public class TodoTask extends Task {

    public TodoTask(String name, String file_format) throws DwightException {
        super(name, Type.T, file_format);
        if (this.name.isBlank()) {
            String error_message = "\tTodo description cannot be empty!\n\tEx: todo return book\n";
            throw new DwightException(error_message);
        }
    }

    @Override
    public String toString() {
        String output;
        if (isDone) {
            output = "[" + this.type + "]" + "[X] " + this.name + "\n";
        } else {
            output = "[" + this.type + "]" + "[ ] " + this.name + "\n";
        }
        return output;
    }
}
