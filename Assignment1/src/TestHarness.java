//
//public class TestHarness {
//	public static void main(String[] args) {
////		Dice roll = new Dice();
////		roll.roll();
////		System.out.println(roll.getValue());
////		
////		Yahtzee game = new Yahtzee();
////		Yahtzee gameOther = new Yahtzee(new Dice[] {new Dice(1), new Dice(2), new Dice(2), new Dice(3), new Dice(4)});
////		
////		System.out.println(game.toString());
////		System.out.println(gameOther.toString());
////		System.out.println(game.valueString());
////		System.out.println(gameOther.valueString());
////		//System.out.println(game.equals(gameOther));
////		
////		System.out.println(gameOther.scoringOptionsString());
////		System.out.println(gameOther.score()[0]+ " " + gameOther.score()[1]);
//		Yahtzee game1, game2, game3;
//		
//		game1 = new Yahtzee();
//		game2 = new Yahtzee(new Dice[] {new Dice(2), new Dice(5), new Dice(3), new Dice(5), new Dice(6)});
//		game3 = new Yahtzee(new Dice[] {new Dice(6), new Dice(1), new Dice(1), new Dice(5), new Dice(2)});
//				
//		
//		// Test 12 - score
//		
//		game3 = new Yahtzee(new Dice[] {new Dice(4), new Dice(5), new Dice(1), new Dice(2), new Dice(3)});
//		if (showArray(game3.score()).equals("[40, 10]")) {
//			System.out.println("Test 12 Passed");
//		} else {
//			System.out.println("Test 12 Failed");
//		}
//		//System.out.println(game3.score()[0] + game3.score()[1]);
//		System.out.println(game3.scoringOptionsString());
//
//		
//	}
//	
//	public static String showArray (int[] arr) {
//		String s = "[";
//		for (int i = 0; i < arr.length; i++) {
//			s += arr[i];
//			if (i < arr.length - 1) s += ", ";
//		}
//		s += "]";
//		return s;
//	}
//
//
//
//}
