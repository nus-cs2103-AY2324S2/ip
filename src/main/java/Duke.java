public class Duke {
    private void greet() {
        System.out.println(TextTemplate.LINE_BREAK);
        System.out.println(TextTemplate.GREETING);
        System.out.println(TextTemplate.LINE_BREAK);
    }

    private void bye() {
        System.out.println(TextTemplate.EXIT);
        System.out.println(TextTemplate.LINE_BREAK);
    }
    public void run() {
        this.greet();
        this.bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
