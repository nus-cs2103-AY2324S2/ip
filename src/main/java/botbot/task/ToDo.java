package botbot.task;

public class ToDo extends Task{
    public ToDo(String task) {
        super(task);
    }
    @Override
    public String getRep() {
        return "[T]" + super.getRep();
    }
    @Override
    public String fileRep() { return "T|" + super.fileRep(); }
}


