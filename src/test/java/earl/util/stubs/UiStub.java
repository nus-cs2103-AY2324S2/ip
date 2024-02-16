package earl.util.stubs;

import earl.util.Ui;

/**
 * Class acting as a stub for {@code Ui}.
 */
public class UiStub extends Ui {

    @Override
    public void makeResponse(String... arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
