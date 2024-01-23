import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] token = new String[]{
                "____________________________________________________________",
                "Hello! I'm chinesepoliceman",
                "What can I do for you?",
                "____________________________________________________________",
                "Bye. Hope to see you again soon!",
                "____________________________________________________________"
        };
        for (int i = 0; i < 4; i++) {
            System.out.println(token[i]);
        }
        Scanner sc = new Scanner(System.in);

        while (true) {
            String echoInput = sc.next();
            if (!echoInput.equals("bye")) {
                String echoString = "____________________________________________________________\n" + echoInput +
                        "\n____________________________________________________________";
                System.out.println(echoString);
            } else {
                break;
            }
        }
        for (int i = 3; i < 6; i++) {
            System.out.println(token[i]);
        }
        return;
    }
}
