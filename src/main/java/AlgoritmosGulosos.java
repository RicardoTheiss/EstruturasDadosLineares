import java.util.*;

public class AlgoritmosGulosos {

    public static void questao1() {

        int capacidade = 100;

        int[] ids = {1, 2, 3, 4, 5};
        int[] volumes = {40, 30, 25, 20, 15};

        int usado = 0;

        List<Integer> itens = new ArrayList<>();

        for (int i = 0; i < volumes.length; i++) {

            if (usado + volumes[i] <= capacidade) {
                usado += volumes[i];
                itens.add(ids[i]);
            }
        }

        System.out.println("Itens escolhidos: " + itens);
        System.out.println("Volume utilizado: " + usado);
        System.out.println("Nem sempre retorna a solucao otima");
        System.out.println();
    }

    public static void questao2() {

        int dias = 7;

        String[] lugares = {
                "Paris",
                "Roma",
                "Berlim",
                "Amsterdam",
                "Praga"
        };

        int[] pontos = {10, 8, 7, 6, 5};
        int[] tempo = {3, 2, 2, 1, 1};

        double[] razao = new double[lugares.length];

        for (int i = 0; i < lugares.length; i++) {
            razao[i] = (double) pontos[i] / tempo[i];
        }

        List<String> escolhidos = new ArrayList<>();

        int tempoUsado = 0;
        int totalPontos = 0;

        boolean[] usado = new boolean[lugares.length];

        while (true) {

            int melhor = -1;

            for (int i = 0; i < lugares.length; i++) {

                if (!usado[i]) {

                    if (melhor == -1 || razao[i] > razao[melhor]) {
                        melhor = i;
                    }
                }
            }

            if (melhor == -1) {
                break;
            }

            usado[melhor] = true;

            if (tempoUsado + tempo[melhor] <= dias) {

                escolhidos.add(lugares[melhor]);

                tempoUsado += tempo[melhor];

                totalPontos += pontos[melhor];
            }
        }

        System.out.println("Lugares escolhidos: " + escolhidos);
        System.out.println("Pontos totais: " + totalPontos);
        System.out.println("Nem sempre gera a solucao ideal");
        System.out.println();
    }

    public static void questao3() {

        Set<String> estados = new HashSet<>(
                Arrays.asList("MT", "RJ", "ES", "SP", "SC", "RS", "PR", "MS")
        );

        Map<String, Set<String>> radios = new HashMap<>();

        radios.put("Kum", new HashSet<>(Arrays.asList("SP", "SC", "RS")));
        radios.put("Kdois", new HashSet<>(Arrays.asList("RJ", "SP", "MT")));
        radios.put("Ktres", new HashSet<>(Arrays.asList("ES", "SC", "PR")));
        radios.put("Kquatro", new HashSet<>(Arrays.asList("SC", "RS")));
        radios.put("Kcinco", new HashSet<>(Arrays.asList("PR", "MS")));

        List<String> escolhidas = new ArrayList<>();

        while (!estados.isEmpty()) {

            String melhorRadio = null;

            Set<String> estadosCobertos = new HashSet<>();

            for (String radio : radios.keySet()) {

                Set<String> cobertura = new HashSet<>(estados);

                cobertura.retainAll(radios.get(radio));

                if (cobertura.size() > estadosCobertos.size()) {
                    melhorRadio = radio;
                    estadosCobertos = cobertura;
                }
            }

            estados.removeAll(estadosCobertos);

            escolhidas.add(melhorRadio);
        }

        System.out.println("Radios escolhidas: " + escolhidas);
    }

    public static void main(String[] args) {

        System.out.println("Questao 1:");
        questao1();

        System.out.println("Questao 2:");
        questao2();

        System.out.println("Questao 3:");
        questao3();
    }
}