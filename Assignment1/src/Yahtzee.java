/**
 * This class represents the game of Yahtzee, where there are 5 rolled dice and the scoring is performed.
 * @author Ana Balteanu
 *
 */
public class Yahtzee {
	///Private attributes
	private Dice[] dice;
	
	private int sumAllDice() {
		int sum = 0;
		for(int i=0;i<5;i++) {
			sum+=this.dice[i].getValue();
		}
		return sum;
	}

	
	private int[] getValueArray() {
		int valueArray[] = new int[5];
		
		for(int i = 0; i<5; i++) {
			valueArray[i]= this.dice[i].getValue();
		}
		
		return valueArray;
	}
	
//	private int smStraight() {
//		int score = 0;
//		int valueArray[] = new int[5];
//		valueArray = this.getValueArray();
//		for(int i = 0; i < 5; i++) {
//			if(this.dice[i].getValue() == 1) {
//				for(int j = i; j < 5; j++) {
//					
//				}
//			}
//		}
//		
//		return score;
//	}
	
	
	private int lgStraight() {
		int score = 0;
		boolean isStraight = true;
		
		for(int i = 0; i < 5; i++) {
			//iterating through each die
			if(this.dice[i].getValue() != i+1) {
				//checking whether the die is equal to its position (eg 1st die = 1, 2nd die = 2, etc)
				isStraight = false;
			}
		}

		if(isStraight) {
			score = 40;
		}
		
		return score;
	}
	
	private int chance() {
		boolean isChance = true;
		for(int i = 0; i<5; i++) {
			//checking that the occurances for each value is exactly one
			if(this.getValueArray()[i] != 1) {
				isChance = false;
			}
		}
		if (isChance) {
			return this.sumAllDice();
		} else {
			return 0;
		}
	}
	
	
	///Public attributes
	public Yahtzee() {
		dice = new Dice[5];
		
		for(int i=0; i<5; i++) {
			this.dice[i] = new Dice();
			this.dice[i].roll();
		}
	}
	
	public Yahtzee(Dice[] dice) {
		this.dice = dice;
	}
	
	public int[] getValueCount() {
		int diceVals[] = new int[]{0, 0, 0, 0, 0, 0};
		
		for(int i = 0; i<5; i++) {
			//iterating through 5 existing dice
			for(int j = 0; j<6; j++) {
				//iterating through 6 possible values of the dice
				if(this.dice[i].getValue() == j+1) {
					//adding count of each value for each die
					diceVals[j]++;
				}
			}
		}
		
		return diceVals;
	}
	
	/// for testing, return int, but change to int[] and return whole array before submitting
	public int[] getScoreOptions() {
		//gets the array of 13 possible score options
		int scoreOpt[] = new int[13];
		
		for(int i = 0; i<9; i++) {
			//iterating through loop for first 6 options (i is used to iterate through the scoreOpt elements as well as the getValueCount elements (uses the same variable for two uses)
			if(i<6) {
				
				//checks score options for values 1 through 6
				//multiplies occurrences of the number by the number itself to determine the score ie if there are three 6s, the number of points would be 6+6+6 or 3*6 or or getValueCount[5]*6
				scoreOpt[i]=this.getValueCount()[i]*(i+1);	//for options aces through sixes
				
				//for 3 of a kind and 4 of a kind and full house and yahtzee
				if(this.getValueCount()[i]==3) {
					//if any of the value counts are three, points are added for three of a kind
					for(int j=0;j<6;j++) {
						//going through each getValueCount component again to see if in addition to a value appearing three times, there is a value that appears twice
						//this finds a full house
						if(this.getValueCount()[j]==2) {
							//condition for a full house
							scoreOpt[8]=25;
						} else {
							//condition for just three of a kind
							scoreOpt[7]=this.sumAllDice();
						}
					}
				}
				
				if(this.getValueCount()[i]==4) {
					//condition for four of a kind
					scoreOpt[8]=this.sumAllDice();
				}
				
				if(this.getValueCount()[i]==5) {
					//condition for five of a kind (Yahtzee)
					scoreOpt[11]=50;
				}
			}
		}
		
		//Now check for small and large straight, and Chance
		//scoreOpt[9] = this.smStraight();
		scoreOpt[10]= this.lgStraight();
		scoreOpt[12]=this.chance();
		
		return scoreOpt;
	}
	
	public int[] score() {
		int array[]=this.getScoreOptions();
		int temp;
		int index = 0;
		for(int i = 0; i<13; i++ ){
			for(int j = i+1; j<13; j++){
				if(array[i]>array[j]){
					index = i;
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		
		int returnArray[] = {array[12], index};
		return returnArray;
	}
	
	public boolean equals(Yahtzee other) {
		boolean equal = true;
		for(int j = 0; j<6; j++) {
			//iterating through 6 possible values of the dice
			if(this.getValueCount()[j] != other.getValueCount()[j]) {
				equal = false;
			}
		}
		return equal;
	}
	
	public String toString() {
		String arrayF = "Dice: {";
		for(int i=0; i<5; i++) {
			arrayF = arrayF + String.valueOf(this.dice[i].getValue());
			if(i==4) {
				arrayF+="}";
			} else {
				arrayF+=", ";
			}
		}
		return arrayF;
	}
	
	//FOR TESTING
	public String valueString() {
		//this method is for testing purposes only
		String arrayF = "Values in this game: {";
		for(int i=0; i<6; i++) {
			arrayF = arrayF + String.valueOf(this.getValueCount()[i]);
			if(i==5) {
				arrayF+="}";
			} else {
				arrayF+=", ";
			}
		}
		return arrayF;
	}
	
	public String scoringOptionsString() {
		String str="Score Options: [";
		int array[] = this.getScoreOptions();
		for(int i = 0;i<13;i++) {
			str=str+String.valueOf(array[i]);
			if(i==12) {
				str+="}";
			} else {
				str+=", ";
			}
		}
		return str;
	}
}
