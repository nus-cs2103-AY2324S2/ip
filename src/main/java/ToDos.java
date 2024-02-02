public class ToDos extends Task{
    private Task task;
    public ToDos(String name, Boolean status) {
        super(name, status);
    }



    @Override
    public String saveOutput() {
        return "T "+ super.saveOutput();
    }

    @Override
    public String taskInfo() {
        String output = "";
        output += "[T]";
        return output + super.taskInfo() + System.lineSeparator();
    }
}
