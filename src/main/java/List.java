public class List implements Command{
    @Override
    public void reply() {
        int i=0;
        for (Task task : Task.task_list) {
            System.out.printf("%s. %s\n",++i,task);
        }
    }
}
