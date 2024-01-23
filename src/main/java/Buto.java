import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Buto {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> textList = new ArrayList<>();

        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Buto\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________\n"
        );

        String command = br.readLine();
        
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                for (int i = 1; i <= textList.size(); i++) {
                    System.out.println(String.format("    %d. %s\n", i, textList.get(i-1)));
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
                textList.add(command);
                System.out.println(
                        "    ____________________________________________________________\n" +
                                String.format("    added: %s\n", command) +
                                "    ____________________________________________________________\n"
                );
            }
            command = br.readLine();
        }

        br.close();

        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n"
        );
    }
}