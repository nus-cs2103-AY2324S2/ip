import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String divider = "____________________________________________________________";
    String indentation = "   ";
    String logo = "     _               _          \n" +
        "    | |   _   _  ___| | ___   _ \n" +
        "    | |  | | | |/ __| |/ / | | |      |\\__/,|   (`\\\n" +
        "    | |__| |_| | (__|   <| |_| |    _.|o o  |_   ) )\n" +
        "    |_____\\__,_|\\___|_|\\_\\\\__, |  -(((---(((--------\n" +
        "                          |___/ ";

    String greeting = indentation + "Hello! I'm Lucky the cat.\n" + indentation + "What can I do for you?";
    String bye = "Goodbye my friend. See you soon!";

    System.out.println(indentation + divider + "\n" + logo + "\n\n" + greeting + "\n" + indentation + divider + "\n");

    Scanner sc = new Scanner(System.in);
    ArrayList<String> inputs = new ArrayList<>();
    boolean isChatting = true;

    while (isChatting) {
      String input = sc.nextLine();
      switch (input.toLowerCase()) {
        case "list":
          System.out.println(indentation + divider);
          System.out.println(indentation + "list");
          System.out.println(indentation + divider + "\n");
          break;
        case "blah":
          System.out.println(indentation + divider);
          System.out.println(indentation + "blah");
          System.out.println(indentation + divider + "\n");
          break;
        case "bye":
          System.out.println(indentation + divider);
          System.out.println(indentation + bye + "\n" + indentation + divider + "\n");
          isChatting = false;
          break;

        default:
          break;
      }
    }
    sc.close();
  }
}
