package com.ml.regression;

public class Line {
	
	
	double slopes[];
	
	int noOfSlopes;
	
	double constant;
	
	public Line(int n){
		
		this.noOfSlopes = n;
		
		this.slopes = new double[n];
	}

	public double[] getSlopes() {
		
		return slopes;
		
	}

	public void setSlopes(double[] slopes) {
		
		this.slopes = slopes;
		
	}

	public double getConstant() {
		
		return constant;
		
	}

	public void setConstant(double constant) {
		
		this.constant = constant;
		
	}
	
	public double predict(double x[]){
		
		double ans = 0;
		
		for(int i =0 ; i<x.length ; i++)
			ans+=(x[i]*this.slopes[i]);
		
		return ans+this.constant;
		
		
	}
	
	@Override
	public String toString(){
		
		String equation="y = ";
		for(int i=0; i<slopes.length; i++){
			equation+="("+slopes[i]+" * x"+i+") + ";
		}
		equation+=this.constant;
		return equation;		
	}

}
