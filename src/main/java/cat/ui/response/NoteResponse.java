package cat.ui.response;

/**
 * A response with a note for the user.
 */
public class NoteResponse extends Response {
    /**
     * Constructs a note.
     * @param blurb The blurb for emoting the cat.
     * @param info The actual note information.
     */
    public NoteResponse(String blurb, String info) {
        super(blurb, info);
        setOutputColor(Colors.BRIGHT_WHITE);
    }
}
