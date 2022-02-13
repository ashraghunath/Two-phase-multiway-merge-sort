import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhaseTwo {
    static List<BufferedReader> listOfFiles = new ArrayList<>();
    static List<Integer> firstElementsOfFiles = new ArrayList<>();
    static int numberOfPasses=0;
    static int lastFileNumberIndex = 0;
    static int numberOfFileFromFirstPass = Math.min(PhaseOne.fileNumber, MergeSort2.memorySize);

    public static void start() {
        System.out.println("Phase 2 Started");
        try {
            readFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readFiles() throws IOException {
        boolean checkIfMergeOrNot = true;
        while (checkIfMergeOrNot) {
            for(int i=0;i<numberOfFileFromFirstPass;i++) {
                listOfFiles.add(new BufferedReader(new FileReader("sortedFiles/Sorted"+(i+lastFileNumberIndex+1)+
                        ".txt")));
                firstElementsOfFiles.add(Integer.parseInt(listOfFiles.get(i).readLine()));
            }
            int minNumberIndex = -1;
            int minNumber = Integer.MAX_VALUE;
            boolean elementsAvailable = true;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sortedFiles/Sorted"+(PhaseOne.fileNumber+1)+".txt"));
            while (elementsAvailable) {
                for(int i=0;i<firstElementsOfFiles.size();i++) {
                    if(minNumber > firstElementsOfFiles.get(i)) {
                        minNumber = firstElementsOfFiles.get(i);
                        minNumberIndex = i;
                    }
                }
                bufferedWriter.write(Integer.toString(minNumber));
                bufferedWriter.write("\n");
//                System.out.println(minNumberIndex + " " + minNumber);
                String newValue = listOfFiles.get(minNumberIndex).readLine();
                if(newValue == null) {
                    BufferedReader removeElement = listOfFiles.remove(minNumberIndex);
                    removeElement.close();
                    firstElementsOfFiles.remove(minNumberIndex);
                } else {
                    firstElementsOfFiles.set(minNumberIndex,Integer.parseInt(newValue));
                }
                if (listOfFiles.size() == 0) {
                    elementsAvailable = false;
                }
                minNumber = Integer.MAX_VALUE;
            }
            bufferedWriter.close();
            PhaseOne.fileNumber++;
            numberOfPasses++;
            lastFileNumberIndex = numberOfPasses * MergeSort2.memorySize;
            numberOfFileFromFirstPass = Math.min(MergeSort2.memorySize,
                    (PhaseOne.fileNumber - lastFileNumberIndex));
            checkIfMergeOrNot = numberOfFileFromFirstPass > 0;
            firstElementsOfFiles.clear();
            listOfFiles.clear();
        }
        System.out.println("Passes: "+ numberOfPasses);
    }
}
