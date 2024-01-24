import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ATSISBot {

  private static final String line = "____________________________________________________________\n";

  private static final String welcomeMessage = line + "Hello! I'm ATSISBot\n" + "What can I do for you?\n" + line;
  private static final String endingMessage = line + "Bye. Hope to see you again soon!\n" + line;

  private static ArrayList<String> list = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println(ATSISBot.welcomeMessage);

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    while (!input.equals("bye")) {
      System.out.print(line);
      if (input.equals("list")) {
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(element -> System.out.println(index.getAndIncrement() + ". " + element));
      } else {
        list.add(input);
        String output = input;
        System.out.println(output);
      }
      System.out.print(line);
      input = sc.nextLine();
    }

    System.out.println(ATSISBot.endingMessage);
  }
}
