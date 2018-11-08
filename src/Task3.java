import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    
    static class Matrix {
        int height, width, size;
        
        Matrix(int height, int width) {
            this.height = height;
            this.width = width;
            this.size = width * height;
        }
        
        private int[] field;
        
        Matrix init() {
            field = new int[size];
            return this;
        }
        
        int get(int row, int col) {
            return field[row * width + col];
        }
        void set(int row, int col, int value) {
            field[row * width + col] = value;
        }
        
        Matrix init(String string) {
            int[] newField = Arrays.stream(string.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            
            if (newField.length != size) {
                throw new IllegalArgumentException("matrix input has to contain ${width * height} integers");
            }
            
            field = newField;
            
            return this;
        }
        
        
        
        Matrix input() {
            Scanner scanner = new Scanner(System.in);
            
            return init(scanner.nextLine());
        }
        
        Matrix add(Matrix m) {
            var numAdditions = 0;
            var numMultiplications = 0;
            
            if (m.width != width || m.height != height) {
                throw new IllegalArgumentException("Matrix dimensions don't match");
            }
            
            var newField = new int[size];
            
            for (int i = 0; i < size; i++) {
                numAdditions++;
                newField[i] = field[i] + m.field[i];
                numAdditions++;
            }
    
            System.out.println("additions: " + numAdditions + ", multiplications: " + numMultiplications);
            
            var newMatrix = new Matrix(height, width);
            newMatrix.field = newField;
            return newMatrix;
        }
        
        Matrix mult(Matrix m) {
            if (width != m.height) {
                throw new IllegalArgumentException("Matrix dimensions don't match");
            }
            var numAdditions = 0;
            var numMultiplications = 0;
            
            var newMatrix = new Matrix(height, m.width).init();
            
            for (int col = 0; col < newMatrix.width; col++) {
                for (int row = 0; row < newMatrix.height; row++) {
                    var sum = 0;
                    for (int i = 0; i < width; i++) {
                        numAdditions += 3;
                        numMultiplications += 3;
                        sum += get(row, i) * m.get(i, col);
                        numAdditions++;
                    }
                    
                    newMatrix.set(row, col, sum);
                    
                    numAdditions++;
                }
                numAdditions++;
            }
            
            System.out.println("additions: " + numAdditions + ", multiplications: " + numMultiplications);
            
            return newMatrix;
        }
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
    
            for (int rowStart = 0; rowStart < size; rowStart += width) {
                for (int i = rowStart; i < rowStart + width; i++) {
                    builder
                        .append(field[i])
                        .append(' ');
                }
                builder.append('\n');
            }
            
            return builder.toString();
        }
        
    }
    
    public static void main(String[] args) {
//    var a = new Matrix(2, 3).init("3 2 1 1 0 2");
//    var b = new Matrix(3, 2).init("1 2 0 1 4 0");
        
        var a = new Matrix(100, 100).init();
        var b = new Matrix(100, 100).init();
        
        var last = System.currentTimeMillis();
        Matrix result = a.mult(b);
        
        var millis = System.currentTimeMillis() - last;
        
        System.out.println("took " + millis + " millis to complete calculation");
    }
}
