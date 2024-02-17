package cat.ui.response;

public class NoteResponse extends Response {
    public NoteResponse(String blurb, String output) {
        super(blurb, output);
        setOutputColor(Colors.BRIGHT_WHITE);
    }
}
