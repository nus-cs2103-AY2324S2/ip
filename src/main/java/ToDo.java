public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T] " + super.getStatusIcon() + " " + description;
    }

}
