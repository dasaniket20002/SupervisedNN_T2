package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import utils.Matrix;

/**
 * @author dasaniket20002 <br>
 * {@link https://github.com/dasaniket20002}
 *
 */
public class FileHandler {

	private static String root = "res";
	private static String info_file = "res/Info.txt";
	private static String weights_file = "res/Weights.txt";
	private static String biases_file = "res/Biases.txt";
	
	public static void init()
	{
		File dir = new File(root);
		if(!dir.exists())
		{
			dir.mkdirs();
			System.out.println("FH: Root created at " + dir.getAbsolutePath());
		}
	}
	
	public static int[] readInfo() throws IOException
	{
		File f = new File(info_file);
		FileReader fr = new FileReader(f);
		Scanner sc = new Scanner(fr);
		
		int num = sc.nextInt();
		int layers[] = new int[num];
		
		for(int i = 0; i < num; i++)
		{
			layers[i] = sc.nextInt();
		}
		
		sc.close();
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return layers;
	}
	
	public static Matrix[] readWeights(int[] layers) throws FileNotFoundException
	{
		Matrix weights[] = new Matrix[layers.length - 1];
		File f = new File(weights_file);
		FileReader fr = new FileReader(f);
		Scanner sc = new Scanner(fr);
		
		for(int i = 0; i < layers.length - 1; i++)
		{
			weights[i] = new Matrix(layers[i + 1], layers[i]);
			for(int j = 0; j < weights[i].getRow(); j++)
			{
				for(int k = 0; k < weights[i].getCol(); k++)
				{
					float val = sc.nextFloat();
					weights[i].setM(j, k, val);
				}
			}
		}
		
		sc.close();
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weights;
	}
	
	public static Matrix[] readBiases(int[] layers) throws FileNotFoundException
	{
		Matrix biases[] = new Matrix[layers.length - 1];
		File f = new File(biases_file);
		FileReader fr = new FileReader(f);
		Scanner sc = new Scanner(fr);
		
		for(int i = 0; i < layers.length - 1; i++)
		{
			biases[i] = new Matrix(layers[i + 1], 1);
			for(int j = 0; j < biases[i].getRow(); j++)
			{
				float val = sc.nextFloat();
				biases[i].setM(j, 0, val);
			}
		}
		
		sc.close();
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return biases;
	}
	
	public static void writeInfo(int[] layers) 
	{
		File f = new File(info_file);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f.getAbsolutePath());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		pw.write(layers.length + "\n");
		for(int i = 0; i < layers.length; i++)
		{
			pw.write(layers[i] + "\n");
		}
		
		pw.close();
	}
	
	private static void writeMatrix(PrintWriter pw, float[][] m)
	{
		for(int i = 0; i < m.length; i++)
		{
			for(int j = 0; j < m[0].length; j++)
			{
				pw.write(m[i][j] + "\t");
			}
			pw.write("\n");
		}
		pw.write("\n\n");
	}
	
	public static void writeWeights(Matrix[] weights)
	{
		File f = new File(weights_file);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < weights.length; i++)
		{
			writeMatrix(pw, weights[i].getM());
		}
		System.out.println("FH: Weights written to file");
		
		pw.close();
	}
	
	public static void writeBiases(Matrix[] biases)
	{
		File f = new File(biases_file);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < biases.length; i++)
		{
			writeMatrix(pw, biases[i].getM());
		}
		System.out.println("FH: Biases written to file");
		
		pw.close();
	}
	
}
