package playground;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
		int[][] suffledMatrix = createSuffledArrayFromQueues(matrix.size(),matrix.get(0).size(), r, queues);
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

	private static int[][] createSuffledArrayFromQueues(int matrixRows, int matrixCols, int r, List<List<Integer>> queues) {
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
		IntStream.range(queNo + 1, numberOfRows - 1).boxed().sorted(Collections.reverseOrder())
				.forEach(i -> {
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

}
