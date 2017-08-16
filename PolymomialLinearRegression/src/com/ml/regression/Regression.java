package com.ml.regression;

public class Regression {
	
	private Data d;
	
	private Line l ;
	
	public Regression(Data d){
		
		this.d = d;
		
		this.l = new Line(d.getNoOfSlopes());
		
	}

	public Line findLinearEquation() {
		
		double modf_XT[][] = createXTransposeMatrix();
		
		//displaymatrix(modf_XT);
		
		double modf_Y[][] = createYMatrix();
		
		//displaymatrix(modf_Y);
		
		double modf_X[][] = createXMatrix(modf_XT);
		
		//displaymatrix(modf_XT);
		
		//displaymatrix(modf_X);
		
		// X'X
		double XTrans_X[][] = multiplicar(modf_XT,modf_X);
		
		//displaymatrix(XTrans_X);
		
		// X'y
		double XTrans_Y[][] = multiplicar(modf_XT,modf_Y);
		
		double m[][] = invert(XTrans_X);
		
		double b[][] = multiplicar(m,XTrans_Y);
		
		//displaymatrix(b);
		
		l.setConstant(b[0][0]);
		
		double slopes[] = new double[l.noOfSlopes];
		
		for(int i=0;i<slopes.length; i++)
			slopes[i] = b[i+1][0];
		
		l.setSlopes(slopes);
		
		return l;
	}
	
    public static double[][] invert(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transform the matrix into an upper triangle
        gaussian(a, index);
 
 // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
 // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
    
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
 // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
 // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
 // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
	
	public static double[][] multiplicar(double[][] modf_XT, double[][] modf_X) {

        int aRows = modf_XT.length;
        int aColumns = modf_XT[0].length;
        int bRows = modf_X.length;
        int bColumns = modf_X[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += modf_XT[i][k] * modf_X[k][j];
                }
            }
        }

        return C;
    }

	private double[][] createXMatrix(double t[][]) {
		double temp[][] = new double[d.getNoOfSamples()][d.getNoOfSlopes()+1];
		for(int i=0; i<t.length; i++){
			for(int j=0; j<t[i].length; j++){
				temp[j][i] = t[i][j];
			}
		}
		return temp;
	}

	private double[][] createYMatrix() {
		double temp[][] = new double[d.getNoOfSamples()][1];
		
		for(int i=0; i<temp.length; i++){
			temp[i][0] = d.getYind(i);
		}
		
		return temp;
	}



	private double[][] createXTransposeMatrix() {
		double temp[][] = new double[d.getNoOfSlopes()+1][d.getNoOfSamples()];
		
		for(int i = 0; i<temp.length; i++){
			for(int j=0; j<temp[i].length; j++){
				if(i==0)
					temp[i][j] = 1;
				else
					temp[i][j] = d.getXind(i-1, j);
			}
		}
		
		return temp;
	}
	
	private void displaymatrix(double[][] t) {
		for(int i=0; i<t.length; i++){
			for(int j=0; j<t[i].length; j++){
				System.out.print(t[i][j]+" ");
			}
			System.out.println();
		}		
	}

}