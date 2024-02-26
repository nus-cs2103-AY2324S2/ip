package task;

import task.Task;


/**
 * Todo is a type of task
 */
public class Todo extends Task {
    /**
     * instantiates a todo object
     * @param name Describes the todo task
     * @param isDone A boolean that indicates if the todo task is completed
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * String representation of the todo task
     * @return type, status and name of the todo task
     */
    @Override
    public String toString(){
        return "[T] " + super.toString();
    }

    /**
     * String representation of the todo task in a storage format
     * @return type, status represented as a number and name of the todo task
     */
    @Override
    public String toFileString() {
        return "T" + " | " + getStatusNum() + " | " + this.name ;
    }

}