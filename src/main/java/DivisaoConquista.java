public class DivisaoConquista {

    public static int buscaBinaria(int[] A, int inicio, int fim, int valor) {

        if (inicio > fim) {
            return -1;
        }

        int meio = (inicio + fim) / 2;

        if (A[meio] == valor) {
            return meio;
        }

        if (valor < A[meio]) {
            return buscaBinaria(A, inicio, meio - 1, valor);
        }

        return buscaBinaria(A, meio + 1, fim, valor);
    }

    public static int encontrarIndice(int[] A, int inicio, int fim) {

        if (inicio > fim) {
            return -1;
        }

        int meio = (inicio + fim) / 2;

        if (A[meio] == meio) {
            return meio;
        }

        if (A[meio] > meio) {
            return encontrarIndice(A, inicio, meio - 1);
        }

        return encontrarIndice(A, meio + 1, fim);
    }

    public static String inverter(String texto) {

        if (texto.length() <= 1) {
            return texto;
        }

        int meio = texto.length() / 2;

        String esquerda = texto.substring(0, meio);
        String direita = texto.substring(meio);

        return inverter(direita) + inverter(esquerda);
    }

    public static int maioria(int[] A, int inicio, int fim) {

        if (inicio == fim) {
            return A[inicio];
        }

        int meio = (inicio + fim) / 2;

        int esquerda = maioria(A, inicio, meio);
        int direita = maioria(A, meio + 1, fim);

        if (esquerda == direita) {
            return esquerda;
        }

        int contEsquerda = contar(A, esquerda, inicio, fim);
        int contDireita = contar(A, direita, inicio, fim);

        return contEsquerda > contDireita ? esquerda : direita;
    }

    public static int contar(int[] A, int valor, int inicio, int fim) {

        int contador = 0;

        for (int i = inicio; i <= fim; i++) {

            if (A[i] == valor) {
                contador++;
            }
        }

        return contador;
    }

    public static void main(String[] args) {

        int[] vetor1 = {1, 3, 5, 7, 9};

        System.out.println("Questao 1:");
        System.out.println(buscaBinaria(vetor1, 0, vetor1.length - 1, 7));

        int[] vetor2 = {-1, -1, 1, 3, 8, 9};

        System.out.println("Questao 2:");
        System.out.println(encontrarIndice(vetor2, 0, vetor2.length - 1));

        System.out.println("Questao 3:");
        System.out.println(inverter("FURB"));

        int[] vetor4 = {1, 2, 1, 1, 3};

        System.out.println("Questao 4:");
        System.out.println(maioria(vetor4, 0, vetor4.length - 1));
    }
}