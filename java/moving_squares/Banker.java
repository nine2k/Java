class Banker {
	public String name;
	public double interestRate;
	public Money cash;
	public Banker copy() {
		Banker result = new Banker();
		result.name = name;
		result.interestRate = interestRate;
		result.cash = cash;
		return result;
	} // end copy
} // end class Banker

class Money {
	public int dollars;
	public int cents;
	public Money(int d, int c) { 
		dollars = d; 
		cents = c; 
	}
} // end class Money