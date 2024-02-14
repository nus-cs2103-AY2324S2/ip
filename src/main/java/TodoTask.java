public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name);
    }


    @Override
    public String toString() {

        String taskString = "[T] ";

        String temp = "[ ] ";

        if(this.isDone()){
            temp = "[X] ";
        }

        return taskString + temp + this.getName();
    }

}