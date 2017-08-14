package com.ml.regression;

public class Regression {
	
	private Data d;
	
	private double LEARNING_RATE;
	
	private Line l;
	
	private int epocs;
	
	/*
	 * Constructor 
	 * parameters : Data object
	 * 
	 */
	public Regression(Data obj, double LEARNING_RATE, Line l, int epocs) {
		
		this.d=obj;
		
		this.LEARNING_RATE = LEARNING_RATE;
		
		this.l = l;
		
		this.epocs = epocs;
		
	}

	public void fitRegression() {
		
		for( int i=0; i<this.epocs; i++){
			
			for(int j=0; j<d.getPairs(); j++){
				
				double actual = l.predict(d.getXind(j));
				
				double target = d.getYind(j);
				
				double error = actual - target ;
				
				//System.out.println(error+"  < "+target+" - "+actual);
				
				double newSlope = (l.getSlope()) - ( LEARNING_RATE * error * d.getXind(j) );
				
				double newConstant = (l.getConstant()) - ( LEARNING_RATE * error);
				
				l.setSlope(newSlope);
				
				l.setConstant(newConstant);
				
			}
			
		}
		
	}

}
