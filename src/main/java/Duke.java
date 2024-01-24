import java.util.Scanner;

import DukeException.InvalidCommandException;
import DukeException.ListOutofBoundsException;
import DukeException.MissingArgumentsException;
import Storage.Deadlines;
import Storage.Events;
import Storage.Todos;
import UI.UI;
import Parser.Parser;
import Storage.Task;

public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        boolean flag = true;
        while(flag) {
            String input = scanner.nextLine().trim();
            parser.feed(input);
            String[] output;
            try {
                output = parser.parse();
            } catch (InvalidCommandException e) {
                ui.error(e.getMessage());
                continue;
            } catch (MissingArgumentsException e) {
                ui.error(e.getMessage());
                continue;
            }
            Task task;
            switch (output[0]) {
                case "bye":
                    UI.goodbye();
                    flag = false;
                    break;
                case "list":
                    ui.listItems();
                    break;
                case "unmark":
                    try {
                        ui.unMarkTask(Integer.parseInt(output[1]) - 1);
                    } catch (ListOutofBoundsException e){
                        ui.error(e.getMessage());
                    }
                    break;
                case "mark":
                    try {
                        ui.markTaskUI(Integer.parseInt(output[1]) - 1);
                    } catch (ListOutofBoundsException e) {
                        ui.error(e.getMessage());
                    }
                    break;
                case "todo":
                    task = new Todos(output[1]);
                    ui.addItem(task);
                    break;
                case "deadline":
                   task = new Deadlines(output[1], output[2]);
                    ui.addItem(task);
                    break;
                case "event":
                   task = new Events(output[1], output[2], output[3]);
                    ui.addItem(task);
            }
        }
    }
}
