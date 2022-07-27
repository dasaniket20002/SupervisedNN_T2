package main;
import java.util.Random;
import java.util.Scanner;

import files.FileHandler;
import nn.NeuralNetwork;
import utils.Matrix;
import utils.Utils;

/**
 * @author dasaniket20002 <br>
 * {@link https://github.com/dasaniket20002}
 *
 */
public class Main {

	public static void main(String[] args)
	{
		float inputs[][] = {{0, 0},
						 	{0, 1},
						 	{1, 0},
						 	{1, 1}};
		float targets[][] = {{0},
							 {1},
							 {1},
							 {0}};
		
		int[] layers = {inputs[0].length, 3, 5, targets[0].length};
		
		int epochs = 1000000;
		float learning_rate = 0.01f;
		
		Random rand;
		NeuralNetwork nn;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU");
		System.out.println("1. Reset Matrices and train");
		System.out.println("2. Train");
		System.out.println("3. Predict");
		
		System.out.print("Choice: ");
		int ch = sc.nextInt();
		
		switch(ch)
		{
		case 1: 
			nn = new NeuralNetwork(true, layers);
			rand = new Random();
			System.out.println("MA: Training...");
			for(int i = 0; i < epochs; i++)
			{
				int r = rand.nextInt(inputs.length);
				nn.backpropagation(inputs[r], targets[r], learning_rate);
			}
			
			FileHandler.writeWeights(nn.getWeights());
			FileHandler.writeBiases(nn.getBiases());
			System.out.println("MA: Trained");
			
			break;
		case 2:
			nn = new NeuralNetwork(layers);
			rand = new Random();
			for(int i = 0; i < epochs; i++)
			{
				int r = rand.nextInt(inputs.length);
				nn.backpropagation(inputs[r], targets[r], learning_rate);
			}
			
			FileHandler.writeWeights(nn.getWeights());
			FileHandler.writeBiases(nn.getBiases());
			System.out.println("MA: Trained");
			
			break;
		case 3:
			nn = new NeuralNetwork(layers);
			System.out.print("Enter input index: ");
			int n = sc.nextInt();
			Matrix output = nn.feed_forward(inputs[n]);
			System.out.println("\nMA: I: ");
			Utils.printArray(inputs[n]);
			System.out.println("\nMA: O: ");
			Utils.printMatrix(output.getM());
			break;
		default:
			System.out.println("Wrong choice");
		}
		
		sc.close();
	}
	
}
