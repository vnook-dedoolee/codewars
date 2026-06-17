package kyu_4.next_bigger_number_with_the_same_digits.groovy

class Kata {
    static long nextBigger(long n) {
        String numStr = String.valueOf(n)
        char[] numChars = numStr.toCharArray()

        int pivotIndex = numChars.length - 1
        while (pivotIndex > 0 && numChars[pivotIndex] <= numChars[pivotIndex - 1]) {
            pivotIndex--
        }

        if (pivotIndex == 0) {
            return -1
        }

        int pivot = numChars[pivotIndex - 1]
        int minIndex = pivotIndex

        for (int j = pivotIndex + 1; j < numChars.length; j++) {
            if (numChars[j] > pivot && numChars[j] < numChars[minIndex]) {
                minIndex = j
            }
        }

        swap(numChars, pivotIndex - 1, minIndex)
        Arrays.sort(numChars, pivotIndex, numChars.length)
        return Long.parseLong(new String(numChars))
    }

     static void swap(char[] numChars, int i, int j) {
        char temp = numChars[i];
        numChars[i] = numChars[j];
        numChars[j] = temp;
    }
}
