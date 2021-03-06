package floatingPoint;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Random;

// Convert a java double to a bit representation of a FloCoPro float
public class FloPoCoFloat extends BitSet implements Iterator<Boolean> {

	private static final long serialVersionUID = 1L;

	private double value;
	private int exponentWidth;
	private int mantissaWidth;
	
	private FloPoCoFloat(int exponentWidth, int mantissaWidth) throws FloPoCoException {
		super(exponentWidth+mantissaWidth+3);
		this.exponentWidth = exponentWidth;
		this.mantissaWidth = mantissaWidth;
		
		if( exponentWidth<1 || mantissaWidth<1 )
			throw new FloPoCoException("invalid parameter.");
		
		if( exponentWidth>11 )
			throw new FloPoCoException("exponents widths greater than 11 cannot be converted.");
		
		if( mantissaWidth>53 )
			throw new FloPoCoException("mantissa widths greater than 53 cannot be converted.");

	}
	
	public FloPoCoFloat(double value, int exponentWidth, int mantissaWidth) throws FloPoCoException {
		this(exponentWidth, mantissaWidth);
		this.value = value;
		convertFromDouble();
	}

	public FloPoCoFloat(String value, int exponentWidth, int mantissaWidth) throws FloPoCoException {
		this(exponentWidth, mantissaWidth);
		this.value = Double.valueOf(value);
		convertFromDouble();
	}

	public FloPoCoFloat(String value, int radix, int exponentWidth, int mantissaWidth) throws FloPoCoException {
		this(exponentWidth, mantissaWidth);
		switch(radix) {
		case 2:
			convertFromBinaryString(value);
			break;
		case 10:
			convertFromDouble();
			break;
		case 16:
			convertFromHexString(value);
			break;
		default:
			throw new FloPoCoException("Only base 2, 10 and 16 conversion supported.");
		}
	}
	
	private void convertFromHexString(String value) throws FloPoCoException {
		long p = Long.parseLong(value, 16);
		for(int i=0; i<size(); i++) {
			if( (p & (1<<i)) == 0 ) clear(i);
			else set(i);
		}
	}

	private void convertFromBinaryString(String value) throws FloPoCoException {
		String s = value.replaceAll(" ", "");
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			switch(c) {
			case '1':
				this.set(i);
				break;
			case '0':
				this.clear(i);
				break;
			default:
				throw new FloPoCoException("Invalid binary string");	
			}
			
		}
	}

	private void convertFromDouble() throws FloPoCoException {
		if( Double.isNaN(value) ) {
			set(0, true);
			set(1, true);
			return;
		}
		
		if( value == 0 ) return;

		if( value < 0 ) {
			set(2, true);
			value = - value;
		}
		
		if( Double.isInfinite(value) ) {
			set(0, true);
			set(1, false);
			return;
		}

		// Normal numbers 
		set(0, false);
		set(1, true);
	
		int exponent = 0;
		while( value < 1) {
			exponent--;
			value *= 2;
		}
		while( value >= 2 ) {
			exponent++;
			value /= 2;
		}
		int biased_exponent = exponent + (1<<(exponentWidth-1)) - 1;
		
		if( biased_exponent < 0 ) throw new FloPoCoException("underflow - format does not support all java doubles");
		if( biased_exponent >= 1<<exponentWidth ) throw new FloPoCoException("should not happen - overflow");
		
		for(int i=exponentWidth+2; i>=3; i-- ) {
			set(i, biased_exponent%2==1);
			biased_exponent >>= 1;
		}
		
		value--; // take off implied leading 1
		for(int i=0; i<mantissaWidth; i++ ) {
			boolean b;
			value *= 2;
			if( value >= 1 ) {
				b = true;
				value--;
			} else
				b = false;
			set(i+3+exponentWidth, b);
		}

	}
	
	public String toString() {
		StringBuffer s = new StringBuffer();
		int indx = 0;
		
		// 2-bit exception field
		s.append(bitToChar(indx++));
		s.append(bitToChar(indx++));
		s.append(' ');
		
		// sign bit
		s.append(bitToChar(indx++));
		s.append(' ');
		
		// exponent field
		for(int i=0; i<exponentWidth; i++) s.append(bitToChar(indx++));
		s.append(' ');
		
		// exponent field
		for(int i=0; i<mantissaWidth; i++) s.append(bitToChar(indx++));
		
		return new String(s);		
	}
	
	public String toBitString() { return toString(); }
	
	public String toHexadecimalString() {
		// Should really move the byte stream into this class
		FloPoCoTable t = new FloPoCoTable();
		t.add(this);
		String s = "";
		for(Byte b : t)	s += String.format("%02X", b);
		return s;
	}
	
	public String toDecimalString() {
		return Double.toString(toDouble());
	}
	
	public double toDouble() {
		double x = 1;
		int sign = 0;
		
		if( !get(0) && !get(1) ) return 0;
		if(  get(0) &&  get(1) ) return Double.NaN;
		if(  get(0) && !get(1) ) return Double.POSITIVE_INFINITY;
		
		sign = get(2) ? -1 : 1;  
		
		// Fraction
		for(int i=exponentWidth+3; i<exponentWidth+mantissaWidth+3; i++) {
			x *= 2;
			if( get(i) ) x++;
		}
		x /= (1L << mantissaWidth);
		
		// Exponent
		int exp = 0;
		for(int i=3; i<exponentWidth+3; i++) {
			exp <<= 1;
			if( get(i) ) exp++;
		}
		exp -= (1<<(exponentWidth-1)) - 1;

		if( exp< 0 )
			for(int i=0; i<-exp; i++) x /= 2;
		else
			for(int i=0; i<exp; i++)  x *= 2;
		
		x *= sign;
		
		return x;
	}
	
	private char bitToChar(int i) { 
		boolean b = get(i);
		return b ? '1' : '0'; 
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		double m, x = 0, z = 0, w = 0;
		FloPoCoFloat y = null;
		int s, e;
		try {
			for(int i=0; i<1000000; i++) {
				// mantissa 0<=x<1
				m = r.nextDouble();
				// exponent 2^-9<e<2^9 (not all java doubles are supported)
				e = r.nextInt(1<<10)-(1<<9);
				// sign
				s = r.nextInt(2);
				x = m;
				if( e > 0)
					for(int j=0; j<e; j++)  x *= 2;
				else
					for(int j=0; j<-e; j++) x /= 2;
				x *= (2*s-1);
				y = new FloPoCoFloat(x, 11, 53);
				z = y.toDouble();
				w = x-z;
				if( w!=0 ) throw new FloPoCoException("Conversion fail.");
			}
			System.out.println("success.");
		} catch (FloPoCoException ex) {
			System.out.println(x+"  "+y+"  "+z+"  "+w);
			System.out.println(ex.getMessage());
		}
	}

	// super.size() returns garbage
	public int size() { return mantissaWidth+exponentWidth+3; }
	
	// Iterator interface
	private int indx;
	
	public Iterator<Boolean> iterator() {
		indx = 0;
	    return this;
	}
	@Override
	public boolean hasNext() {
		return indx<mantissaWidth+exponentWidth+3;
	}

	@Override
	public Boolean next() {
		return new Boolean(get(indx++));
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public int getExponentWidth() {
		return exponentWidth;
	}
	
	public int getMantissaWidth() {
		return mantissaWidth;
	}
}
