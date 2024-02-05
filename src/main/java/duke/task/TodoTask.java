package duke.task;

import duke.exceptions.DukeException;

import duke.task.Task;

public class TodoTask extends Task {

    public TodoTask(String name, String file_format) throws DukeException {
        super(name, Type.T, file_format);
        if (this.name.isBlank()) {
            String error_message = "\tTodo description cannot be empty!\n\tEx: todo return book\n";
            throw new DukeException(error_message);
        }
    }

    @Override
    public String toString() {
        String output;
        if (done) {
            output = "[" + this.type + "]" + "[X] " + this.name + "\n";
        } else {
            output = "[" + this.type + "]" + "[ ] " + this.name + "\n";
        }
        return output;
    }
}
