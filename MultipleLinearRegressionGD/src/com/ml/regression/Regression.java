package com.ml.regression;

public class Regression {
	
	private Data d;
	
	private Line l ;
	
	private double LEARNING_RATE;
	
	private int epocs;
	
	public Regression(Data d,double LEARNING_RATE, int epocs){
		
		this.d = d;
		
		this.LEARNING_RATE = LEARNING_RATE;
		
		this.epocs = epocs;
		
		this.l = new Line(d.getNoOfSlopes());
		
	}

	public Line findLinearEquation() {
		
		
		for(int i=0; i<this.epocs; i++){
			
			for(int j=0; j<this.d.getNoOfSamples(); j++){
				
				double actual = this.l.predict(d.getXT(j));
				
				double target = this.d.getYind(j);
				
				double error = actual - target;
				
				double newConstant = (l.getConstant()) - ( LEARNING_RATE * error);
				
				double newSlope[] = new double[d.getNoOfSlopes()];
				
				for(int m=0; m<newSlope.length; m++){
					
					newSlope[m] = (l.getSlope(m)) - (LEARNING_RATE * error * d.getXind(m, j) );
					
				}
						
				l.setConstant(newConstant);
				
				l.setSlopes(newSlope);
				
			}		
			
		}	
		
		return l;
	}
	

}
