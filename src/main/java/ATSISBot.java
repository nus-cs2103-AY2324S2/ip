import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Pair<S, T> {
  private S first;
  private T second;

  public Pair(S first, T second) {
    this.first = first;
    this.second = second;
  }

  public S getFirst() {
    return this.first;
  }

  public T getSecond() {
    return this.second;
  }

  public void setFirst(S first) {
    this.first = first;
  }

  public void setSecond(T second) {
    this.second = second;
  }

  @Override
  public String toString() {
    return this.first + " " + this.second;
  }
}

public class ATSISBot {

  private static final String line = "____________________________________________________________\n";

  private static final String welcomeMessage = line + "Hello! I'm ATSISBot\n" + "What can I do for you?\n" + line;
  private static final String endingMessage = line + "Bye. Hope to see you again soon!\n" + line;
  private static final String listMessage = "Here are the tasks in your list:\n";
  private static final String markMessage = "Nice! I've marked this task as done:\n";
  private static final String unmarkMessage = "OK, I've marked this task as not done yet:\n";

  private static ArrayList<Pair<Boolean, String>> list = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println(ATSISBot.welcomeMessage);

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    while (!input.equals("bye")) {
      System.out.print(line);
      if (input.equals("list")) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.print(listMessage);
        list.forEach(element -> System.out
            .println(index.getAndIncrement() + ". [" + (element.getFirst() ? "X" : " ") + "] " + element.getSecond()));
      } else if (input.startsWith("mark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        list.get(elementIdx - 1).setFirst(true);
        System.out.println(markMessage);
        System.out.println("  [X] "
            + list.get(elementIdx - 1).getSecond());
      } else if (input.startsWith("unmark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        list.get(elementIdx - 1).setFirst(false);
        System.out.println(unmarkMessage);
        System.out.println("  [ ] "
            + list.get(elementIdx - 1).getSecond());
      } else {
        list.add(new Pair<>(false, input));
        String output = input;
        System.out.println(output);
      }
      System.out.print(line);
      input = sc.nextLine();
    }

    System.out.println(ATSISBot.endingMessage);
  }
}
