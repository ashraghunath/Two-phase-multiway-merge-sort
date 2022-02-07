import java.io.*;
import java.util.Random;
import java.util.Scanner;

//Please create "sortedFiles" directory for storing sorted data.
public class MergeSort {

    static int maxN = 100_000;
    static int fileNumber = 0;
    static final String filename = "input.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operation;
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
                phase1(20);
            }
        } while (operation != 4);
        scanner.close();
    }

    public static void phase1(int memorySize) {
        //int fileNumber=0;
        int[] tempArray = new int[memorySize];
        BufferedReader bufferedReader = null;
        int numberOfElementsInArray = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String number;
            while((number = bufferedReader.readLine()) != null) {
                if(numberOfElementsInArray == memorySize) {
                    applySortingAndSave(tempArray,numberOfElementsInArray);
                    numberOfElementsInArray=0;
                }
                tempArray[numberOfElementsInArray] = Integer.parseInt(number);
                numberOfElementsInArray++;
            }
//            while ((number = bufferedReader.readLine()) != null && numberOfElementsInArray < memorySize) {
//                tempArray[numberOfElementsInArray] = Integer.parseInt(number);
//                numberOfElementsInArray++;
//            }
            applySortingAndSave(tempArray,numberOfElementsInArray);
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

    public static void applySortingAndSave(int[] unSorted, int numberOfElementsInArray) {
        System.out.print("The member of Array (Before Sorted): ");
        for(int member = 0;member<numberOfElementsInArray;member++) {
            System.out.print(unSorted[member]+",");
        }
        System.out.println();
        mergeSort(unSorted,0,numberOfElementsInArray-1);
        System.out.print("The member of Array (After Sorted): ");
        for(int member = 0;member<numberOfElementsInArray;member++) {
            System.out.print(unSorted[member]+",");
        }
        System.out.println();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sortedFiles/Sorted"+fileNumber+".txt"));
            for (int member = 0;member<numberOfElementsInArray;member++) {
                bufferedWriter.write(Integer.toString(unSorted[member]));
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileNumber++;
    }

    public static void displayRandomList() {
        System.out.println("The Data in file is: ");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
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

    public static void mergeSort(int[] unSorted, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(unSorted, start, middle);
            mergeSort(unSorted, middle + 1, end);
            merge(unSorted, start, middle, end);
        }
    }

    public static void merge(int[] unSorted, int start, int middle, int end) {
        int leftArraySize = middle - start + 1;
        int rightArraySize = end - middle;
        int[] leftArray = new int[leftArraySize];
        int[] rightArray = new int[rightArraySize];
        for (int i = 0; i < leftArraySize; i++) {
            leftArray[i] = unSorted[start + i];
        }
        for (int i = 0; i < rightArraySize; i++) {
            rightArray[i] = unSorted[middle + 1 + i];
        }
        int i = 0, j = 0, k = start;
        while (i < leftArraySize && j < rightArraySize) {
            if (leftArray[i] < rightArray[j]) {
                unSorted[k] = leftArray[i];
                i++;
            } else {
                unSorted[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < leftArraySize) {
            unSorted[k] = leftArray[i];
            k++;
            i++;
        }
        while (j < rightArraySize) {
            unSorted[k] = rightArray[j];
            k++;
            j++;
        }
    }

    public static void generateNumbersIntoFile(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
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
            numbers[i] = random.nextInt(101); //Max 100

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

}
