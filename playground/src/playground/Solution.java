package playground;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String[] genes = new String[n];

		String[] genesItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			String genesItem = genesItems[i];
			genes[i] = genesItem;
		}

		int[] health = new int[n];

		String[] healthItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int healthItem = Integer.parseInt(healthItems[i]);
			health[i] = healthItem;
		}

		int s = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		List<Integer> healthScores = new ArrayList<>();
		for (int sItr = 0; sItr < s; sItr++) {

			String[] firstLastd = scanner.nextLine().split(" ");

			int first = Integer.parseInt(firstLastd[0]);

			int last = Integer.parseInt(firstLastd[1]);

			String d = firstLastd[2];
			healthScores
					.add(getHealth(Arrays.copyOfRange(genes, first, last), Arrays.copyOfRange(health, first, last), d));

		}
		System.out.println(Collections.min(healthScores) + " " + Collections.max(healthScores));

		scanner.close();
	}

	public static Integer getHealth(String[] genes, int[] health, String stripe) {
		Map<String, Integer> cacheGeneOccurencies = new HashMap<String, Integer>();
		return IntStream.range(0, genes.length).map(indexGene -> {
			System.out.println("<<<<<<<");
			System.out.println(genes[indexGene]+"  "+KMPSearch(genes[indexGene], stripe));
			System.out.println(cacheGeneOccurencies);
			System.out.println(">>>>>>>>");
			cacheGeneOccurencies.computeIfAbsent(genes[indexGene], key -> KMPSearch(key, stripe));
			Integer cHealth = cacheGeneOccurencies.get(genes[indexGene]) * health[indexGene];
			return cHealth;
		}).sum();
	}

	static int calcGenOccurency(String gene, String stripe) {
		int lastIndex = 0;
		int totalOccurencies = 0;
		while (lastIndex != -1) {
			lastIndex = stripe.indexOf(gene, lastIndex);
			if (lastIndex != -1) {
				totalOccurencies++;
				lastIndex += 1;
			}
		}
		return totalOccurencies;
	}

	static int calcGenOccurencyWithSplit(String gene, String stripe) {
		return (gene.split(Pattern.quote(stripe), -1).length) - 1;
	}

	static int KMPSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[]
		// array)
		computeLPSArray(pat, M, lps);

		int i = 0; // index for txt[]
		int res = 0;
		int next_i = 0;

		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				// When we find pattern first time,
				// we iterate again to check if there
				// exists more pattern
				j = lps[j - 1];
				res++;

				// We start i to check for more than once
				// appearance of pattern, we will reset i
				// to previous start+1
				if (lps[j] != 0)
					i = ++next_i;
				j = 0;
			}

			// mismatch after j matches
			else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				// Do not match lps[0..lps[j-1]] characters,
				// they will match anyway
				if (j != 0)
					j = lps[j - 1];
				else
					i = i + 1;
			}
		}
		return res;
	}

	static void computeLPSArray(String pat, int M, int lps[]) {
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar
				// to search step.
				if (len != 0) {
					len = lps[len - 1];

					// Also, note that we do not increment
					// i here
				} else // if (len == 0)
				{
					lps[i] = len;
					i++;
				}
			}
		}
	}
}
