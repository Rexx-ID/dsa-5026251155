package lw01.unguided;

public class projectorRental extends Rental{
    public projectorRental(String id, int day){
        super(id, day);
    }

    @Override 
    public int calculateCharge(){
        int D = getDay();
        int cost;
        if(D <= 3){
            cost = D * 60000;
        }
        else{
            cost = (3*60000)+((D-3)*45000);
        }
        return cost + 20000;
    }
    public String label(){
        return "Projector";
    }
}