package dude;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public enum ParameterTypes {
        INTEGER,
        STRING,
        DATE
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDate(String str) {
        String dateString = str.stripTrailing();
        try {
            LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static boolean checkParameterExists(ArrayList<String> output, String command, String parameterName, String parameter) {
        if (!parameter.isEmpty()) {
            output.add(parameter);
            return true;
        }
        Ui.print("Parameter " + parameterName + " of " + command + " cannot be empty!\n");
        return false;
    }

    private static boolean checkFlagExists(ArrayList<String> output, String command, String parameterName, String parameter) {
        String[] parameterSplit = parameter.split(" ", 2);
        if (!parameterSplit[0].equals(parameterName)) {
            Ui.print("Invalid flag name " + parameterName + " for command " + command + "\n");
            return false;
        }
        String arg = parameterSplit.length > 1 ? parameterSplit[1] : "";
        return checkParameterExists(output, command, parameterName, arg);
    }

    public static boolean getParameters(ArrayList<String> output, String command, String[] parameterNames, String args) {
        String[] argsSplit = args.split("/");
        if (argsSplit.length != parameterNames.length) {
            Ui.print("Invalid number of parameters for " + command + ", need to have: "
                    + Arrays.toString(parameterNames) + "\n");
            return false;
        }
        if (!checkParameterExists(output, command, parameterNames[0], argsSplit[0])) return false;
        for (int i = 1; i < argsSplit.length; i++) {
            if (!checkFlagExists(output, command, parameterNames[i], argsSplit[i])) return false;
        }

        return true;
    }

    public static boolean formatParameters(ArrayList<Object> formattedParameters, ArrayList<String> parameters, ParameterTypes[] formats) {
        for (int i = 0; i < formats.length; i++) {
            if (formats[i] == ParameterTypes.INTEGER) {
                if (isNumeric(parameters.get(i))) {
                    formattedParameters.add(Integer.parseInt(parameters.get(i)));
                } else {
                    Ui.print("Format of " + parameters.get(i) + " is not an integer\n");
                    return false;
                }
            } else if  (formats[i] == ParameterTypes.DATE) {
                if (isDate(parameters.get(i))) {
                    formattedParameters.add(parameters.get(i).stripTrailing());
                } else {
                    Ui.print("Format of " + parameters.get(i) + " is not a date (yyyy-mm-dd)\n");
                    return false;
                }
            } else {
                    formattedParameters.add(parameters.get(i).stripTrailing());
            }
        }
        return true;
    }

    public static boolean parse(
            ArrayList<Object> formattedParameters,
            String command,
            String args,
            String[] parameterNames,
            ParameterTypes[] formats
    ) {
        ArrayList<String> parameters = new ArrayList<>();
        if (!Parser.getParameters(parameters, command, parameterNames, args)) {
            return false;
        }
        return Parser.formatParameters(formattedParameters, parameters, formats);
    }
}
