import java.util.Scanner;

public class Duke {
    private static String hRULER = "____________________________________________________________\n";
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            int token = Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
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
                System.out.printf("%s Here are the tasks in your list:\n", hRULER);
                for (int i = 0; i < currentIdx; i++) {
                    System.out.printf(" %d.%s\n", i + 1, storage[i].toString());
                }
                System.out.println(hRULER);
                continue;
            }
            if (echoInput.substring(0, 4).equals("mark")
                    && isNumeric(echoInput.substring(5, echoInput.length()))) {
                int taskIdx = Integer.parseInt(echoInput.substring(5, echoInput.length()));
                storage[taskIdx - 1].markDone();
                continue;
            }
            if (echoInput.substring(0, 6).equals("unmark") &&
                    isNumeric(echoInput.substring(7, echoInput.length()))) {
                int taskIdx = Integer.parseInt(echoInput.substring(7, echoInput.length()));
                storage[taskIdx - 1].unMarkDone();
                continue;
            }

            Task newTask = new Task(echoInput);
            storage[currentIdx++] = newTask;
            System.out.printf("%s added: %s\n%s", hRULER, newTask.toString(), hRULER);
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
