package floatingPoint;

import java.util.ArrayList;
import java.util.Iterator;

public class FloPoCoTable implements Iterable<Byte>, Iterator<Byte> {

	protected enum ENDIAN { LITTLE_ENDIAN, BIG_ENDIAN; }
	private ENDIAN endian;

	private static final int EXPONENT_WIDTH = 8;
	private static final int MANTISSA_WIDTH = 23;
	
	private ArrayList<FloPoCoFloat> table;
	private int indx;
	private Iterator<Boolean> currentFloat;
	
	public static int floatSize() { return EXPONENT_WIDTH+MANTISSA_WIDTH+3; }
	
	public FloPoCoTable() {
		table = new ArrayList<FloPoCoFloat>();
		endian = ENDIAN.LITTLE_ENDIAN;
	}
	
	public FloPoCoTable(double [] x) throws FloPoCoException {
		table = new ArrayList<FloPoCoFloat>();
		endian = ENDIAN.LITTLE_ENDIAN;
		addTable(x);
	}
	
	public void add(FloPoCoFloat f) { table.add(f); }
	public void add(double f) throws FloPoCoException { 
		table.add(new FloPoCoFloat(f, EXPONENT_WIDTH, MANTISSA_WIDTH)); 
	}
	public FloPoCoFloat get(int i) { return table.get(i); }
	public void remove(FloPoCoFloat f) { table.remove(f); }
	public int size() { return table.size(); }
	
	public void addTable(double [] x) throws FloPoCoException {
		for(int i=0; i<x.length; i++) 
			add(new FloPoCoFloat(x[i], EXPONENT_WIDTH, MANTISSA_WIDTH));
	}
	
	private byte [] buffer;
	private int bufferIndx;
	
	public Iterator<Byte> iterator() {
		indx = 0;
		if( table.size()>0 ) {
			buffer = new byte[(table.get(0).size()+7)/8];
			bufferIndx = 0;
			fillBuffer();
		} else {
			currentFloat = null;
			buffer = null;
			bufferIndx = 0;
		}
		return this;
	}

	private void fillBuffer() {
		currentFloat = table.get(indx++).iterator();
		for(int i=0; i<buffer.length; i++) {
			byte b = 0;
			for(int j=0; j<8; j++) {
				if( !currentFloat.hasNext() ) break;
				boolean bit = currentFloat.next();
				if( bit ) b |= 1<<j;
			}
			buffer[i] = b;
		}
		bufferIndx = 0;
	}
	
	@Override
	public boolean hasNext() {
		if( currentFloat==null ) return false;
		if( indx==table.size() ) return bufferIndx<buffer.length;
		return true;
	}

	@Override
	public Byte next() {

		if( bufferIndx<buffer.length ) {
			switch(endian) {
			case LITTLE_ENDIAN:
				return new Byte(buffer[buffer.length-1-bufferIndx++]);
			case BIG_ENDIAN:
				return new Byte(buffer[bufferIndx++]);
			}
		}
	
		fillBuffer();
		return next();
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
