import java.util.Scanner;
public class Duke {
    private String[] tasks;
    private int no_of_tasks;
    public static void main(String[] args) {
        Duke greg = new Duke();
    }

    public Duke() {
        tasks = new String[100];
        no_of_tasks = 0;
        greet();
        listen();
    }
    public void greet() {
        fillerLine();
        System.out.println("    Hello! I am Greg.\n    What can I do for you?");
        fillerLine();
    }

    public void bye() {
        fillerLine();
        System.out.println("    Goodbye! Hope to see you again soon!");
        fillerLine();
    }

    public static void fillerLine() {
        System.out.println("    _______________________________________");
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        while (true) {
            if (s.equals("bye")) {
                bye();
                break;
            } else {
                if (s.equals("list")) {
                    list();
                } else {
                    add(s);
                }
                s = sc.next();
            }
        }
    }

    public void list() {
        for(int i = 0; i < no_of_tasks; i++) {
            String str = "";
            str = String.format("%s: %s",i + 1, tasks[i]);
            System.out.println(str);
        }
    }

    public void add(String s) {
        tasks[no_of_tasks] = s;
        no_of_tasks++;
        fillerLine();
        System.out.println("    added: " + s);
        fillerLine();
    }
}
