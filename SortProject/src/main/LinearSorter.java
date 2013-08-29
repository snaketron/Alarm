package main;

public class LinearSorter {

	public static void main(String[] args) {
		LinearSorter s = new LinearSorter();
		int [] array = {30, 2, 99, 1,5,7,9,5,3,2};
		s.display(s.sort(array));
	}
	
	private int [] sort(int [] array) {
		if(array.length == 0) {
			return array;
		}
		else {
			int [] left = array.clone();
			for(int index = 0; index < array.length; index++) {
				left = insert(array[index], left, index);
			}
			return left;
		}
	}
	
	private int [] insert(int value, int [] left, int max) {
		if(left.length == 1) {
			left[0] = value;
			return left;
		}
		else {
			for(int index = max - 1; index >= 0; index--) {
				if(left[index] > value) {
					int old = left[index];
					left[index] = value;
					left[index + 1] = old;
				}
			}
			return left;
		}
	}
	
	private void display(int [] array) {
		for(int x : array) {
			System.out.println(x);
		}
	}
}
