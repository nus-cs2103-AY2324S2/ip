public class Program {

    private final String NAME = "Linus";
    private final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public void start() {
        System.out.println(this.BORDER);
        String greeting = String.format("Hello I'm %s", this.NAME);
        String request = "What can I do for you?";
        this.textFormat(String.format("%s \n%s", greeting, request));
        this.end();
    }

    private void end() {
        String exit = "Bye. Hope to see you again soon!";
        this.textFormat(exit);
    }

    private void textFormat(String text) {
        System.out.println(String.format("%s \n%s", 
        text, 
        this.BORDER));
    }
    
}
