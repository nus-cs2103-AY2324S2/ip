package pan;

import pan.enums.TaskStatus;

class ToDos extends Task{
    /**
     * Constructs a ToDos instance.
     *
     * @param description The description of the todos.
     * @param isDone The status of whether the todos has been completed.
     */
    public ToDos(String description, TaskStatus isDone) {
        super(description, isDone);
    }

    /**
     * Converts the ToDos instance into its correpsonding string representation.
     * 
     * @return String that represents whether the corresponding ToDos has been comopleted.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}