package tiny.extensions;

/**
 * Represents a note.
 */
public class Note {
    protected String title;
    protected String body;

    /**
     * Initializes Note.
     *
     * @param title Title of the note.
     * @param body  Body of the note.
     */
    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String formatToSave() {
        return "NO | " + title + " | " + body;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Body: " + body;
    }
}
