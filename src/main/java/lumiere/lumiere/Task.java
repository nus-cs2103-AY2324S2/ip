package lumiere.lumiere;

import java.time.LocalDate;

public class Task {
    private boolean isMarked;
    private String item;
    private boolean isFun;

    /**
     * Constructor for Task object
     * 
     * @param item     The item that describes what the task is about, i.e. what
     *                 needs
     *                 to be done
     * @param isMarked A boolean that indicates whether this task is marked as done
     *                 or
     *                 not.
     * @param isFun    A boolean that indicates whether this task is tagged as #fun
     *                 or not.
     * @return Nothing, it is a constructor.
     */
    public Task(String item, boolean isMarked, boolean isFun) {
        this.item = item;
        this.isMarked = isMarked;
        this.isFun = isFun;
    }

    /**
     * An instance method that marks the task object as done.
     * 
     * @return Nothing, it is a void method.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * An instance method that marks the task object as not done.
     * 
     * @return Nothing, it is a void method.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * An instance method that marks the task object as fun.
     * 
     * @return Nothing, it is a void method.
     */
    public void markAsFun() {
        this.isFun = true;
    }

    /**
     * An instance method that marks the task object as not fun.
     * 
     * @return Nothing, it is a void method.
     */
    public void unmarkAsFun() {
        this.isFun = false;
    }

    /**
     * An instance method that returns whether the task object is done.
     * 
     * @return A boolean, true if task is marked done, false otherwise.
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * An instance method that returns whether the task object is fun.
     * 
     * @return A boolean, true if task is marked fun, false otherwise.
     */
    public boolean isFun() {
        return this.isFun;
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
            return createTodo(info);
        } else if (taskDescription.startsWith("D")) {
            return createDeadline(info);
        } else {
            return createEvent(info);
        }
    }

    private static Todo createTodo(String[] info) {
        boolean isMarked;
        boolean isFun;
        if (info[2].equals("true"))
            isMarked = true;
        else
            isMarked = false;

        if (info[6].equals("true"))
            isFun = true;
        else
            isFun = false;

        Todo task = new Todo(info[4], isMarked, isFun);
        return task;
    }

    private static Deadline createDeadline(String[] info) {
        boolean isMarked;
        boolean isFun;
        if (info[2].equals("true"))
            isMarked = true;
        else
            isMarked = false;

        if (info[8].equals("true"))
            isFun = true;
        else
            isFun = false;

        Deadline task = new Deadline(info[4], isMarked, isFun, LocalDate.parse(info[6]), info[6]);
        return task;
    }

    private static Event createEvent(String[] info) {
        boolean isMarked;
        boolean isFun;
        if (info[2].equals("true"))
            isMarked = true;
        else
            isMarked = false;

        if (info[10].equals("true"))
            isFun = true;
        else
            isFun = false;
        Event task = new Event(info[4], isMarked, isFun, info[6], info[8]);
        return task;
    }
}