public class ToDo extends Task {

    public ToDo(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + this.description;
    }

    @Override
    public String toSave() {
        return " T" + (super.getStatusIcon().equals("X") ? " | 1 | " : " | 0 | ") + this.description;
    }
}
