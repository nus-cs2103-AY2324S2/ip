package Parser;

import java.util.ArrayList;

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

    public String[] parse() {
        String[] ans = new String[4];
        String[] split = this.input.split(" ");
        switch(split[0]) {
            case "list":
                if (split.length != 1) {
                    ans[0] = "invalid";
                } else {
                    ans[0] = "list";
                }
                break;
            case "bye":
                if (split.length != 1) {
                    ans[0] = "invalid";
                } else {
                    ans[0] = "bye";
                }
                break;
            case "unmark":
            case "mark":
                if (split.length != 2) {
                    ans[0] = "invalid";
                } else {
                    ans = split;
                }
                break;
            case "todo":
                if (split.length == 1) {
                    ans[0] = "invalid";
                } else {
                    int space = this.input.indexOf(" ");
                    ans[0] = "todo";
                    ans[1] = this.input.substring(space + 1, this.input.length());
                }
                break;
            case "event":
                if (split.length == 1) {
                    ans[0] = "invalid";
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
                if (split.length == 1) {
                    ans[0] = "invalid";
                } else {
                    int space = this.input.indexOf(" ");
                    int slash = this.input.indexOf("/");
                    ans[0] = "deadline";
                    ans[1] = this.input.substring(space + 1, slash).trim();
                    int by = this.input.indexOf(" ", slash + 1);
                    ans[2] = this.input.substring(by+1, this.input.length()).trim();
                }
                break;
            default:
                ans[0] = "invalid";
                break;
        }
        return ans;
    }

}
