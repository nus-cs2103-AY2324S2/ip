public class Chatbot {
  private String name;
  private String hello = "____________________________________________________________\n"
      + "Hello! I'm %s\n" + "What can I do for you?\n"
      + "____________________________________________________________\n";

  private String goodbye = "Bye. Hope to see you again soon!\n"
      + "____________________________________________________________";

  public Chatbot(String name) {
    this.name = name;
    this.hello = String.format(this.hello, name);
  }

  public void greet() {
    String message = this.hello + this.goodbye;
    System.out.println(message);
  }
}