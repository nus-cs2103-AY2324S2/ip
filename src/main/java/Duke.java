import java.io.*;
import java.util.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = "   __     __   _  _    _  _           \n"
                    + " / __ \\  |  \\ | | \\ \\/ / | |            \n"
                    + "| |__| | |   \\| |  \\  /  | |     \n"
                    + "|  __  | | |\\   |  /  \\  | |\n"
                    + "|_|  |_| |_| \\__| /_/\\_\\ |_|\n";
        String name = "Anxi";
        String line = "--------------------------------------";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> tasks = new ArrayList<String>();

        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm " + name + "\r\nWhat can I do for you? \r\n" + line);

        String item = br.readLine();
        while(!item.equalsIgnoreCase("bye")) {
            System.out.println(line);

            if (item.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); ++i) {
                    System.out.print(i + 1);
                    System.out.println(". " + tasks.get(i));
                }
            } else {
                tasks.add(item);
                System.out.println("added: " + item);
            }

            System.out.println(line);
            item = br.readLine();
        }

        System.out.println(line + "\r\nBye. Hope to see you again soon!\r\n" + line);
        br.close();
    }
}
