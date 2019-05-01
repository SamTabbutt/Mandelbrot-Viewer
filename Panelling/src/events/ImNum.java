package events;
import java.lang.Math;

public class ImNum {
	double a;
	double b;
	double magnitude;
	double theta;
	
	//class construct to create imaginary number with a and b value
	public ImNum(double a1, double b1) {
		a=a1;
		b=b1;
		magnitude = Math.sqrt(a*a+b*b);
		theta = Math.atan2(b,a);
	}

	public String readNum() {
		return a+"+"+b+"i";
	}
	
	public ImNum exponent(double p) {
		double newa = Math.pow(magnitude, p)*Math.cos(theta*p);
		double newb = Math.pow(magnitude, p)*Math.sin(theta*p);
		return new ImNum(newa,newb);
	}
	
	
	public ImNum square() {
		return new ImNum(a*a-b*b,2*a*b);
	}
	
	public ImNum plus (ImNum c) {
		return new ImNum(a+c.a,b+c.b);
	}
	
	public ImNum minus (ImNum c) {
		return new ImNum(a-c.a,b-c.b);
	}
	
	public ImNum sqrRoot () {
		double newa = Math.sqrt((Math.sqrt((a*a)+(b*b))+a)/2);
		double newb = Math.sqrt((Math.sqrt((a*a)+(b*b))-a)/2);
		return new ImNum(newa,newb);
	}
	
	public ImNum opp() {
		return new ImNum(-a,-b);
	}
	
}