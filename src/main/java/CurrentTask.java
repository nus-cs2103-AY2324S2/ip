import java.time.LocalDate;

class CurrentTask implements Command{
    private LocalDate currentTime;
    private TaskList taskList;
    public CurrentTask(TaskList taskList) {
        this.currentTime = LocalDate.now();
        this.taskList=taskList;
    }

    @Override
    public void reply() {
        System.out.println("    Dear sir, here are the tasks by now:");
        int i=0;
        for (Task task: taskList.getTaskList()) {
            if (task.isTimeForStart(currentTime)) {
                System.out.printf("    %s.%s\n",++i,task);
            }
        }
    }
}
