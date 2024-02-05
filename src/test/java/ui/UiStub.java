package ui;

/**
 * Stub implementation of the Ui class for testing purposes.
 */
public class UiStub extends Ui {
    private boolean showToUserCalled;
    private boolean showErrorMessageCalled;

    @Override
    public void showToUser(String... messages) {
        showToUserCalled = true;
        // For simplicity, do nothing in the stub implementation
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        showErrorMessageCalled = true;
        // For simplicity, do nothing in the stub implementation
    }

    public boolean wasShowToUserCalled() {
        return this.showToUserCalled;
    }
}
