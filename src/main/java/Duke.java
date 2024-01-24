public class Duke {

  private static final String NAME = ">uwu<";

  private static void message_end() {
    System.out.println("___________________________");
  }

  private static void greet() {
    System.out.printf(
      "Hello! I'm %s\n" + "What can I do for you?\n",
      Duke.NAME
    );
    Duke.message_end();
  }

  private static void bye() {
    System.out.println("Bye. Hope to see you again soon!");
    Duke.message_end();
  }

  public static void main(String[] args) {
    Duke.greet();
    Duke.bye();
  }
}
