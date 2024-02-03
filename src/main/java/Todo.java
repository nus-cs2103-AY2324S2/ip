public class Todo extends Task{

    public Todo(String description) {
        super(description);
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
}
