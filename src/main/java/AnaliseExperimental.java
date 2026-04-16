import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AnaliseExperimental {

    private static final int N_ELEMENTOS = 10000; // Quantidade de elementos para os testes
    private static final int N_TESTES = 20;       // Número de repetições para média

    public static void main(String[] args) {
        System.out.println("=== Iniciando Testes de Desempenho ===\n");

        questao1();
        questao2();
        questao3();
        questao4();
        questao5();
    }

    private static void questao1() {
        System.out.println("--- Questão 1: Inserção no Final ---");

        double mediaArray = calcularMediaInsercaoFinal(new ArrayList<>(), N_ELEMENTOS);
        double mediaLinked = calcularMediaInsercaoFinal(new LinkedList<>(), N_ELEMENTOS);

        exibirResultado("ArrayList", mediaArray, "LinkedList", mediaLinked);

        System.out.println("\nAvaliando Capacidade Inicial no ArrayList (10 execuções):");
        int[] capacidades = {10, 1000, 100000};
        for (int cap : capacidades) {
            double tempo = 0;
            for (int i = 0; i < 10; i++) {
                tempo += medirInsercaoFinal(new ArrayList<>(cap), N_ELEMENTOS);
            }
            System.out.printf("Capacidade %d: %.4f ms\n", cap, tempo / 10);
        }
        System.out.println();
    }

    private static void questao2() {
        System.out.println("--- Questão 2: Inserção Aleatória ---");
        double mediaArray = 0, mediaLinked = 0;
        Random rand = new Random();

        for (int i = 0; i < N_TESTES; i++) {
            List<Integer> al = new ArrayList<>();
            List<Integer> ll = new LinkedList<>();

            long start = System.nanoTime();
            for (int j = 0; j < N_ELEMENTOS; j++) al.add(rand.nextInt(Math.max(1, al.size())), j);
            mediaArray += (System.nanoTime() - start) / 1_000_000.0;

            start = System.nanoTime();
            for (int j = 0; j < N_ELEMENTOS; j++) ll.add(rand.nextInt(Math.max(1, ll.size())), j);
            mediaLinked += (System.nanoTime() - start) / 1_000_000.0;
        }
        exibirResultado("ArrayList", mediaArray / N_TESTES, "LinkedList", mediaLinked / N_TESTES);
    }

    private static void questao3() {
        System.out.println("--- Questão 3: Remoção Extremidades ---");

        double mAI = medirRemocao(true, true);
        double mLI = medirRemocao(false, true);
        System.out.print("[Início] ");
        exibirResultado("ArrayList", mAI, "LinkedList", mLI);

        double mAF = medirRemocao(true, false);
        double mLF = medirRemocao(false, false);
        System.out.print("[Fim] ");
        exibirResultado("ArrayList", mAF, "LinkedList", mLF);
    }

    private static void questao4() {
        System.out.println("--- Questão 4: Remoção Aleatória ---");
        double mediaArray = 0, mediaLinked = 0;
        Random rand = new Random();

        for (int i = 0; i < N_TESTES; i++) {
            List<Integer> al = popular(new ArrayList<>());
            List<Integer> ll = popular(new LinkedList<>());

            long start = System.nanoTime();
            while (!al.isEmpty()) al.remove(rand.nextInt(al.size()));
            mediaArray += (System.nanoTime() - start) / 1_000_000.0;

            start = System.nanoTime();
            while (!ll.isEmpty()) ll.remove(rand.nextInt(ll.size()));
            mediaLinked += (System.nanoTime() - start) / 1_000_000.0;
        }
        exibirResultado("ArrayList", mediaArray / N_TESTES, "LinkedList", mediaLinked / N_TESTES);
    }

    private static void questao5() {
        System.out.println("--- Questão 5: Acesso Aleatório (10.000 acessos) ---");
        List<Integer> al = popular(new ArrayList<>());
        List<Integer> ll = popular(new LinkedList<>());
        Random rand = new Random();
        double mediaArray = 0, mediaLinked = 0;

        for (int i = 0; i < N_TESTES; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 10000; j++) al.get(rand.nextInt(N_ELEMENTOS));
            mediaArray += (System.nanoTime() - start) / 1_000_000.0;

            start = System.nanoTime();
            for (int j = 0; j < 10000; j++) ll.get(rand.nextInt(N_ELEMENTOS));
            mediaLinked += (System.nanoTime() - start) / 1_000_000.0;
        }
        exibirResultado("ArrayList", mediaArray / N_TESTES, "LinkedList", mediaLinked / N_TESTES);
    }

    private static List<Integer> popular(List<Integer> lista) {
        for (int i = 0; i < N_ELEMENTOS; i++) lista.add(i);
        return lista;
    }

    private static double calcularMediaInsercaoFinal(List<Integer> lista, int n) {
        double total = 0;
        for (int i = 0; i < N_TESTES; i++) {
            lista.clear();
            total += medirInsercaoFinal(lista, n);
        }
        return total / N_TESTES;
    }

    private static long medirInsercaoFinal(List<Integer> lista, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) lista.add(i);
        return (System.nanoTime() - start) / 1_000_000;
    }

    private static double medirRemocao(boolean isArray, boolean doInicio) {
        double total = 0;
        for (int i = 0; i < N_TESTES; i++) {
            List<Integer> lista = isArray ? popular(new ArrayList<>()) : popular(new LinkedList<>());
            long start = System.nanoTime();
            while (!lista.isEmpty()) {
                lista.remove(doInicio ? 0 : lista.size() - 1);
            }
            total += (System.nanoTime() - start) / 1_000_000.0;
        }
        return total / N_TESTES;
    }

    private static void exibirResultado(String n1, double t1, String n2, double t2) {
        System.out.printf("%s: %.4f ms | %s: %.4f ms\n", n1, t1, n2, t2);
        if (t1 < t2) System.out.printf("-> %s foi %.2fx mais rápida.\n", n1, t2 / t1);
        else System.out.printf("-> %s foi %.2fx mais rápida.\n", n2, t1 / t2);
    }
}