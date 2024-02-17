package Commands;

import Exceptions.DudeException;

public abstract class Command {

    private final String format;
    private final String regex;

    protected Command(String format, String regex) {
        this.format = format;
        this.regex = regex;
    }

    public abstract String execute() throws DudeException;

    public String getFormat() {
        return this.format;
    }

    public String getRegex() {
        return this.regex;
    }

}
