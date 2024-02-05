package dude;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

/**
 * Dude - a Duke variant.
 */
public class Dude {
    private TaskList taskList;

    /**
     * Class constructor.
     */
    public Dude() {
        taskList = new TaskList();
    }

    public String getResponse(String input) {
        String[] inputSplit = input.split(" ", 2);
        String command = inputSplit[0];
        String ipArgs = inputSplit.length > 1 ? inputSplit[1] : "";
        ArrayList<Object> formattedParameters = new ArrayList<>();
        String parseResult;
        switch (command) {
            case "bye":
                return Ui.goodbye();
            case "list":
                return taskList.list();
            case "mark":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER});
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.mark((int) formattedParameters.get(0) - 1);
            case "unmark":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER});
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.unmark((int) formattedParameters.get(0) - 1);
            case "delete":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER});
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.delete((int) formattedParameters.get(0) - 1);
            case "find":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"keyword"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING});
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.find((String) formattedParameters.get(0));
            case "todo":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING});
                if (parseResult != "success") {
                    return parseResult;
                }
                Todo todo = new Todo((String) formattedParameters.get(0));
                return taskList.add(todo);
            case "deadline": {
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description", "by"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE});
                if (parseResult != "success") {
                    return parseResult;
                }
                Deadline deadline = new Deadline(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1));
                return taskList.add(deadline);
            }
            case "event": {
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description", "from", "to"},
                        new Parser.ParameterTypes[]{
                                Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE, Parser.ParameterTypes.DATE});
                if (parseResult != "success") {
                    return parseResult;
                }
                Event event = new Event(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1),
                        (String) formattedParameters.get(2));
                return taskList.add(event);
            }
            default:
                return "Unknown command detected!\n";
        }
    }
}
