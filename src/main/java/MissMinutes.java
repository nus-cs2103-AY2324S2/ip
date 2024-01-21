public class MissMinutes {
    public static void main(String[] args) {
        String logo =
                " __  __ _           __  __ _             _            \n" +
                " |  \\/  (_)         |  \\/  (_)           | |           \n" +
                " | \\  / |_ ___ ___  | \\  / |_ _ __  _   _| |_ ___  ___ \n" +
                " | |\\/| | / __/ __| | |\\/| | | '_ \\| | | | __/ _ \\/ __|\n" +
                " | |  | | \\__ \\__ \\ | |  | | | | | | |_| | ||  __/\\__ \\\n" +
                " |_|  |_|_|___/___/ |_|  |_|_|_| |_|\\__,_|\\__\\___||___/\n" +
                "                                                       \n";

        String separator = "-".repeat(60);

        System.out.println(separator);
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you\n");

        System.out.println(separator);

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}