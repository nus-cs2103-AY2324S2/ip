public class Ui {
    public void greet() {
        String logo =
                "  ______\n" +
                " |           /  \\ \n" +
                " |______    /____\\     / \\    / \\\n" +
                "        |  /      \\   /   \\  /   \\\n" +
                "  ______| /        \\ /     \\/     \\\n";
        String greetMessage = "Hello! I'm Sam\n" +
                "How can I help you?";
        System.out.println(logo);
        System.out.println(greetMessage);
    }
    public void bye() {
        String byeMessage = "Hope you enjoy my help!";
        System.out.println(byeMessage);
    }
}
