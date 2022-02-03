/**
 * This class represents the game of Yahtzee, where there are 5 rolled dice and the scoring is performed.
 * @author Ana Balteanu
 *
 */
public class Yahtzee {
	
	///Private attributes
	
	/**
	 * Array of dice for Yahtzee game
	 */
	private Dice[] dice;
	
	/**
	 * Helper method to add the sum of the values of all five dice in the game.
	 * Used to determine certain point values
	 * @return the sum of the faces of all the dice
	 */
	private int sumAllDice() {
		int sum = 0;
		for(int i=0;i<5;i++) {
			sum+=this.dice[i].getValue();
		}
		return sum;
	}

	/**
	 * Helper method that gets the array of the five dice and each of their values 
	 * @return an array of the dice values
	 */
	private int[] getValueArray() {
		int valueArray[] = new int[5];
		
		for(int i = 0; i<5; i++) {
			valueArray[i]= this.dice[i].getValue();
		}
		
		return valueArray;
	}
	
	/**
	 * Helper method that sorts a given array in ascending order
	 * @param oldArray is the unsorted array
	 * @return array, which is the new sorted version of oldArray
	 */
	private int[] sortArray(int[] oldArray) {
		int temp = 0;
		int array[] = oldArray;
		 //Sort the array in ascending order
		for (int i = 0; i <array.length; i++) {     
			for (int j = i+1; j <array.length; j++) {     
				if(array[i] >array[j]) {      //swap elements if not in order
					temp = array[i];    
					array[i] = array[j];    
					array[j] = temp;    
				}     
			}     
		}
		return array;
	}
	
	/**
	 * Helper method that assesses whether the dice in the Yahtzee game have the right values for a small straight
	 * @return the number of points given after the assessment (if the dice have the values for a small straight, return 30 points, if not return 0)
	 */
	private int smStraight() {
        int score = 0;
        boolean isStraight = true;
        int array[] = sortArray(this.getValueArray());
        //because a small straight is only four consecutive numbers, there is room for one duplicate, which will appear in the middle of the sorted array
        //because of this, if a single repeated number is found, a small straight is still possible.
        boolean repeatNum = false;	

        for(int i = 1; i < 5; i++) {
            //iterating through each die, starting from the second
            if(array[i] != array[i-1] + 1) {
            	//comparing current die to the die before it, seeing that the one before it is exactly one less than the current die's value
            	if (array[i] == array[i-1] && !repeatNum){
            		//if the current die is equal to the die before it, and there as not been a repeated number yet, program continues to check for a small straight.
                	repeatNum = true; //allows for exactly one repeated number
                } else {
                	//if current die is not exactly one more than the one before it, it is not a straight
                    isStraight = false;
                }
            }
        }

        if(isStraight) {
        	//if isStraight is true after all the checks above, the score should be 30. If not, score remains 0.
            score = 30;
        }
        return score;
    }
	
	/**
	 * Helper method that checks if the dice have values with a valid large Straight.
	 * @return the score after checking if there is a large straight or not 
	 */
	private int lgStraight() {
        int score = 0;
        boolean isStraight = true;
        int array[] = sortArray(this.getValueArray()); //sorting array before verifying conditions

        for(int i = 1; i < 5; i++) {
            //iterating through each die, starting with the second die
            if(array[i] != array[i-1] + 1) {
            	//checking that current die is exactly one greater than the previous die before it
            	isStraight = false;
            }
        }

        if(isStraight) {
        	//if isStraight is still true after checking above, the score is set to 40. if not, score remains 0
            score = 40;
        }
        return score;
    }
	
	///Public attributes
	
	/**
	 * Constructor takes no parameters, initializes an array of five dice and rolls each of them.
	 */
	public Yahtzee() {
		dice = new Dice[5];
		
		for(int i=0; i<5; i++) {
			this.dice[i] = new Dice();
			this.dice[i].roll();
		}
	}
	
	/**
	 * Constructor that takes one parameter, and initializes the pre-existing dice array (inside Yahtzee class) to the one in the parameter
	 * @param dice, an array of dice, which can be entered by user
	 */
	public Yahtzee(Dice[] dice) {
		this.dice = dice;
	}
	
	/**
	 * Method that counts the number of occurrences of each value from one to six on each of the five die in the Yahtzee game
	 * @return diceVals, which is the array of the number of value occurrences from the five dice.
	 */
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
	
	/**
	 * Method that determines the score options possible for the given dice combination of the game. 
	 * There are 13 different scoring options:
	 * Aces, Twos, Threes, Fours, Fives, Sixes, 3 of a kind, 4 of a kind, full house, small straight, large straight, chance, and Yahtzee (5 of a kind)
	 * @return scoreOpt, the array of the thirteen possible scores that are attainable from the given dice combination
	 */
	public int[] getScoreOptions() {
		//gets the array of 13 possible score options
		int scoreOpt[] = new int[13];
		
		for(int i = 0; i<6; i++) {
			//iterating through loop for first 6 options (i is used to iterate through the scoreOpt elements as well as the getValueCount elements (uses the same variable for two uses)
			//if(i<6) {
				
				//checks score options for values 1 through 6
				//multiplies occurrences of the number by the number itself to determine the score 
				//ie if there are three 6s, the number of points would be 6+6+6 or 3*6 or or getValueCount[5]*6
				scoreOpt[i]=this.getValueCount()[i]*(i+1);	//for options aces through sixes
				
				///for 3 of a kind and 4 of a kind, full house and yahtzee
				if(this.getValueCount()[i]>=3) {
					//if any of the value counts are three, points are added for three of a kind
					for(int j=0;j<6;j++) {
						//going through each getValueCount component again to see if in addition to a value appearing three times, there is a value that appears twice
						//this finds a full house
						if(this.getValueCount()[j]==2) {
							//condition for a full house
							scoreOpt[8]=25;
						} else {
							//condition for just three of a kind
							scoreOpt[6]=this.sumAllDice();
						}
					}
				}
				
				if(this.getValueCount()[i]>=4) {
					//condition for four of a kind
					scoreOpt[7]=this.sumAllDice();
				}
				
				if(this.getValueCount()[i]==5) {
					//condition for five of a kind (Yahtzee)
					scoreOpt[11]=50;
				}
			//}
		}
		
		//Now check for small and large straight, and Chance
		scoreOpt[9] = this.smStraight();
		scoreOpt[10]= this.lgStraight();
		scoreOpt[12]=this.sumAllDice();
		
		return scoreOpt;
	}
	
	public int[] score() {
		int array[]=this.getScoreOptions();
		int index = 0;
		int max = array[0];
		for(int i = 0; i<13; i++){
			if(array[i]>max){
				index = i;
				max = array[i];
			}
		}
		int returnArray[] = {array[index], index};
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