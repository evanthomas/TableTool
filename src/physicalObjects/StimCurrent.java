package physicalObjects;

public abstract class StimCurrent extends Current {
	public StimCurrent(String myName) {
		super(myName);
	}

	public abstract double I(double t);
}
