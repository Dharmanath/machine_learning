package com.ml.regression;

public class Regression {
	
	Data d;
	
	double SummationOfX;
	
	double SummationOfY;
	
	double SummationOfX2;
	
	double SummationOfY2;
	
	double SummationOfXY;
	
	double slope;
	
	double constant;
	
	private Line l = new Line();

	/*
	 * Constructor 
	 * parameters : Data object
	 * 
	 */
	public Regression(Data obj) {
		
		this.d=obj;
		
	}

	/*
	 * 
	 * findLinearEquation
	 * Parameters: -
	 * 
	 */
	public Line findLinearEquation() {
		
		SummationOfX=findSummationOfX();
		
		SummationOfY=findSummationOfY();
		
		SummationOfX2=findSummationOfX2();
		
		SummationOfY2=findSummationOfY2();
		
		SummationOfXY=findSummationOfXY();
		
		//System.out.println("SummationOfX : "+SummationOfX);
		
		//System.out.println("SummationOfY : "+SummationOfY);
		
		//System.out.println("SummationOfX2 : "+SummationOfX2);
		
		//System.out.println("SummationOfY2 : "+SummationOfY2);
		
		//System.out.println("SummationOfXY : "+SummationOfXY);
		
		slope = findSlope();
		
		constant = findConstant();
		
		l.setSlope(slope);
		
		l.setConstant(constant);
		
		return l;
	}

	/*
	 * computr and returns constant
	 * 
	 */
	private double findConstant() {
		
		return (((SummationOfX*SummationOfXY)-(SummationOfY*SummationOfX2))/((Math.pow(SummationOfX, 2))-(d.getPairs()*SummationOfX2)));
		
	}

	/*
	 * compute and returns slope
	 * 
	 */
	private double findSlope() {
		
		return (((SummationOfX*SummationOfY)-(d.getPairs()*SummationOfXY))/((Math.pow(SummationOfX, 2))-(d.getPairs()*SummationOfX2)));
		
	}

	/*
	 * Finding summation of X * Y
	 */
	private double findSummationOfXY() {
		
		for(int i=0; i<d.getPairs(); i++){
			
			SummationOfXY += d.getXind(i) * d.getYind(i);
			
		}
		
		return SummationOfXY;	
	}

	/*
	 * Finding of Y * Y
	 * 
	 */
	private double findSummationOfY2() {
		
		for(double temp:d.getY()){
			
			SummationOfY2+=Math.pow(temp, 2);
			
		}
		
		return SummationOfY2;		
	}

	/*
	 * Finding summation of X * X
	 */
	private double findSummationOfX2() {
		
		for(double temp:d.getX()){
			
			SummationOfX2+=Math.pow(temp, 2);
			
		}
		
		return SummationOfX2;
		
	}

	/*
	 * Finding summation of Y
	 */
	private double findSummationOfY() {
		
		for(double temp:d.getY()){
			
			SummationOfY+=temp;
			
		}
		
		return SummationOfY;		
		
	}

	/*
	 * Finding Summation of X
	 */
	private double findSummationOfX() {
		
		for(double temp:d.getX()){
			
			SummationOfX+=temp;
			
		}
		
		return SummationOfX;
		
	}

}
