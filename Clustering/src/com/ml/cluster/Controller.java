package com.ml.cluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
	
	private static  HashMap<Integer,ArrayList<Double>> dataset = new  HashMap<Integer,ArrayList<Double>>();
	
	private static int k;

	public static void setDataset(HashMap<Integer,ArrayList<Double>> dataset) {
		
		Controller.dataset = dataset;
		
	}

	public static void setK(int k) {
		
		Controller.k = k;
		
	}

	public static void fitCluster() throws IOException {
			
			System.out.println("\n\n====================== FORMING CLUSTERS =====================\n");
			
			Kmeans km = new Kmeans(k,dataset);
			
			km.initializeCentroids();
			
			km.makeCluster();		
			
			System.out.println("\n========================== RESULT ===========================");
			
			//System.out.println("dataset : "+dataset);
			
			System.out.println();
			
			System.out.println("Final centroids : "+km.getCentroids());
			
			System.out.println();
			
			//System.out.println("Distances: "+km.getDistances());
			
			//System.out.println();
			
			//System.out.println("result : "+km.getClusters());
			for(int i=0; i<km.getClusters().size();i++){
				
				System.out.println("CLUSTER ID "+i+" :\n    Total Samples : "+km.getClusters().get(i).size());
				
				System.out.println("    Samples : "+km.getClusters().get(i));
				
				System.out.println();
			}			
		
	}
	

}

