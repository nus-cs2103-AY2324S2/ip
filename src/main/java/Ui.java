import java.util.Scanner;

public class Ui {
    public Ui () {
    }

    public void showLoadingError() {
        System.out.println("Invalid filepath.");
    }
    public void greet() {
        System.out.println("Quack! My name is Bearducky. I am a duck with a bear hat and a baseball bat." +
                "How may I help you today?");
    }

    public void happy() {
        System.out.println("[happy quacking]");
    }
    public void goodbye() {
        System.out.println("[sad quacking] Can I have some bread before you go?");
    }
}
