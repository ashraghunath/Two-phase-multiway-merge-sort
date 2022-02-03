import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {

    static int maxN=100_000;


    public static void main(String[] args) {

        final String filename = "numbers.txt";

        generateNumbersIntoFile(filename);
    }

    public static void generateNumbersIntoFile(String filename)
    {
        Scanner scanner = new Scanner(System.in);
        int n = 0;

        while(true) {
            System.out.println("Please enter the count of integers to generate");
            n = Integer.parseInt(scanner.nextLine());

            if(n>=1 && n<=maxN)
                break;
            else
                System.out.println("Please enter a valid number (Max : "+maxN+")");
        }

        int[] numbers = new int[n];
        Random random = new Random();

        for(int i=0; i<n; i++)
            numbers[i]=random.nextInt(101); //Max 100

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
            for(int i=0; i<numbers.length; i++) {
                bufferedWriter.write(Integer.toString(numbers[i]));
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
