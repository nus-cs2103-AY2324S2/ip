public class Toothless {
    private static String indentation = "____________________________________________________________\n";
    private static String chatBotName = "Toothless";
    private static String greetingString = "Hello! I'm " + chatBotName + "!\n"
                            + "What can I do for you?\n" + indentation;
    private static String exitString = "Bye. Hope to see you again soon!\n" + indentation;

    public static void main(String[] args){
        System.out.println(indentation + greetingString + exitString);
    }
}
