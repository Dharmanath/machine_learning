package com.ml.cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Kmeans {
	
	private int k;
	
	private HashMap<Integer,ArrayList<Double>> dataset = new HashMap<Integer,ArrayList<Double>>();
	
	private HashMap<Integer,ArrayList<Double>> centroids = new HashMap<Integer,ArrayList<Double>>();
	
	private HashMap<Integer,HashMap<Integer,Double>> distances = new HashMap<Integer,HashMap<Integer,Double>>();
		
	private HashMap<Integer,ArrayList<Integer>> clusters= new HashMap<Integer,ArrayList<Integer>>();
	
	private boolean flag;
	
	
	public HashMap<Integer,ArrayList<Double>> getCentroids() {
		return centroids;
	}



	public HashMap<Integer,HashMap<Integer,Double>> getDistances() {
		return distances;
	}



	public HashMap<Integer, ArrayList<Integer>> getClusters() {
		return clusters;
	}



	/*
	 * constructor takes number of clusters and data set.
	 */
	Kmeans(int k, HashMap<Integer,ArrayList<Double>> dataset){
		
		this.k = k;
		
		this.dataset=dataset;
		
		//System.out.println("Dataset : "+this.dataset);
		
	}

	
	/*
	 * method to initialize centroid
	 * with k values taken from data set.
	 * 
	 */
	public void initializeCentroids() {
		
		HashMap<Integer,ArrayList<Double>> ds = new HashMap<Integer,ArrayList<Double>>(this.dataset);
		
		ArrayList<Integer> no = new ArrayList<Integer>();
		
		int count=0;
		
		while(count<this.k){
			
			int n = (int) Math.round(Math.random()*(this.dataset.size()-1));
			
			if(!no.contains(n)){
				no.add(n);
				count++;
			}
			
		}
		
		for(int i=0; i<no.size();i++){
			this.centroids.put(i, this.dataset.get(no.get(i)));
		}
			
			
		System.out.println("Centroids which are selected are "+no);
		
		System.out.println("initial centroids : "+this.centroids);
		
		
	}


	public void makeCluster() {
		
		System.out.println("\n===================== LERNING STARTED =======================\n");
		
		int count = 0;
		
		while(true){
			
			System.out.println("Iteration : "+(++count));
			
			//System.out.println("=======================================================");
			
			distances= new HashMap<Integer,HashMap<Integer,Double>>();
			
			this.findDistances();
			
			//System.out.println("distances: "+this.distances);
			
			clusters= new HashMap<Integer,ArrayList<Integer>>();
			
			this.findClusterInvolvment();
			
			//System.out.println("Clusters are: "+this.clusters); 
			
			HashMap<Integer,ArrayList<Double>> updatedCentroids = this.findUpdatedCentroids();
			
			//System.out.println("updatedCentroids: "+updatedCentroids);
			
			//System.out.println("old_centroids: "+this.centroids);
						
			this.flag = this.checkCentroidEquality(updatedCentroids);
			
			//System.out.println("Clusters are: "+this.clusters); 
			
			
			if(this.flag) break;
			
			else this.centroids = updatedCentroids;
			
			//System.out.println("distances: "+this.distances);
			
			//System.out.println("Clusters are: "+this.clusters); 
			
			//System.out.println("old_centroids: "+this.centroids);
			
			
		}
		
	}


	private boolean checkCentroidEquality(HashMap<Integer,ArrayList<Double>> updatedCentroids) {
		
		boolean f = true;
		
		for(int i=0 ; i<this.centroids.size();i++){
			
			for(int j=0; j<this.centroids.get(i).size();j++){
				
				if((double)this.centroids.get(i).get(j)!=(double)updatedCentroids.get(i).get(j)){
					
					f = false;
					
					break;
				}
				
				if(f==false) break;
			}
			
		}
		return f;
	}


	private HashMap<Integer,ArrayList<Double>> findUpdatedCentroids() {
		
		HashMap<Integer,ArrayList<Double>> c = new HashMap<Integer,ArrayList<Double>>();
		
		//System.out.println("clu : "+this.clusters);
		
		for(int i=0; i<this.clusters.size();i++){
			
			HashMap<Integer,Double> sumb = new HashMap<Integer,Double>();
			
			for(int j=0; j<this.clusters.get(i).size();j++){
				
				ArrayList<Double> temp = dataset.get(this.clusters.get(i).get(j));
				
				ArrayList<Double> list = c.get(i);
				
				if(list==null)
					c.put(i, new ArrayList<Double>());
				
				ArrayList<Double> t = c.get(i);
				
				if(t.size()==0)
					t.addAll(temp);
				else{
					for(int in=0; in<t.size();in++){
						
						double d = t.get(in);
						
						d = d+temp.get(in);
						
						t.remove(in);
						
						t.add(in,d);
						
					}
				}
				
			}
				
			}
		
		//System.out.println("c= "+c);
		
		for(int s=0 ; s<c.size(); s++){
			
			for(int m=0; m<c.get(s).size();m++){
				
				double d = c.get(s).get(m);
				
				d/=this.clusters.get(s).size();
				
				d=(int)d;
				
				c.get(s).remove(m);
				
				c.get(s).add(m,d);
				
			}
			
		}
		
		return c;
		
	}


	private void findClusterInvolvment() {
		
		for(int i=0; i<this.dataset.size(); i++ ){
			
			int small = 0;
						
			for(int j=0; j<this.k; j++){
				
				//System.out.println(this.distances.get(small).get(i)+" compared with "+this.distances.get(j).get(i));
				
				if(this.distances.get(i).get(small)>this.distances.get(i).get(j))
					
					small = j;
				
			}
			
			//System.out.println(" small: "+small);
			
			if(this.clusters.get(small)==null){
				
				this.clusters.put(small, new ArrayList<Integer>());
				
			}
				
			ArrayList<Integer> temp = this.clusters.get(small);
				
			temp.add(i);
		
		}
		
	}


	private void findDistances() {
		
		for(int i=0; i<this.dataset.size(); i++){
			
			for(int j=0; j<this.centroids.size(); j++){
				
				double sum = 0;
				
				for(int m=0; m<this.centroids.get(j).size(); m++){
					
					sum+= Math.pow((this.centroids.get(j).get(m)-this.dataset.get(i).get(m)), 2);
					
				}
				
				sum = Math.sqrt(sum);
				
				HashMap<Integer,Double> temp = this.distances.get(i);
				
				if(temp==null)
					
				temp = new HashMap<Integer,Double>();
				
				temp.put(j, sum);
				
				this.distances.put(i, temp);
				
			}
			
		}
		
	}

}
