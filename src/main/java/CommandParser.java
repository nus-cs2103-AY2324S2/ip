package main.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private String input;
    private String type;
    public CommandParser(String input) {
        this.input = input;
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex != -1) {
            this.type = input.substring(0, firstSpaceIndex);
        } else {
            this.type = input;
        }
    }
    public String getType() {
        return this.type;
    }
    public String[] parseInput() {
        switch (this.type) {
            case "todo":
                String regex1 = "^(\\w+)(\\s)(.*)";
                Pattern pattern1 = Pattern.compile(regex1);
                Matcher matcher1 = pattern1.matcher(input);
                if (matcher1.find()) {
                    String description = matcher1.group(3);
                    return new String[]{description};
                }
                else {
                    // Throw Error, should not be doing text here
                    System.out.println("Input does not match the expected format.");
                    return null;
                }
            case "deadline":
                String regex2 = "^(\\w+) (.+?)\\/by (.+)$";
                Pattern pattern2 = Pattern.compile(regex2);
                Matcher matcher2 = pattern2.matcher(input);
                if (matcher2.find()) {
                    String description = matcher2.group(2);
                    String toTime = matcher2.group(3);
                    return new String[]{description, toTime};
                }
                else {
                    // Throw Error, should not be doing text here
                    System.out.println("Input does not match the expected format.");
                    return null;
                }
            case "event":
                String regex3 = "^(\\w+) (.+?) \\/from (.+?) \\/to (.+)$";
                Pattern pattern3 = Pattern.compile(regex3);
                Matcher matcher3 = pattern3.matcher(input);
                if (matcher3.find()) {
                    String description = matcher3.group(2);
                    String fromTime = matcher3.group(3);
                    String toTime = matcher3.group(4);
                    return new String[]{description, fromTime, toTime};
                }
                else {
                    // Throw Error, should not be doing text here
                    System.out.println("Input does not match the expected format.");
                    return null;
                }
            case "mark":
            case "unmark":
                String regex4 = "^(\\w+) (\\d+)";
                Pattern pattern4 = Pattern.compile(regex4);
                Matcher matcher4 = pattern4.matcher(input);
                if (matcher4.find()) {
                    String number = matcher4.group(2);
                    return new String[]{number};
                } else {
                    System.out.println("Input does not match the expected format.");
                    return null;
                }
            default:
                return null;
        }
    }
}
