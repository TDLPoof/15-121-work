# Question 1
Assume that strings s1 and s2 have n characters each, where n ≥ 0.

Part A: What is the worst-case runtime complexity of s1.equals(s2) in big-O notation in terms of n? Explain your answer.
		
	The worst-case runtime of s1.equals(s2) is in O(n). This occurs when n > 1, as the program must iteratively check n characters for each string.

Part B: What is the best-case runtime complexity of s1.equals(s2) in big-O notation in terms of n? Explain your answer.
		
	The best-case runtime of s1.equals(s2) is in O(1). This occurs when n ≤ 1, as there is only one comparison to make.

# Question 2
What is the runtime complexity of the following code fragments in Big-O notation as functions of m and/or n? Explain each answer. (Your answers should represent the tightest/closest function for the code given.)
			
Fragment A: 

	int sum = 0;
	for (int i = 1; i <= n; i+=2)
    		for (int j = 1; j <= n; j+=3)
        		sum += (i+j);
						
Answer A:

	O(n²), as there are two loops dependent on the size of n.

Fragment B:
			
	int sum = 0;
	for (int i = 1; i <= 5; i++)
    		for (int j = 1; j <= n; j++)
        		sum += (i+j);

Answer B:
			
	O(n), as there is only one loop dependent on the size of n.

Fragment C:
			
	int sum = 0;
	for (int i = 1; i <= m; i*=2)
    		for (int j = 1; j <= n; j++)
        		sum += (i+j);
						
Answer C:
			
	O(n log m), as the second loop is dependent on the size of n and the first loop is dependent on the size of log m. (when m doubles, i increases once more)

Fragment D:
			
	int sum1 = 0;
	for (int i = 1; i <= n; i++)
    		sum1 += i;
	int sum2 = 0;
	for (int j = 1; j <= n*n; j++)
    		sum2 += j;
					
Answer D:
			
	O(n²), as the second for loop runs in O(n²) and O(n²) dominates O(n).

# Question 3
Consider the following Java method that returns true if an array of n integers has two adjacent values that are the same, false otherwise:

	public static boolean hasAdjacentDuplicates(int[] a) {
    	for (int i = 0; i < a.length-1; i++)
        	if (a[i] == a[i+1])
	   	       	return true;
    	return false;
	}

Part A: What is the runtime complexity of this method using Big-O notation in the worst case? Explain your answer.

	The worst-case runtime complexity of this method is in O(a.length), as there is a single loop running (a.length) times, and array lookup is in constant time.

Part B: What is the runtime complexity of this method using Big-O notation in the best case? Explain your answer.

	The best-case runtime complexity of this method is in O(1), when a.length == 2. In this case, the loop runs once and is the same as running the statement without a loop.

# Question 4
Suppose we have an ArrayList of n Integer objects, sorted in increasing order. Consider the following algorithms for removing a particular Integer value from that ArrayList. It is already known that this value is in the ArrayList. Identify the best and worst case running times (using Big O) for each algorithm. Explain each of your answers.

Algorithm 1: Perform a linear search from the beginning of the list to the end. When the element is found, call ArrayList's remove(index).

	Worst Case: O(n), when the element is at the end of the list.
	Best Case: O(1), when the element is at the beginning of the list.

Algorithm 2: Perform a linear search from the end of the list to the beginning. When the element is found, call ArrayList's remove(index).

	Worst Case: O(n), when the element is at the beginning of the list.
	Best Case: O(1), when the element is at the end of the list.

Algorithm 3: Perform a binary search on the array. When the element is found, call ArrayList's remove(index).

	Worst Case: O(log n), when the element is at the beginning or end of the list.
	Best Case: O(1), when the element is at the exact center of the list.
