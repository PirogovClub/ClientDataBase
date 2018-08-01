 package utils;

import java.util.List;
import java.util.Map;

public class PrintOuts {

	public static void doMap(Map<String, String> resultMap, String CommentString) {
		System.out.println("Printing Map with comment:"+ CommentString);
		doMap(resultMap);
	}
	
	
	
	public static void doListOfMap(List<Map<String,String>> varToPrint) {
		for(Map<String,String> mapElement : varToPrint) {
			for (Map.Entry<String, String> pair : mapElement.entrySet()) {
				System.out.print(pair.getKey()+":"+pair.getValue()+"|");
			}
			System.out.println("");
		}
		
	}

	public static void doMap(Map<?,?> resultMap) {
		// TODO Auto-generated method stub
		for(Map.Entry<?, ?> entry : resultMap.entrySet()) {
			String key = (String) entry.getKey();
		    String value = (String) entry.getValue();
		    System.out.println("For key "+ key +" result is "+ value);
		    // do what you have to do here
		    // In your case, another loop.
		}
	}
	
	public static void doString(String value) {
		System.out.println(value);
	}


	
}
