import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Buto {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Buto\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________\n"
        );

        String command = br.readLine();
        while (!command.equals("bye")) {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            String.format("    %s\n", command) +
                            "    ____________________________________________________________"
            );
            command = br.readLine();
        }

        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n"
        );
    }
}