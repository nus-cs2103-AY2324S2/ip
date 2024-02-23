package eve.parser;

import java.time.LocalDateTime;

import eve.Eve;
import eve.TaskList;
import eve.exceptions.EveExceptions;

public class Parser {

    private Eve eve;

    public Parser(Eve eve) {
        this.eve = eve;
    }

    public static String parse(String input) throws EveExceptions {
        String[] tempyArr = input.split(" ", 2);
        String commandCheck = tempyArr[0].toUpperCase();

        Tasklist list = eve.getTaskList();
        try {
                switch (commandCheck) {
                case "BYE":
                    Commands.commandBye(list);
                    break;
                case "LIST":
                    TaskList.commandList(list);
                    break;
                case "MARK":
                    TaskList.commandMark(tempyArr, list);
                    break;
                case "UNMARK":
                    TaskList.commandUnMark(tempyArr, list);
                    break;
                case "DELETE":
                    TaskList.commandDelete(tempyArr, list);
                    break;
                case "TODO":
                    TaskList.commandTodo(tempyArr, list);
                    break;
                case "DEADLINE":
                    TaskList.commandDeadline(tempyArr, list);
                    break;
                case "EVENT":
                    TaskList.commandEvent(tempyArr, list);
                    break;


                case "FIND":
                    TaskList.commandFind(tempyArr, list);
                    break;

                default:
                    throw new EveExceptions("OOPS!!! I'm sorry, but I don't know what that means, please try again");

                }
            } catch (EveExceptions e) {
                System.out.println(e.getMessage());
            }

    }
    
    public static LocalDateTime stringToDateTime(String s) {
        String[] temp = s.split(" ");
        int hour = Integer.parseInt(temp[1].substring(0, 2));
        int min = Integer.parseInt(temp[1].substring(2));
        String[] dateTemp = temp[0].split("/");
        int day = Integer.parseInt(dateTemp[0]);
        int month = Integer.parseInt(dateTemp[1]);
        int year = Integer.parseInt(dateTemp[2]);

        LocalDateTime newDateTime = LocalDateTime.of(year, month, day, hour, min);

        return newDateTime;
    }
}
