import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "shu heng";
        String name_display = "_________________________\n" +
          "Hello! I'm " + name + "\n" +
          "What can I do for you?\n" +
          "_________________________\n";
      System.out.println(name_display);
      String current_input = "";

      while (!current_input.equals("bye")) {
        current_input = sc.nextLine();
        String to_print = "_________________________\n" +
          current_input + "\n" +
          "_________________________\n";
        System.out.println(to_print);
      }

      String final_print = "_________________________\n" +
        "Bye. Hope to see you again soon!\n" +
        "_________________________\n";
      System.out.println(final_print);
      sc.close();
    }
}
