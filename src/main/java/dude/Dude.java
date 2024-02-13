package dude;

import java.util.ArrayList;
import java.util.Map;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

/**
 * Dude - a Duke variant.
 */
public class Dude {
    private static final Map<String, String[]> COMMAND_PARAMETERS = Map.ofEntries(
            Map.entry("bye", new String[]{}),
            Map.entry("list", new String[]{}),
            Map.entry("mark", new String[]{"index"}),
            Map.entry("unmark", new String[]{"index"}),
            Map.entry("delete", new String[]{"index"}),
            Map.entry("find", new String[]{"keyword"}),
            Map.entry("todo", new String[]{"description"}),
            Map.entry("deadline", new String[]{"description", "by"}),
            Map.entry("event", new String[]{"description", "from", "to"})
    );

    private static final Map<String, Parser.ParameterTypes[]> COMMAND_PARAMETER_TYPES = Map.ofEntries(
            Map.entry("bye", new Parser.ParameterTypes[]{}),
            Map.entry("list", new Parser.ParameterTypes[]{}),
            Map.entry("mark", new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER}),
            Map.entry("unmark", new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER}),
            Map.entry("delete", new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER}),
            Map.entry("find", new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING}),
            Map.entry("todo", new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING}),
            Map.entry("deadline", new Parser.ParameterTypes[]{
                    Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE}),
            Map.entry("event", new Parser.ParameterTypes[]{
                    Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE, Parser.ParameterTypes.DATE})
    );
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
                        COMMAND_PARAMETERS.get("mark"),
                        COMMAND_PARAMETER_TYPES.get("mark"));
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.mark((int) formattedParameters.get(0) - 1);
            case "unmark":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        COMMAND_PARAMETERS.get("unmark"),
                        COMMAND_PARAMETER_TYPES.get("unmark"));
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.unmark((int) formattedParameters.get(0) - 1);
            case "delete":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        COMMAND_PARAMETERS.get("delete"),
                        COMMAND_PARAMETER_TYPES.get("delete"));
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.delete((int) formattedParameters.get(0) - 1);
            case "find":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        COMMAND_PARAMETERS.get("find"),
                        COMMAND_PARAMETER_TYPES.get("find"));
                if (parseResult != "success") {
                    return parseResult;
                }
                return taskList.find((String) formattedParameters.get(0));
            case "todo":
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        COMMAND_PARAMETERS.get("todo"),
                        COMMAND_PARAMETER_TYPES.get("todo"));
                if (parseResult != "success") {
                    return parseResult;
                }
                Todo todo = new Todo((String) formattedParameters.get(0));
                return taskList.add(todo);
            case "deadline": {
                parseResult = Parser.parse(
                        formattedParameters, command, ipArgs,
                        COMMAND_PARAMETERS.get("deadline"),
                        COMMAND_PARAMETER_TYPES.get("deadline"));
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
                        COMMAND_PARAMETERS.get("event"),
                        COMMAND_PARAMETER_TYPES.get("event"));
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
