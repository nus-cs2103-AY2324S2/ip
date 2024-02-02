import java.util.ArrayList;

public abstract class Parser {

    private static final ArrayList<String> COMMANDS = new ArrayList<>() {
        {
            add("todo");
            add("deadline");
            add("event");
            add("list");
            add("mark");
            add("unmark");
            add("bye");
            add("delete");
        }
    };

    public static boolean isValidCommand(String input) {
        return COMMANDS.contains(input.toLowerCase());
    }

    public static boolean isNumber(String input) {
        char[] digits = input.toCharArray();
        boolean isNumber = true;

        for (char c : digits) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

    public static String changeDateFormat(String month, String day, String year) {
        String newMonth = "";
        String newDay = "";

        switch (month) {
            case "Jan":
                newMonth = "01";
                break;
            case "Feb":
                newMonth = "02";
                break;
            case "Mar":
                newMonth = "03";
                break;
            case "Apr":
                newMonth = "04";
                break;
            case "May":
                newMonth = "05";
                break;
            case "Jun":
                newMonth = "06";
                break;
            case "Jul":
                newMonth = "07";
                break;
            case "Aug":
                newMonth = "08";
                break;
            case "Sep":
                newMonth = "09";
                break;
            case "Oct":
                newMonth = "10";
                break;
            case "Nov":
                newMonth = "11";
                break;
            case "Dec":
                newMonth = "12";
                break;
        }

        if (day.length() == 1) {
            newDay = "0" + day;
        } else {
            newDay = day;
        }

        return year + "-" + newMonth + "-" + newDay;
    }

}
