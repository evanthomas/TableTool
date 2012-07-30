package floatingPoint;

import java.util.ArrayList;
import java.util.Iterator;

public class FloPoCoTable implements Iterable<Byte>, Iterator<Byte> {


	private ArrayList<FloPoCoFloat> table;
	private int indx;
	private Iterator<Boolean> currentFloat;
	
	public FloPoCoTable() {
		table = new ArrayList<FloPoCoFloat>();
	}
	
	public FloPoCoTable(double [] x) throws FloPoCoException {
		table = new ArrayList<FloPoCoFloat>();
		for(int i=0; i<x.length; i++) add(new FloPoCoFloat(x[i], 11, 53));
	}
	
	public void add(FloPoCoFloat f) { table.add(f); }
	public FloPoCoFloat get(int i) { return table.get(i); }
	public void remove(FloPoCoFloat f) { table.remove(f); }
	public int size() { return table.size(); }
	
	public Iterator<Byte> iterator() {
		indx = 1;
		if( table.size()>0 )
			currentFloat = table.get(0).iterator();
		else
			currentFloat = null;
		return this;
	}
	
	@Override
	public boolean hasNext() {
		if( currentFloat==null ) return false;
		if( indx==table.size() ) return currentFloat.hasNext();
		return true;
	}

	@Override
	public Byte next() {
		byte b = 0;
		int i = 0;
		while( i<8 ) {
			while( !currentFloat.hasNext() && indx<table.size() ) {
				currentFloat = table.get(indx++).iterator();
			}
			if( !currentFloat.hasNext() ) break;
			
			boolean bit = currentFloat.next();
			if( bit ) b |= 1<<i;
			i++;
		}
		return new Byte(b);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) throws FloPoCoException {
		FloPoCoTable t = new FloPoCoTable();
//		for(int i=0; i<2; i++) t.add(new FloPoCoFloat(11, 53));
		t.add(new FloPoCoFloat(1, 11, 53));
		t.add(new FloPoCoFloat(16.444444444, 11, 53));

		System.out.println();
		for(int i=0; i<t.size(); i++)
			System.out.println(t.get(i));
		
		for( Byte b : t ) 
			System.out.print(byteToString(b)+" ");
	}
	
	private static String byteToString(byte b, int base) {
		return Integer.toString( ( b & 0xff ) + 0x100, base).substring( 1 );
	}
	
	private static String byteToString(byte b) {
		return byteToString(b, 2);
	}
}
