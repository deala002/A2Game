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

## Starting the timer
