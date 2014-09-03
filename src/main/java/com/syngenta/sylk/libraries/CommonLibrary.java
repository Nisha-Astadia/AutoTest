package com.syngenta.sylk.libraries;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syngenta.sylk.utils.TestDataLoader;

public class CommonLibrary {

	private static String generatedResourceFolder;
	private static boolean firstTime = true;

	public String getStackTrace(Throwable t) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}

	public static synchronized File newFileObject(String fileName) {

		try {

			if (generatedResourceFolder == null) {

				firstTime = false;

				String pathResourceFolder = (new File(".").getCanonicalPath())

				+ File.separator + "used_resources" + File.separator;

				File f = new File(pathResourceFolder);

				if (!f.exists()) {

					f.mkdir();

				} else {

					if (firstTime) {

						deleteFile(f);

					}

				}

				generatedResourceFolder = f.getAbsolutePath();

			}

		} catch (IOException e) {

			throw new SyngentaException("Could not create temp folder:"

			+ new CommonLibrary().getStackTrace(e));

		}

		return new File(generatedResourceFolder + File.separator + fileName);

	}

	private static void deleteFile(File f) {

		if (f.isDirectory()) {

			// directory is empty, then delete it

			if (f.list().length == 0) {

				f.delete();

				System.out.println("Directory is deleted : "

				+ f.getAbsolutePath());

			} else {

				// list all the directory contents

				String files[] = f.list();

				for (String temp : files) {

					// construct the file structure

					File fileDelete = new File(f, temp);

					// recursive delete

					deleteFile(fileDelete);

				}

				// check the directory again, if empty then delete it

				if (f.list().length == 0) {

					f.delete();

					System.out.println("Directory is deleted : "

					+ f.getAbsolutePath());

				}

			}

		} else {

			// if file, then delete it

			f.delete();

			System.out.println("File is deleted : " + f.getAbsolutePath());

		}

	}

	public List<Object[]> getTestDataAsObjectArray(String filename) {
		List<Object[]> data = new ArrayList<Object[]>();
		TestDataLoader loader = new TestDataLoader(filename);
		loader.loadData();
		HashMap<String, HashMap<String, String>> allData = loader.getTestData();

		Iterator<Map.Entry<String, HashMap<String, String>>> entries = allData
				.entrySet().iterator();
		HashMap<String, String> row = new HashMap<String, String>();
		while (entries.hasNext()) {

			Map.Entry<String, HashMap<String, String>> entry = entries.next();
			row = entry.getValue();
			data.add(new Object[]{row.get("Test_Description"),
					"Row_Number=" + entry.getKey(), row});
		}

		return data;
	}

	public void slowDown() {// do not use this, unless absolutely necessary

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

	}

}
