package lw01.prelab;

public class ColourPrint extends PrintJob {
	
	public ColourPrint(String id, int pages) {
		super(id, pages);
	}

	@Override
	public int calculateCharge() {
        int p = getPages();
        int cost;
        if (p <= 10) {
            cost = p * 1500;
        } else {
            cost = (10 * 1500) + ((p - 10) * 1000);
        }
        return cost + 2000; 
    }

	public String label() {
		return "Colour";
	}

}
