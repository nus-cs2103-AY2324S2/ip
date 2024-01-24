package Parser;

import DukeException.*;
import java.util.Arrays;

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

    public String[] parse() throws InvalidCommandException, MissingArgumentsException {
        String[] ans = new String[4];
        String[] split = this.input.split(" ");
        int flag;
        int flag2;
        switch(split[0]) {
            case "list":
                if (split.length != 1) {
                    throw new InvalidCommandException("InvalidCommandException");
                } else {
                    ans[0] = "list";
                }
                break;
            case "bye":
                if (split.length != 1) {
                    throw new InvalidCommandException("InvalidCommandException");
                } else {
                    ans[0] = "bye";
                }
                break;
            case "delete":
            case "unmark":
            case "mark":
                if (split.length != 2) {
                    throw new MissingArgumentsExceptionMarking(split[0]);
                } else {
                    try {
                        flag = Integer.parseInt(split[1]);
                    } catch (NumberFormatException e) {
                        throw new InvalidCommandException("InvalidCommandException");
                    }
                    ans = split;
                }
                break;
            case "todo":
                if (split.length == 1) {
                    throw new MissingArgumentsExceptionTodo("todo");
                } else {
                    int space = this.input.indexOf(" ");
                    ans[0] = "todo";
                    ans[1] = this.input.substring(space + 1, this.input.length());
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
                    int slash = this.input.indexOf("/");
                    int sSlash = this.input.indexOf("/", slash + 1);
                    ans[0] = "event";
                    ans[1] = this.input.substring(space + 1, slash).trim();
                    int from = this.input.indexOf(" ", slash + 1);
                    ans[2] = this.input.substring(from + 1, sSlash).trim();
                    int to = this.input.indexOf(" ", sSlash + 1);
                    ans[3] = this.input.substring(to + 1, this.input.length()).trim();
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
                    int slash = this.input.indexOf("/");
                    ans[0] = "deadline";
                    ans[1] = this.input.substring(space + 1, slash).trim();
                    int by = this.input.indexOf(" ", slash + 1);
                    ans[2] = this.input.substring(by+1, this.input.length()).trim();
                }
                break;
            default:
                throw new InvalidCommandException("InvalidCommandException");
        }
        return ans;
    }

}
