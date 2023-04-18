# Scope
This is a basic timer application designed for meetings.

It allows users to add subtasks, and then counts down, notifying users when time is up for one task and they should be moving onto the next item on the agenda.

The goal of this application is to keep your meeting (in person or online) on track.

# Overview
## Setup
1. Download the folder and unzpip all files.
2. Right click the **timer.html** file and click *'open with'*.
3. Choose your preferred browser to run the timer in.

## Usage

### Adding a task

Tasks are the building blocks of everything in this application. There are 2 ways to add a task:

**1. Press the *Add Task* button.**

This will add two input boxes to the screen next to each other, one titled **Task name**, which is where the name of the task should go, and the other with the number 0 inside, where the time allocated for the task (in minutes) should go.

![Add task example](doc/addTaskExample.png)

**2. Press the *Choose File* button.**

This will open your file explorer and enable you to open a **.csv** file to input your tasks. The .csv **must be in the format pictured below**, with one column for task names, and another for task times. The header row will not be included in the task import.

![CSV Example](doc/CSV Example.png)

There is no limit (within reason) to the number of tasks that can be imported, as long as the file is a .csv and follows this format.

Once you open the .csv file, the program will automatically place the information from the .csv in the same format as above, and you are free to edit any names or times however you want.

### Running the timer

Once a timer is added, the "Start" button runs the timer from its current point in time. It also acts as a "Pause" button, where you can freeze the timer.

"Reset" sets the timer back to its built time limit in a frozen state to be able to run again. "Skip" will make the timer jump to the end time of a single task, and "+1 min" adds an additional minute to the timer.

"Remove Task" will take out the newest task added to the agenda.

### Imports and Exports

Accepting the data in "csv" files, the program also allows users to take in and out arranged data for the timer. To save an agenda, press "Export Tasks". This will download a csv into your file directory. You can call back to this by selecting the file after pressing "Choose File".
