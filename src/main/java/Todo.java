public class Todo extends Task{
    private String type;

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String print() {
        String str = "[T]" + super.print();
        return str;
    }

    @Override
    public String getDescription() {
        String str = "[T] " + super.getDescription();
        return str;
    }


    @Override
    public String getTaskInfo() {
        return "[T] " + "/ [" + super.getStatusIcon()
                + "] / " + super.getTaskInfo();
    }
}
