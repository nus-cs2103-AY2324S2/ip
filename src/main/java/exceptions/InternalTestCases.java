package exceptions;

import enums.Commands;

public class InternalTestCases {
    public static void TestMissingParameters(String instruction) throws MissingParameterException {
        String brokenCommand = instruction.split(" ")[0].trim();
        String backCommand = instruction.substring(brokenCommand.length()).trim();
        switch (brokenCommand) {
            case "mark":
                if (backCommand.length() == 0) throw new MissingParameterException(brokenCommand + " missing index!");
                break;
            case "unmark":
                if (backCommand.length() == 0) throw new MissingParameterException(brokenCommand + " missing index!");
                break;
            case "todo":
                if (backCommand.length() == 0) throw new MissingParameterException(brokenCommand + " missing description!");
                break;
            case "deadline":
                if (backCommand.length() == 0) throw new MissingParameterException(brokenCommand + " missing description & date by!");
                if (backCommand.length() > 0 && backCommand.split("/by")[1].length() == 0) throw new MissingParameterException(brokenCommand + " missing date by!");
                break;
            case "event":
            if (backCommand.length() == 0) throw new MissingParameterException(brokenCommand + " missing description & from & to!");
            if (backCommand.length() > 0 && backCommand.split("/from")[1].split("/to")[0].length() == 0) throw new MissingParameterException(brokenCommand + " missing from!");
            if (backCommand.length() > 0 && backCommand.split("/from")[1].split("/to")[1].length() == 0) throw new MissingParameterException(brokenCommand + " missing to!");
                break;
        }
    }

    public static void TestInvalidCommand(String instruction) throws InvalidCommandException {
        String brokenCommand = instruction.split(" ")[0];
        Commands [] commands = {Commands.BYE, Commands.DEADLINE, Commands.DELETE, Commands.EVENT, Commands.MARK, Commands.UNMARK, Commands.TODO};
        boolean isMatched = false;
        for (Commands command : commands) {
            if (brokenCommand.equals(command.name().toLowerCase())) {
                isMatched = true;
            } else continue;
        }

        if (!isMatched) throw new InvalidCommandException(brokenCommand + " is not a valid command!");
    }
}
