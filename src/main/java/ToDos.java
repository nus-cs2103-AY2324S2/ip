public class ToDos extends Task{
    private Task task;
    public ToDos(String name) {
        super(name);
    }

    @Override
    public void taskInfo() {
        System.out.print("[T]");
        super.taskInfo();
        System.out.println();
    }
}
