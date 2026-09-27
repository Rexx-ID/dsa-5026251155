package lw02.prelab;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ProgramBank{
    public static void main(String[] args) throws Exception {
        LinkedList<String[]> transaksi = new LinkedList<>();
        LinkedList<String[]> customer = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> transaksiGagal = new Stack<>();

        Scanner buktiTransaksi = new Scanner(ProgramBank.class.getResourceAsStream("Transaction.txt"));

        while(buktiTransaksi.hasNextLine()){
            String baris = buktiTransaksi.nextLine();
            String[] data = baris.split(" ");
            transaksi.add(data);
        }
        buktiTransaksi.close();

        for(int i=0;i<transaksi.size();i++){
            String namaCustomer = transaksi.get(i)[0];

            boolean ada = false;
            for(int j=0; j< customer.size();j++){
                if(customer.get(j)[0].equals(namaCustomer)){
                    ada=true;
                }
            }
            if(!ada){
                String[] listCustomer = {namaCustomer,"0"};
                customer.add(listCustomer);
            }
        }
        for (int i = 0; i < transaksi.size(); i++) {
            queue.add(transaksi.get(i));
        }

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String nama = t[0];
            String jenis = t[1];
            int jumlah = Integer.parseInt(t[2]);
            int index = -1;
            for (int j = 0; j < customer.size(); j++) {
                if (customer.get(j)[0].equals(nama)) {
                    index = j;
                }
            }

            int saldo = Integer.parseInt(customer.get(index)[1]);

            if (jenis.equals("DEPOSIT")) {
                saldo = saldo + jumlah;
                customer.get(index)[1] = String.valueOf(saldo);
            }
            else {
                if (jumlah > saldo) {
                    transaksiGagal.push(t);
                } else {
                    saldo = saldo - jumlah;
                    customer.get(index)[1] = String.valueOf(saldo);
                }
            }
        }
        System.out.println("=== Final Balances ===");
        for (int i = 0; i < customer.size(); i++) {
            System.out.println(customer.get(i)[0] + " : " + customer.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!transaksiGagal.isEmpty()) {
            String[] t = transaksiGagal.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }   
    }
}
