public class Duke {
    private String name;
    public Duke(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you?\n");
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }
    public static void main(String[] args) {
        Duke chatbot = new Duke("Bob");
        chatbot.greet();
        chatbot.exit();
    }
}
