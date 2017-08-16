package com.ml.regression;

public class Client {
	
	/*
	 * 
	 *	x1	x2	y
	 *	1	2	9
	 * 	3	6	25
	 * 	5	7	32
	 * 	4	3	18
	 * 	5	6	29
	 * 	3	5	22
	 * 
	 * Equation : y = b0 + (b1 * x1) + (b2 * x2)
	 * Find b0 b1 and b2
	 * 
	 * 
	 */

	public static void main(String[] args) {
		
		int no_of_samples = 6;
		
		int no_of_independent_var =2;
		
		Data d = new Data(no_of_samples,no_of_independent_var);
		
		d.setX(new double[][]{{1,3,5,4,5,3},{2,6,7,3,6,5}});
		
		d.setY(new double[]{9,25,32,18,29,22});

		Regression r = new Regression(d);
		
		Line l = r.findLinearEquation();
		
		System.out.println(l);
		
		//prediction 
		
		System.out.println(l.predict(new double[]{3,4}));
		
	}

}
