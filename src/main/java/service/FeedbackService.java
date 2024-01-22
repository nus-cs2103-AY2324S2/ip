package service;

public class FeedbackService {
  public void PrintWelcome() {
    // Logo generated from : https://patorjk.com/software/taag/#p=display&f=Sub-Zero&t=OAK
    String logo =
    "______     ______     __  __    \n" +
    "/\\  __ \\   /\\  __ \\   /\\ \\/ /    \n" +
    "\\ \\ \\/\\ \\  \\ \\  __ \\  \\ \\  _-.    \n" +
    " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \n" +
    "  \\/_____/   \\/_/\\/_/   \\/_/\\/_/ \n";
    System.out.println("Hello from\n" + logo);

    System.out.println("Hello! I'm Professor Oak");
    System.out.println("What can I do for you?");

    System.out.println("Bye! Hope to see you again soon!");
  }
}
