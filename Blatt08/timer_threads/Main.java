package timer_threads;

import java.io.File;
import java.util.Timer;

/**
 * Uses MyTimerTask to watch size-changes of a given file every second
 *
 * For example, "testfile.txt" or "testdirectory" can be used, they already exist and can be used for testing.
 * But also, any other filename can be used, if it does not exist, then a new file with this name will be created.
 */
public class Main {

    public static void main(String[] args){

        // Add Shutdown hook to display when program is forced to terminate
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("Program forced to terminate.");
            }
        });

        if(args.length != 1){
            throw new IllegalArgumentException("Wrong length of args, must be 1, but is " + args.length);
        } else{
            File file = new File(args[0]);
            Timer timer = new Timer();
            timer.schedule(new MyTimerTask(file), 1000, 1000);
        }
    }


}
