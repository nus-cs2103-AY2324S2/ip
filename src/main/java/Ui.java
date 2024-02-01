import java.util.Scanner;

public class Ui {
    public static final String LINE = "__________________________________________________________\n";
    public void welcome() {
        System.out.println(LINE + "Hello! I'm KitchenSink!\n" + "What can I do for you?\n" + LINE);
    }

    public void sayGoodBye() {
        System.out.println(LINE + "Bye. Hope to see you again soon!\n" + LINE);
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
