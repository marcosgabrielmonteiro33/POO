package Projeto;

import java.time.LocalDateTime;
import java.util.Objects; 

public class Demanda {
    private int tipo;
    private int distanciaDaSede;
    private int distanciaDaEquipe;
    private int custoReparo;
    private int custoImpacto;
    private int prePrioridade;
    private int prejuizoFiscal;
    private int tempoEspera;
    private LocalDateTime horarioCriacao;

    public Demanda(int tipo, int distanciaDaSede, int distanciaDaEquipe,
                   int custoReparo, int custoImpacto, int prePrioridade,
                   int prejuizoFiscal, int tempoEspera) {
        this.tipo = tipo;
        this.distanciaDaSede = distanciaDaSede;
        this.distanciaDaEquipe = distanciaDaEquipe;
        this.custoReparo = custoReparo;
        this.custoImpacto = custoImpacto;
        this.prePrioridade = prePrioridade;
        this.prejuizoFiscal = prejuizoFiscal;
        this.tempoEspera = tempoEspera;
        this.horarioCriacao = LocalDateTime.now();
    }

    public int calcularPrioridade() {
        int pesoTipo;
        switch (tipo) {
            case TipoDemanda.URGENTE:
                pesoTipo = 10;
                break;
            case TipoDemanda.CRITICO:
                pesoTipo = 7;
                break;
            case TipoDemanda.NORMAL:
            default:
                pesoTipo = 3;
                break;
        }
        return pesoTipo + distanciaDaSede + distanciaDaEquipe + custoReparo +
               custoImpacto + prePrioridade + prejuizoFiscal + tempoEspera;
    }

    // Getters para todos os atributos
    public int getTipo() { return tipo; }
    public int getDistanciaDaSede() { return distanciaDaSede; }
    public int getDistanciaDaEquipe() { return distanciaDaEquipe; }
    public int getCustoReparo() { return custoReparo; }
    public int getCustoImpacto() { return custoImpacto; }
    public int getPrePrioridade() { return prePrioridade; }
    public int getPrejuizoFiscal() { return prejuizoFiscal; }
    public int getTempoEspera() { return tempoEspera; }
    public LocalDateTime getHorarioCriacao() { return horarioCriacao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demanda demanda = (Demanda) o;
        // Comparar todos os atributos para determinar igualdade
        return tipo == demanda.tipo &&
               distanciaDaSede == demanda.distanciaDaSede &&
               distanciaDaEquipe == demanda.distanciaDaEquipe &&
               custoReparo == demanda.custoReparo &&
               custoImpacto == demanda.custoImpacto &&
               prePrioridade == demanda.prePrioridade &&
               prejuizoFiscal == demanda.prejuizoFiscal &&
               tempoEspera == demanda.tempoEspera &&
               Objects.equals(horarioCriacao, demanda.horarioCriacao); // Usar Objects.equals para LocalDateTime
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, distanciaDaSede, distanciaDaEquipe, custoReparo,
                            custoImpacto, prePrioridade, prejuizoFiscal, tempoEspera, horarioCriacao);
    }

    @Override
    public String toString() {
        return "Demanda [prioridade=" + calcularPrioridade() +
               ", tipo=" + TipoDemanda.obterDescricao(tipo) +
               ", distanciaDaSede=" + distanciaDaSede +
               ", distanciaDaEquipe=" + distanciaDaEquipe +
               ", custoReparo=" + custoReparo +
               ", custoImpacto=" + custoImpacto +
               ", prePrioridade=" + prePrioridade +
               ", prejuizoFiscal=" + prejuizoFiscal +
               ", tempoEspera=" + tempoEspera +
               ", horarioCriacao=" + horarioCriacao + "]";
    }
}
