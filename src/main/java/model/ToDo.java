package model;

/**
 * The {@code ToDo} class represents "To Do" tasks.
 * 
 * <p> This is an immutable class.
 * 
 * <p> Contains:
 * <ul>
 * <li>a {@code String} name.</li>
 * <li>a {@code boolean} done state.</li>
 * </ul>
 */
public class ToDo implements Task {
    private final String name;
    private final boolean isDone;
    private final String tag;

    /**
     * Creates a new {@code ToDo} object, with {@code done} set to {@code false} by default.
     * 
     * @param name Name of the "To Do" task.
     */
    public ToDo(String name) {
        this.name = name;
        isDone = false;
        tag = "";
    }

    private ToDo(String name, boolean done, String tag) {
        this.name = name;
        this.isDone = done;
        this.tag = tag;
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@code ToDo} object with {@code done} set as {@code true}.
     */
    public ToDo mark() {
        return new ToDo(name, true, tag);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@code ToDo} object with {@code done} set as {@code false}.
     */
    public ToDo unmark() {
        return new ToDo(name, false, tag);
    }

    public boolean nameContains(String s) {
        return name.contains(s);
    }

    public ToDo tag(String tag) {
        return new ToDo(name, isDone, tag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String d = this.isDone ? "X" : " ";
        String t = tag.isBlank() ? "" : " #" + tag;
        return String.format("[T][%s] %s%s", d, name, t);
    }
}
