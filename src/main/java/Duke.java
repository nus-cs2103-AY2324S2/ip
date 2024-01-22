import java.util.ArrayList;
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

      ArrayList<Task> history = new ArrayList<>();

      while (!current_input.equals("bye")) {
        current_input = sc.nextLine();
        String[] current_input_split = current_input.split(" ");

        if (current_input.equals("list")) {
          System.out.println("_________________________\n" +
            "Get off your ass and starting doing work! " + "\n");
          for (int i = 0; i < history.size(); i++) {
            Task curr = history.get(i);
            System.out.println((i + 1) + "." +
              curr.getStatusIcon() + " " + curr.getDescription());
          }
          System.out.println("_________________________\n");
        } else if (current_input_split[0].equals("mark")) {
          int focus_index = Integer.parseInt(current_input_split[1]) - 1;
          Task focus_task = history.get(focus_index);
          focus_task.mark();
          String print_out = "_________________________\n" +
            "Marking this done! " + "\n" +
            focus_task.getStatusIcon() + " " + focus_task.getDescription() + "\n" +
            "_________________________\n";
          System.out.println(print_out);
        } else if (current_input_split[0].equals("unmark")) {
          int focus_index = Integer.parseInt(current_input_split[1]) - 1;
          Task focus_task = history.get(focus_index);
          focus_task.unmark();
          String print_out = "_________________________\n" +
            "Ok this is not done... " + "\n" +
            focus_task.getStatusIcon() + " " + focus_task.getDescription() + "\n" +
            "_________________________\n";
          System.out.println(print_out);
        } else {
          history.add(new Task(current_input));
          String to_print = "_________________________\n" +
            "added: " + current_input + "\n" +
            "_________________________\n";
          System.out.println(to_print);
        }
      }

      String final_print = "_________________________\n" +
        "Bye. Hope to see you again soon!\n" +
        "_________________________\n";
      System.out.println(final_print);
      sc.close();
    }
}
