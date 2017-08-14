package com.ml.regression;

public class Line {
	
	/*
	 *  y = mx + c
	 *  where
	 *  	m = slope
	 *  	c = constant
	 */
	
	double slope;
	
	double constant;
	
	public double getSlope() {
		
		return slope;
		
	}
	
	public void setSlope(double slope) {
		
		this.slope = slope;
		
	}
	
	public double getConstant() {
		
		return constant;
		
	}
	
	public void setConstant(double constant) {
		
		this.constant = constant;
		
	}
	
	public double predict(double x){
		
		return (this.slope*x)+this.constant;
		
	}
	
	@Override
	public String toString(){
		return "y = ("+this.slope+" x ) + "+this.constant;		
	}

}
