public abstract class Command {
    public static String getUsage() {
        return "Usage:";
    }

    public abstract void execute() throws MeanDukeException;

    public boolean isExitCommand() {
        return false;
    }

}
