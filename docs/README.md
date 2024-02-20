# JavAssist User Guide

![Screenshot of product.](Ui.png)

Introducing JavAssist: your all-in-one solution for managing tasks and expenses effortlessly. 

With easy-to-use chat commands, the chatbot simplifies organization, saving you time and hassle. 

Try it now and experience the future of streamlined task and expense management!

## Features
- Add tasks
- Add deadlines
- Add events
- Delete todos
- Mark tasks as done / not done
- Search using keywords
- View tasks
- Add expenses
- Deduct expenses
- View expenses
- View total expenses
- Clos application
- Concatenate commands

## Adding tasks
Add a task with description.

Command: `todo [description]`

Example: `todo read book`

Expected output: Display new todo added and number of tasks in list.

Example output:
<pre>
Got it! I've added this task:  
    [T][] read book  
Now you have 1 task in the list.
</pre>
## Adding deadlines
Add a deadline with description and due date.

Command: `deadline [description] /by [dd-MM-yy hh:mm]`

Example: `deadline return book /by 20-02-2024 18:20`

Expected output: Display new deadline added and number of tasks in list.

Example output:
<pre>
Got it! I've added this task:  
    [D][] return book (by: Feb 20 2024, 18:20) 
Now you have 1 task in the list.
</pre>

## Adding events
Add an event with description and start and end date.

Command: `event [description] /from [dd-MM-yy hh:mm] /to [dd-MM-yy hh:mm]`

Example: `event project meeting /from 20-02-2024 20:00 /to 20-02-2024 22:00`

Expected output: Display new event added and number of tasks in list.

Example output:
<pre>
Got it! I've added this task:  
    [E][] project meeting (from: Feb 20 2024, 20:00 to Feb 20 2024, 22:00) 
Now you have 1 task in the list.
</pre>

## Listing all tasks
Lists the details of all tasks added.

Command: `list`

Expected output: Display all tasks with indexing.

Example output:
<pre>
Here are the tasks in your list:  
1. [E][] project meeting (from: Feb 20 2024, 20:00 to Feb 20 2024, 22:00) 
2. [T][X] read book
</pre>

## Marking tasks as done
Mark the task as done by providing the index associated when listed. Only one index per command.

Command: `mark [index of task]`

Example: `mark 1`

Expected output: Display details of the task that is marked, with [X] indicating done.

Example output:
<pre>
Nice! I've marked this task as done:  
    [T][X] read book
</pre>

## Marking tasks as not done
Mark the task as not done by providing the index associated when listed. Only one index per command.

Command: `unmark [index of task]`

Example: `unmark 1`

Expected output: Display details of the task that is unmarked, with [ ] indicating not done.

Example output:
<pre>
Ok! I've marked this task as not done yet:  
    [T][] read book
</pre>

## Deleting tasks
Delete the task by providing the index associated when listed. Only one index per command.

Command: `delete [index of task]`

Example: `delete 1`

Expected output: Display details of the task that is deleted and number of tasks remaining in list.

Example output:
<pre>
Noted I've removed this task:  
    [T][] read book
Now you have 4 tasks in the list.
</pre>

## Searching for tasks with keywords
Lists the task that contains the keywords specified. Can specify more than one keyword.

Command: `find [keyword1 keyword2 ...]`

Example: `find book meet`

Expected output: Display lists of the task that contains any substring matching the keywords.

Example output:
<pre>
Here are the matching tasks in your list:  
1. [T][X] read book
2. [E][] project meeting (from: Feb 20 2024, 20:00 to Feb 20 2024, 22:00)
3. [D][] return book (by: Feb 20 2024, 18:20
</pre>

## Adding expenses
Increment the expense of a specified category.

Command: `add [category] /amount [value]`
> categories can be one of: food, books, clothes, transport, grocery, entertainment, other

Example: `add grocery /amount 20.5`

Expected output: Display the updated expenses for each category.

Example output:
<pre>
Ca-Ching! Expense incremented. Here's the list of updated expenses:  
Food: $10.30
Transport: $0.00
Grocery: $20.50
Books: $0.00
Clothes: $100.00
Entertainment: $0.00
Other: $0.00
</pre>

## Deducting expenses
Decrement the expense of a specified category, only if the amount specified is less than or equals to the current expense.

Command: `deduct [category] /amount [value]`
> categories can be one of: food, books, clothes, transport, grocery, entertainment, other

Example: `deduct grocery /amount 10`

Expected output: Display the updated expenses for each category.

Example output:
<pre>
Ca-Ching! Expense deducted. Here's the list of updated expenses:  
Food: $10.30
Transport: $0.00
Grocery: $10.50
Books: $0.00
Clothes: $100.00
Entertainment: $0.00
Other: $0.00
</pre>

## Listing all expenses
Lists the expenses of each category.

Command: `list_e`

Expected output: Display the expenses for each category.

Example output:
<pre>
Here are the expenses for each category:  
Food: $10.00
Transport: $0.00
Grocery: $20.00
Books: $0.00
Clothes: $100.00
Entertainment: $0.00
Other: $0.00
</pre>

## Summing total expenses
Get the sum of all expenses.

Command: `total`

Expected output: Display the calculated total of all expenses.

Example output:
<pre>
Total expenses: $128.50
</pre>

## Closing the application
Exit the application.

Command: `bye`

Expected output: Application Window will be closed.

## Concatenating commands
Input multiple commands in one go. Each command will be executed sequentially from left to right.

Command: `command1 || command2 || ...`

Example: `todo read book || mark 1 || add food /amount 3.3`

Expected output: Display each command taken in and the execution result of each command.

Example output:
<pre>
Got it! I've added this task:  
    [T][] read book  
Now you have 1 task in the list.

Nice! I've marked this task as done:  
    [T][X] read book

Ca-Ching! Expense incremented. Here's the list of updated expenses:  
Food: $3.30
Transport: $0.00
Grocery: $0.00
Books: $0.00
Clothes: $0.00
Entertainment: $0.00
Other: $0.00
</pre>
