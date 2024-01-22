public class Huyang {
    private String logo =
            "  ___ ___                                      \n" +
                    " /   |   \\ __ __ ___.__._____    ____    ____  \n" +
                    "/    ~    \\  |  <   |  |\\__  \\  /    \\  / ___\\ \n" +
                    "\\    Y    /  |  /\\___  | / __ \\|   |  \\/ /_/  >\n" +
                    " \\___|_  /|____/ / ____|(____  /___|  /\\___  / \n" +
                    "       \\/        \\/          \\/     \\//_____/  ";

    public void greet() {
        // Greeting message
        System.out.println("Hello! I'm Huyang");
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public void farewell() {
        // Farewell message
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Huyang huyang = new Huyang();

        huyang.greet();
        huyang.farewell();
    }
}
