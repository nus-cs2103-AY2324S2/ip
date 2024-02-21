package lumiere.lumiere;

import java.time.LocalDate;

public class Task {
    private boolean marked;
    private String item;

    /**
     * Constructor for Task object
     * 
     * @param item   The item that describes what the task is about, i.e. what needs
     *               to be done
     * @param marked A boolean that indicates whether this task is marked as done or
     *               not.
     * @return Nothing, it is a constructor.
     */
    public Task(String item, boolean marked) {
        this.item = item;
        this.marked = marked;
    }

    /**
     * An instance method that marks the task object as done.
     * 
     * @return Nothing, it is a void method.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * An instance method that marks the task object as not done.
     * 
     * @return Nothing, it is a void method.
     */
    public void unmark() {
        this.marked = false;
    }

    /**
     * An instance method that returns whether the task object is done.
     * 
     * @return A boolean, true if task is marked done, false otherwise.
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * An instance method that returns the item of the task, i.e. its description.
     * 
     * @return A string, which is the item of the task.
     */
    public String getItem() {
        return this.item;
    }

    public String stringify() {
        return this.item;
    }

    /**
     * A static method that returns a Task object created using a given task
     * description.
     * 
     * @param taskDescription A string that provides information about the task.
     * @return A task object, either a todo, a deadline, or an event object, with
     *         all its properties set as per the description given.
     */
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

            Deadline task = new Deadline(info[4], marked, LocalDate.parse(info[6]), info[6]);
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