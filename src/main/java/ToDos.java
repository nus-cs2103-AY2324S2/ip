public class ToDos extends Task {
    public ToDos(String description) {
        super(description, 'T');
    }
    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}