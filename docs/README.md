# Kewgy User Guide

![Kewgy UI](Ui.png)

Welcome to the user guide for Kewgy, your personal task management assistant through Command-Line Interface (CLI). Kewgy is designed to help you stay organized by managing your tasks and events efficiently.

## Getting Started

To begin using Kewgy, follow these simple steps:

1. **Download and Install**: Ensure you have Java installed on your system. Download the Kewgy program files and run the application.

2. **Launch Kewgy**: Open a terminal or command prompt window and navigate to the directory containing the Kewgy program files. Run the application by executing the command `java -jar kewgy.jar`.

3. **Start Managing Tasks**: Once Kewgy is launched, you can start managing your tasks and events using the available commands.

## List of Commands

Below is a list of commands supported by Kewgy:

- **list**: View all tasks in your task list.
- **todo [description]**: Add a todo task.
- **deadline [description] /by [deadline]**: Add a task with a deadline.
- **event [description] /at [time]**: Add an event with a specific time.
- **mark [task_number]**: Mark a task as completed.
- **unmark [task_number]**: Mark a completed task as incomplete.
- **delete [task_number]**: Delete a task from the task list.
- **find [keyword]**: Search for tasks containing the specified keyword.
- **update_time [task_number] [new_time]**: Update the time of an event or deadline task.
- **bye**: Exit Kewgy.

## Examples

- To add a todo task: `todo Complete homework assignment`
- To add a deadline task: `deadline Submit project proposal /by 2024-02-20`
- To mark a task as completed: `mark 1`
- To delete a task: `delete 3`
- To update the time of an event: `update_time 2 2024-02-25 14:00`

## Notes

- Task numbers are displayed alongside tasks when using the `list` command. Use these numbers to reference tasks in other commands.
- Date and time should be provided in the format `YYYY-MM-DD` for deadlines and events.
- Make sure to save your tasks before exiting Kewgy by using the `bye` command.

## Support

For any assistance or inquiries, please contact me at [yetong@u.nus.edu](mailto:yetong@u.nus.edu).

Thank you for using Kewgy to manage your tasks and events efficiently!
