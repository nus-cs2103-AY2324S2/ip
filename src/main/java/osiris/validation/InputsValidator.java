package osiris.validation;

import osiris.commands.addDeadlineTaskCommand;
import osiris.commands.addEventTaskCommand;
import osiris.commands.addToDoTaskCommand;
import osiris.formatters.DateTimeFormatters;

public class InputsValidator {

    private static InputsValidator instance;

    private InputsValidator() {}

    public static InputsValidator getInstance() {
        if (instance == null) {
            instance = new InputsValidator();
        }
        return instance;
    }

    public boolean validateMarkTaskCompletedInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.out.println("Invalid task index. Please Reenter");
        }
        return false;
    }

    public boolean validateMarkTaskIncompleteInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.out.println("Invalid task index. Please Reenter");
        }
        return false;
    }

    public boolean validateRemoveTaskInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.out.println("Invalid task index. Please Reenter");
        }
        return false;
    }

    public boolean validateAddToDoTaskInput(String userInput) {
        String taskName = userInput.substring(addToDoTaskCommand.COMMAND.length()).trim();
        if (!taskName.isEmpty()) {
            return true;
        } else {
            System.out.println("Task name not provided. Please Reenter.");
        }
        return false;
    }

    public boolean validateAddDeadlineTaskInput(String userInput) {
        int byIndex = userInput.indexOf("/by");

        if (byIndex != -1) {
            String taskName = userInput.substring(addDeadlineTaskCommand.COMMAND.length(), byIndex - 1).trim();

            if (!taskName.isEmpty()) {
                String deadline = userInput.substring(byIndex + "/by".length()).trim();
                String[] deadlineParts = deadline.split("-");
                if (deadlineParts.length == 3) {
                    String dayStr = deadlineParts[0];
                    String monthStr = deadlineParts[1];
                    String yearStr = deadlineParts[2];

                    if (isValidDay(dayStr) && isValidMonth(monthStr) && isValidYear(yearStr)) {
                        return true;
                    } else {
                        System.out.println("Invalid day, month, or year in deadline. Please use dd-MM-yyyy. ");
                    }
                } else {
                    System.out.println("Invalid deadline format. Please use dd-MM-yyyy.");
                }
            } else {
                System.out.println("Task name not provided. Please Reenter.");
            }
        } else {
            System.out.println("Invalid input format. Please Reenter. Ensure '/by' is specified for a Deadline Task. E.g. deadline Do Homework /by dd-MM-yyyy .");
        }
        return false;
    }

    public boolean validateAddEventTaskInput(String userInput) {
        int fromIndex = userInput.indexOf("/from");
        int toIndex = userInput.indexOf("/to");

        if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
            String taskName = userInput.substring(addEventTaskCommand.COMMAND.length(), fromIndex - 1).trim();
            if (!taskName.isEmpty()) {
                String startDateTime = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
                String endDateTime = userInput.substring(toIndex + "/to".length()).trim();
                if (isValidDateTime(startDateTime) && isValidDateTime(endDateTime)) {

                    if (DateTimeFormatters.getInstance().userInputDateTimeFormatter(startDateTime)
                            .isBefore((DateTimeFormatters.getInstance().userInputDateTimeFormatter(endDateTime)))) {
                        return true;
                    } else {
                        System.out.println("Invalid date-time order. Start date/time should be before end date/time.");
                    }
                } else {
                    System.out.println("Invalid date-time format. Please use dd-MM-yyyy HHmm.");
                }
            } else {
                System.out.println("Task name not provided. Please Reenter.");
            }
        } else {
            System.out.println("Invalid input format. Please Reenter. Ensure '/from' & '/to' is specified for a Event Task. E.g. event School Meeting /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm. Please Reenter." );
        }
        return false;
    }

    private boolean isValidDay(String dayStr) {
        try {
            int day = Integer.parseInt(dayStr);
            return day >= 1 && day <= 31 && dayStr.length() == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidMonth(String monthStr) {
        try {
            int month = Integer.parseInt(monthStr);
            return month >= 1 && month <= 12 && monthStr.length() == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidYear(String yearStr) {
        try {
            int year = Integer.parseInt(yearStr);
            return year > 0 && yearStr.length() == 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isValidDateTime(String dateTime) {

        try {
            String[] dateTimeParts = dateTime.split(" ");

            if (dateTimeParts.length != 2) {
                return false;
            }

            String[] dateParts = dateTimeParts[0].split("-");

            if (dateParts.length != 3) {
                return false;
            }

            int hour = Integer.parseInt(dateTimeParts[1].substring(0, 2));
            int minute = Integer.parseInt(dateTimeParts[1].substring(2));

            return isValidDay(dateParts[0]) && isValidMonth(dateParts[1]) && isValidYear(dateParts[2])
                    && hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59 && dateTimeParts[1].length() == 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
