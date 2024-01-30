public class Todo  extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return super.toString() + " Todo : " + this.description;
    }
}