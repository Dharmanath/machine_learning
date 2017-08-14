package com.ml.regression;

public class Data {
	
	private double x[];
	
	private double y[];
	
	private int pairs;
	
	public Data(int pairs){
		
		this.x = new double[pairs];
		
		this.y = new double[pairs];
		
		this.pairs = pairs;
	}

	public double[] getX() {
		
		return x;
		
	}

	public void setX(double[] x) {
		
		this.x = x;
		
	}

	public double[] getY() {
		
		return y;
		
	}

	public void setY(double[] y) {
		
		this.y = y;
		
	}

	public int getPairs() {
		
		return pairs;
		
	}
	
	public double getXind(int t){
		
		return this.x[t];
		
	}
	
	public double getYind(int t){
		
		return this.y[t];
		
	}

	
	

}
