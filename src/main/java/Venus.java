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
             +  "    Bye. Hope to see you again soon!\n"
             +  "    ____________________________________________________________\n";
        String indented_lines = "    ____________________________________________________________\n";
        System.out.println(start);

        ArrayList<String> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        while (!words.equals("bye")) {
            if (!words.equals("list")) {
                data.add(words);
                System.out.println(indented_lines
                        + "     added: "
                        + words +
                        "\n" + indented_lines);
            }
            else {
                int i = 1;
                for (String s : data) {
                    System.out.println("     " + i + ". " + s);
                    i++;
                }
            }
            words = sc.nextLine();
        }
        sc.close();
        System.out.println(end);
    }
}
