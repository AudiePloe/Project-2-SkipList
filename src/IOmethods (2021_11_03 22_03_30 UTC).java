import java.io.IOException;
import java.io.RandomAccessFile;

public class IOmethods // taken from the provided files from project 1
{
	public void writeString(String s, RandomAccessFile out) throws IOException
	{
		for (int i = 0; i < s.length(); i++)
			out.writeChar(s.charAt(i));
	}

	public String readString(int len, RandomAccessFile in) throws IOException
	{
		String s = "";
		for (int i = 0; i < len; i++)
			s += in.readChar();
		return s;
	}

	public static String readLine() throws IOException
	{
		int ch;
		String s = "";
		while (true)
		{
			ch = System.in.read();
			if (ch == -1 || (char) ch == '\n') // end of file or end of line;
				break;
			else if ((char) ch != '\r') // ignore carriage return;
				s = s + (char) ch;
		}
		return s;
	}
}
