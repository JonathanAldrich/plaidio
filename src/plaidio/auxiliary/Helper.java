package plaidio.auxiliary;

import java.io.File;
import java.io.IOException;

public class Helper
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
