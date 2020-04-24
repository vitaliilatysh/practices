package practice6;

import java.util.*;

public class Part2 {
	
	private static final int MAX_ELEMENTS = 10_000;
	
	public static void remove1(List<Object> list) {
		while(list.size() > 1){
			for(int i = 0; i < list.size();i++){
				if(i%3 == 0){
					list.remove(i);
				}
			}
		}
	}
	
	public static void remove2(List<Object> list) {
		int counter = 0;
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			++counter;
			it.next();
			if (counter == 3) {
				it.remove();
			}
		}
	}
	
	public static List<Object> init(List<Object> list) {
		for(int i = 0; i < MAX_ELEMENTS; i++){
			list.add(new Object());
		}
		return list;
	}
	
	public static void main(String[] args) {
		List<Object> arrayList = init(new ArrayList<>());
		List<Object> linkedList = init(new LinkedList<>());
		
//		System.out.println("=========== Indexed");
		long startTimeArrayList = System.currentTimeMillis();
		remove1(arrayList);
		long endTimeArrayList = System.currentTimeMillis();

		long startTimeLinkedList = System.currentTimeMillis();
		remove1(linkedList);
		long endTimeLinkedList = System.currentTimeMillis();
//
//		System.out.println("=========== Iterable");
//		arrayList = init(new ArrayList<>());
//		linkedList = init(new LinkedList<>());
//
//		// ...
//		remove2(arrayList);
//		// ...
//		remove2(linkedList);
//		// ...

		System.out.println(endTimeArrayList - startTimeArrayList);
		System.out.println(endTimeLinkedList - startTimeLinkedList);
	}
}