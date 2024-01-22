public class Duke {
    public static void main(String[] args) {
        drawLine();
        greet();
        drawLine();
        bye();
        drawLine();
    }

    public static void drawLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        String name = "Cortana";
        String sentence1 = "Hello! I'm " + name;
        String sentence2 = "What can I do for you?";
        System.out.println(sentence1);
        System.out.println(sentence2);
    }

    public static void bye() {
        String sentence = "Bye. Hope to see you again soon!";
        System.out.println(sentence);
    }

}
