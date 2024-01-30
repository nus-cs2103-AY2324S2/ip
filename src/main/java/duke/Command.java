package duke;

public class Command {

    public Duke.CommandType type;

    public String[] args;

    public Command(Duke.CommandType newType) {
        this.type = newType;
        this.args = new String[]{};
    }
    public Command(Duke.CommandType newType, String[] newArgs) {
        this.type = newType;
        this.args = newArgs;
    }


}
