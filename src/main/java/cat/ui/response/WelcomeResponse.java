package cat.ui.response;

public class WelcomeResponse extends NoteResponse {
    public WelcomeResponse() {
        super(blurb(), info());
    }

    private static String catAscii() {
        return " |\\ /| \n"
                + "=(O O)=\n"
                + " /   \\ \n";
    }

    private static String blurb() {
        return catAscii()
                + "The cat that lives in your walls pokes its head out.";
    }

    private static String info() {
        return "It seems like it's waiting for a response";
    }
}
