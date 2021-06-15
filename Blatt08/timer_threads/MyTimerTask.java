package timer_threads;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Looks for size-changes of a file or directory
 */
public class MyTimerTask extends TimerTask {

    /**
     * The file or directory to watch
     */
    private File file;

    /**
     * The current size of this file
     */
    private long size;

    /**
     * The size before a change
     */
    private long oldSize;


    /**
     * Constructor, sets this file to the given file.
     * If the file does not exist, creates it.
     * @param file the file to set this file to
     */
    public MyTimerTask(File file){
        this.file = file;
        if (this.file.exists()){
            System.out.println("Exists!");
            size = getFolderSize(file);
            oldSize = size;
        } else {
            try {
                System.out.println("File does not exist");
                file.createNewFile();
                System.out.println("File created");
            } catch (IOException e) {
                System.err.println("File could not be created");
            }
        }

    }


    /**
     * Prints the size of this file if the file-sized has changed
     */
    @Override
    public void run() {
        if(sizeChanged()){
            System.out.println("Size: " + size + "B, size before: " + oldSize + "B");
        }
    }

    /**
     * Gets the size of a file
     * If the file is a directory, it recursively gets the size of all directories and files in the directory
     */
    private static long getFolderSize(File directory){
        long length = 0;

        // Case: It's a directory
        if(directory.listFiles() != null){
            for (File file : directory.listFiles()) {
                    length += getFolderSize(file);
            }
        }

        // Case: it's a single file
        else{
            length = directory.length();
        }

        return length;
    }

    /**
     * Get whether there was a size-change in this file.
     * Also sets this size to the newly calculated size
     * @return True if the size changed, false otherwise
     */
    private boolean sizeChanged(){
        long newSize = MyTimerTask.getFolderSize(this.file);
        this.oldSize = this.size;

        this.size = newSize;

        return newSize != oldSize;
    }
}
