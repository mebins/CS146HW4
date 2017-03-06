import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingProblems {

	/**
	 * Given a list of integers, is there a subset of these integers whose sum
	 * is equal to the specified target value? An empty set of integers is
	 * considered to have a sum of 0.
	 * 
	 * @return true if such a subset exists
	 */
	public static boolean subsetSum(List<Integer> list, int target) {
		if (list.isEmpty())
			return target == 0;
		List<Integer> restOfTheList = list.subList(1, list.size());
		boolean canMakeSubsetUsingFirstElement = subsetSum(restOfTheList, target - list.get(0));
		boolean canMakeSubsetWithoutFirstElement = subsetSum(restOfTheList, target);
		return canMakeSubsetUsingFirstElement || canMakeSubsetWithoutFirstElement;
	}

	/**
	 * Give a list of integers, is there a subset of these integers whose sum is
	 * equal to the specified target value, with the additional condition that
	 * all elements that make up the sum must be odd? An empty set of integers
	 * is considered to have a sum of 0, and have only odd integers (a vacuously
	 * true statement) Solve this problem without using any loops.
	 * 
	 * @return true if such a subset exists
	 */
	public static boolean oddSubsetSum(List<Integer> list, int target) {
		if (target == 0)
			return true;
		if (list.size() > 0) {
			if (list.get(0) <= target && list.get(0) % 2 != 0) {
				if (oddSubsetSum(list.subList(1, list.size()), target - list.get(0))) {
					return true;
				}
			}
			return oddSubsetSum(list.subList(1, list.size()), target);
		}
		return false;
	}

	/**
	 * Given a list of integers, is there a subset of exactly 8 integers whose
	 * sum is equal to the specified target value? If the list has 7 or fewer
	 * numbers, the answer is no.
	 *
	 * Solve this problem without using any loops.
	 *
	 * Hint: to solve the problem, you will need the information of how many
	 * numbers have already been used, so you will need to solve a more general
	 * problem, which will implemented in a helper function,
	 * 
	 * @return true if such a subset exists
	 */
	public static boolean subsetOf8Sum(List<Integer> list, int target) {
		return recurseSubsetOf8Sum(list, target, 0);
	}

	private static boolean recurseSubsetOf8Sum(List<Integer> list, int target, int used) {

		if (target == 0 && used != 8)
			return false;
		if (target == 0)
			return true;
		if (list.size() == 0)
			return false;

		if (list.get(0) <= target) {
			if (recurseSubsetOf8Sum(list.subList(1, list.size()), target - list.get(0), used + 1)) {
				return true;
			}
		}
		return recurseSubsetOf8Sum(list.subList(1, list.size()), target, used);
	}

	/**
	 * Given a non-empty list of integers, is there a non-empty subset whose sum
	 * is equal to the specified target? Hint: you will need the information of
	 * whether any number has already been used (same idea as in subset8Sum, but
	 * with "exactly 8" replaced by "at least 1"), so you will also need a
	 * helper function. Solve this problem without using any loops.
	 */
	public static boolean nonEmptySubsetSum(List<Integer> list, int target) {
		if (target == 0) return true;
		if (list.size() > 0) {
			if (list.get(0) <= target) {
				if (nonEmptySubsetSum(list.subList(1, list.size()), target - list.get(0))) {
					return true;
				}
			}
			return nonEmptySubsetSum(list.subList(1, list.size()), target);
		}
		return false;
	}

	/**
	 * Given a list of doubles, is there a (possibly empty) subset of doubles
	 * whose product is in the range between the give lower and upper bound
	 * (bounds included)?
	 *
	 * The product of an empty set is considered to be 1.
	 *
	 * The answer is no if the value given by the parameter upperBound is
	 * strictly less than the parameter lowerBound.
	 *
	 * Solve this problem without using any loops.
	 *
	 * Hint 1: sum is to subtraction as product is to ... Hint 2: what happens
	 * to the order of three values 0 < a < b < c if you divide them all by the
	 * same negative number? what about if you divide by the same number between
	 * 0 and 1? Hint 3: use inequalities (<, >, <=, >=) to compare doubles, do
	 * not use equality (==).
	 * 
	 * @return true if such a subset exists
	 */
	public static boolean subsetProduct(List<Double> list, double lowerBound, double upperBound) {
		if (list.size() == 0) {
			return false;
		}
		double value = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if ((value * list.get(i)) / lowerBound >= 1 && upperBound / (value * list.get(i)) >= 1) {
				return true;
			}
		}
		return subsetProduct(list.subList(1, list.size()), lowerBound, upperBound);
	}

	/**
	 * Given a list of non-negative integers and a positive upperBound, what is
	 * the maximum value of any subsets of the set that is less than or equal to
	 * the specified upperBound?
	 *
	 * For example {6, 7, 8}, 18 returns 15 {}, 10 returns 0
	 */

	public static int maxSubsetSum(List<Integer> list, int upperBound) {
		return recurseMaxSubsetSum(list, upperBound, 0);
	}

	private static int recurseMaxSubsetSum(List<Integer> list, int upperBound, int counter) {
		if (list.size() > 0) {
			if (list.get(0) + counter <= upperBound) {
				int result = recurseMaxSubsetSum(list.subList(1, list.size()), upperBound, counter + list.get(0));
				int secondResult = recurseMaxSubsetSum(list.subList(1, list.size()), upperBound, counter);
				if (result != 0 || secondResult != 0) {
					int temp = Integer.max(result, secondResult);
					if (temp == list.get(0)) {
						return 0;
					}
					return temp;
				}
			}
			return recurseMaxSubsetSum(list.subList(1, list.size()), upperBound, counter);
		}
		return counter;
	}

	public static void main(String[] args) {
		// odd subset sum
		List<Integer> list1 = new ArrayList<>(Arrays.asList(5, 13, 9, 8, 2));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 1, 4, 1, 5, 1, 1, 1, 1, 2, 1, 4, 1, 3));
		List<Double> list3 = new ArrayList<>(Arrays.asList(1.0, 4.0, 5.0, 2.0, 7.0, 1.0, 7.0, 2.0, 8.0));
		List<Double> list4 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 5.0, 12.0, 60.0, 24.0, 623.0, 8.0, 22.0));
		List<Integer> list5 = new ArrayList<>(Arrays.asList(6, 7, 8));
		List<Double> empty = new ArrayList<>();

		System.out.println(list1 + ", 8: oddSubsetSum : " + oddSubsetSum(list1, 8));
		System.out.println(list1 + ", 13: oddSubsetSum : " + oddSubsetSum(list1, 13));
		System.out.println(list1 + ", 14: oddSubsetSum : " + oddSubsetSum(list1, 14));
		System.out.println(list1 + ", 15: oddSubsetSum : " + oddSubsetSum(list1, 15));
		System.out.println();
		System.out.println(list2 + ", 8: SubsetOf8Sum : " + subsetOf8Sum(list2, 8));
		System.out.println(list2 + ", 10: SubsetOf8Sum : " + subsetOf8Sum(list2, 10));
		System.out.println(list2 + ", 1: SubsetOf8Sum : " + subsetOf8Sum(list2, 1));
		System.out.println(list2 + ", 16: SubsetOf8Sum : " + subsetOf8Sum(list2, 16));
		System.out.println();
		System.out.println(list2 + ", 8: nonEmptySubsetSum : " + nonEmptySubsetSum(list2, 8));
		System.out.println(list2 + ", 10: nonEmptySubsetSum : " + nonEmptySubsetSum(list2, 10));
		System.out.println(list2 + ", 1: nonEmptySubsetSum : " + nonEmptySubsetSum(list2, 1));
		System.out.println(list2 + ", 16: nonEmptySubsetSum : " + nonEmptySubsetSum(list2, 16));
		System.out.println(list2 + ", -2: nonEmptySubsetSum : " + nonEmptySubsetSum(list2, -2));
		System.out.println();

		System.out.println(
				list3 + ", List 3: subsetProduct Upperbound : 10, Lowerbound : 5 : " + subsetProduct(list3, 5, 10));
		System.out.println(
				list4 + ", List 4: subsetProduct Upperbound : 21, Lowerbound : 20 : " + subsetProduct(list4, 20, 21));
		System.out.println(
				list4 + ", List 4: subsetProduct Upperbound : 22, Lowerbound : 20 : " + subsetProduct(list4, 20, 22));
		System.out.println(
				empty + ", Empty : subsetProduct Upperbound : 22, Lowerbound : 20 : " + subsetProduct(empty, 20, 22));

		System.out.println();
		System.out.println(list1 + ", List 1: maxSubsetSum Upperbound : 10 : " + maxSubsetSum(list1, 10));
		System.out.println(list1 + ", List 1: maxSubsetSum Upperbound : 30 : " + maxSubsetSum(list1, 30));
		System.out.println(list2 + ", List 2: maxSubsetSum Upperbound : -2 : " + maxSubsetSum(list2, 0));
		System.out.println(list2 + ", List 2: maxSubsetSum Upperbound : 10 : " + maxSubsetSum(list2, 10));
		System.out.println(list2 + ", List 2: maxSubsetSum Upperbound : 30 : " + maxSubsetSum(list2, 30));
		System.out.println(list5 + ", List 5: maxSubsetSum Upperbound : 10 : " + maxSubsetSum(list5, 10));
		System.out.println(list5 + ", List 5: maxSubsetSum Upperbound : 18 : " + maxSubsetSum(list5, 18));

	}

}