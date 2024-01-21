public class Duke {
    public static void main(String[] args) {
        greet();
    }

    public static void speak(String str) {
        System.out.println(str);
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        String logo = " ____    _    ___  ____ ___ ____   ____   ___ _____ \n" +
                "/ ___|  / \\  / _ \\|  _ \\_ _/ ___| | __ ) / _ \\_   _|\n" +
                "\\___ \\ / _ \\| | | | |_) | | |  _  |  _ \\| | | || |  \n" +
                " ___) / ___ \\ |_| |  __/| | |_| | | |_) | |_| || |  \n" +
                "|____/_/   \\_\\___/|_|  |___\\____| |____/ \\___/ |_|\n";
        speak(logo);
        speak("Hello! I'm SAOPIG BOT\nWhat can I do for you?");
        speak("Bye. Hope to see you again soon!");
    }
}
