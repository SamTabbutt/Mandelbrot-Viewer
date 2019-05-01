package events;
import java.util.ArrayList;
import java.util.List;


//This is the class that views the behavior of a point on the complex plane under iterations of a function
//in this case, we are looking at the function f(x)=x^2+c
public class Orbit {
	static ImNum x0;
	static double power;
	static List<ImNum> orbit;
	static int N;
	static ImNum c;
	static double diverge=0;
	
	public Orbit(ImNum x, double p, int Ni, ImNum c1) {
		x0=x;
		power = p;
		N=Ni;
		c=c1;
		makeOrbit();
	}
	
	public static void makeOrbit() {
		List<ImNum> init = new ArrayList <ImNum>();
		orbit = orb(power,c,N,x0,init);
	}
	
	public static List<ImNum> orb (double p,ImNum c, int n, ImNum x,List<ImNum> init) {
	//Commented out is one method
	//potential algorithm from wikipedia page on mandelbrot set
		if (n==0||x.magnitude>100) {
			if(x.magnitude>100) {
				//if(x.a>50) {
					diverge=nu(x,n);
				//}else if(x.b<50) {
					//diverge=n+200;
				//}else {
					//diverge=n+100;
				//}
			}else {
				diverge=0;
			}
			return init;
		}else {
			init.add(x);
			ImNum test = x.exponent(p).plus(c);
			return orb(p,c,n-1,test,init);
		}
	}
	
	public static void changeP(double p) {
		power=p;
		makeOrbit();
	}
	
	private static double nu(ImNum z, double n) {
		return n - Math.log(Math.log(z.magnitude/Math.log(2)));
	}
	
	public void printOrbit() {
		//for(int i = 0;i<orbit.size();i++) {
		//	System.out.println(orbit.get(i).readNum()+"  n:" +i);
		//}
	}
	
}

