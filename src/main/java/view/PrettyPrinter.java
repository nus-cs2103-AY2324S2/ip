package view;

public class PrettyPrinter {
  private static void printIndentedln(String message) {
    System.out.println("    " + message);
  }

  private static void printHorizontalln() {
    printIndentedln("____________________________________________________________\n");
  }
  public static void print(String message) {
    String[] lines = message.split("\n");

    if (lines.length == 0) {
      return;
    }

    printHorizontalln();

    for (String line : lines) {
      printIndentedln(line);
    }

    printHorizontalln();
  }
}
