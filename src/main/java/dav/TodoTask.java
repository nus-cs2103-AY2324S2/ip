package dav;

class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length == 3) {
            TodoTask todoTask = new TodoTask(parts[2]);
            todoTask.isDone = parts[1].equals("1");
            return todoTask;
        }
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

