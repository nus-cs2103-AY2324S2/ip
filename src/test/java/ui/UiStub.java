package ui;

import ui.Ui;

public class UiStub extends Ui {
    public boolean showToUserCalled;
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
}