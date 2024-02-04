package earl.logic.stubs;

import earl.util.Ui;

public class UiStub extends Ui {

    @Override
    public void makeResponse(String... arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
