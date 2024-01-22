public class Duke {
    static String botName = "Corgi";
    public static void greet(){
        String greetMessage = String.format(" Hello! I'm %s\n" + " What can I do for you?\n", botName);
        System.out.println(greetMessage);
    }
    public static void goodbye(){
        String exitMessage = " Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public static void main(String[] args) {
        greet();
        goodbye();
    }
}
