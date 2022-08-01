
public class Node // node is a storage object that stores a user and knows what other nodes are in
					// front of it
{
	User user;

	Node next100;
	Node next10;
	Node next1;
	Node next;


	Node()
	{

		user = null;

		next100 = null;
		next10 = null;
		next1 = null;
		next = null;

	}


	public Node(User u)
	{
		user = u;

		next100 = null;
		next10 = null;
		next1 = null;
		next = null;
	}


	public Node(String ssn) // used when setting up the skip list
	{
		user = new User();

		user.SSN = ssn;

		next100 = null;
		next10 = null;
		next1 = null;
		next = null;
	}


}
