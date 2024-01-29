package TaskList;

import TaskList.Tasks.Task;

public class TaskStub extends Task {
    int index;
    public TaskStub(int index){
        super("TaskStub");
        this.index = index;
    }
    @Override
    public String save(){
       return "TaskStub";
    }

    public String toString() {
        return super.toString() + this.index;
    }
}
