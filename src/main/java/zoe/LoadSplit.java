package zoe;

public enum LoadSplit {
    Task_Type(0),
    Description(1),
    Status(2);

    private final int idx;
    private LoadSplit(int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }
}
