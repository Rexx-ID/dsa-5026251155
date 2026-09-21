package lw01.unguided;

public class laptopRental extends Rental{
    
    public laptopRental(String id,int day){
        super(id, day);
    }

    @Override 
    public int calculateCharge(){
        return getDay() * 40000;
    }

    public String label(){
        return "Laptop";
    }
}
