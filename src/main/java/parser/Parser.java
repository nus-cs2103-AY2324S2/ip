package parser;

import dukeException.*;

import java.time.LocalTime;
import java.util.Arrays;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import storage.Task;
import storage.Events;
import storage.Deadlines;
import storage.Todos;

public class Parser {
    private String input;

    public Parser() {
        this.input = "";
    }
    public Parser(String input) {
        this.input = input;
    }

    public void feed(String input) {
        this.input = input;
    }

    public Token parse() throws InvalidCommandException, MissingArgumentsException ,WrongTimeFormatException {
        String[] split = this.input.split(" ");
        Token token;
        Task task = null;
        int flag;
        int flag2;
        switch(split[0]) {
        case "list":
            if (split.length != 1) {
                throw new InvalidCommandException("InvalidCommandException");
            } else {
                token = new Token(Command.LIST);
            }
            break;
        case "bye":
            if (split.length != 1) {
                throw new InvalidCommandException("InvalidCommandException");
            } else {
                token = new Token(Command.BYE);
            }
            break;
        case "delete":
            if (split.length != 2) {
                throw new MissingArgumentsExceptionMarking(split[0]);
            } else {
                try {
                    Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("InvalidCommandException");
                }
                token = new Token(Command.DELETE, Integer.parseInt(split[1]));
            }
            break;
        case "unmark":
            if (split.length != 2) {
                throw new MissingArgumentsExceptionMarking(split[0]);
            } else {
                try {
                    Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("InvalidCommandException");
                }
                token = new Token(Command.UNMARK, Integer.parseInt(split[1]));
            }
            break;
        case "mark":
            if (split.length != 2) {
                throw new MissingArgumentsExceptionMarking(split[0]);
            } else {
                try {
                    Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("InvalidCommandException");
                }
                token = new Token(Command.MARK, Integer.parseInt(split[1]));
            }
            break;
        case "todo":
            if (split.length == 1) {
                throw new MissingArgumentsExceptionTodo("todo");
            } else {
                int space = this.input.indexOf(" ");
                task = new Todos(this.input.substring(space + 1));
                token = new Token(Command.TODO, task);
            }
            break;
        case "event":
            flag = Arrays.asList(split).indexOf("/from");
            flag2 = Arrays.asList(split).indexOf("/to");
            if (split.length < 5) {
                throw new MissingArgumentsExceptionEvents("event");
            } else if (flag < 2 || flag2 == split.length -1 || flag2 - flag <= 1) {
                throw new MissingArgumentsExceptionEvents("event");
            } else {
                int space = this.input.indexOf(" ");
                int from = this.input.indexOf("/from");
                int to = this.input.indexOf("/to");
                String fromDateTime;
                String toDateTime;
                // Processes From DateTime
                try {
                    checkTimeFormat(this.input.substring(from + 5, to).trim());
                    String[] temporaryArray = this.input.substring(from + 5, to).trim().split("[\\s/\\-]+");
                    int lenTemp = temporaryArray.length;
                    for (int i=0; i<temporaryArray.length/2; i++) {
                        String temp = temporaryArray[i];
                        temporaryArray[i] = temporaryArray[lenTemp - 1 - i];
                        temporaryArray[lenTemp - 1 - i] = temp;
                    }
                    if (temporaryArray[lenTemp - 2].length() == 1) {
                        temporaryArray[lenTemp - 2] = "0" + temporaryArray[lenTemp - 2];
                    }
                    if (temporaryArray[lenTemp - 1].length() == 1) {
                        temporaryArray[lenTemp - 1] = "0" + temporaryArray[lenTemp - 1];
                    }

                    fromDateTime = String.join("-", temporaryArray);

                } catch (WrongTimeFormatException exception) {
                    throw exception;
                }
                // Processes To DateTime
                try {
                    checkTimeFormat(this.input.substring(to + 3).trim());
                    String[] temporaryArray = this.input.substring(to + 3)
                            .trim().split("[\\s/\\-]+");

                    int lenTemp = temporaryArray.length;
                    for (int i=0; i<temporaryArray.length/2; i++) {
                        String temp = temporaryArray[i];
                        temporaryArray[i] = temporaryArray[lenTemp - 1 - i];
                        temporaryArray[lenTemp - 1 - i] = temp;
                    }
                    if (temporaryArray[lenTemp - 1].length() == 1) {
                        temporaryArray[lenTemp - 1] = "0" + temporaryArray[lenTemp - 1];
                    }
                    if (temporaryArray[lenTemp - 2].length() == 1) {
                        temporaryArray[lenTemp - 2] = "0" + temporaryArray[lenTemp - 2];
                    }

                    toDateTime = String.join("-", temporaryArray);

                } catch (WrongTimeFormatException exception) {
                    throw exception;
                }
                task = new Events(this.input.substring(space + 1, from).trim(), fromDateTime, toDateTime);
                token = new Token(Command.EVENT, task);
            }
            break;
        case "deadline":
            flag = Arrays.asList(split).indexOf("/by");
            if (split.length < 4) {
                throw new MissingArgumentsExceptionDeadlines("deadline");
            } else if (flag < 2 || flag == split.length -1) {
                throw new MissingArgumentsExceptionDeadlines("deadline");
            }else {
                int space = this.input.indexOf(" ");
                int by = this.input.indexOf("/by");
                String byDateTime;
                try {
                    checkTimeFormat(this.input.substring(by + 3).trim());
                    String[] temporaryArray = this.input.substring(by + 3)
                            .trim().split("[\\s/\\-]+");
                    int lenTemp = temporaryArray.length;
                    for (int i = 0; i < temporaryArray.length / 2; i++) {
                        String temp = temporaryArray[i];
                        temporaryArray[i] = temporaryArray[lenTemp - 1 - i];
                        temporaryArray[lenTemp - 1 - i] = temp;
                    }
                    if (temporaryArray[lenTemp - 1].length() == 1) {
                        temporaryArray[lenTemp - 1] = "0" + temporaryArray[lenTemp - 1];
                    }
                    if (temporaryArray[lenTemp - 2].length() == 1) {
                        temporaryArray[lenTemp - 2] = "0" + temporaryArray[lenTemp - 2];
                    }
                    byDateTime = String.join("-", temporaryArray);
                } catch (WrongTimeFormatException exception) {
                    throw exception;
                }
                task = new Deadlines(this.input.substring(space + 1, by).trim(), byDateTime);
                token = new Token(Command.DEADLINE, task);
            }
            break;
        default:
            throw new InvalidCommandException("InvalidCommandException");
        }
        return token;
    }

