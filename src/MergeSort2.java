import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MergeSort2 {

    static int maxN = 100_000;
    static final String filename = "input.txt";
    static int memorySize;
    //static int maxFileCouldBeInMemory = 50;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operation;
        System.out.println("Please enter max number of integers in memory");
        memorySize = Integer.parseInt(scanner.nextLine());
        do {
            System.out.println("1.\tCreate a random list of integers");
            System.out.println("2.\tDisplay the random list");
            System.out.println("3.\tRun 2PMMS");
            System.out.println("4.\tExit");
            System.out.print("Please Choose One operation > ");
            operation = Integer.parseInt(scanner.nextLine());
            if (operation == 1)
                generateNumbersIntoFile(scanner);
            else if (operation == 2)
                displayRandomList();
            else if (operation == 3) {
                twoPMMS();
            }
        } while (operation != 4);

        //cleanup files after use
        for (int i =1; i<PhaseOne.fileNumber+1; i++)
        {
            File file = new File("Sorted"+i+".txt");
            if(file.exists())
                file.delete();
        }

        //close scanner
        scanner.close();
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
            numbers[i] = random.nextInt(1000000); //Max 100

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

    public static void displayRandomList() {
        System.out.println("The Data in file is: ");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String number;
            while ((number = bufferedReader.readLine()) != null) {
                System.out.print(number+" ");
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

    public static void twoPMMS()
    {
        PhaseOne.start();
        PhaseTwo.start();
    }

}
