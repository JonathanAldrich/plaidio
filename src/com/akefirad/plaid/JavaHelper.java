package com.akefirad.plaid;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidMethod;
import plaid.runtime.Util;

public class JavaHelper {
	public static boolean isValidPath(File file) {
		try {
			file.getCanonicalPath();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static String getRootPath() {
		return File.listRoots()[0].getAbsolutePath();
	}

	public static boolean hasParent(File file) {
		return (file.getParent() == null) ? false : true;
	}

	public static boolean isRootPath(File file) {
		return (file.getAbsoluteFile().getParentFile() == null) ? true : false;
	}

	public static File getRootFile(File file) {
		if (file.isAbsolute() == false)
			throw new InvalidParameterException("File should be absolute!");

		if (isRootPath(file))
			return file;
		else
			return getRootFile(file.getParentFile());
	}

	public static boolean isCanonicalPath(File file) throws IOException {
		return (file.getAbsolutePath().equals(file.getCanonicalPath())) ? true
				: false;
	}

	public static void debug(Object object) {
		System.out.println(object.toString());
	}

	public static void throwException(String message) throws Exception {
		throw new PlaidException(message);
	}

	public static Object runMethodInTryBlock(PlaidMethod tryBlock,
			PlaidMethod catchBlock, String exception) throws Exception {
		Object result = null;
		try {
			result = tryBlock.invoke(Util.unit());
		} catch (Exception ex) {
			if (exception.length() == 0
					|| ex.getClass().getName().equals(exception))
				result = catchBlock.invoke(Util.unit());
			else
				throw ex;
		}
		return result;
	}

	public static void buildStateCheckers(String packagePath)
			throws IOException {
		File packageFolder = new File(packagePath);
		if (packageFolder.exists() && packageFolder.isDirectory()) {
			File statecheckersFolder = new File(
					packageFolder.getAbsolutePath(), "statecheckers");
			if (statecheckersFolder.exists() == false) {
				statecheckersFolder.mkdir();
			} else if (statecheckersFolder.isDirectory() == false) {
				statecheckersFolder.delete();
				statecheckersFolder.mkdir();
			}

			File[] files = statecheckersFolder.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}

			ArrayList<File> states = getStateFiles(packageFolder);

			if (states.size() > 0) {
				String rootPackageName = packageFolder.getName();
				String rootPackagePath = packageFolder.getAbsolutePath();
				File packageplaidFile = new File(
						statecheckersFolder.getAbsoluteFile(), "package.plaid");
				packageplaidFile.createNewFile();
				StringBuilder sb = new StringBuilder();
				sb.append("package ").append(rootPackageName).append(".")
						.append(statecheckersFolder.getName()).append(";\n\n");
				for (int i = 0; i < states.size(); i++) {
					File f = states.get(i);
					String stateQualifiedName = f.getAbsolutePath()
							.replace(rootPackagePath, rootPackageName)
							.replace(".plaid", "").replace("\\", ".");
					sb.append("import ").append(stateQualifiedName)
							.append(";\n");
				}

				sb.append("\n");

				for (int i = 0; i < states.size(); i++) {
					File f = states.get(i);
					String state = f.getName().replace(".plaid", "");
					sb.append("method immutable Boolean is").append(state)
							.append("(object) { match (object) { case ")
							.append(state)
							.append(" { true; } default { false; } }; }\n");
				}

				sb.append("\n");

				for (int i = 0; i < states.size(); i++) {
					File f = states.get(i);
					String state = f.getName().replace(".plaid", "");
					sb.append("method immutable Boolean debugIs")
							.append(state)
							.append("(object, immutable Boolean verbose) { ifElse (is")
							.append(state)
							.append("(object)) { if (verbose) { printLine(\"The object is ")
							.append(state)
							.append("\"); }; true; } /*else*/ { if (verbose) { printLine(\"The object is NOT ")
							.append(state).append("\"); }; false; }; }\n");
				}

				FileWriter fw = new FileWriter(packageplaidFile);
				fw.append(sb.toString());
				fw.flush();
				fw.close();
			}
		}
	}

	public static ArrayList<File> getStateFiles(File packageFolder)
			throws IOException {
		ArrayList<File> states = new ArrayList<File>();
		if (packageFolder.exists() && packageFolder.isDirectory()) {
			// List plaid files
			File[] files = packageFolder.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					String n = file.getName();
					if (file.isFile() && n.endsWith(".plaid")) {
						boolean isPackage = n.equals("package.plaid");
						boolean isMain = n.equals("main.plaid");
						boolean isInitializing = n.startsWith("i_");
						return ((isPackage || isMain || isInitializing) ? false
								: true);
					} else {
						return false;
					}
				}
			});
			states.addAll(Arrays.asList(files));

			// List sub-folders
			File[] folders = packageFolder.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					if (file.isDirectory()) {
						return true;
					} else {
						return false;
					}
				}
			});

			// Get StateFiles for each sub-folder and add them into state list
			for (int i = 0; i < folders.length; i++) {
				states.addAll(getStateFiles(folders[i]));
			}

			return states;
		} else {
			throw new IOException("The argument is not a valid folder.");
		}
	}

	public static byte castBigIntegerToByte(BigInteger value) {
		return (byte) value.intValue();
	}
	
	public static byte[] generateRandomBytes(int size)
	{
		byte[] bytes = new byte[size];
		Random random = new Random();
		random.nextBytes(bytes);
		return bytes;
	}

	public static byte[] generateRandomBytes(int size, int min, int max)
	{
		byte[] bytes = new byte[size];
		Random random = new Random();
		for (int i = 0; i < bytes.length; i++)
		{
			int b = random.nextInt();
			while (b < min || b > max)
				b = random.nextInt();
			bytes[i] = (byte)b;
		}
		return bytes;
	}
}
