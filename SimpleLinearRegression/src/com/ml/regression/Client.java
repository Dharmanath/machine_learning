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
	 */
	

	
	public static void main(String[] args) {
		
		Data obj = new Data(6);
		
		obj.setX(new double[]{-1,1,2,4,6,7});
		
		obj.setY(new double[]{-1,2,3,3,5,8});
		
		Regression r = new Regression(obj);
		
		Line l = r.findLinearEquation();
		
		System.out.println(l);
		
		//prediction 
		
		System.out.println(l.predict(1));
		
	}


}
