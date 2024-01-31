package dude;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Dude {

    public static void main(String[] args) {
        Ui.greeting();

        Dude dude = new Dude();
        TaskList taskList = new TaskList();

        Scanner scanner = new Scanner(System.in);
        loop:
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();

            String[] inputSplit = input.split(" ", 2);
            String command = inputSplit[0];
            String ipArgs = inputSplit.length > 1 ? inputSplit[1] : "";
            ArrayList<Object> formattedParameters = new ArrayList<>();
            switch (command) {
            case "bye":
                break loop;
            case "list":
                taskList.list();
                break;
            case "mark":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER})
                ) {
                    continue;
                }
                taskList.mark((int) formattedParameters.get(0) - 1);
                break;
            case "unmark":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER})
                ) {
                    continue;
                }
                taskList.unmark((int) formattedParameters.get(0) - 1);
                break;
            case "delete":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER})
                ) {
                    continue;
                }
                taskList.delete((int) formattedParameters.get(0) - 1);
                break;
            case "todo":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING})
                ) {
                    continue;
                }
                Todo todo = new Todo((String) formattedParameters.get(0));
                taskList.add(todo);
                break;
            case "deadline": {
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description", "by"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE})
                ) {
                    continue;
                }
                Deadline deadline = new Deadline(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1));
                taskList.add(deadline);
                break;
            }
            case "event": {
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description", "from", "to"},
                        new Parser.ParameterTypes[]{
                                Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE, Parser.ParameterTypes.DATE})
                ) {
                    continue;
                }
                Event event = new Event(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1),
                        (String) formattedParameters.get(2));
                taskList.add(event);
                break;
            }
            default:
                Ui.print("Unknown command detected!\n");
            }
        }
        Ui.goodbye();
    }
}
