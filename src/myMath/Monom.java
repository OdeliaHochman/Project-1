package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Efrat and Odelia
 *
 */
public class Monom implements function{

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
		

	}
	public Monom(Monom ot) {

		this(ot.get_coefficient(), ot.get_power());
	}

	public int get_power () 
	{
		return this._power;
	}
	public double get_coefficient () 
	{
		return round(this._coefficient);
	}
	public double f(double x)
	{
		double a = get_coefficient();
		int b = get_power();
		double monomAns= a*(Math.pow(x, b));
		return monomAns;
	}
	public Monom derivative() 
	{
		double a = this.get_coefficient()*this.get_power();
		int b = this.get_power()-1;
		return new Monom(a,b);	
	}
	public String toString() 
	{
		return "" +this.get_coefficient()+"x^"+this.get_power();

	}
	public boolean isEqual(Monom m1) 
	{
		boolean monom=false;

		if(m1!=null)
		{
			if(this.get_power() == m1.get_power() && this.get_coefficient() == m1.get_coefficient())
			{
				monom=true;
			}
			else
			{
				monom=false;
			}
		}
		return monom;

	}
	public void multiply(Monom m1)
	{
		this.set_coefficient(this.get_coefficient() * m1.get_coefficient());
		this.set_power(this.get_power() + m1.get_power());
	}
	static public Monom multiply(Monom m1,Monom m2)
	{
		Monom res = new Monom(0,0);
		res.set_coefficient(m1.get_coefficient() * m2.get_coefficient());
		res.set_power(m1.get_power() + m2.get_power());
		return res;
	}

	public void add(Monom m1)
	{
		if(this.get_power() != m1.get_power())
		{
			System.out.println("ERROR: It is not possible to add two monomes from a different power");
		}
		else
		{
			this.set_coefficient(this.get_coefficient() + m1.get_coefficient());
		}
	}
	public void sub(Monom m1)
	{
		if(m1.get_power()==this.get_power())
		{
			this.set_coefficient(this.get_coefficient()-m1.get_coefficient());
		}
	}

	public Monom InvSign()
	{
		this. set_coefficient(this.get_coefficient()*-1);
		return this;
	}

	public boolean validValues()
	{
		boolean validValues = true;
		if(this.get_power()<0)
		{
			validValues=false;
		}
		if(this.get_coefficient() == 0)
		{
			validValues=false;
		}
		return validValues;
	}

	public double round(double longValue)
	{
		return Math.round(longValue * 100.0) / 100.0;
	}
	
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int b) 
	{
		this._power=b;

	}



	private double _coefficient; // 
	private int _power; 



}
