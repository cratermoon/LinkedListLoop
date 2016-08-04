package com.cmdev.llloop;

public class SimpleLinkedList {
	
	private Node head = null;
	private Node current;

	public void add(Node node) {
		if (head == null) {
			head = node;
			current = head;
		}
		current.next = node;
		current = current.next;
	}

	public Node head() {
		return head;
	}
	
	public int size() {
		int size = 0;
		Node c = head;
		while(c != null) {
			size++;
			c = c.next;
		}

		return size;
	}
}