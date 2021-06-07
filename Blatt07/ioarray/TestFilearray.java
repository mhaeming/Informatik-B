package ioarray;

import java.io.IOException;

public class TestFilearray {
    public static void main(String[] args) {
        int[] integers = {1,2,3,4,5,6,7,8,9};

        try (Filearray fa = new Filearray("Test", integers)){
            System.out.println(fa.get(1));
            fa.set(1, -8);
            System.out.println(fa.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
