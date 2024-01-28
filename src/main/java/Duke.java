public class Duke {
    private static final String NAME = "Arctic";
    private static final Integer BORDER_LEN = 60;

    private static String duplicateChar(Character c, Integer num) {
        return String.valueOf(c).repeat(num);
    }
    private static void userGreeting () {
        System.out.println(duplicateChar('_', BORDER_LEN));
        System.out.printf("Hello! I'm %s\n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(duplicateChar('_', BORDER_LEN));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(duplicateChar('_', BORDER_LEN));
    }
    public static void main(String[] args) {
        userGreeting();
    }
}
