package com.ml.cluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
	
	public static HashMap<Integer,ArrayList<Double>> dataset = new HashMap<Integer,ArrayList<Double>>();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Controller.setK(2);	
		
		get_data();
		
		System.out.println("\n\n========================== DATASET ==========================\n");
		
		System.out.println(dataset);
		
		Controller.setDataset(dataset);
		
			
		Controller.fitCluster();
		
	}
	

	

	
	private static void get_data() {
		

		ArrayList<Double> temp = new ArrayList<Double>();		
		temp.add(170.0);
		temp.add(10.0);		
		dataset.put(0, temp);
		
		temp = new ArrayList<Double>();		
		temp.add(180.0);
		temp.add(12.0);
		dataset.put(1, temp);
		
		temp = new ArrayList<Double>();		
		temp.add(185.0);
		temp.add(12.0);
		dataset.put(2, temp);
		
		temp = new ArrayList<Double>();		
		temp.add(190.0);
		temp.add(13.0);
		dataset.put(3, temp);
		
		temp = new ArrayList<Double>();		
		temp.add(177.0);
		temp.add(14.0);
		dataset.put(4, temp);
		
		
		temp = new ArrayList<Double>();		
		temp.add(100.0);
		temp.add(84.0);
		dataset.put(5, temp);
		
		
		temp = new ArrayList<Double>();		
		temp.add(101.0);
		temp.add(75.0);
		dataset.put(6, temp);
		
		
		temp = new ArrayList<Double>();		
		temp.add(106.0);
		temp.add(77.0);
		dataset.put(7, temp);
		
	}

}

