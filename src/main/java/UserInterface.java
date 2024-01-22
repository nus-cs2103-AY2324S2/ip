public class UserInterface {
    /*
    This class is for the the user interface.
    */

    String greeting = "Hi! My name is HAL9000";
    String exit = "Bye! See ya soon";

    public void greet() {
        System.out.println(greeting);
    }
    public void exit() {
        System.out.println(exit);
    }

    public void start() {
        greet();
        exit();
    }
}