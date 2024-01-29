package simpli.tasks;

public class Todo extends Task {
    public Todo(boolean isDone, String name) {
        super(isDone, name);
    }

    @Override
    public String toCsv() {
        return "Todo," + super.toCsv();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
