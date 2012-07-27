package floatingPoint;

import java.util.BitSet;
import java.util.Random;

// Convert a java double to a bit representation of a FloCoPro float
public class FloPoCoFloat {

	private BitSet bitRepresentation;
	private double value;
	private int exponentWidth;
	private int mantissaWidth;
	
	public FloPoCoFloat(double value, int exponentWidth, int mantissaWidth) throws FloPoCoException {
		bitRepresentation = new BitSet(exponentWidth+mantissaWidth+3);
		this.exponentWidth = exponentWidth;
		this.mantissaWidth = mantissaWidth;
		this.value = value;
		convertFromDouble();
	}

	public FloPoCoFloat(String value, int exponentWidth, int mantissaWidth) throws FloPoCoException {
		bitRepresentation = new BitSet(exponentWidth+mantissaWidth+3);
		this.exponentWidth = exponentWidth;
		this.mantissaWidth = mantissaWidth;
		this.value = Double.parseDouble(value);
		convertFromDouble();
	}
	
	private void convertFromDouble() throws FloPoCoException {
		
		if( exponentWidth<1 || mantissaWidth<1 )
			throw new FloPoCoException("invalid parameter.");
		
		if( exponentWidth>11 )
			throw new FloPoCoException("exponents widths greater than 11 cannot be converted.");
		
		if( mantissaWidth>53 )
			throw new FloPoCoException("mantissa widths greater than 53 cannot be converted.");
		
		if( value == Double.NaN ) {
			bitRepresentation.set(0, true);
			bitRepresentation.set(1, true);
			return;
		}
		
		if( value == 0 ) return;

		if( value < 0 ) {
			bitRepresentation.set(2, true);
			value = - value;
		}
		
		if( value == Double.NEGATIVE_INFINITY || value==Double.POSITIVE_INFINITY ) {
			bitRepresentation.set(0, true);
			bitRepresentation.set(1, false);
			return;
		}

		// Normal numbers 
		bitRepresentation.set(0, false);
		bitRepresentation.set(1, true);
	
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
		if( biased_exponent < 0 ) throw new FloPoCoException("underflow");
		if( biased_exponent >= 1<<exponentWidth ) throw new FloPoCoException("overflow");
		
		for(int i=exponentWidth+2; i>=3; i-- ) {
			bitRepresentation.set(i, biased_exponent%2==1);
			biased_exponent >>= 1;
		}
		
		value--;
		for(int i=0; i<mantissaWidth; i++ ) {
			boolean b;
			value *= 2;
			if( value >= 1 ) {
				b = true;
				value--;
			} else
				b = false;
			bitRepresentation.set(i+3+exponentWidth, b);
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
	
	public double toDouble() {
		double x = 1;
		int sign = 0;
		
		if( !bitRepresentation.get(0) && !bitRepresentation.get(1) ) return 0;
		if(  bitRepresentation.get(0) &&  bitRepresentation.get(1) ) return Double.NaN;
		if(  bitRepresentation.get(0) && !bitRepresentation.get(1) ) return Double.POSITIVE_INFINITY;
		
		sign = bit(2) ? -1 : 1;  
		
		// Fraction
		for(int i=exponentWidth+3; i<exponentWidth+mantissaWidth+3; i++) {
			x *= 2;
			if( bit(i) ) x++;
		}
		x /= (1L << mantissaWidth);
		
		// Exponent
		int exp = 0;
		for(int i=3; i<exponentWidth+3; i++) {
			exp <<= 1;
			if( bit(i) ) exp++;
		}
		exp -= (1<<(exponentWidth-1)) - 1;
		
		x *= 1L<<exp;
		x *= sign;
		
		return x;
	}
	
	private boolean bit(int i) { return bitRepresentation.get(i); }
	
	private char bitToChar(int i) { 
		boolean b = bitRepresentation.get(i);
		return b ? '1' : '0'; 
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		try {
			for(int i=0; i<10000000; i++) {
				double x = r.nextDouble()+r.nextInt();
				FloPoCoFloat y = new FloPoCoFloat(x, 11, 53);
				double z = y.toDouble();
				double w = x-z;
				if( w!=0 )
					System.out.println(x+"  "+y+"  "+w);
			}
		} catch (FloPoCoException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("done.");
	}
	
}
