public class Main {

    public static void main(String[] args) {
        int inputSizes[] = {256,512,1024,2048,4096,8192,16384,32768,65536,131072,262144,524288,1048576,2097152,4194304};
        //All sorting algorithms has measured 10 times for each input to get an average result.
        //If you want to see my measurements,please run the program.
        for(int j =0;j<10;j++) {
            for (int i : inputSizes) {
                Experiment.displayMeasurements(i);
            }
        }
    }
}






