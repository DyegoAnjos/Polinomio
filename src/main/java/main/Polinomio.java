package main;
import java.util.Scanner;

public class Polinomio implements IPolinomio{

    private No head;
    private No tail;
    private int qtdItens = 0;


    private class No{
        public double c;
        public String variavel;
        public int exp;
        public No prox;
    }

    @Override
    public void add(double c, String variavel, int exp) {
        No novoNo = new No();
        novoNo.c = c;
        novoNo.exp = exp;

        if("_".equals(variavel)){
            novoNo.variavel = null;
        } else {
            novoNo.variavel = variavel;
        }
        if(qtdItens == 0){
            head = novoNo;
            tail = novoNo;
        }

        else {
            tail.prox = novoNo;
            tail = novoNo;
        }

       qtdItens++;
    }

    @Override
    public double calc(Scanner sc) {
        if (head == null) return 0;

       //LSE auxiliar
        No atual = head;
        double resultado = 0;
        String ultimaVar = "";
        double valorVarAtual = 0;

        while (atual != null){
            if(atual.variavel != null){
                if(!atual.variavel.equalsIgnoreCase(ultimaVar)){
                    System.out.println(atual.variavel + ": ");
                    valorVarAtual = sc.nextDouble();
                    ultimaVar = atual.variavel;
                }
                resultado += atual.c * Math.pow(valorVarAtual, atual.exp);
            }
            else{
                resultado += atual.c;
            }
            atual = atual.prox;
        }
        if(sc.hasNextLine()) sc.nextLine();
        return resultado;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        qtdItens = 0;
    }

    public void ordenar() {
        if (head == null || head.prox == null) return;

        No HeadListaOrdenada = null;
        No atual = head;

        while (atual != null) {
            No proximoNoOriginal = atual.prox;

            if (HeadListaOrdenada == null || vemAntes(atual, HeadListaOrdenada)) {
                atual.prox = HeadListaOrdenada;
                HeadListaOrdenada = atual;
            } else {
                No busca = HeadListaOrdenada;
                while (busca.prox != null && !vemAntes(atual, busca.prox)) {
                    busca = busca.prox;
                }
                atual.prox = busca.prox;
                busca.prox = atual;
            }

            atual = proximoNoOriginal;
        }

        head = HeadListaOrdenada;

        No auxTail = head;
        while (auxTail.prox != null) {
            auxTail = auxTail.prox;
        }
        tail = auxTail;
    }

    private boolean vemAntes(No novo, No existente){
        //caso o no novo for constante e o que ja tava na posicao nao
        if(novo.variavel == null && existente.variavel != null){
            return false;
        }
        //caso o no novo tiver variavel e o que ja tava na posicao for constante
        if(novo.variavel != null && existente.variavel == null){
            return true;
        }
        //caso os dois forem constantes ordena pelo expoente
        if(novo.variavel == null && existente.variavel == null){
            return novo.exp > existente.exp;
        }

        int compara = novo.variavel.compareTo(existente.variavel);
        if (compara < 0){
            return true;
        } else if (compara == 0) {
            return novo.exp > existente.exp;
        }
        return false;
    }

    @Override
    public String toString() {
        No aux = head;
        String retorno = "";

        while (aux != null) {
            if (aux != head && aux.c >= 0) {
                retorno = retorno + "+";
            }
            retorno = retorno + String.format("%.2f", aux.c);
            if (aux.variavel != null) {
                retorno = retorno + "." + aux.variavel + toSuperscript(aux.exp);
            }
            aux = aux.prox;
        }
      return retorno;
    }

    private String toSuperscript(int n) {
        String normal = "0123456789";
        String superS = "\u2070\u00B9\u00B2\u00B3\u2074\u2075\u2076\u2077\u2078\u2079";
        StringBuilder sb = new StringBuilder();
        if (n < 0) {
            sb.append("\u207B");
            n = -n;
        }
        for (char c : String.valueOf(n).toCharArray()) {
            int i = normal.indexOf(c);
            sb.append(superS.charAt(i));
        }
        return sb.toString();
    }
}
