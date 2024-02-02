public class Delete implements Action {
    public Task deletedTask;

    public Delete(Task deletedTask) {
        this.deletedTask = deletedTask;
    }

    @Override
    public String response() {
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\nNow you have " +
            " tasks in the list." ;
    }
}

