import java.util.Scanner;

public class Goblin {
    static String greetings = "Hello！ I'm NetGoblin\n"
            + "What can I do for you?\n";
    static String bye = "Bye. Hope to see you agian soon!\n";
    public static void main(String[] args) {
        line();
        sayHello();
        line();
        sayBye();
        line();
    }

    public static void line() {
        System.out.println("--------------------------------");
    }
    public static void sayHello() {
        System.out.println(greetings);
    }

    public static void sayBye() {
        System.out.println(bye);
    }
}
