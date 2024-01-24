package chatbot;

class DeadlineTask extends Task {
    private String deadlineTime;

    public DeadlineTask(String desc, String deadlineTime) {
        super(desc);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineTime);
    }
}
