public interface Action {
    public String response();

    default boolean isExit() {
        return false;
    }
}