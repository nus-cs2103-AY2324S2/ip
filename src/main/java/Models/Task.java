package Models;

import Database.Database;

import java.io.File;

public class Task {

  private int id;
  private String name;
  private boolean isDone;

  public Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  public Task(String name, boolean isDone) {
    this.name = name;
    this.isDone = isDone;
  }

  public Task(int id, String name, boolean isDone) {
    this.id = id;
    this.name = name;
    this.isDone = isDone;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }

  public boolean isDone() {
    return isDone;
  }

  public void markAsDone() {
    isDone = true;
  }

  public void markAsUndone() {
    isDone = false;
  }

  @Override
  public String toString() {
    String done = isDone ? "X" : " ";
    return "[" + done + "] " + name;
  }

  public String toDataString() {
    String done = this.isDone() ? "1" : "0";
    return done + " | " + this.getName();
  }
}
