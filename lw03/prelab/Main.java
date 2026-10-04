package lw03.prelab;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main{
    public static void main(String[] args){

        List<String> playlist = new LinkedList<>();

        Scanner logPlaylist = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while(logPlaylist.hasNextLine()){
            String baris = logPlaylist.nextLine().trim();
            if(baris.isEmpty()){
                continue;
            }
            String[] data = baris.split(" ", 2);
            String operasi = data[0];

            if(operasi.equals("ADD")){
                playlist.add(data[1]);
            } else if(operasi.equals("INSERT")){
                String[] bagian = data[1].split(" ", 2);
                int indeks = Integer.parseInt(bagian[0]);
                playlist.add(indeks, bagian[1]);
            } else if(operasi.equals("REMOVE")){
                playlist.remove(data[1]);
            }
        }
        logPlaylist.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for(int i = 0; i < playlist.size(); i++){
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println();

        Set<String> peserta = new LinkedHashSet<>();
        int duplikat = 0;

        Scanner logPeserta = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while(logPeserta.hasNextLine()){
            String nama = logPeserta.nextLine().trim();
            if(nama.isEmpty()){
                continue;
            }
            if(peserta.contains(nama)){
                duplikat = duplikat + 1;
            } else {
                peserta.add(nama);
            }
        }
        logPeserta.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + peserta.size());
        int nomor = 1;
        for(String nama : peserta){
            System.out.println(nomor + ". " + nama);
            nomor = nomor + 1;
        }
        System.out.println("Duplicate registrations: " + duplikat);

        System.out.println();

        Map<String, Integer> stok = new LinkedHashMap<>();
        int gagalJual = 0;

        Scanner logInventory = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while(logInventory.hasNextLine()){
            String baris = logInventory.nextLine().trim();
            if(baris.isEmpty()){
                continue;
            }
            String[] data = baris.split(" ");
            String tipe = data[0];
            String produk = data[1];
            int jumlah = Integer.parseInt(data[2]);

            if(tipe.equals("ADD")){
                if(stok.containsKey(produk)){
                    stok.put(produk, stok.get(produk) + jumlah);
                } else {
                    stok.put(produk, jumlah);
                }
            } else if(tipe.equals("SELL")){
                if(stok.containsKey(produk) && stok.get(produk) >= jumlah){
                    stok.put(produk, stok.get(produk) - jumlah);
                } else {
                    gagalJual = gagalJual + 1;
                }
            }
        }
        logInventory.close();

        System.out.println("===== Problem 3 =====");
        for(Map.Entry<String, Integer> item : stok.entrySet()){
            System.out.println(item.getKey() + ": " + item.getValue());
        }
        System.out.println("Failed sales: " + gagalJual);
    }
}