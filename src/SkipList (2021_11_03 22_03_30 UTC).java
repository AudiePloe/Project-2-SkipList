import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class SkipList // crates and maintains a skip list
{
    private static final int MAX_LEVEL = 32; // Maximum number of levels
    private int level = 0; // Current highest level of the list

    Node head = new Node(null, MAX_LEVEL); // the beginning of the list


    void compile() throws IOException // sets up and reads from file the users and nodes
    {
        readFromFile();

    }

    public void readFromFile() throws IOException // reads users from file and adds to list
    {
        RandomAccessFile database = new RandomAccessFile("Users.txt", "rw");
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

                int newLevel = randomLevel();
                Node newNode = new Node(newUser, newLevel);

                add(newNode, newLevel);

                if (i % 10000 == 0) // for every 10000 users print a .
                    System.out.print(".");


            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

    public void add(Node newNode, int newLevel)
    {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        // Find the insertion point for each level.
        // This loop populates the update array up to the current highest level.
        for (int i = level; i >= 0; i--)
        {
            while (current.forward[i] != null && current.forward[i].user.SSN.compareTo(newNode.user.SSN) < 0)
            {
                current = current.forward[i];
            }
            update[i] = current;
        }

        // Determine the new node's random level.
        //int newLevel = randomLevel();

        // If the new node's level is higher than the current list's level,
        // initialize the update array with the head node for these new levels.
        if (newLevel > level)
        {
            for (int i = level + 1; i <= newLevel; i++)
            {
                update[i] = head;
            }
            level = newLevel; // Update the list's max level.
        }

        // Now, insert the new node at all of its levels, using the updated
        // and correctly populated update array.
        for (int i = 0; i <= newLevel; i++)
        {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public int randomLevel()
    {
        int level = 0;
        while (Math.random() < 0.5 && level < MAX_LEVEL)
        {
            level++;
        }
        return level;
    }


    public Node logSearch(String ssn) {
        Node current = head;

        // Start at the top level and move down
        for (int i = level; i >= 0; i--)
        {
            while (current.forward[i] != null && current.forward[i].user.SSN.compareTo(ssn) < 0)
            {
                current = current.forward[i];
            }
        }

        // Check if the next node is the one we are looking for
        current = current.forward[0];
        if (current != null && current.user.SSN.equals(ssn))
        {
            System.out.println(current);
            return current;
        }

        System.out.println("User not found");
        return null; // Not found
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
