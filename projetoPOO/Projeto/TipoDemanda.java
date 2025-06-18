package Projeto;

public class TipoDemanda {
    public static final int URGENTE = 1;
    public static final int CRITICO = 2;
    public static final int NORMAL = 3;

    public static String obterDescricao(int tipo) {
        switch (tipo) {
            case URGENTE:
                return "Urgente";
            case CRITICO:
                return "Crítico";
            case NORMAL:
                return "Normal";
            default:
                return "Desconhecido";
        }
    }
}