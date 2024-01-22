import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        // templates
        String line = "\t____________________________________________________________\n";
        String intro = line +
                "\t Hello! I'm Megatron\n" +
                "\t What can I do for you?\n" +
                line;
        String outro = line +
                "\t Bye. Hope to see you again soon!\n" +
                line;

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String[] store = new String[100];
        int numItems = 0;

        System.out.println(intro);

        while (!exit) {
            String cmd = sc.nextLine();

            switch (cmd) {
                case "list":
                    String toPrint = "";
                    for (int i = 0; i < numItems; i++) {
                        toPrint += "\t " + (i + 1) + ". " + store[i] + "\n";
                    }
                    System.out.println(line + toPrint + line);
                    break;
                case "bye":
                    System.out.println(outro);
                    exit = true;
                    break;
                default:
                    store[numItems++] = cmd;
                    System.out.println(
                            line + "\t" + "added: " + cmd + "\n" + line
                    );
            }
        }
    }
}
