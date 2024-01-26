import java.time.LocalDate;

class CurrentTask implements Command{
    LocalDate currentTime;

    public CurrentTask() {
        this.currentTime = LocalDate.now();
    }

    @Override
    public void reply() {
        System.out.println("    Dear sir, here are the tasks by now:");
        int i=0;
        for (Task task: Task.task_list) {
            if (task.isTimeForStart(currentTime)) {
                System.out.printf("    %s.%s\n",++i,task);
            }
        }
    }
}
