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

        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        while (!words.equals("bye")) {
            System.out.println(indented_lines + "    " + words + "\n" + indented_lines);
            words = sc.nextLine();
        }
        sc.close();
        System.out.println(end);
    }
}
