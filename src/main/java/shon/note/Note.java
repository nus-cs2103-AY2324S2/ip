package shon.note;

/**
 * Represents a note that contains small snippets of textual information the user wants
 * to record.
 */
public class Note {
    private String text;

    public Note(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
