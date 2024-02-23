package ghbot.ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ghbot.Instruction;
import ghbot.exception.UiException;

/**
 * Ui Class.
 * It deals with the interaction with the user.
 */
public class Ui {
    private String input;
    private final DateTimeFormatter inTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Ui Constructor.
     * @param input User input.
     */
    public Ui(String input) {
        this.input = input;
    }

    /**
     * Checks whether the user have key in the input correctly.
     * @return A string array containing instruction and descriptions.
     * @throws UiException When the format of the input is wrong.
     */
    public String[] validateInput() throws UiException {
        checkEmptyInstruction();

        String[] inputDetails = this.input.trim().split(" ", 2);
        checkInvalidInstruction(inputDetails);
        checkMissingDescription(inputDetails);
        checkIntegerDescription(inputDetails);

        if (inputDetails[0].equalsIgnoreCase(Instruction.DEADLINE.name())) {
            additionalCheckForDeadlineTask(inputDetails);
            checkDateTimeForDeadlineTask(inputDetails);
        } else if (inputDetails[0].equalsIgnoreCase(Instruction.EVENT.name())) {
            additionalCheckForEventTask(inputDetails);
            checkDateTimeForEventTask(inputDetails);
        }

        return inputDetails;
    }

    /**
     * Checks for empty input.
     * @throws UiException When the user send without any input.
     */
    public void checkEmptyInstruction() throws UiException {
        if (this.input.isEmpty()) {
            throw new UiException("Helloooo!! please type something and follow the format below!!\n"
                    + "todo 'task description'\n"
                    + "deadline 'task description' /by 'time'\n"
                    + "event 'task description' /from 'start time' /to 'end time'");
        }
    }

    /**
     * Checks for invalid instruction.
     * @param inputDetails A string array containing the input splits by instruction and description.
     * @throws UiException When the user key in an unknown instruction.
     */
    public void checkInvalidInstruction(String[] inputDetails) throws UiException {
        if (!inputDetails[0].equalsIgnoreCase(Instruction.TODO.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.DEADLINE.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.EVENT.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.LIST.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.MARK.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.UNMARK.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.BYE.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.DELETE.name())
                && !inputDetails[0].equalsIgnoreCase(Instruction.FIND.name())) {
            throw new UiException("Sorry! I don't get the instruction!\n"
                    + "please use either todo, deadline, event, list, mark, unmark, bye, find or delete!");
        }
    }

    /**
     * Checks for missing description.
     * @param inputDetails A string array containing the input splits by instruction and description.
     * @throws UiException When user did not provide any description for an instruction.
     */
    public void checkMissingDescription(String[] inputDetails) throws UiException {
        if (inputDetails[0].equalsIgnoreCase(Instruction.TODO.name()) && inputDetails.length < 2) {
            throw new UiException("Sorry! You need to include a description for your task!");
        } else if (inputDetails[0].equalsIgnoreCase(Instruction.DEADLINE.name()) && inputDetails.length < 2) {
            throw new UiException("Sorry! You need to include a description and deadline for your task!");
        } else if (inputDetails[0].equalsIgnoreCase(Instruction.EVENT.name()) && inputDetails.length < 2) {
            throw new UiException("Sorry! You need to include a description, "
                    + "start time and end time for your task!");
        } else if (inputDetails[0].equalsIgnoreCase(Instruction.MARK.name()) && inputDetails.length < 2) {
            throw new UiException("Sorry! You need to include a number to mark your task!");
        } else if (inputDetails[0].equalsIgnoreCase(Instruction.UNMARK.name()) && inputDetails.length < 2) {
            throw new UiException("Sorry! You need to include a number to unmark your task!");
        } else if (inputDetails[0].equalsIgnoreCase(Instruction.DELETE.name()) && inputDetails.length < 2) {
            throw new UiException("Sorry! You need to include a number to delete your task!");
        } else if (inputDetails[0].equalsIgnoreCase(Instruction.FIND.name()) && inputDetails.length < 2) {
            throw new UiException("Sorry! You need to include a partial word or keyword that you want to find!");
        }
    }

