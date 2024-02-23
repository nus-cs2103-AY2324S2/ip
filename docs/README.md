# MyChats User Guide


![img.png](Ui.png)

MyChats is a chatbot that allows you to manage your tasks easily!

## Adding deadlines

MyChats allows you to add deadlines to your task list.

Prompt format: `deadline ~description~ /by yyyy-MM-dd HHmm`

Example prompt: `deadline return book /by 2019-10-15 1800`

The expected outcome is:
```
Got it. I've added this task:
[D][] return book (by: Oct 15 2019 18:00)
Now you have 1 task in the list.
```

## Adding events

MyChats allows you to add events to your task list.

Prompt format: `event ~description~ /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`

Example prompt: `event go to the library /from 2024-02-21 0915 /to 2024-02-21 1330`

The expected outcome is:
```
Got it. I've added this task:
[E][] go to the library (from: Feb 21 2024 09:15 to: Feb 21 2024 13:30)
Now you have 2 tasks in the list.
```

## Deleting tasks

MyChats allows you to delete tasks from your task list.

Prompt format: `delete ~task number~`

Example prompt: `delete 2`

The expected outcome is:
```
Noted. I've removed this task:
[E][] go to the library (from: Feb 21 2024 09:15 to: Feb 21 2024 13:30)
Now you have 1 task in the list.
```

### That's not all! Here's a full list of features that MyChats can do:
<table>
  <tr>
    <th>Feature</th>
    <th>Prompt format</th>
    <th>Example prompt</th>
  </tr>
  <tr>
    <td>List tasks</td>
    <td>list</td>
    <td>list</td>
  </tr>
  <tr>
    <td>Add a todo task to <br> the task list</td>
    <td>todo ~description~</td>
    <td>todo eat dinner</td>
  </tr>
  <tr>
    <td>Add a deadline task to <br> the task list</td>
    <td>deadline ~description~ /by yyyy-MM-dd HHmm</td>
    <td>deadline return book /by 2019-10-15 1800</td>
  </tr>
  <tr>
    <td>Add an event task to <br> the task list</td>
    <td>event ~description~ /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm</td>
    <td>event go to the library /from 2024-02-21 0915 /to 2024-02-21 1330</td>
  </tr>
  <tr>
    <td>Mark task as done</td>
    <td>mark ~task number~</td>
    <td>mark 1</td>
  </tr>
  <tr>
    <td>Mark task as not done</td>
    <td>unmark ~task number~</td>
    <td>unmark 1</td>
  </tr>
  <tr>
    <td>View schedule on a specific date</td>
    <td>view MMM dd yyyy</td>
    <td>view Oct 15 2019</td>
  </tr>
  <tr>
    <td>Find tasks containing a specific word</td>
    <td>find ~word~</td>
    <td>find book</td>
  </tr>
  <tr>
    <td>Undo the most recent command</td>
    <td>undo</td>
    <td>undo</td>
  </tr>
  <tr>
    <td>Exit chat</td>
    <td>bye</td>
    <td>bye</td>
  </tr>
</table>

Enjoy using MyChats!