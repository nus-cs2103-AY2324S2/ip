package dukegui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class LauncherGUI {
  public static void main(String[] args) {
    Application.launch(Main.class, args);
  }
}