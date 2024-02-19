# HAL 9000 User Guide

![HAL 9000 UI Screenshot](docs/Ui.png)

Welcome to the HAL 9000 User Guide!

## Adding a Todo

To add a new task to your list without a specific deadline or event time, use the `todo` command followed by the task description.

Example: `todo Read a book`

## Adding a Deadline

For tasks that need to be completed by a certain date, use the `deadline` command followed by the task description and `/by` with the date in `YYYY-MM-DD` format.

Example: `deadline Submit CS2109 Problem Set /by 2024-03-01`

## Adding an Event

To schedule an event, use the `event` command followed by the event description, `/from` with the start date, and `/to` with the end date in `YYYY-MM-DD` format.

Example: `event CS2103T project meeting /from 2024-03-15 /to 2024-03-16`

## Listing All Tasks

To view all your tasks, simply use the `list` command.

Example: `list`

## Marking a Task as Done

To mark a task as completed, use the `mark` command followed by the task number.

Example: `mark 1`

## Unmarking a Task

If you need to mark a task as not done, use the `unmark` command followed by the task number.

Example: `unmark 1`

## Deleting a Task

To remove a task from your list, use the `delete` command followed by the task number.

Example: `delete 1`

## Finding Tasks by Keyword

To search for tasks that contain a specific keyword, use the `find` command followed by the keyword.

Example: `find book`

## Exiting the Program

To exit TaskMaster Pro, simply use the `bye` command.

Example: `bye`
