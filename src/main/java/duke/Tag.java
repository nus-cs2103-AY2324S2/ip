package duke;

/**
 * A class that represents a task's tag.
 */
public class Tag {
    private String value = "";

    Tag() {

    }
    Tag(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.equals("") ? value : String.format("[%s]", value);
    }

    public String getInput() {
        return value;
    }
}
