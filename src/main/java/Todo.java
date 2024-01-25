public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }



    public String isTodo() {
        return "[T]";
    }

    public String addTodo() {
        return "Got it. I've added this task: \n"
                + "    " + this.isTodo() + this.marked() + " " + this.getTask();
    }
}
