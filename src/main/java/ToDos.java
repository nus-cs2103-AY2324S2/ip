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
    public void taskInfo() {
        System.out.print("[T]");
        super.taskInfo();
        System.out.println();
    }
}
