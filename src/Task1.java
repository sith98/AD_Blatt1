
public final class Task1 {
    private static int gcd(int a, int b) {
        int r;
        do {
            r = a % b;
            a = b;
            b = r;
        } while (r != 0);
        
        return a;
    }
    
    private static int gcdRec(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    
    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    
    static class Pair {
        int a, b;
        
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    
    public static void main(String[] args) {
        Pair[] pairs = new Pair[]{
            new Pair(3, 5),
            new Pair(5, 3),
            new Pair(15, 21),
            new Pair(21, 15)
        };
        
        for (Pair pair : pairs) {
            int a = pair.a;
            int b = pair.b;
            int result = gcd(a, b);
            int recResult = gcdRec(a, b);
            int lcmResult = lcm(a, b);
            System.out.println("gcd(" + a + ", " + b + ") = " + result);
            System.out.println("gcdRec(" + a + ", " + b + ") = " + recResult);
            System.out.println("lcm(" + a + ", " + b + ") = " + lcmResult);
            System.out.println("--------");
        }
        
    }
}
