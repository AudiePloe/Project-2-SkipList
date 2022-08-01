


public class User // stores the user data
{

	public String SSN;
	public String LastName;
	public String FirstName;



	User()
	{

		SSN = null;
		LastName = null;
		FirstName = null;

	}

	User(String s, String l, String f)
	{
		SSN = s;
		LastName = l;
		FirstName = f;

	}


	User getUserFromTxt(String s) // reads a string from txt and returns user from it
	{

		User tmpUser = new User();

		String parts[] = s.split(";"); // splits the inputed string by ; marks

		tmpUser.SSN = parts[0];
		tmpUser.LastName = parts[1];
		tmpUser.FirstName = parts[2];

		return tmpUser;
	}

	void printUser() // prints out usr data
	{
		System.out.println(SSN + " " + LastName + " " + FirstName + "\n");
	}




}
