package com.cmdev.llloop;

import java.util.HashSet;

public class FindLoops {
	public static void main(String args[]) {
		SimpleLinkedList sll = new SimpleLinkedList();
		for(int nodeVal = 0; nodeVal < 100000; nodeVal++) {
			Node newNode = new Node(nodeVal);
			sll.add(newNode);
		}
		System.out.println("SimpleListList created with size: "+ sll.size());
		// now make a loop
		Node looper = new Node(100001);
		System.out.println("Adding loop node: "+ looper);
		sll.add(looper);
		looper.next = sll.head();

		System.out.println("Checking for loop the slow way");
		long start = System.currentTimeMillis();
		for (int ctr = 0; ctr < 500; ctr++) {
			doSlowCheck(sll);
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println(duration + " milliseconds to run");		

		System.out.println("Checking for loop the fast way");
		start = System.currentTimeMillis();
		for (int ctr = 0; ctr < 500; ctr++) {
			doFastCheck(sll);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println(duration + " milliseconds to run");		
	}

	private static void doSlowCheck(SimpleLinkedList listToCheck) {
		// slow way to find the loop

		HashSet<Node> nodeSet = new HashSet<Node>(200000);
		Node current = listToCheck.head();

		boolean doesNotLoop = true;
		while(doesNotLoop && (current != null)) {
			if(nodeSet.contains(current)) {
				doesNotLoop = false;
			} else {
				nodeSet.add(current);
			}
			current = current.next;
		}
		if (doesNotLoop) {
			System.out.println("No loop found");
		}
	}

	private static void doFastCheck(SimpleLinkedList listToCheck) {
		// fast way to find the loop

		Node current = listToCheck.head();
		Node fastWalker = current.next;

		boolean doesNotLoop = true;
		while(doesNotLoop && (current != null) && (fastWalker != null)) {
			current = current.next;
			fastWalker = fastWalker.next;
			if (fastWalker != null) {
				fastWalker = fastWalker.next;
			}
			if (current == fastWalker) {
				doesNotLoop = false;
			}
		}
		if (doesNotLoop) {
			System.out.println("No loop found");
		}
	}
}
