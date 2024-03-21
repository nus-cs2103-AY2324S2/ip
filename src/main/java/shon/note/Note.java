package shon.note;

/**
 * Represents a note that contains small snippets of textual information the user wants
 * to record.
 */
public class Note {
    /**
     * The text of the note.
     */
    private String text;

    /**
     * Creates a new note with the given text.
     * @param text The text of the note.
     */
    public Note(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
