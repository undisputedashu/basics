package com.basics.graph;

import java.util.Comparator;
/**
 * Utility for pair, coordinate and edges
 */
public class Pair {
	Integer x;
	Integer y;
	private Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Pair))
			return false;
		Pair cor = (Pair)o;
		return x.equals(cor.x) && y.equals(cor.y);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result + 31*x;
		result = result + 31*y;
		return result;
	}
	
	static class PairComparatorX implements Comparator<Pair> {

		@Override
		public int compare(Pair o1, Pair o2) {
			return o1.x.compareTo(o2.x);
		}
		
	}

	static class PairComparatorY implements Comparator<Pair> {

		@Override
		public int compare(Pair o1, Pair o2) {
			return o1.y.compareTo(o2.y);
		}
		
	}

}