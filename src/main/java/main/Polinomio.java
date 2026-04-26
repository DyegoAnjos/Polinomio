package main;

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
        novoNo.variavel = variavel;
        novoNo.exp = exp;

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
    public double calc() {
        return 0;
    }

    @Override
    public void clear() {

    }

    public void ordenar() {
        if (head == null || head.prox == null) return;

        No HeadListaOrdenada = null;
        No atual = head;

        while (atual != null) {
            No proximoNoOriginal = atual.prox;

            if (HeadListaOrdenada == null || atual.variavel.compareTo(HeadListaOrdenada.variavel) < 0) {
                atual.prox = HeadListaOrdenada;
                HeadListaOrdenada = atual;
            } else {
                No busca = HeadListaOrdenada;
                while (busca.prox != null && busca.prox.variavel.compareTo(atual.variavel) < 0) {
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

    @Override
    public String toString() {
        No aux = head;
        String retorno = "";
        for (int i = 0; i < qtdItens; i++){
            System.out.println(" " + aux.c + aux.variavel + aux.exp);
            aux=aux.prox;
        }

        System.out.println(" Tail:" + tail.c + tail.variavel + tail.exp);
        return retorno;
    }
}
