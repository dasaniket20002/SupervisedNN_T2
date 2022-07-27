package utils;
/**
 * @author dasaniket20002 <br>
 * {@link https://github.com/dasaniket20002}
 *
 */
public class Utils {
	final static double EPSILON = 1e-12;

	/**
	 * ReLU single element
	 * @param float
	 * @return float
	 */
	public static float relu(float x) {
		return Math.max(0, x);
	}

	/**
	 * ReLU for a matrix
	 * @param Matrix
	 * @return Matrix
	 */
	public static Matrix relu(Matrix m) {
		Matrix ret = new Matrix(m.getRow(), m.getCol());
		for (int i = 0; i < m.getRow(); i++) {
			for (int j = 0; j < m.getCol(); j++) {
				float v = relu(m.getM()[i][j]);
				ret.setM(i, j, v);
			}
		}
		return ret;
	}

	/**
	 * derivative of ReLU, single element
	 * @param x
	 * @return dx
	 */
	public static float d_relu(float x) {
		return (x < 0) ? 0 : 1;
	}

	/**
	 * derivative of ReLU, for a Matrix
	 * @param m
	 * @return d(m)
	 */
	public static Matrix d_relu(Matrix m) {
		Matrix ret = new Matrix(m.getRow(), m.getCol());
		for (int i = 0; i < m.getRow(); i++) {
			for (int j = 0; j < m.getCol(); j++) {
				float v = d_relu(m.getM()[i][j]);
				ret.setM(i, j, v);
			}
		}
		return ret;
	}

	/**
	 * Single element Sigmoid function
	 * @param x
	 * @return
	 */
	public static float sigmoid(float x) {
		float val = (float) (1.0f / (1.0f + Math.exp(-1 * x)));
		return val;
	}
	
	/**
	 * Sigmoid for matrix
	 * @param m
	 * @return
	 */
	public static Matrix sigmoid(Matrix m) {
		Matrix ret = new Matrix(m.getRow(), m.getCol());
		for (int i = 0; i < m.getRow(); i++) {
			for (int j = 0; j < m.getCol(); j++) {
				float v = sigmoid(m.getM()[i][j]);
				ret.setM(i, j, v);
			}
		}
		return ret;
	}
	
	
	/**
	 * derivative of Sigmoid function, for single element
	 * @param x
	 * @return
	 */
	public static float d_sigmoid(float x)
	{
		float val = x * (1.0f - x);
		return val;
	}
	
	/**
	 * derivative of Sigmoid function, for a matrix
	 * @param m
	 * @return
	 */
	public static Matrix d_sigmoid(Matrix m) {
		Matrix ret = new Matrix(m.getRow(), m.getCol());
		for (int i = 0; i < m.getRow(); i++) {
			for (int j = 0; j < m.getCol(); j++) {
				float v = d_sigmoid(m.getM()[i][j]);
				ret.setM(i, j, v);
			}
		}
		return ret;
	}

	/**
	 * helper function, prints a provided array
	 * @param arr
	 */
	public static void printArray(float[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}

	/**
	 * helper function, prints a provided matrix
	 * @param arr
	 */
	public static void printMatrix(float[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * @param valueCoord1 - value to convert
	 * @param startCoord1 - starting of input set
	 * @param endCoord1 - ending of input set
	 * @param startCoord2 - starting of output set
	 * @param endCoord2 - ending of output set
	 * @return converted value
	 */
	public static double map(double valueCoord1, double startCoord1, double endCoord1, double startCoord2,
			double endCoord2) {

		if (Math.abs(endCoord1 - startCoord1) < EPSILON) {
			throw new ArithmeticException("/ 0");
		}

		double offset = startCoord2;
		double ratio = (endCoord2 - startCoord2) / (endCoord1 - startCoord1);
		return ratio * (valueCoord1 - startCoord1) + offset;
	}

	/**
	 * Mean Squared Error function
	 * @param a - predicted outputs
	 * @param y - target values
	 * @return error
	 */
	public static float[] MSE(float[] a, float[] y) {
		if (a.length != y.length)
			return null;

		float ret[] = new float[a.length];
		for (int i = 0; i < a.length; i++) {
			float diff = a[i] - y[i];
			ret[i] = diff * diff;
		}
		return ret;
	}

	/**
	 * Mean Squared Error function
	 * @param a - predicted outputs
	 * @param y - target values
	 * @return error
	 */
	public static Matrix MSE(Matrix a, Matrix y) {
		Matrix m = Matrix.subtract(a, y);
		return m.multiply(m);
	}

	/**
	 * derivative of Mean Squared Error function
	 * @param a - predicted outputs
	 * @param y - target values
	 * @return dE
	 */
	public static float[] d_MSE(float[] a, float[] y) {
		if (a.length != y.length)
			return null;

		float ret[] = new float[a.length];
		for (int i = 0; i < a.length; i++) {
			float diff = a[i] - y[i];
			ret[i] = 2 * diff;
		}
		return ret;
	}

	/**
	 * derivative of Mean Squared Error function
	 * @param a - predicted outputs
	 * @param y - target values
	 * @return dE
	 */
	public static Matrix d_MSE(Matrix a, Matrix y) {
		return Matrix.subtract(a, y).multiply(2);
	}

}
