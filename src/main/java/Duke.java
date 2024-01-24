import java.util.Scanner;

public class Duke {
    private static final String hRULER = "____________________________________________________________\n";
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
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
                    && isNumeric(echoInput.substring(5))) {
                int taskIdx = Integer.parseInt(echoInput.substring(5));
                storage[taskIdx - 1].markDone();
                continue;
            }
            if (echoInput.substring(0, 6).equals("unmark") &&
                    isNumeric(echoInput.substring(7))) {
                int taskIdx = Integer.parseInt(echoInput.substring(7));
                storage[taskIdx - 1].unMarkDone();
                continue;
            }

            Task newTask;

            if (echoInput.substring(0, 4).equals("todo")) {
                newTask = new ToDo(echoInput.substring(5));
            }
            else if (echoInput.substring(0, 8).equals("deadline")) {
                int deadlineStartIdx = 0;
                while (deadlineStartIdx < echoInput.length()) {
                    if (echoInput.charAt(deadlineStartIdx) != '/') {
                        deadlineStartIdx++;
                    } else {
                        break;
                    }
                }
                newTask = new Deadline(echoInput.substring(9, deadlineStartIdx),
                            echoInput.substring(deadlineStartIdx + 4));
            }
            else if (echoInput.substring(0, 5).equals("event")) {
                int startIdx = 0;
                while (startIdx < echoInput.length()) {
                    if (echoInput.charAt(startIdx) != '/') {
                        startIdx++;
                    } else {
                        break;
                    }
                }
                int endIdx = startIdx + 1;
                while (endIdx < echoInput.length()) {
                    if (echoInput.charAt(endIdx) != '/') {
                        endIdx++;
                    } else {
                        break;
                    }
                }
                newTask = new Event(echoInput.substring(6, startIdx),
                        echoInput.substring(startIdx + 6, endIdx),
                        echoInput.substring(endIdx + 4));
            } else {
                newTask = new Task(echoInput);
            }
            storage[currentIdx++] = newTask;
            System.out.printf("%s Got it. I've added this task:\n  " +
                    " %s\n Now you have %d tasks in the list.\n%s", hRULER, newTask, currentIdx, hRULER);
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
    }
}
