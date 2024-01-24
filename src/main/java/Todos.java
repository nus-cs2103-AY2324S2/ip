public class Todos extends Task{
    public Todos(String goal) {
        super(goal);
    }

    @Override
    public String getTag() {
        return "[T]";
    }
}
