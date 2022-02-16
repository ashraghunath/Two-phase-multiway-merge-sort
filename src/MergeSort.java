import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {

    static int maxN = 100_000;
    static final String filename = "input.txt";
    static int memorySize;
    //static int maxFileCouldBeInMemory = 50;

    public static void main(String[] args) throws IOException {

        System.gc();
        Runtime runtime = Runtime.getRuntime();
        System.out.println("System Available Memory: " + (float) (runtime.totalMemory() / 1000000) + " MB");

        memorySize = (int)(runtime.totalMemory() / 1000000) *500;

        Scanner scanner = new Scanner(System.in);
        int operation;
//        System.out.println("Please enter max number of integers in memory");
//        memorySize = Integer.parseInt(scanner.nextLine());
//        memorySize=1000;
        do {
            System.out.println("\n1.\tCreate a random list of integers");
            System.out.println("2.\tDisplay the random list");
            System.out.println("3.\tRun 2PMMS");
            System.out.println("4.\tExit");
            System.out.println("5.\tCleanup");

            System.out.print("Please Choose One operation > ");
            operation = Integer.parseInt(scanner.nextLine());
            if (operation == 1)
                generateNumbersIntoFile(scanner);
            else if (operation == 2) {
                System.out.println("The Data in file is: ");
                displayRandomList(filename);
            }
            else if (operation == 3) {
                twoPMMS();
            }
            else if(operation==5)
            {
                cleanup();
            }
        } while (operation != 4);


        //close scanner
        scanner.close();
    }

    public static void cleanup()
    {
        //        cleanup files after use

        File dir = new File("sortedFiles");
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files)
                if (file.exists())
                    file.delete();
            dir.delete();
        }
    }

    public static void generateNumbersIntoFile(Scanner scanner) {
        int n;
        while (true) {
            System.out.print("Please enter the count of integers to generate > ");
            n = Integer.parseInt(scanner.nextLine());

            if (n >= 1 && n <= maxN)
                break;
            else
                System.out.println("Please enter a valid number (Max : " + maxN + ")");
        }

        int[] numbers = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++)
            numbers[i] = random.nextInt(1000000);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
            for (int number : numbers) {
                bufferedWriter.write(Integer.toString(number));
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayRandomList(String filename) {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String number;
            while ((number = bufferedReader.readLine()) != null) {
                System.out.print(number + " ");
            }
            System.out.println();
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

    public static void twoPMMS() throws IOException {

        cleanup();
        PhaseOne.fileNumber=0;
        PhaseOne.subListCount=0;
        //create directory if it doesnt exist
        File theDir = new File("sortedFiles");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        //start two phase
        PhaseOne.start();
        PhaseTwo.start();

        System.out.println("Final sorted list is :");
        displayRandomList("sortedFiles/"+"Sorted"+PhaseOne.fileNumber+".txt");

    }

}
