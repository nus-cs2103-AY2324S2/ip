package simpli.ui;

import simpli.configs.SimpliConfiguration;

public class Ui {
    public void display(String content) {
        String msg = String.format(SimpliConfiguration.PLACEHOLDER, content.replace("\n", "\n\t\t"));

        System.out.println(msg);
    }
}
