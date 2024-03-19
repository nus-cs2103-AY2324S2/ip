# Huyang Chatbot 🤖

> "Your mind is for having ideas, not holding them." – David Allen ([source](https://gettingthingsdone.com/))

**Huyang** frees your mind of having to remember things you need to do. It's,

- *text-based*
- **easy to learn**
- ***FAST SUPER FAST*** to use

And it is **FREE** (for now)!

![Huyang](Ui.png)

### Features:

- [x] Managing tasks
- [x] Managing deadlines
- [x] Managing events
- [x] `delete`, `find`, `list` functions

## Installation

To create a JAR file, run `./gradlew shadowJar` in your terminal. Alternatively, you may download the JAR file directly.

### Download JAR File

You can download the latest version of our application from the [releases page](https://github.com/YourUsername/YourRepository/releases).

1. Navigate to the [releases page](https://github.com/hiivan/ip/releases).
2. Find the latest release version (e.g., v0.2) and click on it.
3. Download the JAR file from the assets section.

### Usage

Once you've downloaded the JAR file, you can use it in your project by including it in your classpath or running it using the `java -jar huyang.jar` command.

## Commands to Manage Your Tasks

Interact with Huyang through typed commands in the following format: `COMMANDNAME ... /OPTION ...`

### `list`: View your tasks.
- **Usage**: `list`
  - Displays all tasks on your list.

### `todo`, `deadline`, `event`: Add a task to your list.
- **Usage**: `todo NAME`
  - Adds a todo task named `NAME` to your list.
- **Usage**: `deadline NAME /by DATE`
  - Adds a deadline task named `NAME` to your list, due by `DATE` in the format `YYYY-MM-DD HHMM`.
  - **Example**: `deadline Homework /by 2024-03-12 2359`
- **Usage**: `event NAME /from START_DATE /to END_DATE`
  - Adds an event task named `NAME` to your list, from `START_DATE` to `END_DATE`.
  - **Example**: `event Hackathon /from 2024-03-11 0000 /to 2024-03-12 2359`

### `mark`, `unmark`: Mark a task as done or not done.
- **Usage**: `mark INDEX`
  - Marks the task at `INDEX` as done.
- **Usage**: `unmark INDEX`
  - Reverses the action, marking the task as not done.

### `delete`: Remove a task from your list.
- **Usage**: `delete INDEX`
  - Removes the task at `INDEX`.

### `find`: Filter tasks by their names.
- **Usage**: `find TEXT`
  - Displays tasks that contain `TEXT` in their names.

### `bye`: Exit the app.
- **Usage**: `bye`

These commands are designed to make task management efficient and intuitive, allowing you to focus more on what matters most.
