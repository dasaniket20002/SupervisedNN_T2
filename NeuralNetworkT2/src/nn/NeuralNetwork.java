
package nn;

import java.io.FileNotFoundException;
import java.io.IOException;

import files.FileHandler;
import utils.Matrix;
import utils.Utils;

/**
 * @author dasaniket20002 <br>
 * {@link https://github.com/dasaniket20002}
 *
 */
public class NeuralNetwork {

	private int layers;
	private int[] nodes_each_layer;

	private Matrix[] weights, biases;

	private Matrix[] Z;
	private Matrix[] activations;

	public NeuralNetwork(boolean forceInit, int... n) {

		this.layers = n.length;
		this.nodes_each_layer = n;
		
		FileHandler.init();
		
		if (dataExists() && !forceInit)
		{
			System.out.println("NN: Data exists, reading data...");
			readData();
		}
		else
		{
			System.out.println("NN: Creating random data...");
			init();
			FileHandler.writeInfo(nodes_each_layer);
		}
	}
	public NeuralNetwork(int... n)
	{
		this(false, n);
	}

	private boolean dataExists() {
		int[] l;
		try {
			l = FileHandler.readInfo();
		} catch (IOException e) {
			System.out.println("NN: Info.txt not found");
			return false;
		}

		if (l.length != layers)
			return false;

		for (int i = 0; i < l.length; i++) {
			if (l[i] != nodes_each_layer[i])
				return false;
		}

		return true;
	}

	private void readData() {
		try {
			weights = FileHandler.readWeights(nodes_each_layer);
			System.out.println("NN: Weights loaded");
			
			biases = FileHandler.readBiases(nodes_each_layer);
			System.out.println("NN: Biases loaded");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		activations = new Matrix[layers];
		Z = new Matrix[layers];
	}

	private void init() {
		weights = new Matrix[layers - 1];
		biases = new Matrix[layers - 1];
		activations = new Matrix[layers];
		Z = new Matrix[layers];

		for (int i = 0; i < layers - 1; i++) {
			weights[i] = new Matrix(nodes_each_layer[i + 1], nodes_each_layer[i]);
			weights[i].randomize();

			biases[i] = new Matrix(nodes_each_layer[i + 1], 1);
			biases[i].randomize();
		}
	}

	public Matrix feed_forward(float[] inp) {
		activations[0] = Matrix.toMatrix(inp);

		for (int i = 1; i < layers; i++) {
			Z[i] = Matrix.add(Matrix.dot(weights[i - 1], activations[i - 1]), biases[i - 1]);
			activations[i] = Utils.sigmoid(Z[i]);
		}

		return activations[layers - 1];
	}

	public void backpropagation(float[] inp, float[] targets, float learning_rate) {
		feed_forward(inp);

		Matrix deltas[] = new Matrix[weights.length];
		Matrix dE_dW[] = new Matrix[weights.length];

		for (int i = layers - 2; i >= 0; i--) {
			if (i == layers - 2) {
				deltas[i] = Matrix.subtract(activations[i + 1], Matrix.toMatrix(targets));
				deltas[i].multiply(Utils.d_sigmoid(activations[i + 1]));
			} else {
				deltas[i] = Matrix.dot(Matrix.transpose(weights[i + 1]), deltas[i + 1]);
				deltas[i].multiply(Utils.d_sigmoid(activations[i + 1]));
			}
			dE_dW[i] = Matrix.dot(deltas[i], Matrix.transpose(activations[i]));

			weights[i].subtract(Matrix.multiply(dE_dW[i], learning_rate));
			biases[i].subtract(Matrix.multiply(deltas[i], learning_rate));
		}
	}

	public void printInfo() {
		for (int i = 0; i < layers - 1; i++) {
			System.out.println("Weight[" + i + "]");
			Utils.printMatrix(weights[i].getM());
			System.out.println("Bias[" + i + "]");
			Utils.printMatrix(biases[i].getM());

			System.out.println();
		}
	}

	public int getLayers() {
		return layers;
	}

	public int[] getNodes_each_layer() {
		return nodes_each_layer;
	}

	public Matrix[] getWeights() {
		return weights;
	}

	public Matrix[] getBiases() {
		return biases;
	}

	public Matrix[] getActivations() {
		return activations;
	}

}
