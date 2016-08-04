package com.cmdev.llloop;

public class Node {

	public Node next;
	private int value;

	public Node(int val) {
		value = val;
	}

	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "Value: " + value;
	}
}
