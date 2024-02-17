package main;

public class MainResponseCategorized {
    private boolean isError = false;
    private final String responseReturnedFromMain;

    public MainResponseCategorized(boolean isError, String responseReturnedFromMain) {
        this.isError = isError;
        this.responseReturnedFromMain = responseReturnedFromMain;
    }

    public boolean isMessageAError() {
        return isError;
    }

    public String getResponseReturnedFromMain() {
        return this.responseReturnedFromMain;
    }
}
