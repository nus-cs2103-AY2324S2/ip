public class Duke {
    public static final String DIVIDER = "────────────────────────────────────────────────────────────";
    public static final String GREETING = "Hello! I'm Seiki\nHow may I assist you today?";
    public static final String FAREWELL = "Goodbye! If you ever need assistance in the future, don't hesitate to reach out. Take care!";
    public static final String LOGO = "  _____      _  _     _\n"
                                    + " / ____|    (_)| | _ (_)\n"
                                    + "| (___  ___  _ | |/ / _\n"
                                    + " \\___ \\/ _ \\| ||   / | |\n"
                                    + " ____) | __/| || | \\ | |\n"
                                    + "|_____/\\___||_||_|\\_\\|_|\n";

    public static void start() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    public static void end() {
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }
    public static void main(String[] args) {
        start();
        end();
    }
}
