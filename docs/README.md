# Lulu Task Manager 🤖

Lulu Task Manager is a simple command-line task manager written in Java. Keep track of your tasks with ease!

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Lulu Task Manager is a Java-based task management application. It allows users to add, mark, unmark, and delete tasks, as well as perform queries based on specific criteria. The application utilizes a command-line interface for a straightforward user experience.

## Features

- **Task Management:** Add, mark, unmark, and delete tasks.
  - Example syntax:
      - mark *5*
      - delete *1 2 4*
- **Task Types:** Support for different task types, including Todo, Deadline, and Event.
  - Example syntax:
      - todo *todo_name*
      - deadline *deadline_name* /by *2024-03-04*
      - event *event_name* /from *2024-01-02* /to *2024-03-04*
- **Query Tasks:** Find tasks based on name, query tasks based on timing
  - Example syntax:
      - find *query_string*
      - query *task_type* *2022-03-04*

- [T][x] Todo
- [D][ ] Deadline (by: 2 January 2023)
- [E][ ] Event (from: 4 March 2022 to: 6 May 2024)

## Installation

Ensure you have Java installed on your system. Clone the repository and compile the Java files.

```bash
# Clone the repository
git clone https://github.com/your-username/lulu-task-manager.git

# Compile the Java files
javac -d . src/*.java
```

This is Lulu `Lulu chatbot = new Lulu();`.

```java
public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        UI.start();
        try {
            chatbot.respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        UI.exit();
    }
```