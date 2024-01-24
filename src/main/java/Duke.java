import java.io.*;

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

        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm " + name + "\r\nWhat can I do for you? \r\n" + line);

        String task = br.readLine();
        while(!task.equalsIgnoreCase("bye")) {
            System.out.println(line + "\r\n" + task + "\r\n" + line);
            task = br.readLine();
        }

        System.out.println(line + "\r\nBye. Hope to see you again soon!\r\n" + line);
    }
}
