import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        int tracker = 0;
        String[] mylist = new String[100];
        Intro Hi = new Intro();
        Hi.response();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                Farewell Bye = new Farewell();
                Bye.response();
                break;
            }
            else if (command.equals("list")) {
                for (int i = 0; i < tracker; i++) {
                    System.out.println("  " + (i + 1) + ". " + mylist[i]);
                }
            }
            else {
                mylist[tracker] = command;
                tracker += 1;
                Echo repeat = new Echo();
                repeat.response(command);
            }
        }
    }
}


