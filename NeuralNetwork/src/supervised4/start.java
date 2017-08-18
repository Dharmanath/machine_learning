/*
Package: supervised4
Class: Start
Objective:  Call functions and create objects of learn and testing classes in 
            order to train and test the Neural Network.  
Functions defined: main()
Functions in order of occurence: 
            1.main
            2.learn;neuralnetwork
            3.testing;find
*/
package supervised4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class start 
{		
    private static int m;
    private static String str;
    private static int count = 0;
    public static void main(String args[]) throws IOException
    {	
        while( count == 0)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
            System.out.println("Enter 1 to train the Neural Network");
            System.out.println("Enter 2 to check for test case");
            str = br.readLine();
            m = Integer.parseInt(str);
            if( m == 1 )
            {
                learn ob = new learn();
                ob.neuralnetwork(1);
                System.out.println("Network is trained ");
                System.out.println("C./weights.txt for Weights");
                System.out.println();
            }
            else
            {
            	System.out.println();
                System.out.println("Network is finding the class ");
                testing a = new testing();
                a.find();
            }  

            System.out.println("Enter 1 to exit or 0 to continue");
            str = br.readLine();
            count = Integer.parseInt(str); 
        }
    }
}
