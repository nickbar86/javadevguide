package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Play1 {
	public static void main(String[] args) {
		Play1 p = new Play1();
		List<List<Integer>> arr = new ArrayList<>();

		List<Integer> col1 = Arrays.asList(9718805, 60013003, 5103628, 85388216, 21884498, 38021292, 73470430,
				31785927);
		List<Integer> col2 = Arrays.asList(69999937, 71783860, 10329789, 96382322, 71055337, 30247265, 96087879,
				93754371);
		List<Integer> col3 = Arrays.asList(79943507, 75398396, 38446081, 34699742, 1408833, 51189, 17741775, 53195748);
		List<Integer> col4 = Arrays.asList(79354991, 26629304, 86523163, 67042516, 54688734, 54630910, 6967117,
				90198864);
		List<Integer> col5 = Arrays.asList(84146680, 27762534, 6331115, 5932542, 29446517, 15654690, 92837327,
				91644840);
		List<Integer> col6 = Arrays.asList(58623600, 69622764, 2218936, 58592832, 49558405, 17112485, 38615864,
				32720798);
		List<Integer> col7 = Arrays.asList(49469904, 5270000, 32589026, 56425665, 23544383, 90502426, 63729346,
				35319547);
		List<Integer> col8 = Arrays.asList(20888810, 97945481, 85669747, 88915819, 96642353, 42430633, 47265349,
				89653362);
		List<Integer> col9 = Arrays.asList(55349226, 10844931, 25289229, 90786953, 22590518, 54702481, 71197978,
				50410021);
		List<Integer> col10 = Arrays.asList(9392211, 31297360, 27353496, 56239301, 7071172, 61983443, 86544343,
				43779176);
		// List<Integer> col3 = Arrays.asList(9, 10, 11, 12);
		// List<Integer> col4 = Arrays.asList(13, 14, 15, 16);
		arr.add(col1);
		arr.add(col2);
		arr.add(col3);
		arr.add(col4);
		arr.add(col5);
		arr.add(col6);
		arr.add(col7);
		arr.add(col8);
		arr.add(col9);
		arr.add(col10);
		// System.out.println(p.diagonalDifference(arr));
		// int[] arr1 = { -4, 3, -9, 0, 4, 1 };
		// p.plusMinus(arr1);
		p.matrixRotation(arr, 40);
		int[][] container = new int[3][3];
		container[0][0] = 2;
		container[0][1] = 1;
		container[0][2] = 0;

		container[1][0] = 2;
		container[1][1] = 1;
		container[1][2] = 1;

		container[2][0] = 1;
		container[2][1] = 0;
		container[2][2] = 1;
		organizingContainers(container);
		// System.out.println(biggerIsGreater("abc"));
		// System.out.println(fibonacciRec(4));
		// System.out.println((29%30>0)?(30-(29%30)):29);
		//System.out.println(chocolateFeast(6, 2, 2));
		System.out.println("ASDSDA"+withRegEx("caaab","aa"));
		System.out.println(maing2("abaab"));
	}

	public static int diagonalDifference(List<List<Integer>> arr) {
		int counter = 0;
		int fdiagonal = 0;
		int sdigonal = 0;
		for (List<Integer> cols : arr) {
			fdiagonal += cols.get(counter);
			sdigonal += cols.get(cols.size() - (1 + counter));
			counter++;
		}
		// return Math.abs(fdiagonal-sdigonal);
		AtomicInteger counter2 = new AtomicInteger(0);
		return Math.abs(arr.stream().flatMapToInt(childList -> childList.stream().mapToInt(Integer::intValue)).reduce(0,
				(subtotal, col) -> {
					int currentpoint = counter2.getAndIncrement();
					System.out.println(currentpoint + "    " + col + "   " + (currentpoint % (arr.size() + 1) == 0)
							+ "   " + (currentpoint % (arr.size() - 1) == 0));
					if (currentpoint % (arr.size() + 1) == 0 && currentpoint % (arr.size() - 1) == 0) {
						return 0;
					}
					if (currentpoint % (arr.size() + 1) == 0) {
						return subtotal + col;
					}
					if (currentpoint % (arr.size() - 1) == 0) {
						return subtotal - col;
					}
					return 0;
				}));
	}

	static void plusMinus(int[] arr) {
		int numberOfPos = 0;
		int numberOfNeg = 0;
		int numberOfZer = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0)
				numberOfPos++;
			else if (arr[i] < 0)
				numberOfNeg++;
			else
				numberOfZer++;
		}
		System.out.println(String.format("%.6f", (float) numberOfPos / arr.length));
		System.out.println(String.format("%.6f", (float) numberOfNeg / arr.length));
		System.out.println(String.format("%.6f", (float) numberOfZer / arr.length));
	}

	// Complete the matrixRotation function below.
	static void matrixRotation(List<List<Integer>> matrix, int r) {
		List<List<Integer>> queues = generateQueuesFromArray(matrix);
		int[][] suffledMatrix = createSuffledArrayFromQueues(matrix.size(), matrix.get(0).size(), r, queues);
		printArray(suffledMatrix);
	}

	private static void printArray(int[][] suffledMatrix) {
		for (int i = 0; i < suffledMatrix.length; i++) {
			for (int j = 0; j < suffledMatrix[i].length; j++) {
				System.out.print(suffledMatrix[i][j] + " ");
			}
			System.out.println("");
		}
	}

	private static int[][] createSuffledArrayFromQueues(int matrixRows, int matrixCols, int r,
			List<List<Integer>> queues) {
		int[][] suffledMatrix = new int[matrixRows][matrixCols];
		AtomicInteger counter = new AtomicInteger(0);
		queues.forEach(queue -> {
			int queNo = counter.getAndIncrement();
			AtomicInteger suffledCounterStart = new AtomicInteger(queue.size() > r ? r : r % queue.size());
			int numberOfRows = matrixRows - queNo;
			int numberOfCols = matrixCols - queNo;
			populateUpperRow(suffledMatrix, queue, queNo, suffledCounterStart, numberOfCols);
			populateRightColmn(suffledMatrix, queue, queNo, suffledCounterStart, numberOfRows, numberOfCols);
			populateLowerRow(suffledMatrix, queue, queNo, suffledCounterStart, numberOfRows, numberOfCols);
			populateLeftColumn(suffledMatrix, queue, queNo, suffledCounterStart, numberOfRows);
		});
		return suffledMatrix;
	}

	private static void populateLeftColumn(int[][] suffledMatrix, List<Integer> queue, int queNo,
			AtomicInteger suffledCounterStart, int numberOfRows) {
		IntStream.range(queNo + 1, numberOfRows - 1).boxed().sorted(Collections.reverseOrder()).forEach(i -> {
			suffledMatrix[i][queNo] = queue.get((suffledCounterStart.getAndIncrement()) % queue.size());
		});
	}

	private static void populateLowerRow(int[][] suffledMatrix, List<Integer> queue, int queNo,
			AtomicInteger suffledCounterStart, int numberOfRows, int numberOfCols) {
		IntStream.range(queNo, numberOfCols - 1).boxed().sorted(Collections.reverseOrder()).forEach(i -> {
			suffledMatrix[numberOfRows - 1][i] = queue.get((suffledCounterStart.getAndIncrement()) % queue.size());
		});
	}

	private static void populateRightColmn(int[][] suffledMatrix, List<Integer> queue, int queNo,
			AtomicInteger suffledCounterStart, int numberOfRows, int numberOfCols) {
		IntStream.range(queNo + 1, numberOfRows).forEach(i -> {
			suffledMatrix[i][numberOfCols - 1] = queue.get((suffledCounterStart.getAndIncrement()) % queue.size());
		});
	}

	private static void populateUpperRow(int[][] suffledMatrix, List<Integer> queue, int queNo,
			AtomicInteger suffledCounterStart, int numberOfCols) {
		IntStream.range(queNo, numberOfCols).forEach(i -> {
			suffledMatrix[queNo][i] = queue.get((suffledCounterStart.getAndIncrement()) % queue.size());
		});
	}

	private static List<List<Integer>> generateQueuesFromArray(List<List<Integer>> matrix) {
		int numperOfQueues = Math.min(matrix.size(), matrix.get(0).size()) / 2;
		List<List<Integer>> queues = (List<List<Integer>>) IntStream.range(0, numperOfQueues).mapToObj(queNo -> {
			List<Integer> queue = new ArrayList<Integer>();
			int numberOfRows = matrix.size() - queNo;
			int numberOfCols = matrix.get(0).size() - queNo;
			IntStream.range(queNo, numberOfCols).forEach(i -> {
				queue.add(matrix.get(queNo).get(i));
			});
			IntStream.range(queNo + 1, numberOfRows).forEach(i -> {
				queue.add(matrix.get(i).get(numberOfCols - 1));
			});

			IntStream.range(queNo, numberOfCols - 1).boxed().sorted(Collections.reverseOrder()).forEach(i -> {
				queue.add(matrix.get(numberOfRows - 1).get(i));
			});
			IntStream.range(queNo + 1, numberOfRows - 1).boxed().sorted(Collections.reverseOrder()).forEach(i -> {
				queue.add(matrix.get(i).get(queNo));
			});
			return queue;
		}).collect(Collectors.toList());
		return queues;
	}

	private static List<List<Integer>> createQueues(List<List<Integer>> matrix) {
		List<List<Integer>> queues = new ArrayList<>();
		int numperOfQueues = Math.min(matrix.size(), matrix.get(0).size()) / 2;
		IntStream.range(0, numperOfQueues).forEach(i -> queues.add(new ArrayList<>()));
		return queues;
	}

	static String organizingContainers(int[][] container) {

		for (int row = 0; row < container.length; row++) {
			for (int col = row; col < container[row].length; col++) {
				if (container[row][col] != 0 && row != col) {
					for (int repeat = container[row][col]; repeat > 0; repeat--) {
						if (container[col][row] != 0) {
							container[row][col]--;
							container[col][row]--;
							container[col][col]++;
							container[row][row]++;
						} else {
							for (int swappingContainer = 0; swappingContainer < container[col].length; swappingContainer++) {
								if (swappingContainer != col && container[col][swappingContainer] != 0) {
									container[row][col]--;
									container[col][col]++;
									container[col][swappingContainer]--;
									container[row][swappingContainer]++;
									break;
								}
							}
						}
					}
				}
			}
		}

		for (int row = 0; row < container.length; row++) {
			for (int col = 0; col < container[row].length; col++) {
				if (row != col && container[row][col] != 0) {
					return "Impossible";
				}
			}
		}

		return "Possible";
	}

	// dkhc
	static String biggerIsGreater(String w) {
		TreeSet<String> set = new TreeSet<>();
		permutation("", w, set);
		if (!set.isEmpty()) {
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				if (it.next().equals(w))
					return it.next();
			}
		}
		System.out.println();
		return "no answer";
	}

	private static void permutation(String prefix, String str, Set<String> set) {
		int n = str.length();
		if (n == 0) {
			System.out.println(prefix);
			set.add(prefix);
		} else {
			IntStream.range(0, n).forEach(i -> {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), set);
			});
		}

	}

	private static int fibonacciRec(int n) {
		if (n <= 1)
			return 1;
		else
			return fibonacciRec(n - 1) + fibonacciRec(n - 2);
	}

	// Complete the timeInWords function below.
	static String timeInWords(int h, int m) {
		Map<Integer, String> timeToWordMapping = new HashMap<>();
		timeToWordMapping.put(1, "one");
		timeToWordMapping.put(2, "two");
		timeToWordMapping.put(3, "three");
		timeToWordMapping.put(4, "four");
		timeToWordMapping.put(5, "five");
		timeToWordMapping.put(6, "six");
		timeToWordMapping.put(7, "seven");
		timeToWordMapping.put(8, "eight");
		timeToWordMapping.put(9, "nine");
		timeToWordMapping.put(10, "ten");
		timeToWordMapping.put(11, "eleven");
		timeToWordMapping.put(12, "twelve");
		timeToWordMapping.put(13, "thriteen");
		timeToWordMapping.put(14, "fourteen");
		timeToWordMapping.put(15, "quarter");
		timeToWordMapping.put(16, "sixteen");
		timeToWordMapping.put(17, "seventeen");
		timeToWordMapping.put(18, "eighteen");
		timeToWordMapping.put(19, "ninteen");
		timeToWordMapping.put(20, "trwenty");
		timeToWordMapping.put(21, "trwentyone");
		timeToWordMapping.put(22, "trwentytwo");
		timeToWordMapping.put(23, "trwentythree");
		timeToWordMapping.put(24, "trwentyfour");
		timeToWordMapping.put(25, "trwentyfive");
		timeToWordMapping.put(26, "trwentysix");
		timeToWordMapping.put(27, "trwentyseven");
		timeToWordMapping.put(28, "trwentyeight");
		timeToWordMapping.put(29, "trwentynine");
		timeToWordMapping.put(30, "half");
		String pastOrTo = m > 30 ? "to" : " past ";
		if (m == 0)
			return timeToWordMapping.get(h) + " o' clock";
		else if (m % 15 == 0)
			return timeToWordMapping.get(m) + pastOrTo + " " + timeToWordMapping.get(h);
		else
			return timeToWordMapping.get(m % 3) + (m > 1 ? " minutes" : "minute") + pastOrTo + timeToWordMapping.get(h);
	}

	// Complete the chocolateFeast function below.
	static int chocolateFeast(int n, int c, int m) {
		int totaNumberOfChocs = n / c;
		return chockWrapperRec(totaNumberOfChocs, 0, m);
	}

	static int chockWrapperRec(int choc, int wrapper, int wrapperCost) {
		if (wrapperCost > (wrapper + choc))
			return choc;
		return choc + chockWrapperRec((choc + wrapper) / wrapperCost, (choc + wrapper) % wrapperCost, wrapperCost);
	}

	// Complete the gridSearch function below.
	static String gridSearch(String[] G, String[] P) {
		Map<String, List<Integer>> arrayIndex = generateStringIndexes(G);
		List<String> matchingIndexKeys = searchAllFirstSearchLineOccurencies(P, arrayIndex);
		if (!matchingIndexKeys.isEmpty()) {
			List<Integer> fittingRowNumberIndexes = filterIndexMatchingNumberOfRows(G, P, arrayIndex, matchingIndexKeys);
			if (fittingRowNumberIndexes.size() == 0)
				return "NO";
			List<List<Integer>> matchingColIndexes = fittingRowNumberIndexes.stream().map(ind -> {
				return substringIndexOccurences(G[ind], P[0]);
			}).collect(Collectors.toList());
			return IntStream.range(0, fittingRowNumberIndexes.size()).filter(i -> {
				return isSubColumnArray(Arrays.copyOfRange(G, fittingRowNumberIndexes.get(i), fittingRowNumberIndexes.get(i) + P.length), P,
						matchingColIndexes.get(i));
			}).count() > 0 ? "YES" : "NO";
		}
		return "NO";
	}

	private static List<Integer> filterIndexMatchingNumberOfRows(String[] G, String[] P,
			Map<String, List<Integer>> arrayIndex, List<String> matchingIndexKeys) {
		List<Integer> validIndexes = matchingIndexKeys.stream().map(matchingIndexKey-> {
			return arrayIndex.get(matchingIndexKey).stream()
					.filter(ind -> isRowIndexLessOrEqToSearch(G.length, P.length, ind))
					.collect(Collectors.toList());
		}).flatMap(Collection::stream).collect(Collectors.toList());
		return validIndexes;
	}

	private static List<String> searchAllFirstSearchLineOccurencies(String[] P, Map<String, List<Integer>> arrayIndex) {
		List<String> matchingKeys = arrayIndex.keySet().stream().filter(key -> {
			return key.contains(P[0]);
		}).collect(Collectors.toList());
		return matchingKeys;
	}

	private static Map<String, List<Integer>> generateStringIndexes(String[] G) {
		Map<String, List<Integer>> arrayIndex = new HashMap();
		IntStream.range(0, G.length).forEach(index -> {
			arrayIndex.compute(G[index], (key, val) -> {
				if (val == null) {
					List<Integer> indexes = new ArrayList<>();
					indexes.add(index);
					return indexes;
				} else {
					val.add(index);
					return val;
				}
			});
		});
		return arrayIndex;
	}

	static boolean isRowIndexLessOrEqToSearch(Integer GLength, Integer PLength, Integer index) {
		return index + PLength <= GLength;
	}

	static boolean isSubColumnArray(String[] G, String[] P, List<Integer> colIndexes) {
		return colIndexes.stream().filter(colIndex -> {
			Arrays.stream(G).map(gLine -> gLine.substring(colIndex, colIndex + P[0].length()))
					.forEach(System.out::print);
			return Arrays.deepEquals(
					Arrays.stream(G).map(gLine -> gLine.substring(colIndex, colIndex + P[0].length())).toArray(), P);
		}).count() > 0;
	}

	static List<Integer> substringIndexOccurences(String string, String substring) {
		int lastIndex = 0;
		List<Integer> result = new ArrayList<Integer>();
		while (lastIndex != -1) {
			lastIndex = string.indexOf(substring, lastIndex);
			if (lastIndex != -1) {
				result.add(lastIndex);
				lastIndex += 1;
			}
		}
		return result;
	}
	
    public static Integer getHealth(String[] genes, Integer[] health,String stripe){
    	;
    	;
    	return IntStream
    		.range(0, genes.length)
    		.map(indexGene->{
    			return geneHealth(genes[indexGene], stripe, health[indexGene]);
    		})
    		.sum();
    }
    
	static int geneHealth(String gene, String stripe, Integer healthScore) {
		int lastIndex = 0;
		int totalHealth = 0;
		while (lastIndex != -1) {
			lastIndex = stripe.indexOf(gene, lastIndex);
			if (lastIndex != -1) {
				totalHealth+=healthScore;
				lastIndex += 1;
			}
		}
		return totalHealth;
	}
	static int withRegEx(String gene, String stripe) {

		int count = 0, startIndex = 0;

		Pattern p = Pattern.compile(stripe, Pattern.LITERAL);
		Matcher m = p.matcher(gene);

		while (m.find(startIndex)) {
			count++;
			startIndex = m.start() + 1;
		}
		return count;
	}
    public static int maing(String arg){
    	int counter =0;
    	for(int i=0;i<arg.length();i++){
    		for(int j=i+2;j<=arg.length();j++){
    			if(isPalindrome(arg.substring(i, j))){
    				counter++;
    			}
    		}
    	}
    	return counter;
    }
    
    public static int maing2(String arg){
    	return IntStream.range(0, arg.length()+1)
    	.map(calculateNUmberOfSubstringPalindromes(arg))
    	.sum();
    }

	private static IntUnaryOperator calculateNUmberOfSubstringPalindromes(String arg) {
		return i->{
    		return IntStream.range(i+2, arg.length()+1)
    		.map(j-> isPalindrome(arg.substring(i, j))?1:0)
    		.sum();
    	};
	}
	
    public static boolean isPalindrome(String str){
    	return new StringBuilder(str).reverse().toString().equals(str);
    }
}
