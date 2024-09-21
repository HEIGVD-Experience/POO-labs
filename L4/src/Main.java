public class Main {
    public static void main(String[] args) {

        int[] tabInt = new int[args.length];

        for(int i = 0 ; i < args.length ; ++i) {
            tabInt[i] = parseInt(args[i]);
        }

        BubbleSort(tabInt);
    }

    private static int parseInt(String str) {
        int val = 0;
        Boolean isNegative = false;
        for(char c : str.toCharArray()) {
            if(c == '-') {
                isNegative = true;
            } else if (c != '+') {
                val *= 10;
                val += (int)c - (char)'0';
            }
        }
        return isNegative ? -val : val;
    }

    private static void BubbleSort(int[] toSort){

        int j;
        int count = 0;

        for(int i = 0 ; i < (toSort.length - count) ; ++i) {
            j = 1;
            while(toSort[i] > toSort[j]) {
                int temp = toSort[i];
                toSort[i] = toSort[j];
                toSort[j] = toSort[i];
                ++j;
            }
        }

    }
}