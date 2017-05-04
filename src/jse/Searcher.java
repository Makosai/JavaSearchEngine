package jse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Searcher {

	enum Mode {
		OR,
		AND,
		PHRASE
	}
	
	public static Set<Data.FileData> Search(Mode mode, List<Data.FileData> files, String query) {
		String[] queries = query.split(" ");
		
		Set<Data.FileData> matches = new HashSet<Data.FileData>();
		
		// If the queries doesn't contain a single element
		// then return an empty Set.
		if(!(queries.length > 0)) {
			return matches;
		}
		
		switch(mode) {
		// Loop through all files
		// Loop through all queries for each file
		// Add to matches set and break on the first query match
		case OR:
			for(Data.FileData file : files) {
				for(String matcher : queries) {
					if(file.data.toLowerCase().contains(matcher.toLowerCase())) {
						matches.add(file);
						break;
					}
				}
			}
			break;
			
		// Loop through all files
		// Loop through all queries for each file
		// If a query isn't in a file, break
		// If all queries are in a file, add it to the matches set
		case AND:
			break;
		
		// Loop through all files
		// Check the initial query string and compare it to each file.
		// If the query itself is present, and not the split version, then it's an exact phrase.
		case PHRASE:
			break;
	}
		
		return matches;
	}

}
