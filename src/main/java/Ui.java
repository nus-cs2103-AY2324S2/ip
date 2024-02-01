import java.util.Scanner;

public class Ui {
    public void welcome() {
        System.out.println(
                "__________________________________________________________\n"
                        + "Hello! I'm KitchenSink!\n"
                        + "What can I do for you?\n"
                        + "__________________________________________________________\n"
        );
    }

    public void sayGoodBye() {
        System.out.println(
                "__________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "__________________________________________________________"
        );
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
