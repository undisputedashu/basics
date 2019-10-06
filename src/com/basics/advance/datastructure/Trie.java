package com.basics.advance.datastructure;

import java.util.HashMap;
import java.util.Map;

public class Trie {

	public static void main(String args[]) {
		Trie tr = new Trie();
		Node head = new Node();
		tr.add(head, "dog");
		tr.add(head, "doggy");
		tr.add(head, "dogss");
		System.out.println(tr.search(head,"dog"));
		System.out.println(tr.search(head,"dogg"));
		System.out.println(tr.search(head,"doggy"));
		System.out.println(tr.search(head,"dogs"));
	}
	
	private boolean search(Node head, String word) {
		int n = word.length();
		Node crawl = head;
		for (int i=0;i<n;i++) {
			char ch = word.charAt(i);
			Node p = crawl.children.get(ch);
			if (p == null) {
				return false;
			}
			crawl = p;
		}
		return crawl.isEnd;
	}

	private void add(Node head, String word) {
		int n = word.length();
		Node crawl = head;
		for (int i=0;i<n;i++) {
			char ch = word.charAt(i);
			Node p = crawl.children.get(ch);
			if (p == null) {
				p = new Node();
				crawl.children.put(ch, p);
			} 
			crawl = p;
		}
		crawl.isEnd = true;
	}

	private static class Node {
		private boolean isEnd;
		private Map<Character, Node> children;
		private Node() {
			isEnd = false;
			children = new HashMap<Character, Trie.Node>();
		}
	}
	
}