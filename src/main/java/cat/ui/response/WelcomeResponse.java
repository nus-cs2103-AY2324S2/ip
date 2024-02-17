package cat.ui.response;

public class WelcomeResponse extends Response {
    public WelcomeResponse(String blurb, String output) {
        super(welcomeBlurb(), output);
        setOutputColor("ffffff");
    }

    private static String catAscii() {
        return " |\\ /| \n"
                + "=(O O)=\n"
                + " /   \\ \n";
    }

    private static String welcomeBlurb() {
        return catAscii() + "The cat that lives in your walls pokes its head out.";
    }
}
