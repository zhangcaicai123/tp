package seedu.duke.storage;


import seedu.duke.exception.DukeException;
import seedu.duke.task.*;
import seedu.duke.taskList.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String projectRoot = System.getProperty("user.dir");
    private final String directory = projectRoot + "/data";
    private final String filePath = directory + "/duke.txt";

    public Storage() {
    }

    /**
     * Create directory if the directory does not exist
     *
     * @param directoryName directory path name
     * @return true if the directory exist or have been successfully created
     * false if fail to create the directory
     */
    public boolean createDirectory(String directoryName) {
        File dir = new File(directoryName);
        if (!dir.exists()) {
            return dir.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * Create the data file to store task list
     *
     * @param pathName      the absolute path name of data file
     * @param directoryName the directory path name
     */
    public void createFile(String pathName, String directoryName) {
        boolean mkdirs = createDirectory(directoryName);
        if (mkdirs) {
            File f = new File(pathName);
        }
    }

    /**
     * Load data file to current task list
     *
     * @return loaded task list
     */
    public ArrayList<Task> load() throws DukeException {
        File loadFile = new File(this.filePath);
        ArrayList<Task> loadList = new ArrayList<>();
        if (!loadFile.exists()) {
            createFile(this.filePath, directory);
        } else {
            Scanner file = null;
            try {
                file = new Scanner(loadFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (true) {
                assert file != null;
                if (!file.hasNext()) break;
                String text = file.nextLine();
                Task taskToLoad = parserTask(text);
                loadList.add(taskToLoad);
            }
            file.close();
            System.out.println("List has been loaded successfully.");

        }
        return loadList;
    }

    /**
     * Transfer the line in data file into task to load
     *
     * @param text each line of the data file
     * @return task need to load
     * @throws DukeException if the text in data file cannot recognized as a task
     */
    private Task parserTask(String text) throws DukeException {
        Task taskToLoad;
        String time;
        String modName;
        //split each line into task description, done status and deadline/event time
        String[] Text = text.trim().split(" \\| ");
        String description = Text[2];
        String status = Text[1];
        if (text.startsWith("T")) {
            //Todo
            taskToLoad = new Todo(description);
        } else if (text.startsWith("D")) {
            //Deadline
            taskToLoad = new Deadline(description);
            time = Text[3];
            ((Deadline) taskToLoad).setBy(time);
        } else if (text.startsWith("E")) {
            //Event
            taskToLoad = new Event(description);
            time = Text[3];
            ((Event) taskToLoad).setAt(time);
        } else if (text.startsWith("P")) {
            //Project task
            String material = Text[5];
            modName = Text[3];
            time = Text[4];
            taskToLoad = new ProjectTask(modName, description, time, material);
        } else {
            throw new DukeException();
        }
        if (status.equals("1")) {
            taskToLoad.markAsDone();
        }
        return taskToLoad;
    }

    /**
     * Update done status for the task in file
     *
     * @param index    the index of task in the list that needs to be marked as done
     * @param taskList the list contains all tasks
     * @throws IOException if cannot open, read or write the file
     */
    public void updateDoneToFile(int index, TaskList taskList) throws IOException {
        File newFile = new File(directory + "/data-new.txt");
        File f = new File(this.filePath);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        PrintWriter writer = new PrintWriter(newFile);
        String line;
        int lineNum = 0;
        while ((line = reader.readLine()) != null) {
            if (lineNum == index) {
                //update done status
                writer.println(taskList.get(index).text());
                writer.flush();
                lineNum++;
                continue;
            }
            lineNum++;
            writer.println(line);
            writer.flush();
        }
        reader.close();
        writer.close();
        //replace original data file with new data file
        f.delete();
        newFile.renameTo(f);
    }

    /**
     * Delete the task from data file
     *
     * @param index the index of task in the list that needs to be deleted
     * @throws IOException if cannot open, read or write the file
     */
    public void deleteTaskFromFile(int index) throws IOException {
        File newFile = new File(directory + "/data-new.txt");
        File f = new File(this.filePath);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        PrintWriter writer = new PrintWriter(newFile);
        String line;
        int lineNum = 0;
        while ((line = reader.readLine()) != null) {
            if (lineNum == index) {
                lineNum++;
                continue;
            }
            lineNum++;
            writer.println(line);
            writer.flush();
        }
        reader.close();
        writer.close();
        //replace original data file with new data file
        f.delete();
        newFile.renameTo(f);
    }

    /**
     * Add new line to the end of data file
     *
     * @param textToAppend text needs to be added
     * @throws IOException if cannot open and write the file
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
