public class SortingAlgorithm {

    public static void swap(Comparable[] list,int index1,int index2)
    {   /*This method belongs to our outer class called SortingAlgorithm.
          Therefore,it can be used by any inner class in this file.
        */
        //This method swaps the values at the specified indexes.
        Comparable object1 = list[index1];
        Comparable object2 = list[index2];
        Comparable temp = object1;
        list[index1] = object2;
        list[index2] = temp;
    }
    public static boolean isEqual(Comparable object1,Comparable object2){
         /*This method compares 2 comparable objects and return true
         if first object's attribute is equal to the second object's.
         It can be used by all inner classes and sorting functions.*/
        return object1.compareTo(object2) == 0;
    }
    public static boolean isLess(Comparable object1,Comparable object2){
        /*This method compares 2 comparable objects and return true
         if first object's attribute is less than the second object's.
         It can be used by all inner classes and sorting functions.*/
        return object1.compareTo(object2) < 0;
    }
    public static boolean isGreater(Comparable object1,Comparable object2){
        /*This method compares 2 comparable objects and return true
         if first object's attribute is greater than the second object's.
         It can be used by all inner classes and sorting functions.*/
        return object1.compareTo(object2) > 0;
    }
    public static class CombSort {
        private static final double shrink = 1.3d; //Shrink factor is a constant and it is produced by empirical analysis.
        public static void sort(Comparable[] input)
        {
            int gapValue = input.length;
            boolean sorted = false;
            while(!sorted)
            {
                gapValue = (int)(Math.floor(gapValue/shrink));
                if(gapValue <= 1){
                    gapValue = 1;
                    sorted = true;
                }
                int i=0;
                while((i+gapValue) < input.length){
                    if(isGreater(input[i],input[i+gapValue])){
                        swap(input,i,i+gapValue);
                        sorted = false;
                    }
                    i++;
                }
            }
            /* Lines between 47-49 are the most important part while analyzing the CombSort algorithm.All values in the input list
            are compared but function swap is called only when the element which is at the index i and the element which is at index i+gap.
            If input array is already sorted ,we don't need to swap any elements.This is the best case scenario.
            Therefore,the time required for the Best Case scenario is proportional to O(nlog(n)).
            The average and best case runtime of this algorithm is proportional to O(n^2).Proving this scenario by mathematical model
            is not possible at this level but is has been proved by a method based on Kolmogorov Complexity.
            In our experiment class,we will generate random Person objects and try to sort them with CombSort algorithm.However,it
            is not possible to measure the time requirement for the worst case scenario because the required input for this scenario
            is not clear.We will try to cause as much swaps as we can.
            */
        }
    }
    public static class GnomeSort
    {
        public static void sort(Comparable input[]){
            int position = 0;
            while(position < input.length){
                if(position == 0){
                    position++;
                }
                else if(isGreater(input[position],input[position-1]) || isEqual(input[position],input[position-1])){
                    position++;
                }
                else{
                    swap(input,position-1,position);
                    position--;
                }
            }
            /*If our input array is already sorted or nearly sorted,Gnome sort is an efficient algorithm because the
             else if block will be executed and the array accesses which is proportional to N will be the proxy for
             the time requirement.Therefore,Best Case scenario of GnomeSort Algorithm is O(N) where N is the size of our input.
             However,if our input array is not sorted,the variable position will be decremented too and it means that algorithm
             will have more array accesses which is proportional to N^2 to swap and compare.So,time complexity of Worst Case
             scenario is O(N^2).
             Average Case time complexity is O(N^2) and to prove that,this algorithm must be executed with random inputs.
             It will be proved after our measurements.
             */
        }
    }
    public static class ShakerSort{
        public static void sort(Comparable input[]){
            boolean swapped;
            int startingIndex = 0;
            int endingIndex = input.length;
            do{
                swapped = false;
                for(int i=startingIndex;i< endingIndex-1;i++){
                    if(isGreater(input[i],input[i+1])){
                        swap(input,i,i+1);
                        swapped = true;
                    }
                }
                if(!swapped){
                    break;
                }
                endingIndex--;
                swapped = false;
                for(int j = input.length-2;j>=0;j--){
                    if(isGreater(input[j],input[j+1])){
                        swap(input,j,j+1);
                        swapped = true;
                    }
                }
                startingIndex++;
            }while(swapped);

            /*If the input array is sorted or nearly sorted if blocks in the for loops will never be executed and
            the array accesses to control conditions will be the proxy for running time.
            Total array accesses in this algorithm = c1.(4N-2)
            Therefore,Base Case complexity of shaker sort is O(N)
            If the input array is not sorted and the elements are in descending order,if blocks will be executed and
            swap operations will be carried out.
            Therefore,Worst Case complexity of shaker sort algorithm is O(N^2)
            Average Case Complexity is O(N^2) too but it will be proved after our measurements.*/


        }

    }
    public static class StoogeSort{

        public static void sort(Comparable array[],int i,int j){

            if(i>=j){
                return;
            }
            //If leftmost element is greater than rightmost element.
            if(isGreater(array[i],array[j]))
            {
                swap(array,i,j);
            }
            if((j-i+1) > 2){
                int t = (int)(Math.floor((j-i+1)/3)); //We will divide the array into 3 pieces.
                sort(array,i,j-t);
                sort(array,i+t,j);
                sort(array,i,j-t);
            }
        }
        /*Assume that total time requirement for this algorithm = T(N)
         From the beyond algorithm,we get a recurrence relation which is T(N) = 3.T(2N/3) + ?.T(1)
         According to our base case,at lowest level(k) our problem = (2/3)^k.N = 1
         From the beyond equation k= log1.5(N)
         According to the line 146,the number of calls until the lowest level is 3^k
         Therefore,Best Case and Average Case complexity of StoogeSort = O(N^2,7095...) = O(N^2,7)
         Average Case complexity is also O(N^2,7) but we will compare the results after measurements.*/
    }
    public static class BitonicSort
    {
        public static void compareAndSwap(Comparable input[],int i,int j,int dir){
            if((dir == 1 && isGreater(input[i],input[j])) || (dir==0 && isLess(input[i],input[j]))){
                swap(input,i,j);
            }
        }

        public static void bitonicMerge(Comparable input[],int low,int cnt,int dir){
            if(cnt > 1){
                int k = cnt/2;
                for(int i=low;i<low+k;i++){
                    compareAndSwap(input,i,i+k,dir);
                }
                bitonicMerge(input,low,k,dir);
                bitonicMerge(input,low+k,k,dir);
            }

        }

        public static void sort(Comparable input[],int low,int cnt,int dir){
            if(cnt > 1){
                int k = cnt/2;
                sort(input,low,k,1);
                sort(input,low+k,k,0);
                bitonicMerge(input,low,cnt,dir);
            }
        }
        /*Assume that time requirement of sort procedure is S(N) and merge procedure is M(N).
          According to the recursive calls in the sort function S(N) = 2.S(N/2) + M(N)
          M(N) = 2 M(N/2) + N/2
          The recurrence relations reveals that the Worst Case and Best Case Complexity of Bitonic Sort = O(N log^2.N)
          Average Case Complexity is also O(N log^2.N) but it will be proved after measurements.
        */
    }
}


