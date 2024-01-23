public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startChat();
    }
    public void startChat() {
        sayHi();
        sayBye();
    }
    public void sayHi() {
        System.out.println("Hello! I'm myChats\n" + "What can I do for you?\n");
    }
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}