package main;

public class Main {
    public static void main(String[] args) {
        Polinomio poli = new Polinomio();

        poli.add(1,"c",2);
        poli.add(1,"c",5);
        poli.add(1,"e",2);
        poli.add(1,"f",2);
        poli.add(1,"c",0);
        poli.add(1,"e",1);
        poli.add(1,"c",4);
        poli.add(1,"f",4);
        System.out.println(poli.toString() + "\n");

        poli.ordenar();

        System.out.println(poli.toString());

    }
}
