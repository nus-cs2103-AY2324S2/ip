# Numerator User Guide

Numerator is a task list, todo list, and event list all in one.

## Table of Contents

## About

Numerator is named after for the top half of a vulgar fraction, the number on top of the line, so you can stay on top of
things.

As enumerate across your list, you can feel yourself gradually become whole again.

![Ui.png](Ui.png)

## Features

### Storage

Numerator stores your tasks in a file, so you can access them later. Files are stored in `data/storage.txt`.

### Access via text commands

Type faster, and get things done quicker.

## Usage

Install Java 11 or later, and run `Nuemrator.jar` using the command `java -jar Numerator.jar`. A GUI will appear for you
to use.

## Commands

### List

Format: `list`

Example Usage: `list`

Example Usage Output:

```
Here are the tasks in your list: 
1. [T][ ] buy groceries 
2. [D][ ] submit assignment (by: 2021/09/17 23:59) 
3. [E][ ] project meeting (from: 2021/09/17 14:00 to 2021/09/17 16:00)
```

### Add todos

Todos can be added to the list.

Format: `todo <task>`

Example Usage: `todo buy groceries`

Example Usage Output:

```
Added: [T][ ] buy groceries. 
Now you have 4 tasks in the list.
```

### Adding deadlines

Deadlines can be added to tasks by specifying a date

Format: `deadline <task> /by <date> (<time>)`

- Date format: `yyyy/MM/dd`
- Time format: `HH:mm`
- Time is optional, and defaults to `00:00`

Example Usage: `deadline submit assignment /by 2021/09/17 23:59`

Example Output:

```
Added: [D][ ] submit assignment (by: 2024/02/17 23:59). 
Now you have 5 tasks in the list.
```

### Adding events

Events can be added to tasks by specifying a date and time range

Format: `event <task> /from <date> (<time>) /to <date> (<time>)`

- Date format: `yyyy/MM/dd`
- Time format: `HH:mm`
- Time is optional, and defaults to `00:00`
- Time range is inclusive

Example Usage:`event project meeting /from 2021/09/17 14:00 /to 2021/09/17 16:00`

Example Output:

```
Added: [E][ ] project meeting (from: 2021/09/17 14:00 to 2021/09/17 16:00). 
Now you have 6 tasks in the list.
```

### Marking tasks as done

Tasks can be marked as done

Format: `mark <task number>`

Example Usage: `mark 1`

Output:

```
Nice! I've marked this task as done: [T][X] buy groceries
```

### Unmarking tasks as done

Tasks can be unmarked as done

Format: `unmark <task number>`

Example Usage: `unmark 1`

Example Output:

```
OK! I've marked this task as not done yet: [T][ ] buy groceries
```

### Deleting tasks

Tasks can be deleted from the list

Format: `delete <task number>`

Example Usage: `delete 1`

Example Output:

```
Noted. I've removed this task: 
[T][ ] buy groceries. Now you have 5 tasks in the list.
```

### Finding tasks

Tasks can be found by searching for a keyword. The task description is searched for the keyword.

Format: `find <keyword>`

Example Usage: `find assignment`

Example Output:

```
Here are the matching tasks in your list:
1. [D][ ] submit assignment (by: 2021/09/17 23:59)
```

### Tagging tasks

Tasks can be tagged with a keyword. The tag must be alphanumeric without spaces.

Format: `tag <task number> <tag>`

Example Usage: `tag 1 important`

Example Output:

```
OK, I've tagged this task with important:
[D][ ] submit assignment (by: 2021/09/17 23:59) #important
```

### Untagging tasks

Tags can be removed from tasks

Format: `untag <task number> <tag>`

Example Usage: `untag 1 important`

Example Output:

```
OK, I've removed the tag important from this task:
[D][ ] submit assignment (by: 2021/09/17 23:59)
```

## Cheatsheet

<table>
<thead>
<tr>
<th>Command</th>
<th>Description</th>
<th>Usage</th>
<th>Example Usage</th>
</tr>
</thead>

<tbody>
<tr>
<td><code>todo</code></td>
<td>Adds a todo to the list</td>
<td><code>todo &lt;task&gt; </code></td>
<td><code>todo buy groceries</code></td>
</tr>
<tr>
<td><code>deadline</code></td>
<td>Adds a deadline to the list <br> (default time: <code>00:00</code>)</td>
<td><code>deadline &lt;task&gt; /by &lt;date&gt; &lt;time&gt;</code></td>
<td>
<code>deadline submit assignment /by 2021/09/17 2359</code><br><br>
<code>deadline submit assignment /by 2021/09/17</code>
</td>
</tr>
<tr>
<td><code>event</code></td>
<td>Adds an event to the list</td>
<td><code>event &lt;task&gt; /from &lt;date&gt; &lt;time&gt; /to &lt;date&gt; &lt;time&gt; </code></td>
<td><code>event project meeting /from 2021/09/17 1400 /to 2021/09/17 1600</code></td>
</tr>
<tr>
<td><code>mark</code></td>
<td>Marks a task as done</td>
<td><code>mark &lt;task number&gt;</code></td>
<td><code>mark 1</code></td>
</tr>
<tr>
<td><code>unmark</code></td>
<td>Unmarks a task as done</td>
<td><code>unmark &lt;task number&gt;</code></td>
<td><code>unmark 1</code></td>
</tr>
<tr>
<td><code>delete</code></td>
<td>Deletes a task from the list</td>
<td><code>delete &lt;task number&gt;</code></td>
<td><code>delete 1</code></td>
</tr>
<tr>
<td><code>list</code></td>
<td>Lists all tasks</td>
<td><code>list</code></td>
<td><code>list</code></td>
</tr>
<tr>
<td><code>find</code></td>
<td>Finds tasks which include keyword as task description</td>
<td><code>find &lt;keyword&gt;</code></td>
<td><code>find assignment</code></td>
</tr>
<tr>
<td><code>tag</code></td>
<td>Tags a task with a keyword (must be alphanumeric, no spaces)</td>
<td><code>tag &lt;task number&gt; &lt;tag&gt;</code></td>
<td><code>tag 1 important</code></td>
</tr>
<tr>
<td><code>untag</code></td>
<td>Removes a tag from a task</td>
<td><code>untag &lt;task number&gt; &lt;tag&gt;</code></td>
<td><code>untag 1 important</code></td>
</tr>
</tbody>
</table>
