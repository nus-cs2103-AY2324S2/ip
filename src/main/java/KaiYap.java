import java.util.Scanner;
import java.util.ArrayList;
public class KaiYap {

    ArrayList<String> inputList;

    public KaiYap() {
        inputList = new ArrayList<>();
    }
    public void sayHello() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm KaiYap.\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
    }

    public void sayBye() {
        System.out.println("\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________");
    }

    public void startChat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.listInputs();
            } else {
                this.echo(input);
            }
            input = sc.nextLine();
        }
    }

    public void listInputs() {
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < this.inputList.size(); i++) {
            System.out.println("\t" +
                    (i + 1) +
                    ". " +
                    this.inputList.get(i)
            );
        }
        System.out.println("\t____________________________________________________________");
    }
    public void echo(String input) {
        System.out.println("\t____________________________________________________________\n" +
                "\tadded: " + input + "\n" +
                "\t____________________________________________________________\n");
        this.inputList.add(input);
    }

    public static void main(String[] args) {
        KaiYap yap = new KaiYap();
        yap.sayHello();
        yap.startChat();
        yap.sayBye();
    }
}
