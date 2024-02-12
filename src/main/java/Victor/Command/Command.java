package victor.command;

abstract class Command {
    private String taskType;
    abstract void execute(String taskType);

}

class AddCommand extends Command {

    @Override
    void execute(String taskType) {

    }
}
