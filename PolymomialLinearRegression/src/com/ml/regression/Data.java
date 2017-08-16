package com.ml.regression;

public class Data {
	
	
	// attributes
	private int noOfSamples;
	
	private double X[][];
	
	private double Y[];
	
	private int noOfSlopes;
	
	public Data(int n, int xDimention){
		
		this.noOfSamples = n;
		
		this.noOfSlopes = xDimention;
		
		this.X = new double[xDimention][n];
		
		this.Y = new double[n];
		
	}
	
	
	public double getYind(int a){
		
		return this.Y[a];
		
	}
	
	public double getXind(int a, int b){
		
		return this.X[a][b];
	}

	public int getNoOfSlopes() {
		
		return noOfSlopes;
		
	}



	public void setNoOfSlopes(int noOfSlopes) {
		
		this.noOfSlopes = noOfSlopes;
		
	}



	public int getNoOfSamples() {
		
		return noOfSamples;
		
	}

	public void setNoOfSamples(int noOfSamples) {
		
		this.noOfSamples = noOfSamples;
		
	}

	public double[][] getX() {
		
		return X;
		
	}

	public void setX(double[][] x) {
		
		X = x;
		
	}

	public double[] getY() {
		
		return Y;
		
	}

	public void setY(double[] y) {
		
		Y = y;
	}
	
	

}