    private void checkTimeFormat(String string) throws WrongTimeFormatException {
        String[] splitString = string.split("[\\s/\\-]+");
        if (splitString.length < 3) {
            throw new WrongTimeFormatException("wrong time buddy");
        } if (splitString.length > 5) {
            throw new WrongTimeFormatException("Too many inputs");
        } else {
            try {
               int year = Integer.parseInt(splitString[2]);
               int month = Integer.parseInt(splitString[1]);
               int day = Integer.parseInt(splitString[0]);
               checkRealDate(year, month, day);
            } catch (NumberFormatException e) {
                throw new WrongTimeFormatException("Use numerals for date");
            } catch (WrongTimeFormatException exception) {
                throw exception;
            }
            if (splitString.length > 3) {
                String twelveHourFormat = "";
                String[] timeSplit =  splitString[3].split(":");
                if (timeSplit.length == 1) {
                    throw new WrongTimeFormatException("Use : separator in time format");
                }
                if (splitString.length > 4) {
                   twelveHourFormat = splitString[4];
                }
                checkRealTime(splitString[3], twelveHourFormat);
            }
        }
    }

    private void checkRealDate(int year , int month, int day) throws WrongTimeFormatException {
        try {
            LocalDate dateToBeChecked = LocalDate.of(year, month, day);
            if (dateToBeChecked.isBefore(LocalDate.now())) {
                throw new WrongTimeFormatException("Can't go back in time buddy");
            }
        } catch (DateTimeException e) {
            throw new WrongTimeFormatException("Date and time are impossible buddy");
        } catch (WrongTimeFormatException exception) {
            throw exception;
        }
    }

    private void checkRealTime(String time, String twelveHourFormat) throws WrongTimeFormatException {
        if (twelveHourFormat.equals("")) {
            if (time.length() < 5) {
                time = "0" + time;
            }
            try {
                LocalTime.parse(time);
            } catch (DateTimeException exception) {
                throw new WrongTimeFormatException("Date and time are impossible buddy");
            }
        } else if (twelveHourFormat.equals("PM") || twelveHourFormat.equals("pm") || twelveHourFormat.equals("Pm")
                || twelveHourFormat.equals("pM")) {
            try {
                LocalTime.parse(time + " pm", DateTimeFormatter.ofPattern("h:mm a"));
            } catch (DateTimeException exception) {
                throw new WrongTimeFormatException("Date and time are impossible buddy");
            }
        }
    }

}
