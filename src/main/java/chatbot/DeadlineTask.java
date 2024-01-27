package chatbot;

import java.io.Serializable;

class DeadlineTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
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
