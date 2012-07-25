package solving;

public enum ClampMode {
	CURRENT_CLAMP("Current"),
	VOLTAGE_CLAMP("Voltage");

	private String name;
	ClampMode(String s) { this.name = s; }
	public String toString() { return name; }

}
