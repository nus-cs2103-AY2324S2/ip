public class BartenderBob {
    public static final String NAME = "BartenderBob";

    public void greet() {
        System.out.println("Welcome back! I'm " + NAME + "\nHow's it going out there?");
    }

    public void leave() {
        System.out.println("Bye! Another round next time!");
    }

    public void echo(String userInput) {
        System.out.println(userInput);
    }
}
