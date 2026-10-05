package lw03.unguided;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner logPeserta = new Scanner(main.class.getResourceAsStream("enrollment.txt"));

        Map<String, Integer> enrollment = new LinkedHashMap<>();

        List<String> checkResults = new ArrayList<>();

        int rejected = 0;

        while (logPeserta.hasNextLine()) {
            String line = logPeserta.nextLine().trim();
             if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String op = parts[0].toUpperCase();
            if (op.equals("CHECK")) {
                if (parts.length < 2) {
                    rejected++;
                    continue;
                }
                String course = parts[1];
                if (enrollment.containsKey(course)) {
                    checkResults.add(course + ": " + enrollment.get(course) + " students");
                } else {
                    checkResults.add(course + ": Not found");
                }

            } else if (op.equals("REGISTER") || op.equals("WITHDRAW")) {
                if (parts.length < 3) {
                    rejected++;
                    continue;
                }
                String course = parts[1];
                int count;
                try {
                    count = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    rejected++;
                    continue;
                }

                if (count <= 0) {
                    rejected++;
                    continue;
                }

                if (op.equals("REGISTER")) {
                    enrollment.put(course, enrollment.getOrDefault(course, 0) + count);
                } else {
                    if (enrollment.containsKey(course) && enrollment.get(course) >= count) {
                        enrollment.put(course, enrollment.get(course) - count);
                    } else {
                        rejected++;
                    }
                }

            } else {
                rejected++;
            }
        }
        logPeserta.close();

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");
        for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
            
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejected);
    }
}