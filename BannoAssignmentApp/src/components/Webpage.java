package components;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Webpage class contains values specific to self after extracting data from URL
 *
 * @author Micah Reuscher
 * 
 */
public class Webpage {
	
	//-------------------------------------------------------
	//Instance variables
	//-------------------------------------------------------
	private String url;
	private Map.Entry<Character, Integer> topEntry;
	private Map.Entry<Character, Integer> secondEntry;
	private Map.Entry<Character, Integer> thirdEntry;
	private int productsOffered;
	private int stringCount;
	private int pngCount;
	private Character char1;
	private Character char2;
	private Character char3;
	private int char1Count;
	private int char2Count;
	private int char3Count;
	private String twitterHandle;
	private String sourceStr;
	
	//-------------------------------------------------------
	// Constructors
	//-------------------------------------------------------
	/**
	 * Default constructor
	 * @throws Exception 
	 */
	public Webpage(String aUrl) throws Exception {
		url = aUrl;
		topEntry = null;
		secondEntry = null;
		thirdEntry = null;
		sourceStr = new UrlReader(url).getSource();
		productsOffered = 0;
		stringCount = 0;
		pngCount = 0;
		char1Count = 0;
		char2Count = 0;
		char3Count = 0;
		char1 = null;
		char2 = null;
		char3 = null;
		twitterHandle = "";
		this.extractData();
	}
			
	//-------------------------------------------------------
	// Class Methods
	//-------------------------------------------------------
	public String getUrl() {
		return url;
	}
	public int getProductCount() {
		return productsOffered;
	}
	public int getStringCount() {
		return stringCount;
	}
	public int getPngCount() {
		return pngCount;
	}
	public Character getChar1() {
		return char1;
	}
	public Character getChar2() {
		return char2;
	}
	public Character getChar3() {
		return char3;
	}
	public int getChar1Count() {
		return char1Count;
	}
	public int getChar2Count() {
		return char2Count;
	}
	public int getChar3Count() {
		return char3Count;
	}
	public String getTwitterHandle() {
		return twitterHandle;
	}
	
	private void extractData() throws Exception {
		String line;
		BufferedReader reader = new BufferedReader(new StringReader(sourceStr));

		while((line = reader.readLine()) != null) {
			String findPng = ".png";
			pngCount += (line.split(findPng, -1).length-1);
		
			String findStr = "financial institutions";
			stringCount += (line.split(findStr, -1).length-1);
		
			String itemVar = "<div class=\"flex-item\">";
			productsOffered += (line.split(itemVar,-1).length-1);
		
			// Find Twitter Handle
			if (line.contains("twitter:site")) {
				String[] parts = line.split("=");
				String aString = parts[parts.length-1];
				twitterHandle = aString.replaceAll("\"", "").replace(">","");
			}
		}
		
		// Regular expression to clean string for map
		sourceStr = sourceStr.replaceAll("[^a-zA-Z0-9_-]", "");
		char[]char_array = sourceStr.toCharArray();
		char ch;
		Map<Character,Integer> charCounter=new HashMap<Character,Integer>();
		
		// Iterate over sourceStr and create map
		for(int i=0;i<sourceStr.length();i++) {
			ch = char_array[i];
			if(charCounter.containsKey(ch)) {
				charCounter.put(ch, charCounter.get(ch)+1);
	    		} 
				else {
					charCounter.put(ch, 1);
	            	}
	    		}
		
		// Iterate over map to grab three most frequent entries
 		for (Entry<Character, Integer> entry : charCounter.entrySet()) {
			if (topEntry== null || entry.getValue().compareTo(topEntry.getValue()) > 0) {
				topEntry = entry;
			}
			else if (secondEntry == null || entry.getValue().compareTo(secondEntry.getValue()) > 0) {
				secondEntry = entry;
			}
			else if (thirdEntry == null || entry.getValue().compareTo(thirdEntry.getValue()) > 0) {
				thirdEntry = entry;
			}
 		}
 		
 		// Set values
 		char1 = topEntry.getKey();
 		char1Count = topEntry.getValue();
 		char2 = secondEntry.getKey();
 		char2Count = secondEntry.getValue();
 		char3 = thirdEntry.getKey();
 		char3Count = thirdEntry.getValue();
	}
}
