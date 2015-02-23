package kaleidoscope;

/**
 * @author James Park
 * @author Siyang Shu
 * It's a class similar to 2D matrix. Use it to rotate 2D point
 *
 */
public class Rotation {
	public double a[][];
	
	/**
	 * Identity rotation
	 */
	public Rotation(){
		a = new double[2][2];
		a[0][0] = 1;
		a[0][1] = 0;
		a[1][0] = 0;
		a[1][1] = 1;
	}
	
	/**
	 * @param r: another rotation
	 * copy constructor
	 */
	public Rotation(Rotation r){
		a = new double[2][2];
		a[0][0] = r.a[0][0];
		a[0][1] = r.a[0][1];
		a[1][0] = r.a[1][0];
		a[1][1] = r.a[1][1];
	}
	
	/**
	 * @param radius: rotation radius
	 */
	public Rotation(double radius){
		a = new double[2][2];
		a[0][0] = Math.cos(radius);
		a[0][1] = -Math.sin(radius);
		a[1][0] = Math.sin(radius);
		a[1][1] = Math.cos(radius);
	}
	
	/**
	 * @param a11
	 * @param a12
	 * @param a21
	 * @param a22
	 * these four parameters are the four values of the matrix
	 */
	public Rotation(double a11, double a12, double a21, double a22){
		a = new double[2][2];
		a[0][0] = a11;
		a[0][1] = a12;
		a[1][0] = a21;
		a[1][1] = a22;
	}
	
	/**
	 * @param r: combine these two rotation
	 */
	public void rotate(Rotation r){
		double[][] b = new double[2][2];
		b[0][0] = a[0][0] * r.a[0][0] + a[0][1] * r.a[1][0];
		b[0][1] = a[0][0] * r.a[0][1] + a[0][1] * r.a[1][1];
		b[1][0] = a[1][0] * r.a[0][0] + a[1][1] * r.a[1][0];
		b[1][1] = a[1][0] * r.a[0][1] + a[1][1] * r.a[1][1];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				a[i][j] = b[i][j];
			}
		}
	}
	
	@Override
	public String toString(){
		String s = a[0][0] + " " + a[0][1] + "\n" + a[1][0] + " " + a[1][1] + "\n" ;
		return s;
	}
	

	
}
