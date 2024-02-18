package notes;

public class Note {


    private String description;


    /**
     * Constructs a Note object with the given description.
     *
     * @param description The description of the note.
     */
    public Note(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns a string representation of the Note object.
     *
     * @return A string representation of the task.ToDo object.
     */
    @Override
    public String toString() {
        return "Note: " + getDescription();
    }
}