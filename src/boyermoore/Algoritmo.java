package boyermoore;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import static java.lang.Math.abs;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Algoritmo {

    protected char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'y', 'x', 'z'};
    protected int[] valor = new int[alfabeto.length];//valor e um vetor que representa o peso de cada letra do alfabeto
    protected int tam;//tamanho da palavra chave
    protected char[] chave;//vetor da palavra chave
    protected String linha;//String referente a cada linha do arquivo lido
    protected char[] frase;//vetor da linha

    /*
    Construtor da classe Algoritmo
     */
    public Algoritmo() {
    }

    /*
    Função que insere os valores de cada letra do alfabeto de acordo com a palavra chave 
     */
    public void preProcessamento(String key) {
        tam = key.length();

        //inicializa o vetor de valores com o tamanho da palavra chave
        for (int i = 0; i < alfabeto.length; i++) {
            valor[i] = tam;
        }

        System.out.println("Vetor de valores do alfabeto: ");
        System.out.print(" ( ");
        for (int i = 0; i < alfabeto.length; i++) {
            System.out.print(valor[i] + ",");
        }
        System.out.print(" ) ");

        //insercao da palavra chave em um vetor
        chave = key.toCharArray();

        //atualizacao dos valores no vetor apos a comparacao com a palavra chave
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                if (chave[i] == alfabeto[j]) {
                    valor[j] = abs(tam - (i + 1));//so aceita valores positivos
                }
            }
        }

        System.out.println("\nVetor de valores do alfabeto após a atualização: ");
        System.out.print(" ( ");
        for (int i = 0; i < alfabeto.length; i++) {
            System.out.print(valor[i] + ",");
        }
        System.out.print(" ) ");
    }

    /*
    funcao que faz a leitura do arquivo entrada01.txt e faz a chamada da funcao buscaPalavra(String linha) para
    cada linha do arquivo
     */
    public void leArquivo() {
        try {
            /* nao aceita caracteres especiais
            FileReader arq = new FileReader("entrada1.txt");
            BufferedReader ent = new BufferedReader(arq);
             */
            BufferedReader ent = new BufferedReader(new InputStreamReader(new FileInputStream("entrada1.txt"), "UTF-8"));

            int num = 0;
            while (ent.ready()) {
                num++;
                linha = ent.readLine();
                buscaPalavra(linha, num);
            }
            ent.close();
        } catch (IOException e) {
            linha = "NULL";
            System.out.println("Erro ao abrir o arquivo entrada1.txt");
        }
    }

    /*funcao que busca por ocorrencias da palavra chave em cada linha do arquivo*/
    public void buscaPalavra(String linha, int num) {
        frase = linha.toCharArray();
        int cont = 0;
        int posicao = 0;
        int i = 1;
        do {
            while (chave[tam - i] == frase[tam - i]) {
                if (i == tam - 1) {
                    cont++;
                }
                i++;
            }
            i++;
            saidaArquivo(num, cont, posicao);//chama a funcao de saida a cada linha lida do arquivo
        } while (i < tam);
    }

    /*
    funcao que cria arquivo de saida contendo a ocorrencia da palavra chave em cada linha do arquivo lido
    e a posicao inicial de cada ocorrencia da palavra
     */
    public void saidaArquivo(int num, int cont, int posicao) {
        try {
            /* não aceita caracteres especiais
            FileWriter saida = new FileWriter("saida1.txt");
            BufferedWriter sai = new BufferedWriter(saida);
            sai.write("Linha "+ num + " - Número de ocorrências da palavra: "+ cont + " Posição de ocorrência: "+ posicao);
            sai.write();
            sai.newLine();
            sai.flush();
             */
            OutputStreamWriter sai = new OutputStreamWriter(new FileOutputStream("saida1.txt"), "UTF-8");
            sai.write("Linha " + num + " - Número de ocorrências da palavra: " + cont + " Posição de ocorrência: " + posicao);
            sai.close();

        } catch (IOException ex) {
            //Logger.getLogger(Algoritmo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao escrever no arquivo saida1.txt");
        }
    }

}
