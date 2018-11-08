public final class Task2 {
    private static int[] primesTo(int k) {
        int[] numbers = new int[k - 1];
        
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 2;
        }
        
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (number != 0) {
                for (int j = i + number; j < numbers.length; j += number) {
                    numbers[j] = 0;
                }
            }
        }
        
        return numbers;
    }
    
    public static void main(String[] args) {
        for (int prime : primesTo(100000)) {
            if (prime != 0) {
                System.out.println(prime);
            }
        }
        
    }
}
