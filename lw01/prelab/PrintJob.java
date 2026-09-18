package lw01.prelab;

public abstract class PrintJob implements Chargeable {

	private String id;
	private int pages;

	protected PrintJob(String id, int pages) {
		if(pages <=0) {
			throw new IllegalArgumentException("Pages must be greater than 0");
		}
		this.id = id;
		this.pages = pages;
	}

	public String getId() {
		return this.id;
	}

	public int getPages() {
		return this.pages;
	}

	@Override 
	public abstract int calculateCharge();

	public int calculateCharge(int copies) {
		if(copies <= 0) {
			throw new IllegalArgumentException("Copies must be greater than 0");
		}
		return this.calculateCharge() * copies;
	}

	public String label() {
		return "Print";
	}

	public String summary() {
		return id + " | " + label() + " | " + calculateCharge() ;
	}

}