package nihao.action;

public abstract class Action {
    public abstract void execute() throws Exception;

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }
}
