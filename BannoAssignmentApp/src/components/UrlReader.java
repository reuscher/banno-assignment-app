package components;

import java.net.*;
import java.io.*;

/**
 * Uses an inputStreamReader to read HTML from desired URL
 * 
 * @author Micah Reuscher
 *
 */
public class UrlReader {
	private String urlString;
	private String urlSource;
	
	//------------------------------------------------------
	// Default Constructor
	//------------------------------------------------------
	/**
	 * @param aString
	 * @throws Exception
	 */
	public UrlReader(String aString) throws Exception {
		urlString = aString;
		
		//Create URL
		URL url = new URL(urlString);
		
		//Open URL stream
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String line;
		StringBuilder sb = new StringBuilder();
		
		// String builder to create 'urlSource'
		while ((line = reader.readLine()) != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			}
		urlSource = sb.toString();
		reader.close();
		}
	
	public String getSource() {
		return urlSource;
		}

	}
