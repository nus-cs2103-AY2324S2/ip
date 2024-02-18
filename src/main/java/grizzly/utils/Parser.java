package grizzly.utils;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

import grizzly.commands.Command;
import grizzly.commands.DeleteRecordCommand;
import grizzly.commands.ExitCommand;
import grizzly.commands.FindCommand;
import grizzly.commands.GenerateRecordCommand;
import grizzly.commands.HelpCommand;
import grizzly.commands.ListRecordsCommand;
import grizzly.commands.ModifyTaskCommand;
import grizzly.exceptions.MissingInformationException;
import grizzly.exceptions.MissingParameterException;
import grizzly.exceptions.NoSuchCommandException;

/**
 * This class implements the functionality for Parsing commands input into the bot.
 *
 * @author delishad21
 */
public class Parser {

    public static final DateTimeFormatter INPUT_DT_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
    public static final DateTimeFormatter OUTPUT_DT_FORMATTER =
        DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mma", Locale.ENGLISH);

    /**
     * Parses user input and  into the appropriate commands for the bot to execute.
     *
     * @param input
     * @return Command
     * @throws NoSuchCommandException when the input is not part of bot's functionality
     */
    public static Command parse(String input) throws NoSuchCommandException {


        // Splitting action from parameters
        String[] inputSplit = input.split(" ", 2);
        String action = inputSplit[0].toLowerCase();

        // Checking for parameters. If not present, assign array with empty string to params
        String[] params = inputSplit.length == 2
                          ? inputSplit[1].split(" /")
                          : new String[]{""};

        // Placing parameters into hashtable with appropriate labels
        Hashtable<String, String> paramsTable = new Hashtable<>();
        paramsTable.put("description", params[0]);
        for (int i = 1; i < params.length; i++) {
            String[] paramSplit = params[i].split(" ", 2);
            String paramLabel = paramSplit[0];
            String paramInfo = paramSplit[1];
            paramsTable.put(paramLabel, paramInfo);
        }

        assert !action.equals("");

        switch (action) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListRecordsCommand(paramsTable);
        case "find":
            return new FindCommand(paramsTable);
        case "todo":
            return new GenerateRecordCommand(GenerateRecordCommand.RecordType.TODO, paramsTable);
        case "event":
            return new GenerateRecordCommand(GenerateRecordCommand.RecordType.EVENT, paramsTable);
        case "deadline":
            return new GenerateRecordCommand(GenerateRecordCommand.RecordType.DEADLINE, paramsTable);
        case "contact":
            return new GenerateRecordCommand(GenerateRecordCommand.RecordType.CONTACT, paramsTable);
        case "mark":
            return new ModifyTaskCommand(ModifyTaskCommand.ModificationTypes.MARK, paramsTable);
        case "unmark":
            return new ModifyTaskCommand(ModifyTaskCommand.ModificationTypes.UNMARK, paramsTable);
        case "delete":
            return new DeleteRecordCommand(paramsTable);
        default:
            throw new NoSuchCommandException(input);
        }
    }

    /**
     * Checks if the Hashtable contains the required parameters.
     *
     * @param params Hashtable with parameters.
     * @param reqParams Array with the required parameters
     * @throws MissingInformationException if parameter information is missing
     * @throws MissingParameterException if parameter is missing
     */
    public static void checkParams(Hashtable<String, String> params, String[] reqParams)
            throws MissingInformationException, MissingParameterException {
        //Checking for missing parameters and information
        ArrayList<String> missingParams = new ArrayList<>();
        ArrayList<String> missingInfo = new ArrayList<>();

        for (String param : reqParams) {
            if (!params.containsKey(param)) {
                missingParams.add(param);
                continue;
            }

            if (params.get(param).equals("")) {
                missingInfo.add(param);
            }

        }

        //Throwing exceptions if parameters/parameter description is missing
        if (missingParams.size() != 0) {
            throw new MissingParameterException(missingParams.toArray(String[]::new));
        } else if (missingInfo.size() != 0) {
            throw new MissingInformationException(missingInfo.toArray(String[]::new));
        }
    }

}
