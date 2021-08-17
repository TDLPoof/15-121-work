# Question 1
Assume that strings s1 and s2 have n characters each, where n ≥ 0.

	Part A: What is the worst-case runtime complexity of s1.equals(s2) in big-O notation in terms of n? Explain your answer.
		Answer: The worst-case runtime of s1.equals(s2) is in O(n). This occurs when n > 1, as the program must iteratively check n characters for each string.

	Part B: What is the best-case runtime complexity of s1.equals(s2) in big-O notation in terms of n? Explain your answer.
		Answer: The best-case runtime of s1.equals(s2) is in O(1). This occurs when n ≤ 1, as there is only one comparison to make.

# Question 2
What is the runtime complexity of the following code fragments in Big-O
			notation as functions of m and/or n? Explain each answer. (Your answers
			should represent the tightest/closest function for the code given.)
			
			Fragment A: 
				int sum = 0;
				for (int i = 1; i <= n; i+=2)
    				for (int j = 1; j <= n; j+=3)
        				sum += (i+j);
			Answer A: O(n²), as there are two loops dependent on the size of n.

			Fragment B:
				int sum = 0;
				for (int i = 1; i <= 5; i++)
    				for (int j = 1; j <= n; j++)
        				sum += (i+j);
			Answer B: O(n), as there is only one loop dependent on the size of n.

			Fragment C:
				int sum = 0;
				for (int i = 1; i <= m; i*=2)
    				for (int j = 1; j <= n; j++)
        				sum += (i+j);
			Answer C: O(n log m), as the second loop is dependent on the size of n and the first loop is dependent on the size of log m. (when m doubles, i increases once more)

			Fragment D:
				int sum1 = 0;
				for (int i = 1; i <= n; i++)
    				sum1 += i;
				int sum2 = 0;
				for (int j = 1; j <= n*n; j++)
    				sum2 += j;
			Answer D: O(n²), as the second for loop runs in O(n²) and O(n²) dominates O(n).
