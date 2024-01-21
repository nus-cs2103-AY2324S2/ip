public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[To-do]%s", super.toString());
    }

}
