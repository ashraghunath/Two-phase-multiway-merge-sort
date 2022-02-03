import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {

    static int maxN = 100_000;
    static int fileNumber = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String filename = "randomGenerate.txt";
        int operation;
        do {
            System.out.println("1.\tCreate a random list of integers");
            System.out.println("2.\tDisplay the random list");
            System.out.println("3.\tRun 2PMMS");
            System.out.println("4.\tExit");
            System.out.print("Please Choose One operation > ");
            operation = Integer.parseInt(scanner.nextLine());
            if (operation == 1)
                generateNumbersIntoFile(filename, scanner);
            else if (operation == 2)
                displayRandomList(filename);
        } while (operation != 4);
        scanner.close();
    }

    public static void phase1(int memorySize) {
        int[] tempArray = new int[memorySize];

    }

    public static void displayRandomList(String fileName) {
        System.out.println("The Data in file is: ");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String number;
            while ((number = bufferedReader.readLine()) != null) {
                System.out.println(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void generateNumbersIntoFile(String filename, Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        int n;

        while (true) {
            System.out.println("Please enter the count of integers to generate");
            n = Integer.parseInt(scanner.nextLine());

            if (n >= 1 && n <= maxN)
                break;
            else
                System.out.println("Please enter a valid number (Max : " + maxN + ")");
        }

        int[] numbers = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++)
            numbers[i] = random.nextInt(101); //Max 100

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < numbers.length; i++) {
                bufferedWriter.write(Integer.toString(numbers[i]));
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
