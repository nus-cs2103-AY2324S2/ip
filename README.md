# Knight Guide

Hail, Your Excellency!

I, thy loyal Knight, doth present myself as thy personal aide in the noble quest of task management.

I am a **command line application**,
crafted to assist thee in the meticulous tracking of thy tasks, deadlines, and noble events.
Fear not, for I am fashioned to be as simple as the swing of a sword, intuitive in nature.

![A screenshot of the basic Knight interface](/docs/Ui.png)

## Features
1. Add tasks (`todo`, `deadline`, `event`)
2. List tasks (`list`)
3. Mark tasks as done (`mark`, `unmark`)
4. Delete tasks (`delete`)
5. Find tasks (`find`)
6. Update tasks (`update`)
7. Save tasks to file (`save`)

## Usage
A simple guide to using me, thy Knight, is as follows:

- Add a task using the `todo`, `deadline`, or `event` command.
    - Todo tasks are simple tasks with only a name.
    - Deadline tasks have a due date specified with `/by`,
    - Event tasks have a start and end date specified with `/from` and `/to`.
- When referring to a task using `mark`, `unmark` and `delete`, use the task number as shown in the list.
    - For example, `mark 1` marks the first task as done.
- The command `find` searches for tasks with descriptions containing the given keyword.
- Update a task as if thou art adding a new task, but with the prefix changed to `update [index]`.
    - For example, `update 1 [description] /by [date]` updates the first task, which must be a deadline.
    - Note that thou canst not update the species of a task.
- Save the tasks to file using the `save` command.
    - The file is saved in the same directory as the Knight application with the name `scroll_of_tasks.txt`.

When thou art unsure of the use of a command,
simply type the command without any arguments to see a brief description of its usage.

At thy service, Your Excellency, is thy
   ```
┓┏┓  •  ┓  
┃┫ ┏┓┓┏┓┣┓╋
┛┗┛┛┗┗┗┫┛┗┗
          ┛
   ```
