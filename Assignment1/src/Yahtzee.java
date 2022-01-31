/**
 * This class represents the game of Yahtzee, where there are 5 rolled dice and the scoring is performed.
 * @author Ana Balteanu
 *
 */
public class Yahtzee {
	///Priv attributes
	private Dice[] dice;
	
	private int sumAllDice() {
		int sum = 0;
		for(int i=0;i<5;i++) {
			sum+=this.dice[i].getValue();
		}
		return sum;
	}
	
//	private int smStraight() {
//
//	}
	
	
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
				scoreOpt[i]=this.getValueCount()[i]*(i+1);	//for options aces through sixes
				
				//for 3 of a kind and 4 of a kind and full house and yahtzee
				if(this.getValueCount()[i]==3) {
					for(int j=0;j<6;j++) {
						//going through each getValueCount component again to see if in addition to a value appearing three times, there is a value that appears twice
						//this finds a full house
						if(this.getValueCount()[j]==2) {
							//condition for a full house
							scoreOpt[9]=25;
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
					scoreOpt[12]=50;
				}
			}
		}
		
		return scoreOpt;
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
		for(int i = 0;i<13;i++) {
			str=str+String.valueOf(this.getScoreOptions()[i]);
			if(i==12) {
				str+="}";
			} else {
				str+=", ";
			}
		}
		return str;
	}
}
