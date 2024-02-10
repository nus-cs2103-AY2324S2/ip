package duke;

import java.time.format.DateTimeParseException;

/**
 * Class that represents the main instance of the app.
 */
public class Duke {
    Ui ui = new Ui();
    State state = Storage.load();

    String getResponse(String input) {
        if (input.equals("bye")) {
            return "bye!";
        }
        try {
            Command c = Parser.parse(input, state);
            String output = c.execute(state, ui);
            Storage.save(state);
            return output;
        } catch (DukeException e) {
            return "Uh Oh! " + e.getMessage();
        } catch (DateTimeParseException e) {
            return "Uh Oh! Format your date as yyyy-mm-dd!";
        }
    }
}
