import java.util.Stack;

public class Experiment {
    public static void rearrangeArray1(Comparable sortedArray[]){
        /*
        Reversed sorted arrays are not worst inputs for Bitonic Sort Algorithm.
        This function was created to make the Bitonic Sort algorithm run slower and
        produce results closer to the theoretic results.However,it can still produce lower
        results than the average results.
        */
        int n = sortedArray.length;
        int controller = 1;
        Comparable temporaryArray[] = new Comparable[n];
        int index = 0;
        for(int i = 0,j=n-1;i<j;i++,j--){
            if(controller %2 == 0){
                temporaryArray[index++] = sortedArray[i];
                temporaryArray[index++] = sortedArray[j];
                controller++;
            }
            else{
                temporaryArray[index++] = sortedArray[j];
                temporaryArray[index++] = sortedArray[i];
                controller++;
            }
        }
        for(int k =0;k<n;k++){
            sortedArray[k] = temporaryArray[k];
        }
    }

    public static void rearrangeArray2(Comparable sortedArray[])
    {

        int length = sortedArray.length;
        if(length >= 3){
            int slicer = length/3;
            Comparable temporaryArray[] = new Comparable[length];
            int index = 0;
            for(int i=length-1;i>= length-slicer;i--){
                temporaryArray[index++] = sortedArray[i];
            }
            for(int j= slicer-1;j>=0;j--){
                temporaryArray[index++] = sortedArray[j];
            }
            for(int k=length-slicer-1;k>=slicer;k--){
                temporaryArray[index++] = sortedArray[k];
            }

            for(int t=0;t< sortedArray.length;t++){
                sortedArray[t] = temporaryArray[t];
            }

        }
        /*This function takes a sorted array as an argument and divides it into 3 pieces.
        First peace consists of the smallest elements,second piece consists of medium elements
        and the third piece consists of the largest elements.
        Final array hierarchy will be like :
        [largest elements in descending order - smallest elements in descending order - medium elements in descending order]
        This function will help us to get more clear results while we are trying to measure the worst case time measurement
        of CombSort algorithm.However,results will not be correct completely and less than the average case's results.
        The main purpose of this function is get results closer to the theoretic results.
         */
    }
    public static void reverseList(Comparable list[]) {
        //This function is used for reversing a sorted array to create the worst input for algorithms.
        Stack<Comparable> stack = new Stack<Comparable>();
        for (Comparable c : list) {
            stack.push(c);
        }
        //After popping elements in the stack,the list will be reversed.
        for (int i = 0; i < list.length; i++) {
            list[i] = stack.pop();
        }
    }

    public static Person[] generatePersonObjects(int number) {
        //This function creates random Person objects by using the default constructor of Person Class.
        Person persons[] = new Person[number];
        for (int i = 0; i < number; i++) {
            persons[i] = new Person();
        }
        System.out.println(number + " random person object created successfully.");
        return persons;
    }

