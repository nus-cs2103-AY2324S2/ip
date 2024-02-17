import java.util.Scanner;

public class Goblin {
    static String greetings = "Hello！ I'm NetGoblin\n"
            + "What can I do for you?\n";
    static String bye = "Bye. Hope to see you agian soon!\n";

    public static void main(String[] args) {
        line();
        sayHello();
        line();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String userInput = input.next();
            if (userInput.equals("bye")) {
                sayBye();
                input.close();
                break;
            } else {
                echo(userInput);
            }
        }
    }
    public static void line() {
        System.out.println("--------------------------------");
    }

    public static void sayHello() {
        System.out.println(greetings);
    }

    public static void sayBye() {
        line();
        System.out.println(bye);
    }

    public static void echo(String input) {
        line();
        System.out.println(input);
        line();
    }
}
