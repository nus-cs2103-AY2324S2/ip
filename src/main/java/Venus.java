import java.util.ArrayList;
import java.util.Scanner;
public class Venus {

    public static void main(String[] args) {
        String start =
                "    ____________________________________________________________\n"
                        + "    Hello! I'm Venus\n"
                        + "    What can I do for you?\n"
                        + "    ____________________________________________________________\n";
        String end =
                "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________\n";
        String indented_lines = "    ____________________________________________________________\n";
        System.out.println(start);

        ArrayList<taskedString> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();

        while (!words.equals("bye")) {
            if (words.equals("list")) {
                int i = 1;
                System.out.println(indented_lines
                        + "     Here are the tasks in your list:");
                for (taskedString s : data) {
                    System.out.println("     " + i + "." + s);
                    i++;
                }
                System.out.println(indented_lines);
            } else if (words.startsWith("mark ")) {
                try {
                    int index = Integer.valueOf(words.substring(5)) - 1;
                    data.get(index).mark();
                    System.out.println(indented_lines
                            + "     Nice! I've marked this task as done:\n"
                            + "       "
                            + data.get(index).toString()
                            + "\n"
                            + indented_lines);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (words.startsWith("unmark ")) {
                try {
                    int index = Integer.valueOf(words.substring(7)) - 1;
                    data.get(index).unmark();
                    System.out.println(indented_lines
                            + "     OK, I've marked this task as not done yet:\n"
                            + "       "
                            + data.get(index).toString()
                            + "\n"
                            + indented_lines);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                taskedString ts = new taskedString(words);
                data.add(ts);
                System.out.println(indented_lines
                        + "     added: "
                        + words +
                        "\n" + indented_lines);
            }
            words = sc.nextLine();
        }

        sc.close();
        System.out.println(end);
    }
}
