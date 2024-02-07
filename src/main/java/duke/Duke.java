package duke;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello it's a-me! Mario!\nWhat-a can I do fo' ya!");
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui();
        State state = Storage.load();
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            try {
                Command c = Parser.parse(line, state);
                c.execute(state, ui);
                Storage.save(state);
            } catch (DukeException e) {
                ui.say("Uh Oh! " + e.getMessage());
            } catch (DateTimeParseException e) {
                ui.say("Uh Oh! Format your date as yyyy-mm-dd!");
            }
        }
    }
}
