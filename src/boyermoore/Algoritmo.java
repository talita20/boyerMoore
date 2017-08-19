package boyermoore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

    /* Construtor da classe Algoritmo */
    public Algoritmo() {
    }

    /* função que insere os valores de cada letra do alfabeto de acordo com a palavra chave */
    public void preProcessamento(String key) {
        tam = key.length();

        //inicializa o vetor de valores com o tamanho da palavra chave
        for (int i = 0; i < alfabeto.length; i++) {
            valor[i] = tam;
        }

//        System.out.println("Vetor de valores do alfabeto: ");
//        System.out.print(" ( ");
//        for (int i = 0; i < alfabeto.length; i++) {
//            System.out.print(valor[i] + ",");
//        }
//        System.out.print(" ) ");
        //insercao da palavra chave em um vetor
        chave = key.toCharArray();

        //atualizacao dos valores no vetor apos a comparacao com a palavra chave
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                if (chave[i] == alfabeto[j]) {
                    valor[j] -= i + 1;
                }
                if (valor[j] <= 0) {
                    valor[j] = 1;
                }
            }
        }

//        System.out.println("\nVetor de valores do alfabeto após a atualização: ");
//        System.out.print(" ( ");
//        for (int i = 0; i < alfabeto.length; i++) {
//            System.out.print(valor[i] + ",");
//        }
//        System.out.print(" ) ");
    }

    /* funcao que faz a leitura do arquivo entrada01.txt e faz a chamada da funcao buscaPalavra(String linha) para
    cada linha do arquivo */
    public void leArquivo() {
        try {
            /* nao aceita caracteres especiais
            FileReader arq = new FileReader("entrada1.txt");
            BufferedReader ent = new BufferedReader(arq);
             */

            //aceita caracteres especiais
            BufferedReader ent = new BufferedReader(new InputStreamReader(new FileInputStream("entrada1.txt"), "ISO-8859-1"));

            int num = 0;//linha do arquivo
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

    /* funcao que busca por ocorrencias da palavra chave em cada linha do arquivo */
    public void buscaPalavra(String linha, int num) {
        frase = linha.toCharArray();//passa a linha do arquivo para um vetor
        /*
        cont -> contador de ocorrencias
        posicao -> posicao inicial do padrao
        flag -> verifica se achou o padrao
        j -> percorre a palavra chave
        i -> percorre a frase
        fim -> guarda o valor inicial de i
        */
        int cont = 0, posicao = -1, flag = 0, j = tam - 1, i = tam - 1, fim = tam-1;
        while (i < frase.length && j < tam) {
            if (frase[i] == chave[j]) {//caso os caracteres sejam iguais 
                while (frase[i] == chave[j]) {//enquanto os caracteres sao iguais
                    i--;
                    j--;
                    if (j == 0) {//se chegou no inicio da palavra chave
                        flag = 1;//achou o padrao
                        posicao = i;//salva posicao inicial
                        break;
                    }
                }
            } else {//se os caracteres sao diferentes
                i += valor[i];//salto
                flag = 0;//nao achou o padrao
            }
                j = tam - 1;//reposiciona o j no fim da palavra chave
                fim = fim+1;//incrementa o fim
                i = fim;//reposiciona o i na frase
            if (flag == 1) {//se encontrou o padrao
                cont += 1;
                saidaArquivo(num, cont, posicao);//escreve no arquivo
            }
        }
    }


    /* funcao que cria arquivo de saida contendo a ocorrencia da palavra chave em cada linha do arquivo lido
    e a posicao inicial de cada ocorrencia da palavra */
    public void saidaArquivo(int num, int cont, int posicao) {
        try {
            /* não aceita caracteres especiais */
            FileWriter saida = new FileWriter("saida1.txt", true);
            BufferedWriter sai = new BufferedWriter(saida);

            //escreve no arquivo
            sai.write("Linha " + num + " - Número de ocorrências da palavra: " + cont + " Posição de ocorrência: " + posicao);
            sai.newLine();
            sai.flush();
            sai.close();

        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo saida1.txt");
        }
    }

}
