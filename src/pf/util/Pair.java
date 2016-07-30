package pf.util;

public class Pair{

	public int a;
	public int b;
	
	public Pair( int a, int b){
		this.a = a;
		this.b = b;
	}

	public int compareB(Pair o1) {
		if (b > o1.b) return 1;
		else if (b == o1.b) return 0;
		else return -1;
	}
	

	public String toString() {
		return "Pair " + a + ", " + b;
	}
}