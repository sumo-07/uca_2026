import java.util.Arrays;

public class matrixZeros {
    public static void setZeroes(int[][] matrix) {
        boolean firstRow= false, firstCol= false;

        // setting markers in first row and first col
        for(int i=0; i < matrix.length; i++) {
            for(int j=0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) firstRow= true;
                    if(j == 0) firstCol= true;

                    matrix[i][0]= 0; // row ke first elem ko zero bna do for identification
                    matrix[0][j]= 0; // col ke first elem ko zero bna do for identification
                }
            }
        }

        // replace inner matrix
        for(int i= 1; i < matrix.length; i++) {
            for(int j= 1; j < matrix[0].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // remaining checks
        if(firstRow) {
            for(int j=0; j < matrix[0].length; j++) {
                matrix[0][j]= 0;
            }
        }

        if(firstCol) {
            for(int i=0; i < matrix.length; i++) {
                matrix[i][0]= 0;
            }
        }
    }

    public static void main(String[] args) {
        
        // Example 1
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        setZeroes(matrix1);
        for(int[] arr : matrix1) {
            System.out.println(Arrays.toString(arr));
        }
        
        System.out.println();

        // Example 2
        int[][] matrix2 = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };
        
        setZeroes(matrix2);
        for(int[] arr : matrix2) {
            System.out.println(Arrays.toString(arr));
        }
        
    }

    
}