package com.ml.regression;

public class Client {
	
	/*
	 * 
	 * Let's take dataset as
	 * 
	 * X	Y
	 * -1	-1
	 *  1	 2
	 *  2	 3
	 *  4	 3
	 *  6	 5
	 *  7	 8
	 *  
	 *  find m and c in y = mx + c
	 *  
	 *  Keep learning rate minimum
	 * 
	 */
	
	private static final double LR = 0.01;
	
	private static int epocs = 200;
	
	public static void main(String[] args) {
		
		Data obj = new Data(6);
		
		obj.setX(new double[]{-1,1,2,4,6,7});
		
		obj.setY(new double[]{-1,2,3,3,5,8});
		
		Line l = new Line();
		
		Regression r = new Regression(obj, LR, l, epocs);
		
		r.fitRegression();
		
		System.out.println(l);
		
		//prediction
		
		System.out.println(l.predict(2));
		
	}


}
