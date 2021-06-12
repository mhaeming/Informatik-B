package io;

import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 * Reads the Hashmap from a file in the beginning and writes it back to the file afterwards.
 * Creates a new Hashmap and new file when called the very first time / the file is removed.
 *
 * @author Per Starke, based on work by Mathias Menninghaus
 */
public class Fibonacci {

   private static HashMap<Integer, Long> fibonacciHash = new HashMap<Integer, Long>();

   /**
    * If .ser file exist read HashMap from the file, otherwise fill HashMap with initial key-value-pairs
    */
   static {
      File f = new File("fibonacciHash.ser");
      if(f.exists()){
         getHashmapFromFile();
         System.out.println("Got HashMap from file.");
      } else{
         System.out.println("New HashMap created and filled with initial key-value-pairs.");
         fibonacciHash.put(0, 0L);
         fibonacciHash.put(1, 1L);
      }
   }

   /**
    * Calculates the fibonacci value of n.
    *
    * @param n a natural number >= 0 to calculate the fibonacci value of
    * @return fibonacci value of n
    */
   public static long fibonacci(int n) {
      if (n < 0) {
         throw new IllegalArgumentException("n = " + n);
      }
      return getFibonacci(n);
   }

   private static long getFibonacci(int n) {
      if (fibonacciHash.containsKey(n)) {
         return fibonacciHash.get(n);
      } else {
         long nMinus1 = getFibonacci(n - 1);
         long nMinus2 = getFibonacci(n - 2);
         long fibonacci = nMinus1 + nMinus2;

         fibonacciHash.put(n, fibonacci);
         return fibonacci;
      }
   }

   private static void printUsage() {
      System.out.println("java calc/Fibonacci n");
      System.out.println("n must be a natural number >= 0");
   }



   /**
    * Gets the Hashmap from the file and stores it in this fibonacciHash
    */
   private static void getHashmapFromFile(){
      try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fibonacciHash.ser"))){
         fibonacciHash = (HashMap<Integer, Long>) ois.readObject();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
    * Writes this fibonacciHash into the file
    */
   private static void writeHashmapToFile(){
      try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fibonacciHash.ser"))){
         oos.writeObject(fibonacciHash);
         oos.flush();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


   /**
    * Main method, calls the other methods and leads through the process
    * @param args specifies which Fibonacci-Number we want to know - args must be of length 1 here.
    */
   public static void main(String[] args) {
      if (args.length != 1) {
         printUsage();
      } else {
         try {
            System.out.println("Fibonacci Number " + args[0] + ": " + fibonacci(Integer.parseInt(args[0])));
            writeHashmapToFile();
            System.out.println("Hashmap written to file.");

            File f = new File("fibonacciHash.ser");
            System.out.println("File length: " + f.length());
         } catch (IllegalArgumentException ex) {
            printUsage();
         }
      }
   }

}
