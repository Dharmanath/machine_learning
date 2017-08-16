package com.ml.regression;

public class Client {
	
	/*
	 * 
	 *	x1	x2	x3	y
	 *	2	4	8	34
	 * 	3	9	27	102
	 * 	5	25	125	430
	 * 	4	16	64	228
	 * 	6	36	216	726
	 * 
	 * Equation : y = b0 + (b1 * Math.pow(x,2)) + (b2 * math.pow(x,3))
	 * Find b0 b1 and b2
	 * 
	 * 
	 */

	public static void main(String[] args) {
		
		int no_of_samples = 5;
		
		int no_of_independent_var = 3;
		
		Data d = new Data(no_of_samples,no_of_independent_var);
		
		d.setX(new double[][]{{2,3,5,4,6},{4,9,25,16,36},{8,27,125,64,216}});
		
		d.setY(new double[]{34,102,430,228,726});

		Regression r = new Regression(d);
		
		Line l = r.findLinearEquation();
		
		System.out.println(l);
		
		//prediction 
		
		System.out.println(l.predict(new double[]{1,1,1}));
		
	}

}