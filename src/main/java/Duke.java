import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    private static String hRULER = "____________________________________________________________\n";
    private static void run() {
        Task[] storage = new Task[100];
        int currentIdx = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            String echoInput = sc.nextLine();
            if (echoInput.equals("bye")) {
                return;
            }
            if (echoInput.equals("list")) {
                System.out.print(hRULER);
                for (int i = 0; i < currentIdx; i++) {
                    System.out.printf(" %d. %s\n", i + 1, storage[i].toString());
                }
                System.out.println(hRULER);
                continue;
            }
            Task newTask = new Task(echoInput);
            storage[currentIdx++] = newTask;
            String toPrint = "____________________________________________________________\n added: " +
                    newTask.toString() + "\n____________________________________________________________";
            System.out.println(toPrint);
        }
    }
    public static void main(String[] args) {
        String[] token = new String[]{
                "____________________________________________________________",
                "Hello! I'm chinesepoliceman",
                "What can I do for you?",
                "____________________________________________________________",
                " Bye. Hope to see you again soon!",
                "____________________________________________________________"
        };
        for (int i = 0; i < 4; i++) {
            System.out.println(token[i]);
        }
        run();
        for (int i = 3; i < 6; i++) {
            System.out.println(token[i]);
        }
        return;
    }
}
