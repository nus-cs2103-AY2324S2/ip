package dude;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parser class used to parse Dude command input.
 */
public class Parser {
    /** Enum of parameter types that can be accepted as input. */
    public enum ParameterTypes {
        INTEGER,
        STRING,
        DATE
    }

    /**
     * Checks if the string input is numeric.
     * @param str String input of a number.
     * @return Whether input is numeric.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the string input is in the yyyy-mm-dd format.
     * @param str String input of a date.
     * @return Whether input is in the correct date format.
     */
    public static boolean isDate(String str) {
        String dateString = str.stripTrailing();
        try {
            LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static boolean checkParameterExists(
            ArrayList<String> output, String command, String parameterName, String parameter) {
        if (!parameter.isEmpty()) {
            output.add(parameter);
            return true;
        }
        throw new DudeParseException("Parameter " + parameterName + " of " + command + " cannot be empty!\n");
    }

    private static boolean checkFlagExists(
            ArrayList<String> output, String command, String parameterName, String parameter) {
        String[] parameterSplit = parameter.split(" ", 2);
        if (!parameterSplit[0].equals(parameterName)) {
            throw new DudeParseException("Invalid flag name " + parameterName + " for command " + command + "\n");
        }
        String arg = parameterSplit.length > 1 ? parameterSplit[1] : "";
        return checkParameterExists(output, command, parameterName, arg);
    }

    private static boolean getParameters(
            ArrayList<String> output, String command, String[] parameterNames, String args) {
        String[] argsSplit = args.split("/");
        if (argsSplit.length != parameterNames.length) {
            throw new DudeParseException("Invalid number of parameters for " + command + ", need to have: "
                    + Arrays.toString(parameterNames) + "\n");
        }
        if (!checkParameterExists(output, command, parameterNames[0], argsSplit[0])) {
            return false;
        }
        for (int i = 1; i < argsSplit.length; i++) {
            if (!checkFlagExists(output, command, parameterNames[i], argsSplit[i])) {
                return false;
            }
        }

        return true;
    }

    private static boolean formatParameters(
            ArrayList<Object> formattedParameters, ArrayList<String> parameters, ParameterTypes[] formats) {
        for (int i = 0; i < formats.length; i++) {
            if (formats[i] == ParameterTypes.INTEGER) {
                if (isNumeric(parameters.get(i))) {
                    formattedParameters.add(Integer.parseInt(parameters.get(i)));
                } else {
                    throw new DudeParseException("Format of " + parameters.get(i) + " is not an integer\n");
                }
            } else if (formats[i] == ParameterTypes.DATE) {
                if (isDate(parameters.get(i))) {
                    formattedParameters.add(parameters.get(i).stripTrailing());
                } else {
                    throw new DudeParseException("Format of " + parameters.get(i) + " is not a date (yyyy-mm-dd)\n");
                }
            } else {
                formattedParameters.add(parameters.get(i).stripTrailing());
            }
        }
        return true;
    }

    /**
     * Parses the command arguments and adds them to the formattedParameter ArrayList.
     * @param formattedParameters Empty ArrayList to be populated with the formatted parameters.
     * @param command The command given.
     * @param args The arguments following the command.
     * @param parameterNames Array of parameter names.
     * @param formats Array of ParameterTypes corresponding to the parameter names.
     * @return Whether command and arguments could be successfully parsed.
     */
    public static String parse(
            ArrayList<Object> formattedParameters,
            String command,
            String args,
            String[] parameterNames,
            ParameterTypes[] formats
    ) {
        ArrayList<String> parameters = new ArrayList<>();
        try {
            Parser.getParameters(parameters, command, parameterNames, args);
            Parser.formatParameters(formattedParameters, parameters, formats);
            return "success";
        } catch (DudeParseException e) {
            return e.getMessage();
        }
    }
}
