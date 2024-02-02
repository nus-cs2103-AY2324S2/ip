package duke;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    public String receive() {
        return scanner.nextLine();
    }
    public void display(Object n) {
        System.out.println(n.toString());
    }
}
