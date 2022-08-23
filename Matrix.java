public class Matrix {

    private int row;
    private int column;
    private double matrixArray[][] ;

    public Matrix(int row, int column){
        this.row = row;
        this.column = column;
        this.matrixArray = new double[row][column];
    }

    public void sumMatrix( Matrix second){
        if(this.row != second.row || this.column != second.column){
            System.out.println("Error : разные размеры матриц");
            return;
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.matrixArray[i][j] += second.matrixArray[i][j];
            }
        }
    }

    public void multiNumber(double num){
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.matrixArray[i][j] *= num;
            }
        }
    }

    public void printMatrix(){
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.matrixArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void fillMatrix(double arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                this.matrixArray[i][j] = arr[i][j];
            }
        }
    }

    private void fillRandomMatrix(int rang){
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.matrixArray[i][j] = (int) (Math.random() * rang);
            }
        }
    }


    public static void main(String[] args) {
        Matrix matrix = new Matrix(3,3);
        matrix.fillMatrix(new double[][]{ {7,2,3}, {4,5,6,}, {10,15,16,} });
        matrix.printMatrix();
        matrix.multiNumber(10);
        System.out.println("-----------------------");
        matrix.printMatrix();

        Matrix matrix1 = new Matrix(3,3);
        matrix1.fillMatrix(new double[][]{ {7,2,3}, {4,5,6,}, {10,15,16,} });

        matrix.sumMatrix(matrix1);
        System.out.println("-----------------------");
        matrix.printMatrix();

        Matrix matrix2 = new Matrix(4,4);
        matrix.sumMatrix(matrix2);
        System.out.println("-----------------------");
        matrix.printMatrix();

        Matrix matrix3 = new Matrix(3,3);
        matrix3.fillRandomMatrix(20);
        System.out.println("-----------------------");
        matrix3.printMatrix();
    }

}
