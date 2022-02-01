
public class TestHarness {
	public static void main(String[] args) {
//		Dice roll = new Dice();
//		roll.roll();
//		System.out.println(roll.getValue());
//		
		Yahtzee game = new Yahtzee();
		Yahtzee gameOther = new Yahtzee(new Dice[] {new Dice(6), new Dice(6), new Dice(6), new Dice(6), new Dice(6)});
		
		System.out.println(game.toString());
		System.out.println(gameOther.toString());
		System.out.println(game.valueString());
		System.out.println(gameOther.valueString());
		//System.out.println(game.equals(gameOther));
		
		System.out.println(gameOther.scoringOptionsString());
		System.out.println(gameOther.score()[0]+ " " + gameOther.score()[1]);

		
	}


}
