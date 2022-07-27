package utils;

/**
 * @author dasaniket20002 <br>
 * {@link https://github.com/dasaniket20002}
 *
 */
public class Matrix {

	private int row, col;
	private float M[][];

	public Matrix(int r, int c) {
		this.row = r;
		this.col = c;
		this.M = new float[r][c];
	}

	public Matrix(float[][] m) {
		this.M = m;
	}

	public Matrix(Matrix m) {
		this.row = m.row;
		this.col = m.col;
		this.M = m.M;
	}

	/**
	 * fill this matrix with zeroes
	 * 
	 * @return Matrix
	 */
	public Matrix zeros() {
		return fill(0);
	}

	/**
	 * fill matrix with ones
	 * 
	 * @return Matrix
	 */
	public Matrix ones() {
		return fill(1);
	}

	/**
	 * fill matrix with f
	 * 
	 * @param float
	 * @return Matrix
	 */
	public Matrix fill(float f) {
		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getCol(); j++) {
				M[i][j] = f;
			}
		}
		return this;
	}

	/**
	 * fill matrix with random values between -1 and 1
	 * @return Matrix
	 */
	public Matrix randomize() {
		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getCol(); j++) {
				M[i][j] = (float) Utils.map(Math.random(), 0, 1, -1, 1);
			}
		}
		return this;
	}

	/**
	 * Element-wise Addition
	 * @param m1 Matrix
	 * @param m2 Matrix
	 * @return a new Matrix
	 */
	public static Matrix add(Matrix m1, Matrix m2) {
		if (m1.getRow() != m2.getRow() || m1.getCol() != m2.getCol())
			return null;
		Matrix ret = new Matrix(m1.getRow(), m1.getCol());
		for (int i = 0; i < ret.getRow(); i++) {
			for (int j = 0; j < ret.getCol(); j++) {
				ret.M[i][j] = m1.M[i][j] + m2.M[i][j];
			}
		}
		return ret;
	}

	/**
	 * Element-wise Addition
	 * @param m Matrix
	 * @return modified Matrix
	 */
	public Matrix add(Matrix m) {
		if (this.getRow() != m.getRow() || this.getCol() != m.getCol())
			return null;
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.M[i][j] += m.M[i][j];
			}
		}
		return this;
	}

	/**
	 * Scalar Addition <br>
	 * Adds a single value to all the elements
	 * @param m1
	 * @param f
	 * @return a new Matrix
	 */
	public static Matrix add(Matrix m1, float f) {
		Matrix ret = new Matrix(m1.getRow(), m1.getCol());
		for (int i = 0; i < ret.getRow(); i++) {
			for (int j = 0; j < ret.getCol(); j++) {
				ret.M[i][j] = m1.M[i][j] + f;
			}
		}
		return ret;
	}

	/**
	 * Scalar Addition <br>
	 * Adds a single value to all the elements
	 * @param f
	 * @return modified Matrix
	 */
	public Matrix add(float f) {
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.M[i][j] += f;
			}
		}
		return this;
	}

	/**
	 * Element-wise Subtraction
	 * @param m1
	 * @param m2
	 * @return a new Matrix
	 */
	public static Matrix subtract(Matrix m1, Matrix m2) {
		if (m1.getRow() != m2.getRow() || m1.getCol() != m2.getCol())
			return null;
		Matrix ret = new Matrix(m1.getRow(), m1.getCol());
		for (int i = 0; i < ret.getRow(); i++) {
			for (int j = 0; j < ret.getCol(); j++) {
				ret.M[i][j] = m1.M[i][j] - m2.M[i][j];
			}
		}
		return ret;
	}

	/**
	 * Element-wise Subtraction
	 * @param m
	 * @return modified Matrix
	 */
	public Matrix subtract(Matrix m) {
		if (this.getRow() != m.getRow() || this.getCol() != m.getCol())
			return null;
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.M[i][j] -= m.M[i][j];
			}
		}
		return this;
	}

	/**
	 * Scalar Subtraction <br>
	 * Subtracts a single value from all the elements
	 * @param m1
	 * @param f
	 * @return a new Matrix
	 */
	public static Matrix subtract(Matrix m1, float f) {
		Matrix ret = new Matrix(m1.getRow(), m1.getCol());
		for (int i = 0; i < ret.getRow(); i++) {
			for (int j = 0; j < ret.getCol(); j++) {
				ret.M[i][j] = m1.M[i][j] - f;
			}
		}
		return ret;
	}

	/**
	 * Scalar Subtraction <br>
	 * Subtracts a single value from all the elements
	 * @param f
	 * @return modified Matrix
	 */
	public Matrix subtract(float f) {
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.M[i][j] -= f;
			}
		}
		return this;
	}

	/**
	 * Performs dot product of 2 given matrices
	 * @param m1 - First Matrix
	 * @param m2 - Second Matrix
	 * @return a new Matrix
	 */
	public static Matrix dot(Matrix m1, Matrix m2) {
		if (m1.getCol() != m2.getRow())
			return null;

		Matrix ret = new Matrix(m1.getRow(), m2.getCol());
		ret.zeros();
		for (int i = 0; i < m1.getRow(); i++) {
			for (int j = 0; j < m2.getCol(); j++) {
				for (int k = 0; k < m2.getRow(); k++)
					ret.M[i][j] += m1.M[i][k] * m2.M[k][j];
			}
		}

		return ret;
	}

	/**
	 * Performs dot product of current matrix with the provided matrix
	 * @param m - Second Matrix
	 * @return modified Matrix
	 */
	public Matrix dot(Matrix m) {
		Matrix ret = Matrix.dot(this, m);
		this.M = ret.M;
		this.row = ret.row;
		this.col = ret.col;
		return this;
	}

	/**
	 * Scalar Multiplication <br>
	 * Multiplies a single value to all the elements
	 * @param m1
	 * @param f
	 * @return a new Matrix
	 */
	public static Matrix multiply(Matrix m, float f) {
		Matrix ret = new Matrix(m.getRow(), m.getCol());
		for (int i = 0; i < ret.getRow(); i++) {
			for (int j = 0; j < ret.getCol(); j++) {
				ret.M[i][j] = m.M[i][j] * f;
			}
		}
		return ret;
	}

	/**
	 * Scalar Multiplication <br>
	 * Multiplies a single value to all the elements
	 * @param f
	 * @return modified Matrix
	 */
	public Matrix multiply(float f) {
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.M[i][j] *= f;
			}
		}
		return this;
	}

	/**
	 * Hadamard Multiplication <br>
	 * Multiplies all the elements element wise
	 * @param m1 - first matrix
	 * @param m2 - second matrix
	 * @return a new Matrix
	 */
	public static Matrix multiply(Matrix m1, Matrix m2) {
		if (m1.getRow() != m2.getRow() || m1.getCol() != m2.getCol())
			return null;
		Matrix ret = new Matrix(m1.getRow(), m2.getCol());
		for (int i = 0; i < ret.getRow(); i++) {
			for (int j = 0; j < ret.getCol(); j++) {
				ret.M[i][j] = m1.M[i][j] * m2.M[i][j];
			}
		}
		return ret;
	}

	/**
	 * Hadamard Multiplication <br>
	 * Multiplies all the elements element wise
	 * @param m - second matrix
	 * @return modified Matrix
	 */
	public Matrix multiply(Matrix m) {
		if (this.getRow() != m.getRow() || this.getCol() != m.getCol())
			return null;
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.M[i][j] *= m.M[i][j];
			}
		}
		return this;
	}

	/**
	 * gives Transpose of the provided matrix
	 * @param m
	 * @return a new Matrix
	 */
	public static Matrix transpose(Matrix m) {
		Matrix ret = new Matrix(m.getCol(), m.getRow());
		for (int i = 0; i < ret.getRow(); i++) {
			for (int j = 0; j < ret.getCol(); j++) {
				ret.M[i][j] = m.M[j][i];
			}
		}
		return ret;
	}

	/**
	 * gives Transpose of the current matrix
	 * @param m
	 * @return modified Matrix
	 */
	public Matrix transpose() {
		Matrix ret = Matrix.transpose(this);
		this.M = ret.M;
		this.row = ret.row;
		this.col = ret.col;
		return this;
	}

	
	/**
	 * Helper function to convert from matrix to array
	 * @param m
	 * @return
	 */
	public static float[] toArray(Matrix m) {
		float[] arr = new float[m.getRow() * m.getCol()];
		for (int i = 0; i < m.getRow(); i++) {
			for (int j = 0; j < m.getCol(); j++) {
				arr[i * m.getCol() + j] = m.M[i][j];
			}
		}
		return arr;
	}

	
	/**
	 * Helper function to convert the current matrix to array
	 * @return
	 */
	public float[] toArray() {
		return Matrix.toArray(this);
	}

	
	/**
	 * Helper function to convert array to a Matrix(or a vector)
	 * @param arr
	 * @return
	 */
	public static Matrix toMatrix(float[] arr) {
		Matrix m = new Matrix(arr.length, 1);
		for (int i = 0; i < arr.length; i++)
			m.M[i][0] = arr[i];
		return m;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public float[][] getM() {
		return M;
	}

	public void setM(int i, int j, float v) {
		M[i][j] = v;
	}

}
