/*
Package: supervised4
Class: learn
Objective: Create the Neural Network and train it using feedforward and backpropagation.  
Functions defined: neuralnetwork(),feedforward(),backprop(),write_to_file(),get_training_data(),	
                    assign_random_weights(),display()
Functions in order of occurence:
            1.neuralnetwork
            2.get_training_data	
            3.assign_random_weights
            4.display
            5.feedforward
            6.backprop
            7.write_to_file
*/
package supervised4;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class learn
{

    protected static final int input_neurons = 10;                              //No of neurons in Input Layer
    protected static final int hidden_neurons = 7;                              //No of neurons in Hidden Layer
    protected static final int output_neurons = 1;                              //No of neurons in Output Layer
    protected double wih[][] = new double[input_neurons+1][hidden_neurons];	//Weight matrix between IL and HL
    protected double who[][] =  new double[hidden_neurons+1][output_neurons];	//Weight matrix between HL and OL
    public double LEARN_RATE=0.5;                                               //Learning Rate
    protected double input[] = new double[input_neurons];                       //Input values
    protected double output[] = new double[output_neurons];                     //Output values
    protected double target[] = new double[output_neurons];                       //Target output values
    protected double hidden[]=new double[hidden_neurons+1];                     //Hidden layer values
    private double erro[] = new double[output_neurons];                         //Error @OL
    private double errh[]=new double[hidden_neurons];                           //Error @HL
    private int sampleq = 0;
    private static double training_input_data[][] = new double[256][11];	

    /*
    Function: neuralnetwork
    Objective: Create the neural network and call feedforward and backpropagation
    functions in order to train the network.
    */
    public void neuralnetwork(int m) throws IOException
    {		
        
        System.out.println("This is a Neural network with 1 hidden layer and number of hidden neurons in that layer = "+hidden_neurons);	
        System.out.println();
        System.out.println("Network is training . . .");
        get_training_data();	
        assign_random_weights();
        //display(); 
        int trail = 0;	 
        for(int epoc = 0 ; epoc < 25600 ; epoc++)
        {	 
            trail = trail+1;				
            if(trail == sampleq)
            {
                trail = 0;
            }
            for(int i = 0 ; i < input_neurons ; i++)
            {
              input[i] = training_input_data[trail][i];
            }

            for(int i = 0 ; i < output_neurons ; i++)
            {
               target[i] = training_input_data[trail][i+input_neurons];
            }

            feedforward();
            backprop();

       }
        //write updated weights to the file
        write_to_file();

    }

    /*
    Function:write_to_file
    Objective: write updated weights to the file
    */
    private void write_to_file() throws IOException
    {
        String str=null;

        BufferedWriter bf=new BufferedWriter(new FileWriter("./weights.txt"));
        for(int i=0;i<input_neurons+1;i++)
        {
            for(int j=0;j<hidden_neurons;j++)
            {
                str=wih[i][j]+" ";							
                bf.write(str);
            }
            str="\n";
            bf.write(str);
        }

        for(int i=0;i<hidden_neurons+1;i++)
        {
            for(int j=0;j<output_neurons;j++)
            {
                str=who[i][j]+" ";							
                bf.write(str);

            }
            str="\n";
            bf.write(str);
        }
        
        bf.close();
    }
    

    /*
    Function:get_training_data
    Objective:Obtain training data from the file
    */
    private void get_training_data() throws NumberFormatException, IOException
    {
        BufferedReader bre= new BufferedReader(new FileReader("./data.txt"));
        int iq = 0;         
        double sq = 0; 
        String strq;	    
        while((strq = bre.readLine())!=null)         
        {       
            String []line = strq.split("\n");
            for(String temp: line)
            {
                iq = 0;
                String []number = temp.split(" ");
                for(String kq: number)
                {
                    sq = Double.parseDouble(kq);
                    sq = sq/100;
                    training_input_data[sampleq][iq] = sq;
                    iq++;

                }
             sampleq++;
            }
        }
        bre.close();
    }

    
    /*
    Function: feedforward
    Objective: Carry out initialization and activation of the neurons in the neural network.
    */
    public void feedforward()
    {
        double sum = 0;		
        //for input layer to hidden layer
        for(int i = 0 ; i < hidden_neurons ; i++)
        {
            sum = 0;
            for(int j = 0 ; j < input_neurons ; j++)
            {
               sum += input[j] * wih[j][i];
            }
            sum += wih[input_neurons][i];
            hidden[i] = activation(sum);			
        }
        sum = 0;
        //for hidden layer to output layer
        for(int i = 0 ; i < output_neurons ; i++)
        {
            sum = 0;
            for(int j = 0 ; j < hidden_neurons ; j++)
            {
               sum += hidden[j] * who[j][i];
            }
            sum += who[hidden_neurons][i];
            output[i] = activation(sum);
        } 

    }
    
    /*
    Function: sigmoid
    Objective: mathematical function to carry out activation of nodes
    */
	public  double activation(double sum) {
		return ((Math.exp(2*sum))-1)/((Math.exp(2*sum))+1);			
		 	
	}
    
    /*
    Function: backprop
    Objective: Updation of weights using the error calculated between the target and output value obtained.
    */
    private void backprop()
    {
        
        //Error calculation for output layer
        for(int i = 0; i < output_neurons; i++)
        {
            erro[i] = (target[i]-output[i]) * tangent_derivative(output[i]);
        }		
        
        // Calculate the hidden layer error wrt output.   
        for(int hid = 0; hid < hidden_neurons; hid++)
        {
            errh[hid] = 0.0;
            for(int out = 0; out < output_neurons; out++)
            {
               errh[hid] += erro[out] * who[hid][out];
            }
            errh[hid] *= tangent_derivative(hidden[hid]);
        }
        
        // Update the weights for the output layer.
        for(int out = 0; out < output_neurons; out++)
        {
            for(int hid = 0; hid < hidden_neurons; hid++)
            {
               who[hid][out] += (LEARN_RATE * erro[out] * hidden[hid]);
            } 
            who[hidden_neurons][out] += (LEARN_RATE * erro[out]); // Update the bias.
        } 
        
        //Update input and first hidden layer weights    
        for(int hid = 0; hid < hidden_neurons; hid++)
        {
            for(int inp = 0; inp < input_neurons; inp++)
            {
              wih[inp][hid] += (LEARN_RATE * errh[hid] * input[inp]);
            } // inp
            wih[input_neurons][hid] += (LEARN_RATE * errh[hid]); // Update the bias.
        } // hid
        return;		
    }
    
    /*
    Function:sigmoid_derivative
    Objective:mathematical function used while updating the weights while backpropagation
    */
	private double tangent_derivative(double d) {
		double n=Math.pow(2.71828,d);
		double n1=Math.pow(2.71828,-d);
		d=((2)/(n+n1));
		d=Math.pow(d, 2);
		return d;
	}	
    
    /*
    Function: assign_random_weights
    Objective: Assigning random weights to the edges of the neural network.
    */
    private void assign_random_weights()
    {
            // assign random weights to input to hidden layer
        for(int i = 0; i < input_neurons+1; i++)
        {
            for(int j = 0; j < hidden_neurons; j++)
            {
               wih[i][j] = new Random().nextDouble() - 0.5;
            }
        }

        //assign random weights to output layer
        for(int i = 0; i < hidden_neurons+1; i++)
        {
            for(int j = 0; j < output_neurons; j++)
            {
               who[i][j] = new Random().nextDouble() - 0.5;
            }
        }	
    }

    
    /*
    Function: display
    Objective: Display the weight values assigned to the edges after assign_random_weights().
    */
    /*
    private void display()
    {
        System.out.println("FROM INPUT TO HIDDEN 1");
        
        for(int i = 0; i < input_neurons+1; i++)
        {
            for(int j = 0; j < hidden_neurons; j++)
            {
               System.out.println("weight from i/p "+i+"  to hidden "+j+"  "+wih[i][j]);
            }
        }		
        System.out.println("FROM  LAST HIDDEN LAYER TO OUTPUT ");
    
        for(int i = 0; i < hidden_neurons+1; i++)
        {
            for(int j = 0; j < output_neurons; j++)
            {
               System.out.println("weight from last h/l "+i+"  to o/p layer "+j+"  "+who[i][j]);
            }
        }	

    }
    */
}
