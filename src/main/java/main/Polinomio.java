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

    public void ordernar(){
        No aux = head.prox;
        No baseParaAgrupar = head.prox;
        No anterior = head;
        No troca = head;
        String varBase = head.variavel;
        while (aux != null){

            if (aux.variavel == varBase){
                baseParaAgrupar = aux;
                aux = aux.prox;
            }
            else{
                while (aux != null){
                    if (aux.variavel != varBase){
                        anterior = aux;
                        if(aux.prox != null)
                            aux = aux.prox;
                    }
                    else{
                        System.out.println("aux: "+aux.variavel + aux.exp);
                        anterior.prox = aux.prox;

                        System.out.println("baseParaAgrupar: "+baseParaAgrupar.variavel + baseParaAgrupar.exp);
                        System.out.println("head.prox: "+head.prox.variavel + head.prox.exp);
                        baseParaAgrupar = baseParaAgrupar.prox;
                        troca = baseParaAgrupar.prox;
                        aux.prox=troca;

                        aux = troca.prox;



                    }
                }
                    tail = aux;
                    varBase = baseParaAgrupar.prox.variavel;
                    aux = baseParaAgrupar.prox;

            }
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        No aux = head;
        String retorno = "";
        for (int i = 0; i < qtdItens; i++){
            System.out.println(" " + aux.c + aux.variavel + aux.exp);
            aux=aux.prox;
        }


        return retorno;
    }
}
