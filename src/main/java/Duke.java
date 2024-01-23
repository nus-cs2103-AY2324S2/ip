public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startChat();
    }
    
    /**
     * Initiates the chat by invoking the sayHi() and sayBye() methods.
     */
    public void startChat() {
        sayHi();
        sayBye();
    }

    /**
     * Displays a starting message to greet the user.
     */
    public void sayHi() {
        System.out.println("Hello! I'm myChats\n" + "What can I do for you?\n");
    }

    /**
     *  Displays an exit message.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}