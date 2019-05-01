package events;

//this is the class which holds all of the information of the complex number in the display matrix
//including maximum and minimum real and imaginary values, and resolution values for each dimension
public class CompPlane implements Plane {
	static double imMin;
	static double imMax;
	static double realMin;
	static double realMax;
	static double resIm;
	static double resRe;
	static double height;
	static double width;
	static ImNum[][] plane;

	//when instantiated with five contructor arguments, the complex plane creates a square-frame
	//centered on imaginary number imCent
	//with real axis extremes rm and rM
	public CompPlane(double imCent, double rm, double rM,double h, double w) {
		realMin = rm;
		realMax = rM;
		height = h;
		width = w;
		resRe = width/(realMax-realMin);
		resIm=resRe;
		imMin=imCent-(width/(resIm*2));
		imMax=imCent+(width/(resIm*2));
		plane = makePlane();
	}
	
	//when instantiated with six constructor arguments, the complex plane is established with the 
	//specified real and imaginary extremes.
	//the image adjusts to make the display a 1:1 ratio
	public CompPlane(double im, double iM,double rm, double rM,double h, double w) {
		realMin = rm;
		realMax = rM;
		height = h;
		width = w;
		resRe = width/(realMax-realMin);
		
		//make iMin and iMax adjust to have a square frame	
		resIm = resRe;
		double center = (iM+im)/2;
		imMin=center-(height/(resIm*2));
		imMax=center+(height/(resIm*2));
		
		plane = makePlane();
	}
	
	
	//the plane is simply comprised of a 2D array of imaginary numbers
	private static ImNum[][] makePlane(){
		ImNum[][] plane1 = new ImNum[(int) (width+1)][(int) (height+1)];
		for(int r = 0;r<plane1.length;r++) {
			for(int i = 0;i<plane1[0].length;i++) {
				double curReal = realMin+(r/resRe);
				double curIm = imMin+(i/resIm);
				plane1[r][i]= new ImNum(curReal,curIm);
			}
		}
		return plane1;
	}

	public double[] returnExtremes() {
		double[]extremes = {realMin,realMax,imMin,imMax};
		return extremes;
	}
	
	public double[] getPlaneValue(int a,int b) {
		double[] temp = {plane[a][b].a,plane[a][b].b};
		return temp;
		}
	
}
