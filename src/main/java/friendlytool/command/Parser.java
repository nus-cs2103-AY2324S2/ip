package friendlytool.command;

import java.util.Arrays;
import java.util.StringTokenizer;

import friendlytool.process.FtException;

/**
 * Parser for identifying commands.
 */
public class Parser {

    /**
     * Identifies the type of command from the input.
     *
     * @param s user input.
     * @return type of command.
     */
    public static CommandTypes parseType(String s) {
        StringTokenizer st = new StringTokenizer(s);
        return CommandTypes.valueOf(st.nextToken().toUpperCase());
    }

    /**
     * parses toDo tasks.
     *
     * @param s user input.
     * @return name of the task.
     */
    public static String parseToDo(String s) {
        StringBuilder sbTd = new StringBuilder();
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            sbTd.append(" ").append(token);
        }
        return sbTd.toString().trim();
    }


    /**
     * parses DeadLine tasks.
     *
     * @param s user input.
     * @return string array containing name and by date.
     */
    public static String[] parseDeadline(String s) {
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        StringBuilder sbDl = new StringBuilder();
        StringBuilder sbBy = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.equals("/by")) {
                while (st.hasMoreTokens()) {
                    sbBy.append(" ").append(st.nextToken());
                }
                break;
            } else {
                sbDl.append(" ").append(token);
            }
        }
        String dt = sbDl.toString().trim();
        String by = sbBy.toString().trim();
        return new String[]{dt, by};
    }

    /**
     * Parses Event tasks.
     *
     * @param s user input.
     * @return string array containing name, from date, and to date.
     */
    public static String[] parseEvent(String s) {
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        StringBuilder sb = new StringBuilder();
        StringBuilder sbFrom = new StringBuilder();
        StringBuilder sbTo = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.equals("/from")) {
                while (st.hasMoreTokens()) {
                    String curr = st.nextToken().trim();
                    if (curr.equals("/to")) {
                        while (st.hasMoreTokens()) {
                            sbTo.append(" ").append(st.nextToken());
                        }
                    } else {
                        sbFrom.append(" ").append(curr);
                    }
                }
            } else {
                sb.append(" ").append(token);
            }
        }
        String name = sb.toString().trim();
        String from = sbFrom.toString().trim();
        String to = sbTo.toString().trim();
        return new String[]{name, from, to};
    }

    /**
     * Parses the text the in the save file.
     *
     * @param s text in the save file.
     * @return string array containing data for the task
     */
    public static String[] parseSave(String s) {
        String[] elements = s.split("\\|");
        elements = Arrays.stream(elements).map(String::trim).toArray(String[]::new);
        return elements;
    }


    /**
     * Converts the string to number index.
     *
     * @param s user input.
     * @return index for finding tasks.
     * @throws FtException if no index is provided
     */
    public static int parseNumber(String s) throws FtException {
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        if (!st.hasMoreTokens()) {
            throw new FtException("Error: No index provided");
        }
        return Integer.parseInt(st.nextToken());
    }

    /**
     * Converts the string to boolean.
     *
     * @param s user input.
     * @return boolean value for isDone.
     */
    public static Boolean parseBool(String s) {
        return Boolean.parseBoolean(s);
    }

    /**
     * Parse the input to find keyword
     *
     * @param s user input
     * @return keyword that user provided
     * @throws FtException if the number of words is incorrect
     */
    public static String parseKeyword(String s) throws FtException {
        String[] strings = s.split(" ");
        if (strings.length < 2) {
            throw new FtException("Please provide keyword!");
        } else if (strings.length == 2) {
            return strings[1];
        } else {
            throw new FtException("Please provide one word");
        }
    }
}
