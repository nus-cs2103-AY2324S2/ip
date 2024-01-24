package chatbot;

public class Plana {
    private static final String logo =
            "    ____  __                 \n" +
            "   / __ \\/ /___ _____  ____ _\n" +
            "  / /_/ / / __ `/ __ \\/ __ `/\n" +
            " / ____/ / /_/ / / / / /_/ / \n" +
            "/_/   /_/\\__,_/_/ /_/\\__,_/  \n";

    private static final String name = "Plana";

    public void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("What can I do for you?");
        System.out.println("======================");
    }

    public void bye() {
        System.out.println("==================");
        System.out.println("See you next time!");
    }
}
