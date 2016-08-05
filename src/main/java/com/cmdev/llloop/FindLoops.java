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

		doSlowCheck(sll);
		doFastCheck(sll);
	}

	private static void doSlowCheck(SimpleLinkedList listToCheck) {
		// slow way to find the loop
		System.out.println("Checking for loop the slow, memory-intensive way");
		Runtime rt = Runtime.getRuntime();
		rt.gc(); // maybe run GC
		long memory = rt.totalMemory() - rt.freeMemory();
		System.out.println("Used memory before, in megabytes: " + bytesToMB(memory));

		HashSet<Node> nodeSet = new HashSet<Node>(200000);
		Node current = listToCheck.head();

		boolean doesNotLoop = true;
		long start = System.currentTimeMillis();
		while(doesNotLoop && (current != null)) {
			if(nodeSet.contains(current)) {
				doesNotLoop = false;
			} else {
				nodeSet.add(current);
			}
			current = current.next;
		}
		long duration = System.currentTimeMillis() - start;
		if (doesNotLoop) {
			System.out.println("No loop found");
		} else {
			System.out.println("Loop found in linked list");

		}
		memory = rt.totalMemory() - rt.freeMemory();
		System.out.println("Used memory after, in megabytes: " + bytesToMB(memory));
		System.out.println(duration + " milliseconds to run");
	}

	private static void doFastCheck(SimpleLinkedList listToCheck) {
		// fast way to find the loop
		System.out.println("Checking for loop the fast, memory efficient way");

		Runtime rt = Runtime.getRuntime();
		rt.gc(); // maybe run GC
		long memory = rt.totalMemory() - rt.freeMemory();
		System.out.println("Used memory before, in megabytes: " + bytesToMB(memory));

		Node current = listToCheck.head();
		Node fastWalker = current.next;

		boolean doesNotLoop = true;
		long start = System.currentTimeMillis();
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
		long duration = System.currentTimeMillis() - start;
		if (doesNotLoop) {
			System.out.println("No loop found");
		} else {
			System.out.println("Loop found in linked list");

		}
		memory = rt.totalMemory() - rt.freeMemory();
		System.out.println("Used memory after, in megabytes: " + bytesToMB(memory));
		System.out.println(duration + " milliseconds to run");
	}

	private static long bytesToMB(long bytes) {
		return bytes / (1024L * 1024L);
	}
}