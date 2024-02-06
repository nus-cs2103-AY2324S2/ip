package bytetalker.task;

public enum TaskType {
    TODO("T") {
    },
    DEADLINE("D") {

    },
    EVENT("E") {

    };

    TaskType(String icon) {
        this.icon = icon;
    }

    private String icon;

    public String getIcon() {
        return this.icon;
    }
}