    /**
     * Checks the description of mark, unmark and delete instruction to use valid list number.
     * @param inputDetails A string array containing the input splits by instruction and description.
     * @throws NumberFormatException When user key in an invalid number in the description.
     */
    public void checkIntegerDescription(String[] inputDetails) {
        if (inputDetails[0].equalsIgnoreCase(Instruction.MARK.name())
                || inputDetails[0].equalsIgnoreCase(Instruction.UNMARK.name())
                || inputDetails[0].equalsIgnoreCase(Instruction.DELETE.name())) {
            try {
                Integer.parseInt(inputDetails[1]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Sorry! Please key in a valid list number for the task!\n"
                        + "E.g. mark 1, unmark 2, delete 10");
            }
        }
    }

    /**
     * Checks the description format for deadline task.
     * @param inputDetails A string array containing the input splits by instruction and description.
     * @throws UiException When the description is incomplete or the format is wrong.
     */
    public void additionalCheckForDeadlineTask(String[] inputDetails) throws UiException {
        String[] descriptionDetails = inputDetails[1].split("/by");
        if (descriptionDetails.length < 2) {
            throw new UiException("Sorry! Please follow the following format! \n"
                    + "deadline 'task description' /by 'time'");
        }
    }

    /**
     * Checks the date and time format for deadline task.
     * @param inputDetails A string array containing the input splits by instruction and description.
     * @throws DateTimeParseException When the date and time format is wrong.
     */
    public void checkDateTimeForDeadlineTask(String[] inputDetails) {
        String[] descriptionDetails = inputDetails[1].split("/by");
        try {
            LocalDateTime.parse(descriptionDetails[1].trim(), this.inTimeFormat);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Your date/time format is wrong.\n"
                    + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", descriptionDetails[1].trim(), 0);
        }
    }

    /**
     * Checks the description format for event task.
     * @param inputDetails A string array containing the input splits by instruction and description.
     * @throws UiException When the description is incomplete or the format is wrong.
     */
    public void additionalCheckForEventTask(String[] inputDetails) throws UiException {
        String[] descriptionDetails = inputDetails[1].split("/from");
        if (descriptionDetails.length < 2) {
            throw new UiException("Sorry! Please follow the following format! \n"
                    + "event 'task description' /from 'start time' /to 'end time'");
        }
        String[] dateTimeDetails = descriptionDetails[1].split("/to");
        if (dateTimeDetails.length < 2) {
            throw new UiException("Sorry! Please follow the following format! \n"
                    + "event 'task description' /from 'start time' /to 'end time'");
        }
    }

    /**
     * Checks the date and time format for event task.
     * @param inputDetails A string array containing the input splits by instruction and description.
     * @throws DateTimeException When the start time is later than end time.
     * @throws DateTimeParseException When the date and time format is wrong.
     */
    public void checkDateTimeForEventTask(String[] inputDetails) {
        String[] descriptionDetails = inputDetails[1].split("/from");
        String[] dateTimeDetails = descriptionDetails[1].split("/to");
        try {
            LocalDateTime startTime = LocalDateTime.parse(dateTimeDetails[0].trim(), this.inTimeFormat);
            LocalDateTime endTime = LocalDateTime.parse(dateTimeDetails[1].trim(), this.inTimeFormat);
            if (!startTime.isBefore(endTime)) {
                throw new DateTimeException("Sorry! Your start time should be earlier than your end time.");
            }
        } catch (DateTimeParseException e) {
            if (e.getParsedString().equals(dateTimeDetails[0].trim())) {
                throw new DateTimeParseException("Your start date/time format is wrong.\n"
                        + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", dateTimeDetails[0].trim(), 0);
            } else if (e.getParsedString().equals(dateTimeDetails[1].trim())) {
                throw new DateTimeParseException("Your end date/time format is wrong.\n"
                        + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", dateTimeDetails[1].trim(), 0);
            }
        }
    }
}
