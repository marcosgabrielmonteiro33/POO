package Projeto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorDeDemandas gerenciador = new GerenciadorDeDemandas();
        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n===================");
            System.out.println("Menu:");
            System.out.println("1- Criar demanda");
            System.out.println("2- Listar demandas");
            System.out.println("3- Remover demanda");
            System.out.println("4- Sair");
            System.out.print("Digite sua opção: ");

            try {
                op = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para a opção do menu.");
                scanner.next(); // Descarta a entrada inválida
                op = 0;
                continue;
            }

            switch (op) {
                case 1:
                    System.out.println("\nDigite os atributos da nova demanda:");
                    try {
                        System.out.print("Tipo (1-Urgente, 2-Crítico, 3-Normal): ");
                        int tipo = scanner.nextInt();
                        System.out.print("Distância da sede: ");
                        int distanciaDaSede = scanner.nextInt();
                        System.out.print("Distância da equipe: ");
                        int distanciaDaEquipe = scanner.nextInt();
                        System.out.print("Custo do reparo: ");
                        int custoReparo = scanner.nextInt();
                        System.out.print("Custo do impacto: ");
                        int custoImpacto = scanner.nextInt();
                        System.out.print("Pré-prioridade: ");
                        int prePrioridade = scanner.nextInt();
                        System.out.print("Prejuízo fiscal: ");
                        int prejuizoFiscal = scanner.nextInt();
                        System.out.print("Tempo de espera: ");
                        int tempoEspera = scanner.nextInt();

                        Demanda novaDemanda = new Demanda(tipo, distanciaDaSede, distanciaDaEquipe,
                                                        custoReparo, custoImpacto, prePrioridade,
                                                        prejuizoFiscal, tempoEspera);

                        System.out.print("Adicionar à fila de andamento? (true/false): ");
                        String entrada = scanner.next();
                        boolean emAndamento;
                        if (entrada.equalsIgnoreCase("true")) {
                            emAndamento = true;
                        } else if (entrada.equalsIgnoreCase("false")) {
                            emAndamento = false;
                        } else {
                            System.out.println("Entrada inválida! Digite apenas 'true' ou 'false'.");
                            continue;
                        }
                        gerenciador.adicionarDemanda(novaDemanda, emAndamento);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida para os atributos da demanda. Por favor, digite números inteiros ou 'true'/'false'.");
                        scanner.nextLine(); // Consome a linha inválida inteira
                    }
                    break;
                case 2:
                    gerenciador.listarDemandas();
                    gerenciador.ordenarDemandasEmAndamentoPorPrioridade();
                    gerenciador.listarDemandas();
                    break;
                case 3:
                    System.out.println("\n--- Remover Demanda ---");
                    gerenciador.listarDemandas(); // Mostra as demandas para o usuário escolher o índice

                    try {
                        System.out.print("Remover da fila de andamento (true) ou espera (false)? ");
                        boolean removerDeAndamento = scanner.nextBoolean();

                        System.out.print("Digite o índice da demanda a ser removida: ");
                        int indiceParaRemover = scanner.nextInt();

                        // O 'tipoDemanda' aqui não é mais necessário no método de remover
                        gerenciador.removerDemanda(indiceParaRemover, removerDeAndamento);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, digite 'true' ou 'false' para a fila, e um número para o índice.");
                        scanner.nextLine(); // Consome a linha inválida inteira
                    }
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    if (op != 0) {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
            }
        } while (op != 4);

        scanner.close();
    }
}