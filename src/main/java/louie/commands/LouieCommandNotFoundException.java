package louie.commands;

import louie.LouieException;

public class LouieCommandNotFoundException extends LouieException {
    public LouieCommandNotFoundException() {
        super("command not found");
    }
}
