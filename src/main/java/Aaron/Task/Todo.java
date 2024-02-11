package Aaron.Task;

import Aaron.Exception.AaronBotException;

/**
 * class that represents a todo type of task in aaronbot
 */
public class Todo extends Task{
    
    public Todo(String taskString) throws AaronBotException{
        super(taskString);
    }

    public Todo(String taskString, boolean isDone) {
        super(taskString, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Todo)) {
            return false;
        }

        Todo todo = (Todo) obj;
        return (super.equals(todo));
    }
    
}
