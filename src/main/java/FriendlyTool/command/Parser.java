package FriendlyTool.command;

import FriendlyTool.main.ftException;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Parser {

    public static CommandTypes parseType(String s) {
        StringTokenizer st = new StringTokenizer(s);
        return CommandTypes.valueOf(st.nextToken().toUpperCase());
    }

    public static String parseToDo(String s) {
        StringBuilder sbTD = new StringBuilder();
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            sbTD.append(" ").append(token);
        }
        return sbTD.toString();
    }

    public static String[] parseDeadLine(String s) {
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        StringBuilder sbDL = new StringBuilder();
        StringBuilder sbBy = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.equals("/by")) {
                while (st.hasMoreTokens()) {
                    sbBy.append(" ").append(st.nextToken());
                }
                break;
            } else {
                sbDL.append(" ").append(token);
            }
        }
        String dt = sbDL.toString();
        String by = sbBy.toString();
        return new String[]{dt, by};
    }

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
        String name = sb.toString();
        String from = sbFrom.toString();
        String to = sbTo.toString();
        return new String[]{name, from , to};
    }

    public static String[] parseSave(String s) {
        String[] elements = s.split("\\|");
        elements = Arrays.stream(elements).map(String::trim).toArray(String[]::new);
        return elements;
    }

    public static int parseNumber(String s) throws ftException {
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        if (!st.hasMoreTokens()) {
            throw new ftException("Error: No index provided");
        }
        return Integer.parseInt(st.nextToken());
    }

    public static Boolean parseBool(String s) {
        return Boolean.parseBoolean(s);
    }
}
