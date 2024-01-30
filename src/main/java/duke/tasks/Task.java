package duke.tasks;

public class Task {
    private Boolean status;
    private String detail;

    public Task(Boolean status, String detail) {
        this.status = status;
        this.detail = detail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String inFileStringFormat() {
        String intStatus = this.status? "1":"0";
        return intStatus + "|" + this.detail;
    }
    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.detail;
        } else {
            return "[ ] " + this.detail;
        }
    }
}
