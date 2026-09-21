package lw01.unguided;

public abstract  class Rental implements Chargeable{
    private String id;
    private int day;

    protected Rental(String id, int day){
        if(day <= 0){
            throw new IllegalArgumentException("Must be Greater than 0");
        }
        this.id = id;
        this.day = day;
    }

    public String getId(){
        return this.id;
    }

    public int getDay(){
        return this.day;
    }

    @Override 
    public abstract int calculateCharge();

    public int calculateCharge(int units){
        if(units <= 0) {
			throw new IllegalArgumentException("Units must be greater than 0");
		}
        return this.calculateCharge()*units;
    }

    public String label(){
        return "Rental";
    }

    public String summary(){
        return id + " | " + label()+ " | "+calculateCharge(); 
    }
}
