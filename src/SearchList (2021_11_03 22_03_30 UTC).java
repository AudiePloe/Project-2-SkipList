//
// Audie Ploe
// 10/02/2021 -- Revised 09/02/2025
//

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SearchList
{

    static SkipList userList = new SkipList(); // the skip list that holds all the nodes

    public static void main(String[] a) throws IOException
    {

        long timeS = System.currentTimeMillis(); // takes starting time in milliseconds
        long timeE = 0;

        System.out.println("LOADING-DATABASE----------------------------------");

        userList.compile(); // creates the list

        timeE = System.currentTimeMillis(); // takes ending time in milliseconds

        System.out.print("\n\nData loaded in " + ((timeE - timeS) / 1000) + " seconds.\n\n"); // print out how many seconds it took

        menu(); // the user input part

        System.out.println("\nClosed");

    }

    static void menu() throws FileNotFoundException // used for getting user input.
    {
        Scanner reader = new Scanner(System.in);

        String tmpString = ""; // used as storage for user input
        int num = 0; // used as storage for user input

        do // loops asking the user what they want to do
        {
            System.out.println("\nWelcome to the user database. To look up a user type 1. To add a new user type 2. To exit type 3. \n");

            num = reader.nextInt();

            if (num == 1) // If looking up user
            {
                do
                {
                    System.out.print("\n\nPlease enter the SSN of the user you are looking for: ");

                    tmpString = reader.next();

                } while (!isNumeric(tmpString));

                userList.logSearch(tmpString); // look up user using skip list
            }

            else if (num == 2) // or if add new user
            {
                String ssn = "";
                String last = "";
                String first = "";


                System.out.println("\n\nPlease enter the SSN of the user you are adding: \n");

                ssn = reader.next();

                System.out.println("\n\nPlease enter the last name of the user you are adding: \n");

                last = reader.next();

                System.out.println("\n\nPlease enter the first name of the user you are adding: \n");

                first = reader.next();

                addUser(ssn, last, first);

                System.out.println("\n\nUser has been added: \n\n");

                userList.logSearch(ssn);

            }


        } while (num != 3); // loop until user enters 3 to close


        reader.close();

    }


    static void addUser(String s, String l, String f)
    {
        // 1. Create the new User object.
        User newUser = new User(s, l, f);

        // 2. Determine the random level for the new node.
        int newLevel = userList.randomLevel();

        // 3. Create the new Node with the correct level.
        Node newNode = new Node(newUser, newLevel);

        // 4. Add the new node to the skip list.
        userList.add(newNode, newLevel);
    }

    public static boolean isNumeric(String str) // checks to see if ssn is a number or not
    {
        try
        {
            Double.parseDouble(str); // if it can parse to a double then true
            return true;
        }

        catch (NumberFormatException e)
        {
            return false;
        }

    }
}
