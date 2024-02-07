import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {

    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy M d HH mm");

    public Parser() {
    }

    public List<String> convertDateDeadline(List<String> splitStr) throws CroException {
        int byIndex = splitStr.indexOf("/by");
        if (byIndex < 0) {
            throw new CroException("deadline not found, please include with '/by' as an indicator.");
        }
        List<String> dateSplitStr = splitStr.subList(byIndex + 1, splitStr.size());
        LocalDateTime time = LocalDateTime.parse(String.join(" ", dateSplitStr), inputFormat);
        splitStr.subList(byIndex + 1, splitStr.size()).clear();
        splitStr.add(time.toString());
        return splitStr;
    }
    public List<String> convertDateEvent(List<String> splitStr) throws CroException {
        int fromIndex = splitStr.indexOf("/from");
        int toIndex = splitStr.indexOf("/to");
        if (fromIndex < 0 || toIndex < 0) {
            throw new CroException("event timings not found, please use /from and /to to indicate.");
        }
        List<String> fromSplitStr = splitStr.subList(fromIndex + 1, toIndex);
        List<String> toSplitStr = splitStr.subList(toIndex + 1, splitStr.size());
        LocalDateTime from = LocalDateTime.parse(String.join(" ", fromSplitStr), inputFormat);
        LocalDateTime to = LocalDateTime.parse(String.join(" ", toSplitStr), inputFormat);

        splitStr.subList(toIndex + 1, splitStr.size()).clear();
        splitStr.add(to.toString());
        splitStr.subList(fromIndex + 1, toIndex).clear();
        splitStr.add(fromIndex + 1, from.toString());

        return splitStr;
    }
    public boolean handleInput(Cro cro) {
        String inText = scanner.nextLine();
        List<String> splitStr = new ArrayList<>(Arrays.asList(inText.trim().split("\\s+")));
        String command = splitStr.remove(0);
        try {
            if (command.equals("bye")) {
                System.out.println("-----------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------");
                return false;
            } else if (command.equals("list")) {
                cro.taskList.displayTasks();
            } else if (command.equals("mark")) {
                cro.taskList.markTaskAsDone(splitStr);
            } else if (command.equals("unmark")) {
                cro.taskList.markTaskAsUndone(splitStr);
            } else if (command.equals("todo")) {
                List<String> res = new ArrayList<>(Arrays.asList("T","0"));
                res.addAll(splitStr);
                cro.taskList.addToDo(res);
            } else if (command.equals("deadline")) {
                List<String> res = new ArrayList<>(Arrays.asList("D","0"));
                splitStr = convertDateDeadline(splitStr);
                res.addAll(splitStr);
                cro.taskList.addDeadline(res);
            } else if (command.equals("event")) {
                List<String> res = new ArrayList<>(Arrays.asList("E","0"));
                splitStr = convertDateEvent(splitStr);
                res.addAll(splitStr);
                cro.taskList.addEvent(res);
            } else if (command.equals("delete")) {
                cro.taskList.deleteEvent(splitStr);
            } else {
                throw new CroException("Unknown command. Please try again.");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }
}
