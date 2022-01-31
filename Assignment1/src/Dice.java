/**
 * This class represents a single die in the game of Yahtzee. Each die has a value.
 * @author Ana Balteanu
 *
 */
public class Dice {
	/**
	 * Instance variable value which holds the value rolled by the die.
	 */
	private int value;
	
	/**
	 * Constructor assigns instance variable value to default number, -1\
	 * No parameters or returns
	 */
	public Dice() {
		this.value = -1;
	}
	
	/**
	 * Constructor assigns instance variable to inputted number
	 * @param num is the number assigned to value
	 */
	public Dice(int num) {
		this.value = num;
	}
	
	/**
	 * Roll method simulates a die roll by creating a random number using the RandomNumber class. Variable value is then set to the number rolled.
	 */
	public void roll() {
		//create random num between 1 and 6 using random num class provided and set value to that number
		RandomNumber random = new RandomNumber();
		this.value = random.getRandomNumber(1, 6);
	}
	
	/**
	 * Accessor method to return the one and only private instance variable: value
	 * @return
	 */
	public int getValue() {
		return this.value;
	}
}
