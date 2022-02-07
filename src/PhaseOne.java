import java.io.*;

public class PhaseOne {

    static int fileNumber = 0;

    public static void start() {
        int[] tempNumbers = new int[MergeSort2.memorySize];
        BufferedReader bufferedReader = null;
        int numberOfElementsInArray = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(MergeSort2.filename));
            String number;
            while((number = bufferedReader.readLine()) != null) {
                if(numberOfElementsInArray == MergeSort2.memorySize) {
                    applySortingAndSave(tempNumbers,numberOfElementsInArray);
                    numberOfElementsInArray=0;
                }
                tempNumbers[numberOfElementsInArray] = Integer.parseInt(number);
                numberOfElementsInArray++;
            }

            //remaining elements
            applySortingAndSave(tempNumbers, numberOfElementsInArray);
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

    private static void applySortingAndSave(int[] tempNumbers, int numberOfElementsInArray) {
        System.out.print("The sublist before sorting : ");
        for(int member = 0;member<numberOfElementsInArray;member++) {
            System.out.print(tempNumbers[member]+",");
        }
        System.out.println();
        quicksort(tempNumbers,0,numberOfElementsInArray-1);
        System.out.print("The sublist after sorting : ");
        for(int member = 0;member<numberOfElementsInArray;member++) {
            System.out.print(tempNumbers[member]+",");
        }
        System.out.println();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Sorted"+(fileNumber+1)+".txt"));
            for (int member = 0;member<numberOfElementsInArray;member++) {
                bufferedWriter.write(Integer.toString(tempNumbers[member]));
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileNumber++;
    }

    private static void quicksort(int[] nums, int start, int end) {
        if(start<end)
        {
            int pIndex = partition(nums,start,end);
            quicksort(nums,0,pIndex-1);
            quicksort(nums,pIndex+1,end);
        }
    }

    private static int partition(int[] nums, int start, int end) {

        int pivot = nums[end];
        int pIndex=start;

        for(int i=start; i<end; i++)
        {
            if(nums[i]<=pivot)
            {
                int temp = nums[i];
                nums[i]=nums[pIndex];
                nums[pIndex]=temp;
                pIndex++;
            }
        }

        int temp = nums[pIndex];
        nums[pIndex]=pivot;
        nums[end]=temp;
        return pIndex;
    }


}
