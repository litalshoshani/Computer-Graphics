package Math;

/**
 *  Lital Shoshani
 *  Uriah Ahrak
 * The class represents Matrix
 */
public class Matrix {
    // Two dimensional double matrix
    protected double[][] matrix;
    // Number of rows
    protected int rows;
    // Number of cols
    protected int cols;

    /**
     * The constructor builds the Matrix object.
     * @param matrix
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;

        calcDimensions();
    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * Getter for rows
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Getter for cols
     * @return number of cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * Getter for matrix
     * @return the matrix
     */
    public double[][] getMatrix() {
        return matrix;
    }

    /********************************
    *            SETTERS            *
    ********************************/

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
        calcDimensions();
    }


    /********************************
    *        BUSINESS LOGIC         *
    ********************************/

    /**
     * The method calcs the dimensions
     * of the current matrix
     */
    private void calcDimensions() {
        // Find rows and cols amount
        rows = matrix.length;
        cols = rows > 0 ? matrix[0].length : 0;
    }

    /**
     * A convenient method - converts
     * the matrix to a vector.
     * @return
     */
    public Vector toVector() {
        return new Vector(
            matrix[0][0],
            matrix[1][0],
            matrix[2][0],
            matrix[3][0]
        );
    }

    /**
     * The method gets a matrix, and multiplies
     * the current matrix with the accepted one
     * @param matrix a matrix
     * @return multiplication between matrices
     */
    public Matrix mult(Matrix matrix) {
        // Check if multiplication is legal
        if (cols != matrix.getRows()) {
            return null;
        }

        // Get the matrix from the other one
        double[][] otherMatrix = matrix.getMatrix();

        // Get the rows and cols
        int multipliedRows = rows;
        int multipliedCols = matrix.getCols();

        // Init a new matrix for multiplication
        double[][] multipliedMatrixArray = new double[multipliedRows][multipliedCols];

        // For each value in the new matrix -> multiply row by col and sum them
        for (int row = 0; row < multipliedRows; row++) {
            for (int col = 0; col < multipliedCols; col++) {
                for (int index = 0; index < cols; index++) {
                    multipliedMatrixArray[row][col] += 
                        (this.matrix[row][index] * otherMatrix[index][col]);
                }
            }
        }

        // Return the multiplied matrix as an object
        return new Matrix(multipliedMatrixArray);
    }

    /**
     * The method generates a new Identity
     * matrix
     * @param size size of the matrix
     * @return
     */
    public static Matrix I(int size) {
        double[][] matrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }

        return new Matrix(matrix);
    }
}
