package lw01.unguided;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(new File("lw01/unguided/rentals.txt"));
        List<Rental> rentals = new ArrayList<>();

        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int day = scanner.nextInt();

            if (type.equals("Laptop")) {
                rentals.add(new laptopRental(id, day));
            } else if (type.equals("Projector")){
                rentals.add(new projectorRental(id, day));
            }
    }
    scanner.close();
    for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}
