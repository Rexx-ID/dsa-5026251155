package lw02.unguided;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Library {
    public static void main(String[] args) throws Exception {
        final int MAX_BORROW = 2;

        LinkedList<String[]> request = new LinkedList<>();
        LinkedList<String[]> buku = new LinkedList<>();
        LinkedList<String[]> member = new LinkedList<>();
        LinkedList<String[]> berhasil = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> requestGagal = new Stack<>();

        Scanner dataPinjam = new Scanner(Library.class.getResourceAsStream("borrowing.txt"));

        while (dataPinjam.hasNext()) {
            String nama = dataPinjam.next();
            String judulBuku = dataPinjam.next();
            String[] data = {nama, judulBuku};
            request.add(data);
        }
        dataPinjam.close();

        
        String[] judul = {"Kalkulus", "Fisika", "Statistika"};
        String[] stok = {"2", "1", "2"};
        for (int i = 0; i < judul.length; i++) {
            String[] dataBuku = {judul[i], stok[i]};
            buku.add(dataBuku);
        }

        
        for (int i = 0; i < request.size(); i++) {
            String namaMember = request.get(i)[0];

            boolean ada = false;
            for (int j = 0; j < member.size(); j++) {
                if (member.get(j)[0].equals(namaMember)) {
                    ada = true;
                }
            }
            if (!ada) {
                String[] listMember = {namaMember, "0"};
                member.add(listMember);
            }
        }
        
        for (int i = 0; i < request.size(); i++) {
            queue.add(request.get(i));
        }

        
        while (!queue.isEmpty()) {
            String[] r = queue.poll();
            String nama = r[0];
            String judulBuku = r[1];

            int indexMember = -1;
            for (int j = 0; j < member.size(); j++) {
                if (member.get(j)[0].equals(nama)) {
                    indexMember = j;
                }
            }

            int indexBuku = -1;
            for (int j = 0; j < buku.size(); j++) {
                if (buku.get(j)[0].equals(judulBuku)) {
                    indexBuku = j;
                }
            }

            if (indexBuku == -1) {
                requestGagal.push(r);
                continue;
            }

            int stokBuku = Integer.parseInt(buku.get(indexBuku)[1]);
            int jumlahPinjam = Integer.parseInt(member.get(indexMember)[1]);

            if (stokBuku > 0 && jumlahPinjam < MAX_BORROW) {
                buku.get(indexBuku)[1] = String.valueOf(stokBuku - 1);
                member.get(indexMember)[1] = String.valueOf(jumlahPinjam + 1);
                berhasil.add(r);
            } else {
                requestGagal.push(r);
            }
        }

        
        System.out.println("=== Successfully Processed Requests ===");
        for (int i = 0; i < berhasil.size(); i++) {
            System.out.println(berhasil.get(i)[0] + " " + berhasil.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Book Stock ===");
        for (int i = 0; i < buku.size(); i++) {
            System.out.println(buku.get(i)[0] + " : " + buku.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (!requestGagal.isEmpty()) {
            String[] r = requestGagal.pop();
            System.out.println(r[0] + " " + r[1]);
        }
    }
}