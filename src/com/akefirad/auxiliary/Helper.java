package com.akefirad.auxiliary;

import java.io.File;
import java.io.IOException;

public class Helper
{
	public static boolean isValidPath(File file)
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
	
	public static void debug(Object object)
	{
		System.out.println(object.toString());
	}
}
