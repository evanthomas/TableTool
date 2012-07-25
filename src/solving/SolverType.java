package solving;

public enum SolverType {
	Euler("Euler"),
	midpoint("midpoint"),
	RungeKutta("Runge-Kutta"), 
	Gill("Gill"),
	ThreeEight("3/8"),
	HighamHill("Higham-Hill"),
	DormandPrince54("Dormand-Prince 5(4)"),
	DormandPrince853("Dormand-Prince 8(5,3)"),
	GraggBurlischStoer("Gragg-Burlisch-Stoer"),
	AdamsBashforth("Adams-Bashforth"),
	AdamsMoulton("Adams-Moulton");

	private String name;
	SolverType(String s) { this.name = s; }
	public String toString() { return name; }
};
