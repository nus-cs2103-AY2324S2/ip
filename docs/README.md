# SamuelBot User Guide

// Update the title above to match the actual product name

// Product screenshot goes here
![Ui](https://github.com/SamuelZero1/ip/assets/111351811/4c99bafc-e6b7-4f23-8b57-1e7b0fe7a362)


// Product intro goes here
Welcome to SamuelBot, your personal task manager! SamuelBot helps you keep track of your tasks by allowing you to add, delete, mark as done, and find tasks easily. It can even save your previous added tasks! Below is a guide to help you make the most out of SamuelBot's features.

## Adding todos
Command format: todo <description>

e.g. User's input:
todo Read a book

expected outcome example:
Got it. I've added this task:
[T][ ] Read a book
Now you have 1 tasks in the list.

## Adding deadlines
Command format: deadline <description> /by <due_date>
due date would be in the format YYYY-MM-DD

e.g. User's input:
deadline Submit report /by 2024-03-15

expected outcome example:
Got it. I've added this task:
[D][ ] Submit report (by: 3月 15 2024)
Now you have 2 tasks in the list.

## Adding events
Command format: event <description> /from <start_date> /to <end_date>
<start_date> and <end_date> should be in the format YYYY-MM-DD HH:mm 

e.g. User's input:
event Play Mario Kart /from 2024-03-10 14:00 /to 2024-03-10 16:00


expected outcome example:
Got it. I've added this task:
[E][ ] Play Mario Kart (from: 2024-03-10 14:00 to 2024-03-10 16:00)
Now you have 3 tasks in the list.


## Listing Tasks
To list all tasks, simply type:
list

Expected Output:
Here are the tasks in your list:
1.[T][ ] Read a book
2.[D][ ] Submit report (by: 3月 15 2024)
3.[E][ ] Play Mario Kart (from: 2024-03-10 14:00 to 2024-03-10 16:00)

## Marking Tasks as Done
Command:
mark <task_number>

e.g. User's input:
mark 1

Expected Output:
Nice! I've marked this task as done:
[T][X] Read a book

## Marking Tasks as Not Done
Command:
unmark <task_number>

e.g. User's input:
unmark 1

Expected Output:
OK, I've marked this task as not done yet:
[T][] Read a book

## Deleting a Task
Command:
delete <task_number>
e.g User's input:
delete 1
Expected Output:
Noted. I've removed this task:
[D][ ] Submit report (by: 3月 15 2024)
Now you have 2 tasks in the list.
Here are the tasks in your list:
1.[T][ ] Read a book
2.[E][ ] Play Mario Kart (from: 2024-03-10 14:00 to 2024-03-10 16:00)

## Finding Task
Command:
find <keyword>
e.g. User's input:
find Kart
Expected Output:
Here are the matching tasks in your list:
1. [E][ ] Play Mario Kart (from: 2024-03-10 14:00 to 2024-03-10 16:00)

