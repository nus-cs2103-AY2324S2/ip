package duke;

import duke.Task;

public class ToDo extends Task {



    public ToDo(String description) {
        super(description, Task.TaskType.TODO);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    @Override
    public String getType(){
        return "T";
    }
}
