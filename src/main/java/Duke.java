import java.util.Objects;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BartenderBob bartenderBob = new BartenderBob();
        bartenderBob.greet();
        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "list")) {
                bartenderBob.list();
            } else if (Objects.equals(userInput, "bye")) {
                bartenderBob.leave();
                break;
            } else {
                bartenderBob.store(userInput);
            }
        }
    }
}
