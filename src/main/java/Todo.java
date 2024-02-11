class Todo extends Task {
    /**
     * Constructor
     * 
     * @param name task name
     * @return new task instance
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}