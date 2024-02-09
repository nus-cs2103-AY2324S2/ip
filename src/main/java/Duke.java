import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello it's a-me! Mario!\nWhat-a can I do fo' ya!");
        Scanner scanner = new Scanner(System.in);
        State state = Storage.load();
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            try {
                Command c = Parser.parse(line, state);
                c.execute(state);
                Storage.save(state);
            } catch (DukeException e) {
                System.out.println("Uh Oh! " + e.getMessage());
            }
        }
    }
}
