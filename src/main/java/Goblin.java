import java.util.Scanner;
import java.util.ArrayList;
public class Goblin {
    static String greetings = "Hello！ I'm NetGoblin\n"
            + "What can I do for you?\n";
    static String bye = "Bye. Hope to see you agian soon!\n";

    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        sayHello();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                sayBye();
                input.close();
                break;
            } else if (userInput.equals("list")){
                showList();
            } else {
                line();
                System.out.println("added: " + userInput);
                line();
                list.add(userInput);
            }
        }
    }
    public static void line() {
        System.out.println("--------------------------------");
    }

    public static void sayHello() {
        line();
        System.out.println(greetings);
        line();
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

    public static void showList() {
        line();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
    }
}
