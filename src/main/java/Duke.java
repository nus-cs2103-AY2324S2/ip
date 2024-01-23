import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "    ____________________________________________________________\n";
        ArrayList<String> textList = new ArrayList<>();

        System.out.println(
                line +
                        "     Hello! I'm Buto\n" +
                        "     What can I do for you?\n" +
                        line
        );

        String command = br.readLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(line);
                for (int i = 1; i <= textList.size(); i++) {
                    System.out.printf("    %d. %s\n", i, textList.get(i-1));
                }
                System.out.println(line);
            } else {
                textList.add(command);
                System.out.println(
                        line +
                                String.format("    added: %s\n", command) +
                                line
                );
            }
            command = br.readLine();
        }

        br.close();

        System.out.println(
                line +
                        "     Bye. Hope to see you again soon!\n" +
                        line
        );
    }
}