public class Duke {
    public static void main(String[] args) {
        String logo =  " _____ _               _\n"
                +       "/  __ (_)             | |      \n"
                +       "| /  \\/_  ___ __ _  __| | __ _ \n"
                +       "| |   | |/ __/ _` |/ _` |/ _` |\n"
                +       "| \\__/\\ | (_| (_| | (_| | (_| |\n"
                +       " \\____/_|\\___\\__,_|\\__,_|\\__,_|\n";

        System.out.println("Hello from\n" + logo);
        String horizontalLine = "-".repeat(60);
        greeting("Cicada", horizontalLine);
        ending(horizontalLine);
    }

    public static void greeting(String chatbotName, String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + chatbotName
                + "\nWhat can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void ending(String horizontalLine) {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
