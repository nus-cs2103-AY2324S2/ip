public class Duke {

    private void greet(){
        System.out.println("Hello! I'm Balom.\nWhat can I do for you today?\n");
    }
    private void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke Balom = new Duke();
        Balom.greet();
        Balom.bye();

    }
}
