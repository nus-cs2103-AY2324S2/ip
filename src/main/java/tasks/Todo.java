package tasks;

class Todo extends Task {  // default access modifier
    private static final String TODO_ICON = "[T]";

    Todo(String description) {  // default access modifier
        super(description);
    }

    @Override
    public String toString() {
        return TODO_ICON + super.toString();
    }
}
