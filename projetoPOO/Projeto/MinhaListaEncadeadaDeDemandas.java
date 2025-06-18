package Projeto;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Classe interna para representar um nó na lista encadeada
class NoDemanda {
    Demanda demanda;
    NoDemanda proximo;

    public NoDemanda(Demanda demanda) {
        this.demanda = demanda;
        this.proximo = null;
    }
}

// Implementando Iterable para permitir o for-each loop
public class MinhaListaEncadeadaDeDemandas implements Iterable<Demanda> {
    private NoDemanda cabeca;
    private NoDemanda cauda; // Adicionado ponteiro para a cauda para adição O(1) no final
    private int tamanho;

    public MinhaListaEncadeadaDeDemandas() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionar(Demanda demanda) {
        NoDemanda novoNo = new NoDemanda(demanda);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.proximo = novoNo; // Adiciona no final
            cauda = novoNo;
        }
        tamanho++;
    }

    // Novo método: adicionar no início (útil para algumas lógicas de fila)
    public void adicionarNoInicio(Demanda demanda) {
        NoDemanda novoNo = new NoDemanda(demanda);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            novoNo.proximo = cabeca;
            cabeca = novoNo;
        }
        tamanho++;
    }

    // Novo método: obter Demanda por índice
    public Demanda get(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites: " + indice + ", Tamanho: " + tamanho);
        }
        NoDemanda atual = cabeca;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        return atual.demanda;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    /**
     * Remove a primeira ocorrência da demanda especificada desta lista encadeada.
     * Retorna true se a demanda foi encontrada e removida, false caso contrário.
     */
    public boolean remover(Demanda demandaParaRemover) {
        if (cabeca == null) {
            return false; // Lista vazia, nada para remover
        }

        // Caso a demanda a ser removida seja a cabeça da lista
        if (cabeca.demanda.equals(demandaParaRemover)) {
            cabeca = cabeca.proximo;
            if (cabeca == null) { // Se a lista ficou vazia após a remoção
                cauda = null;
            }
            tamanho--;
            return true;
        }

        NoDemanda atual = cabeca;
        while (atual.proximo != null && !atual.proximo.demanda.equals(demandaParaRemover)) {
            atual = atual.proximo;
        }

        // Se encontramos a demanda (atual.proximo não é null e sua demanda é a que queremos remover)
        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo; // Pula o nó a ser removido
            if (atual.proximo == null) { // Se o nó removido era a cauda
                cauda = atual;
            }
            tamanho--;
            return true;
        }

        return false; // Demanda não encontrada na lista
    }

    // Permite converter a lista para um array
    public Demanda[] toArray() {
        Demanda[] array = new Demanda[tamanho];
        NoDemanda atual = cabeca;
        int i = 0;
        while (atual != null) {
            array[i++] = atual.demanda;
            atual = atual.proximo;
        }
        return array;
    }

    // Permite reconstruir a lista a partir de um array (útil após a ordenação)
    public void fromArray(Demanda[] array) {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
        for (Demanda d : array) {
            adicionar(d); // Adiciona cada elemento do array
        }
    }

    // Implementação da interface Iterable
    @Override
    public Iterator<Demanda> iterator() {
        return new DemandaIterator();
    }

    // Classe interna que implementa o Iterator
    private class DemandaIterator implements Iterator<Demanda> {
        private NoDemanda current = cabeca;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Demanda next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Demanda demanda = current.demanda;
            current = current.proximo;
            return demanda;
        }
    }
}
