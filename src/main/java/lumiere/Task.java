// package lumiere.tasks;

// import lumiere.*;

public class Task {
    private boolean marked;
    private String item;

    public Task(String item, boolean marked) {
        this.item = item;
        this.marked = marked;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public String getItem() {
        return this.item;
    }

    public String stringify() {
        return this.item;
    }

    public static Task parseTask(String taskDescription) {
        String[] info = taskDescription.split(" | ");

        if (taskDescription.startsWith("T")) {
            boolean marked;
            if (info[2].equals("true"))
                marked = true;
            else
                marked = false;
            Todo task = new Todo(info[4], marked);
            return task;
        } else if (taskDescription.startsWith("D")) {
            boolean marked;
            if (info[2].equals("true"))
                marked = true;
            else
                marked = false;

            Deadline task = new Deadline(info[4], marked, info[6]);
            return task;
        } else {
            boolean marked;
            if (info[2].equals("true"))
                marked = true;
            else
                marked = false;
            Event task = new Event(info[4], marked, info[6], info[8]);
            return task;
        }
    }
}