public class Duke {

    private final String name;
    public Duke(String name) {
        this.name = name;
    }
//
    public String greet() {
        return "Hello! I'm " + this.name + "\n" + "What can I do for you?";
    }
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static void main(String[] args) {
        Duke Lery = new Duke("Lery");

        System.out.println(Lery.greet());
        System.out.println(Lery.exit());

    }


}
