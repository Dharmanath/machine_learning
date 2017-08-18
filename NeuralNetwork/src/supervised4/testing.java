/*
Project: High Resolution Radar Range Profiles Classification 
Package: supervised4
Class: Start
Objective:  Testing the Neural Network with the test samples provided.  
Functions defined: find(),maximum(),get_weights()
Functions in order of occurence: 
            1.find
            2.learn;feedforward
            3.maximum
            4.get_weights
*/
package supervised4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class testing extends learn
{
    //Test Data
    private static double after_training[][] = new double[][]
    {{1,2,3,4}};
    
    private static final int max_samples = 118;
    private int a = 0, b = 0;

    /*
    Function: find
    Objective: Determine the Output class for the given testing samples.
    */
    public void find() throws IOException
    {
        //Obtain the updated weights from the file
    	BufferedWriter bw = new BufferedWriter(new FileWriter("./stocks.csv"));
    	bw.write("\"Day,Visits,Unique Visitors\\n");
    	bw.newLine();
    	String month[]=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    	int ii=1,jj=1,count=0;
        get_weights();
        
        for(int i = 0 ; i < max_samples ; i++)
        {
        	if(ii==28){ii=1;jj++;}
        	System.out.println("==========================");
           for(int j = 0 ; j < input_neurons ; j++)
            {
                input[j] = after_training[i][j]/100;
            }			
            
           feedforward();
           System.out.print("SAMPLE :");
           for(int j = 0 ; j < input_neurons ; j++)
           {
              System.out.print(input[j]+" ");
           }
           
           System.out.println();
           System.out.println("Expected "+after_training[i][10]);
          // bw.write("expected,"+month[ii]+" "+(2000+jj)+","+after_training[i][10]+"\n");
           bw.write(ii+"/"+jj+"/2010"+","+(output[0]*100)+","+(after_training[i][10])+"\\n");
           
           System.out.print("Actual : ");
           //Determine the maximum output value in order to classify
           System.out.println(output[0]);
          // bw.write("actual,"+month[ii]+" "+(2000+jj)+","+(output[0]*100)+"\n");
           ii++;
        }
        bw.write("\"");
        bw.close();
    }

    /*
    Function: maximum
    Objective: Determine the maximum output value in order to determine the output class
    */
    private int maximum(double v[])
    {
        int index = 0;
        double max = v[index];		
        for(int ind = 0 ; ind < output_neurons ; ind++)
        {
            if(v[ind] > max)
            {
                max = v[ind];
                index = ind;
            }
        }
        return index;
    }


    /*
    Function: get_weights
    Objective: Obtain updated weights from the file
    */
    private void get_weights() throws IOException
    {
        BufferedReader bre= new BufferedReader(new FileReader("./weights.txt"));
        int iq = 0;         
        double sq = 0; 
        String strq;	    
        while( (strq = bre.readLine()) != null )         
        {       
            String []line=strq.split("\n");
            for(String temp: line)
            {
                iq=0;
                String []number=temp.split(" ");
                for(String kq: number)
                {
                    sq=Double.parseDouble(kq);
                    if(a<=10)
                    {
                       wih[a][iq]=sq;
                    }
                    else
                    {
                       who[a-11][iq]=sq;
                    }	    			
                    iq++;	    			 
                }
             a++;
            }
        }
        bre.close();			
    }

}
