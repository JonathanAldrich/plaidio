package plaid.auxiliary;

import java.io.File;
import java.io.IOException;

public class IOHelper
{
	public static boolean IsValidPath(File file)
	{
		try
		{
			file.getCanonicalPath();
			return true;
		}
		catch (IOException e)
		{
			return false;
		}
	}
}
