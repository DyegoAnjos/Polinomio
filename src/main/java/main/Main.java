package main;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Polinomio poli = new Polinomio();

        while (true) {
            System.out.println("Polinômio: ");
            String input = sc.nextLine();
            if (input.trim().isEmpty()) break;

            Scanner linhaLida = new Scanner(input);
            int numParcelas = 1;
            poli.clear();
            boolean erro = false;

            while (linhaLida.hasNext()) {
                try {
                    double c = linhaLida.nextDouble();
                    if (!linhaLida.hasNext()) throw new Exception();
                    String var = linhaLida.next().toLowerCase();
                    if (!linhaLida.hasNextInt()) throw new Exception();
                    int exp = linhaLida.nextInt();
                    poli.add(c, var, exp);
                    numParcelas++;
                } catch (Exception e) {
                    System.out.println("Erro na parcela " + numParcelas);
                    erro = true;
                    break;
                }
            }
            linhaLida.close();

            if (!erro) {
                poli.ordenar();
                System.out.println("Polinômio: " + poli.toString());
                double resultado = poli.calc(sc);
                System.out.printf("Valor: %.4f\n", resultado);
            }
        }
        sc.close();
        System.out.println("Programa encerrado.");
    }
}

