import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        String logo = "____________________________________________________________\n" +
                " Hello! I'm Gluti\n" +
                " What can I do for you?\n";
        String end = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(logo);
        String word = sc.nextLine();
        System.out.println(word);
        System.out.println(end);
    }
}
