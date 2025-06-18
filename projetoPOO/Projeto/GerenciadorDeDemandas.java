package Projeto;

import java.util.Arrays; // Ainda usa Arrays.sort
import java.util.Comparator;

public class GerenciadorDeDemandas {
    private MinhaListaEncadeadaDeDemandas demandasEmAndamento;
    private MinhaListaEncadeadaDeDemandas demandasEmEspera;

    public GerenciadorDeDemandas() {
        this.demandasEmAndamento = new MinhaListaEncadeadaDeDemandas();
        this.demandasEmEspera = new MinhaListaEncadeadaDeDemandas();
    }

    public void adicionarDemanda(Demanda demanda, boolean emAndamento) {
        if (emAndamento) {
            demandasEmAndamento.adicionar(demanda); // Adiciona no final
            System.out.println("Demanda adicionada à fila de andamento!");
        } else {
            demandasEmEspera.adicionar(demanda); // Adiciona no final
            System.out.println("Demanda adicionada à fila de espera!");
        }
    }

    public void removerDemanda(int indiceDaDemandaNaLista, boolean deAndamento) {
        MinhaListaEncadeadaDeDemandas listaAlvo;
        String nomeLista;

        if (deAndamento) {
            listaAlvo = demandasEmAndamento;
            nomeLista = "andamento";
        } else {
            listaAlvo = demandasEmEspera;
            nomeLista = "espera";
        }

        if (listaAlvo.estaVazia()) {
            System.out.println("A lista de demandas em " + nomeLista + " está vazia. Nada para remover.");
            return;
        }

        try {
            // Usa o método get(indice) para obter a demanda, que valida o índice
            Demanda demandaParaRemover = listaAlvo.get(indiceDaDemandaNaLista);

            if (listaAlvo.remover(demandaParaRemover)) {
                System.out.println("Demanda removida com sucesso da fila de " + nomeLista + ": " + demandaParaRemover);
            } else {
                System.out.println("Não foi possível remover a demanda da fila de " + nomeLista + ".");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    public void listarDemandas() {
        System.out.println("\n--- Demandas em Andamento ---");
        if (demandasEmAndamento.estaVazia()) {
            System.out.println("Nenhuma demanda em andamento.");
        } else {
            int i = 0;
            for (Demanda demanda : demandasEmAndamento) { // Usa o for-each loop
                System.out.println("Índice " + i + ": " + demanda);
                i++;
            }
        }

        System.out.println("\n--- Demandas em Espera ---");
        if (demandasEmEspera.estaVazia()) {
            System.out.println("Nenhuma demanda em espera.");
        } else {
            int i = 0;
            for (Demanda demanda : demandasEmEspera) { // Usa o for-each loop
                System.out.println("Índice " + i + ": " + demanda);
                i++;
            }
        }
    }

    public void ordenarDemandasEmAndamentoPorPrioridade() {
        if (!demandasEmAndamento.estaVazia()) {
            Demanda[] arrayDeDemandas = demandasEmAndamento.toArray();
            
            // Ainda usando Arrays.sort, que é um método utilitário.
            // Para evitar completamente bibliotecas prontas, seria necessário
            // implementar um algoritmo de ordenação (ex: Bubble Sort, Merge Sort) aqui.
            Arrays.sort(arrayDeDemandas, new Comparator<Demanda>() {
                @Override
                public int compare(Demanda d1, Demanda d2) {
                    return Integer.compare(d1.calcularPrioridade(), d2.calcularPrioridade());
                }
            });
            demandasEmAndamento.fromArray(arrayDeDemandas); // Recria a lista encadeada ordenada
            System.out.println("\nDemandas em andamento ordenadas por prioridade.");
        } else {
            System.out.println("Não há demandas em andamento para ordenar.");
        }
    }
}