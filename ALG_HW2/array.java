//Fatih AKGÜNDÜZ    150117032
//Yasin Enes POLAT  150117015

import java.util.*;
import java.io.*;
public class array {

    static int[] array;
    static ArrayList<Integer> distinct = new ArrayList<Integer>();
    static FileWriter writer;



    /**
     * Print given array
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println("");
    }

    public static void main(String[] args) throws IOException {
        writer = new FileWriter("inputs.txt");
        int def = 100;
        int size = 100;
        int[] arrsize = new int[1000];
        int index = 0;
        for(int i = 0; i < 10; i++) {
            size = def * (i + 1);
            for(int j = 0; j < 100; j++) {
                //The line below for random size of arrays. If it is commented, program will create arrays which has size of 100, 200, ..., 1000.
                size = 10 + (int)(Math.random() * 1000);
                arrsize[index++] = size;
                array = new int[size];
                createDistinctArray();
                distinct.clear();
            }  
        }
        writer.close();
        printArr(arrsize);
    }

    static void createDistinctArray () throws IOException {
        for (int i = 0; i < array.length; i++) {
            int n = 1 + (int)(Math.random() * array.length);
            while (isThere(n))
                n = 1 + (int)(Math.random() * array.length);
            array[i] = n;
            distinct.add(n);
            writer.write(Integer.toString(n));
            writer.write(" ");
        }
        writer.write("\n");
    }

    static boolean isThere (int num) {
        return distinct.contains(num);
    }

    static void printArr (int[] arr) {
        for(int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}