package com.akefirad.javainterop;

import java.io.File;
import java.io.IOException;

import plaid.runtime.PlaidException;

public class PlaidHelper
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
	
	public static void throwException(String message) throws Exception
	{
		throw new PlaidException(message);
	}
}
