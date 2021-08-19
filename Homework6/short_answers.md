# Question 1
The following method is supposed to remove all duplicates from an ArrayList of Integer:

	public static void removeDuplicates(ArrayList<Integer> list) {
    	int numValues = list.size();
    	for (int i = 0; i < numValues-1; i++) {
        	for (int j = i+1; j < numValues; j++) {
            		if (list.get(i) == list.get(j))
                		list.remove(j);
        		}
    	}
	}
	
Clearly identify all logical errors in this implementation. Derive the worst-case runtime complexity of this algorithm in Big-O notation if the list has n integers in it. When does this worst-case occur?
