package ioarray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 
 * A wrapper class to create a persistent integer array utilizing the file system
 * @author Maximilian Häming
 */
public class Filearray implements AutoCloseable{

    private final int BYTESIZE = 4;

    private RandomAccessFile file;
    private String name;
    private int[] data;

    /**
     * Reads a Filearray by name
     * @param name Name of the Filearray file
     * @throws FileNotFoundException when a file with that name does not exist
     */
    public Filearray(String name) throws IOException {
        this.name = name;

        // Check whether the file really exists
        File f = new File(this.name + ".txt");
        if (!f.exists()) {
            throw new FileNotFoundException();
        }

        // Read existing data
        RandomAccessFile file = new RandomAccessFile(this.name + ".txt", "r");
        this.file = file;
        this.data = new int[getLength()];

        for (int i = 0; i < getLength(); i++) {
            data[i] = file.readInt();
        }
    }


    /**
     * Create a file array with a given name and write the passed data to the file
     * @param name Name of the Filearray file
     * @param data Integer array to be written as Filearray
     */
    public Filearray(String name, int[] data) throws IOException{
        this.name = name;
        this.data = data;

        RandomAccessFile file = new RandomAccessFile(this.name + ".txt", "rw");
        this.file = file;
        for (int i : data) {
            file.writeInt(i);
        }
    }

    /**
     * Get an integer from the Filearray with a given index
     * @param i The ith element of the array to get
     * @return Integer at i
     * @throws IOException
     */
    public int get(int i) throws IOException {
        if (i < 0 || i >= getLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        file.seek(i * BYTESIZE);
        return file.readInt();
    }

    /**
     * Write an Integer at a given position
     * @param i Position at which to write the elem
     * @param elem Integer value to write
     * @throws IOException
     */
    public void set(int i, int elem) throws IOException {
        if (i < 0 || i >= getLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        file.seek(i * BYTESIZE);
        file.writeInt(elem);
    }

    /**
     * 
     * @return the whole integer array
     */
    public int[] data() {
        return data;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    /**
     * 
     * @return Number of elements in the Filearray
     * @throws IOException
     */
    public int getLength() throws IOException{
        // The division by bytesize is important to give us the correct number of elements
        // file.length() returns the total size
        return (int) (this.file.length() / BYTESIZE);
    }

    /**
     * Explicitly close the Filearray
     * @throws IOException
     */
    public void close() throws IOException{
        this.file.close();
    }
}
