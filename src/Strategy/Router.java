package Strategy;

public class Router {
	private Calculator calculator;

	public Calculator getCalculator() {
		return calculator;
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	public long Calculate(long num1,long num2) {
		return calculator.execute(num1, num2);
	}
}
