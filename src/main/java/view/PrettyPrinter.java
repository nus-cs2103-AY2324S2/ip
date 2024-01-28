package view;

public class PrettyPrinter {
  private void printIndentedln(String message) {
    System.out.println("    " + message);
  }

  private void printHorizontalln() {
    printIndentedln("____________________________________________________________\n");
  }

  public void print(String message) {
    String[] lines = message.split("\n");

    if (lines.length == 0) {
      return;
    }

    this.printHorizontalln();

    for (String line : lines) {
      this.printIndentedln(line);
    }

    this.printHorizontalln();
  }
}
