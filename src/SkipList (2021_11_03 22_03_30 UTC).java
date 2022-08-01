import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class SkipList // crates and maintains a skip list
{

	Node head = null; // the beginning of the list

	private RandomAccessFile database;





	void compile() throws IOException // sets up and reads from file the users and nodes
	{
		createList();
		readFromFile();

	}


	public void createList() // creates a template list with no real data
	{

		Node N100 = null;
		Node N10 = null;
		Node N1 = null;


		for (int int100 = 9; int100 >= 0; int100--)
		{

			for (int int10 = 9; int10 >= 0; int10--)
			{

				for (int int1 = 9; int1 >= 0; int1--)
				{

					Node newNode = new Node(int100 + int10 + int1 + "000000");

					newNode.next = N1;
					newNode.next1 = N1;
					N1 = newNode;
				}
				N1.next10 = N10;
				N10 = N1;


			}

			N1.next100 = N100;
			N100 = N1;

		}

		head = N1;

	}




	public void readFromFile() throws IOException // reads users from file and adds to list
	{
		database = new RandomAccessFile("Users.txt", "rw");
		String line = "";
		long lineCount = countLines("Users.txt");
		User newUser = new User();


		System.out.print("\n");

		for (int i = 0; i < lineCount; i++)
		{
			try
			{
				line = database.readLine();
				newUser = newUser.getUserFromTxt(line);

				Node newNode = new Node(newUser);

				add(newNode, false);

				if (i % 10000 == 0) // for every 10000 users print a .
					System.out.print(".");


			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}

	}





	public void add(Node newNode, boolean write) throws FileNotFoundException // adds a new node to the skip list
	{
		Node myNode = head;


		String newSSN = newNode.user.SSN; // saves memory from having to call multiple times



		while (myNode != null)
		{

			if (myNode.user.SSN.equals(newSSN)) // if duplicate user then stop
			{
				break;
			}

			if (myNode.next100 != null && myNode.next100.user.SSN.compareTo(newSSN) <= 0) // check if the 100 node in front is greater
			{
				myNode = myNode.next100;
			} else if (myNode.next10 != null && myNode.next10.user.SSN.compareTo(newSSN) <= 0) // check if the 10 node in front is greater
			{
				myNode = myNode.next10;
			} else if (myNode.next1 != null && myNode.next1.user.SSN.compareTo(newSSN) <= 0) // check if the 1 node in front is greater
			{
				myNode = myNode.next1;
			} else if (myNode.next != null && myNode.next.user.SSN.compareTo(newSSN) <= 0) // check if the node in front is greater
			{
				myNode = myNode.next;
			}

			else // node in front is less than so new node should be in front
			{
				newNode.next = myNode.next;
				myNode.next = newNode;


				if (write) // used for when user adds new user
				{

					try // writes to end of file and formats data so it can be read in future
					{

						String filename = "Users.txt";
						FileWriter fw = new FileWriter(filename, true);
						fw.write(("\n" + newNode.user.SSN + ";" + newNode.user.FirstName + ";" + newNode.user.LastName + ";")); // appends the string to the file
						fw.close();

					} catch (IOException e)
					{

					}
				}

				break; // leave program when done
			}

		}



	}





	public void logSearch(String ssn) // search using the skip list
	{

		Node myNode = head; // start at the top

		while (myNode != null)
		{

			if (myNode.user.SSN.equals(ssn)) // if find the same user
			{
				System.out.println("\nUser found! :");
				myNode.user.printUser();
				return;
			}

			if (myNode.next100 != null && myNode.next100.user.SSN.compareTo(ssn) <= 0) // same as add function
			{
				myNode = myNode.next100;
			} else if (myNode.next10 != null && myNode.next10.user.SSN.compareTo(ssn) <= 0)
			{
				myNode = myNode.next10;
			} else if (myNode.next1 != null && myNode.next1.user.SSN.compareTo(ssn) <= 0)
			{
				myNode = myNode.next1;
			} else if (myNode.next != null && myNode.next.user.SSN.compareTo(ssn) <= 0)
			{
				myNode = myNode.next;
			}

			else
			{
				myNode = myNode.next;
			}

		}

		if (myNode == null) // if user cannot be found
		{
			System.out.println("\nUser not found...\n");
		}


	}


	public void linearSearch(String ssn) // searches not using skip list (takes longer)
	{

		Node myNode = head;



		while (myNode != null)
		{
			if (myNode.user.SSN.equals(ssn))
			{
				System.out.println("\nUser found! :");
				myNode.user.printUser();
				return;
			}

			myNode = myNode.next;

		}

		if (myNode == null)
		{
			System.out.println("\nUser not found...\n");
		}


	}


	long countLines(String fileName) // counts the number of lines in the file (used for reading from file) derived
										// from Mkyong.com source
	{

		long lines = 0;

		try (InputStream is = new BufferedInputStream(new FileInputStream(fileName)))
		{
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean endsWithoutNewLine = false;

			while ((readChars = is.read(c)) != -1)
			{
				for (int i = 0; i < readChars; ++i)
				{
					if (c[i] == '\n')
						++count;
				}
				endsWithoutNewLine = (c[readChars - 1] != '\n');
			}
			if (endsWithoutNewLine)
			{
				++count;
			}
			lines = count;
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return lines;
	}


}
