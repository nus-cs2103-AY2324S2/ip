package Duke;

import Duke.Command.Command;
import Duke.Task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    // deals with interactions with the user
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isActive = true;
        ui.greet();

        while (isActive) {
            String userInput = ui.getInput();
            Command command = Parser.parseCommand(userInput);
            command.execute(this.storage, this.tasks, this.ui);
            isActive = command.getIsActive();
        }

    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}


//    private static void validateNonEmptyDesc(String[] components) throws Duke.DukeException {
//        if (components.length < 2 || components[1].isBlank()) {
//            throw new Duke.DukeException("The description cannot be empty :(");
//        }
//    }


//
//    private static void validateFormat(String[] fragments, int expectedNumberOfFragments) throws Duke.DukeException {
//        if (fragments.length != expectedNumberOfFragments) {
//            throw new Duke.DukeException("Something is wrong with the format!");
//        }
//        for (int i = 0; i < expectedNumberOfFragments; i++) {
//            if (fragments[i].isBlank()) {
//                throw new Duke.DukeException("Something is wrong with the format!");
//            }
//        }
//    }
//    private static void validateIndex(int index, int length) throws Duke.DukeException {
//        if (index > length || index <= 0) {
//            throw new Duke.DukeException("Invalid index");
//        }
//    }
//
//    private static String formatDate(String byDate) {
//        List<DateTimeFormatter> formatters = new ArrayList<>();
//        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        LocalDate parsedDateTime = null;
//
//        System.out.println("Input date: " + byDate);
//
//        for (DateTimeFormatter formatter : formatters) {
//            try {
//                parsedDateTime = LocalDate.parse(byDate, formatter);
//                break;
//            } catch (DateTimeParseException e) {
//            }
//        }
//        if (parsedDateTime == null) {
//            return byDate;
//        }
//        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//    }
//}