    public static void displayMeasurements(int inputSize) {
        Person randomPersons[] = generatePersonObjects(inputSize);
        Person randomCopy1[] = new Person[inputSize]; // Copy of generated input for GnomeSort
        Person randomCopy2[] = new Person[inputSize]; // Copy of generated input for Shaker Sort
        Person randomCopy3[] = new Person[inputSize]; // Copy of generated input for Stooge Sort
        Person randomCopy4[] = new Person[inputSize]; // Copy of generated input for Bitonic Sort
        Person randomCopy5[] = new Person[inputSize]; // One more copy to generate worst inputs.
        System.arraycopy(randomPersons,0,randomCopy1,0,inputSize);
        System.arraycopy(randomPersons,0,randomCopy2,0,inputSize);
        System.arraycopy(randomPersons,0,randomCopy3,0,inputSize);
        System.arraycopy(randomPersons,0,randomCopy4,0,inputSize);
        System.arraycopy(randomPersons,0,randomCopy5,0,inputSize);
        SortingAlgorithm.CombSort.sort(randomCopy5);  // Firstly,we need a sorted array to generate the worst input.I used CombSort.
        reverseList(randomCopy5); // Now,we have an input which have elements in reversed order.Worst input for most sorting algorithms.
        Person worstCopy1[] = new Person[inputSize]; // For Gnome Sort
        Person worstCopy2[] = new Person[inputSize]; // For Shaker Sort
        Person worstCopy3[] = new Person[inputSize]; // For Stooge Sort
        System.arraycopy(randomCopy5,0,worstCopy1,0,inputSize);
        System.arraycopy(randomCopy5,0,worstCopy2,0,inputSize);
        System.arraycopy(randomCopy5,0,worstCopy3,0,inputSize);
        if(inputSize < 8192){
            // Measurements for randomly generated input.
            double sT1 = System.currentTimeMillis();
            SortingAlgorithm.CombSort.sort(randomPersons);
            double eT1 = System.currentTimeMillis();
            double total1 = eT1-sT1;
            double sT2 = System.currentTimeMillis();
            SortingAlgorithm.GnomeSort.sort(randomCopy1);
            double eT2 = System.currentTimeMillis();
            double total2 = eT2-sT2;
            double sT3 = System.currentTimeMillis();
            SortingAlgorithm.ShakerSort.sort(randomCopy2);
            double eT3 = System.currentTimeMillis();
            double total3 = eT3-sT3;
            double sT4 = System.currentTimeMillis();
            SortingAlgorithm.StoogeSort.sort(randomCopy3,0, randomCopy3.length-1);
            double eT4 = System.currentTimeMillis();
            double total4 = eT4-sT4;
            double sT5 = System.currentTimeMillis();
            SortingAlgorithm.BitonicSort.sort(randomCopy4,0, randomCopy4.length, 1);
            double eT5 = System.currentTimeMillis();
            double total5 = eT5-sT5;
            // Measurements for worst input.
            rearrangeArray2(randomPersons); // For Comb Sort
            rearrangeArray1(randomCopy4); // For Bitonic Sort

            double startingTime1 = System.currentTimeMillis();
            SortingAlgorithm.CombSort.sort(randomPersons);
            double endingTime1 = System.currentTimeMillis();
            double totalTime1 = endingTime1-startingTime1;
            double startingTime2 = System.currentTimeMillis();
            SortingAlgorithm.GnomeSort.sort(worstCopy1);
            double endingTime2 = System.currentTimeMillis();
            double totalTime2 = endingTime2-startingTime2;
            double startingTime3 = System.currentTimeMillis();
            SortingAlgorithm.ShakerSort.sort(worstCopy2);
            double endingTime3 = System.currentTimeMillis();
            double totalTime3 = endingTime3-startingTime3;
            double startingTime4 = System.currentTimeMillis();
            SortingAlgorithm.StoogeSort.sort(worstCopy3,0, worstCopy3.length-1);
            double endingTime4 = System.currentTimeMillis();
            double totalTime4 = endingTime4-startingTime4;
            double startingTime5 = System.currentTimeMillis();
            SortingAlgorithm.BitonicSort.sort(randomCopy4,0, randomCopy4.length, 1);
            double endingTime5 = System.currentTimeMillis();
            double totalTime5 = endingTime5-startingTime5;
            System.out.println("Input Size : "+inputSize);
            System.out.println("CombSort    -->  For Random Input : "+total1+" ms    For Worst Input : "+totalTime1+" ms");
            System.out.println("GnomeSort   -->  For Random Input : "+total2+" ms    For Worst Input : "+totalTime2+" ms");
            System.out.println("ShakerSort  -->  For Random Input : "+total3+" ms    For Worst Input : "+totalTime3+" ms");
            System.out.println("StoogeSort  -->  For Random Input : "+total4+" ms    For Worst Input : "+totalTime4+" ms");
            System.out.println("BitonicSort -->  For Random Input : "+total5+" ms    For Worst Input : "+totalTime5+" ms");
        }
        else if(inputSize <= 65536){
            // Measurements for randomly generated input.
            double sT1 = System.currentTimeMillis();
            SortingAlgorithm.CombSort.sort(randomPersons);
            double eT1 = System.currentTimeMillis();
            double total1 = eT1-sT1;
            double sT2 = System.currentTimeMillis();
            SortingAlgorithm.GnomeSort.sort(randomCopy1);
            double eT2 = System.currentTimeMillis();
            double total2 = eT2-sT2;
            double sT3 = System.currentTimeMillis();
            SortingAlgorithm.ShakerSort.sort(randomCopy2);
            double eT3 = System.currentTimeMillis();
            double total3 = eT3-sT3;
            double sT5 = System.currentTimeMillis();
            SortingAlgorithm.BitonicSort.sort(randomCopy4,0, randomCopy4.length, 1);
            double eT5 = System.currentTimeMillis();
            double total5 = eT5-sT5;
            // Measurements for worst input.
            rearrangeArray2(randomPersons); // For Comb Sort
            rearrangeArray1(randomCopy4); // For Bitonic Sort
            double startingTime1 = System.currentTimeMillis();
            SortingAlgorithm.CombSort.sort(randomPersons);
            double endingTime1 = System.currentTimeMillis();
            double totalTime1 = endingTime1-startingTime1;
            double startingTime2 = System.currentTimeMillis();
            SortingAlgorithm.GnomeSort.sort(worstCopy1);
            double endingTime2 = System.currentTimeMillis();
            double totalTime2 = endingTime2-startingTime2;
            double startingTime3 = System.currentTimeMillis();
            SortingAlgorithm.ShakerSort.sort(worstCopy2);
            double endingTime3 = System.currentTimeMillis();
            double totalTime3 = endingTime3-startingTime3;
            double startingTime5 = System.currentTimeMillis();
            SortingAlgorithm.BitonicSort.sort(randomCopy4,0, randomCopy4.length, 1);
            double endingTime5 = System.currentTimeMillis();
            double totalTime5 = endingTime5-startingTime5;
            System.out.println("Input Size : "+inputSize);
            System.out.println("CombSort    -->  For Random Input : "+total1+" ms    For Worst Input : "+totalTime1+" ms");
            System.out.println("GnomeSort   -->  For Random Input : "+total2+" ms    For Worst Input : "+totalTime2+" ms");
            System.out.println("ShakerSort  -->  For Random Input : "+total3+" ms    For Worst Input : "+totalTime3+" ms");
            System.out.println("BitonicSort -->  For Random Input : "+total5+" ms    For Worst Input : "+totalTime5+" ms");

        }
        else if(inputSize <= 8388608){
            // Measurements for randomly generated input.
            double sT1 = System.currentTimeMillis();
            SortingAlgorithm.CombSort.sort(randomPersons);
            double eT1 = System.currentTimeMillis();
            double total1 = eT1-sT1;
            double sT5 = System.currentTimeMillis();
            SortingAlgorithm.BitonicSort.sort(randomCopy4,0, randomCopy4.length, 1);
            double eT5 = System.currentTimeMillis();
            double total5 = eT5-sT5;
            // Measurements for worst input.
            rearrangeArray2(randomPersons); // For Comb Sort
            rearrangeArray1(randomCopy4); // For Bitonic Sort
            double startingTime1 = System.currentTimeMillis();
            SortingAlgorithm.CombSort.sort(randomPersons);
            double endingTime1 = System.currentTimeMillis();
            double totalTime1 = endingTime1-startingTime1;
            double startingTime5 = System.currentTimeMillis();
            SortingAlgorithm.BitonicSort.sort(randomCopy4,0, randomCopy4.length, 1);
            double endingTime5 = System.currentTimeMillis();
            double totalTime5 = endingTime5-startingTime5;
            System.out.println("Input Size : "+inputSize);
            System.out.println("CombSort    -->  For Random Input : "+total1+" ms    For Worst Input : "+totalTime1+" ms");
            System.out.println("BitonicSort -->  For Random Input : "+total5+" ms    For Worst Input : "+totalTime5+" ms");
        }
    }
}
