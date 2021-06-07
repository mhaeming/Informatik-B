package ioarray;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class arrayfile {

    private RandomAccessFile file;


    public static void main(String[] args) {
        
    }

    public int getLength() throws IOException{
        return (int) file.length();
    }

    /**
     * Explicitly close the file
     * @throws IOException
     */
    public void close() throws IOException{
        this.file.close();
    }
}
