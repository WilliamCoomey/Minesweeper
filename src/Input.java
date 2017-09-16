import java.util.Scanner;

public class Input
{
	private static Scanner k = new Scanner(System.in);
	
	//Gets a valid string from the user
	public static String getString()
	{
		String string = "";
		boolean valid = false;
		
		while(!valid)//Loops until given input is valid
		{
			string = k.nextLine();
			if(string.length() < 1)//Checks if the input is blank
				System.out.print("Input must be at least 1 character long. Try again: ");
			else
				valid = true;//Exits loop if the string is of a valid length
		}
		
		return string;//Returns the string that was inputed
	}
	
	//Gets an int from the user
	public static int getInt()
	{
		boolean valid = false;
		int number = -1;
		
		while(!valid)//Loops until the input is valid
		{
			if(k.hasNextInt())//Checks if the input is an in
			{
				number = k.nextInt();
				valid = true;//Exits the loop if a valid int is inputed
			}
			else
			{	//Asks the user to retry if the input wasn't valid
				System.out.print("Input must be a integer. Try again: ");
				k.nextLine();//Clearing the buffer
			}
		}
		k.nextLine();//Clearing the buffer
		
		return number;//Returning the inputed number
	}
	
	//Gets a double from the user
	public static double getDouble()
	{
		boolean valid = false;
		double number = -1;
		
		while(!valid)
		{
			if(k.hasNextDouble())
			{
				number = k.nextDouble();
				valid = true;
			}
			else
			{
				System.out.print("Input must be a number. Try again: ");
				k.nextLine();
			}
		}
		k.nextLine();
		
		return number;
	}
	
	//Gets an int that is above 0 from the user
	public static int getPositiveInt()
	{
		boolean valid = false;
		int number = -1;
		
		while(!valid)//Loops until input is valid
		{
			number = getInt();//Getting an int from the user
			if(number >= 0)//Checks if the input is greater than or equal to 0
				valid = true;//Exits loop if valid
			else
				System.out.print("Input must be greater than 0. Try again: ");//Asks the user to retry if input isn't valid
		}
		
		return number;//Returns the inputed number
	}
	
	//Gets an int from the user that is between the passed in values "lowerLimit" and "upperLimit"
	public static int getIntWithLimits(int lowerLimit, int upperLimit)
	{
		boolean valid = false;
		int number = -1;
		
		while(!valid)//Loops until the input is in range
		{
			number = getInt();//Calling the getInt() method to get an int
			if(number >= lowerLimit && number <= upperLimit)//Checks if it's in the range of the passed in values
				valid = true;//Exits loop if it's between or equal to the limits
			else//Asks the user to try again and tells them what the limits
				System.out.print("Input must be between "+lowerLimit+" and "+upperLimit+". Try again: ");
		}
		
		return number;//Returning the inputs
	}
	
	//Gets a yes or no answer from the user and returns it as a boolean
	public static boolean getYesNo()
	{
		String input = "";
		boolean answer = false, valid = false;
		
		while(!valid)//Loops until the input is valid
		{
			input = getString();
			input = input.toUpperCase();//Making the input upper case so the case doesn't matter
			
			if(input.charAt(0) == 'Y')//Gets the first character and checks if it's a yes
			{
				answer = true;//Setting the answer t
				valid = true;
			}
			else if(input.charAt(0) == 'N')
			{
				answer = false;
				valid = true;
			}
			else
				System.out.print("Invalid choice. Try again: ");
		}
		
		return answer;
	}
}
