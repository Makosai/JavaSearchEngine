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
		String[] queries = query.split(" "); // <- Split by spaces | Split by comma, but group quotations -> query.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		System.out.println(queries.toString());
		
		Set<Data.FileData> matches = new HashSet<Data.FileData>();

		// If the queries doesn't contain a single element
		// then return an empty Set.
		if(!(queries.length > 0)) {
			System.out.print(0);
			return matches;
		}
		System.out.print(1);
		
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
						System.out.println(file.name + "|" + matcher.toLowerCase());
					}
				}
				break;
				
			// Loop through all files
			// Loop through all queries for each file
			// If a query isn't in a file, break
			// If all queries are in a file, add it to the matches set
			case AND:
				for(Data.FileData file : files) {
					for(int i = 0; i < queries.length; i++) {
						if(file.data.toLowerCase().contains(queries[i].toLowerCase())) {
							if(i == queries.length - 1) {
								matches.add(file);
							}
						} else {
							break;
						}
					}
				}
				break;
			
			
			case PHRASE:
				break;
		}
		
		return matches;
	}

}
