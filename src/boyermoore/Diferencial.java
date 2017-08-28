package boyermoore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Diferencial extends Algoritmo{

    int limite = 1000;
    StringBuilder texto = new StringBuilder();
  
    public void abreArquivo(String key2) {
        try {
            FileReader arqGrande = new FileReader("entradaDiferencial.txt");
            BufferedReader entrada = new BufferedReader(arqGrande);

            while (entrada.ready()) {
                linha = entrada.readLine();
            }
            entrada.close();
            divideArquivo(linha, limite, key2);

        } catch (IOException e) {
            linha = "NULL";
            System.out.println("Erro ao abrir o arquivo entradaDiferencial.txt");
        }
    }

    /* funcao que le byte a byte e quando chega no limite (1000 bytes), passa para um arquivo.txt */
    public void divideArquivo(String linha, int limite, String key2) {
        char[] frase = linha.toCharArray();
        int tamanho = linha.length();
        int num = 1, i=0, j=0, fim = limite;

        while(i < limite && j < tamanho){
            texto.append(frase[j]);//adiciona os bytes na String texto
            i++;
            j++;
            
            if(i == limite){//se o i chegar no limite de bytes
                entrada(texto, num, key2);//passa a String texto para um arquivo de texto
                num++;
                texto.delete(0, texto.length());//deleta a String texto
                i = limite;
                limite = limite + fim;
            }
            
            if(j == tamanho){//se o j chegar no fim da entradaDiferencial.txt
                entrada(texto,num, key2);//passa a String texto para um arquivo de texto
                num++;
                texto.delete(0, texto.length());//deleta a String texto
            }
        }
    }

    public void entrada(StringBuilder texto, int num, String key2) {
        try {
            FileWriter saida = new FileWriter("entradaDif" + num + ".txt");
            BufferedWriter sai = new BufferedWriter(saida);

            //escreve no arquivo
            sai.write(texto.toString());
            sai.newLine();
            sai.flush();
            sai.close();
            
            busca(texto, num, key2);//busca por ocorrencias da palavra chave no arquivo

        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo entradaDif.txt");
        }
    }
    
    /* funcao que busca por ocorrencias da palavra chave no arquivo */
    public void busca(StringBuilder texto, int num, String key2){
        char[] frase = texto.toString().toCharArray();
        List<Integer> posicoes = new ArrayList();
        int cont = 0, posicao=-1,flag=0,j=tam-1,i=tam-1,fim=tam-1;
        
        while(i<frase.length && j<tam){
            if(frase[i] == chave[j]){
                while(frase[i] == chave[j]){
                    i--;
                    j--;
                    if(j<0){
                        flag=1;
                        posicao = i+1;
                        cont+=1;
                        break;
                    }
                }
            }else{
                for(int a=0;a<alfabeto.length;a++){
                    if(frase[i] == alfabeto[a]){
                        i+=valor[a];
                        if(i>=frase.length){
                            break;
                        }
                    }
                }
                flag = 0;
            }
            j=tam-1;
            fim = fim+1;
            i=fim;
            if(flag==1){
                posicoes.add(posicao);
            }
        }
        saidaBusca(cont, (ArrayList)posicoes,num);
        substitui(frase, key2, cont, num);//realiza a substituicao 
    }
    
    /* funcao que escreve as ocorrencias no arquivo */
    public void saidaBusca(int cont, ArrayList posicoes, int num){
        try {
            FileWriter saida = new FileWriter("saidaDif"+num+".txt", true);
            BufferedWriter sai = new BufferedWriter(saida);

            //escreve no arquivo
            sai.write("Total de ocorrências no arquivo " + num + ": " + cont);
            sai.newLine();
            sai.write("Posições: " + posicoes);
            sai.newLine();
            sai.flush();
            sai.close();

        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo saidaDif"+num+".txt");
        }
    }
    
    /* funcao que realiza a substituicao da palavra chave */
    public void substitui(char[] frase, String key2, int cont, int num){
        chave2 = key2.toCharArray();
        int tamanho = chave2.length;
        List<Character> fraseAux = new ArrayList();
        char[] frase2 = null;
        
        if(tamanho > tam){
            frase2 = new char[frase.length + (cont*(tamanho-tam))];
        }else{
            frase2 = new char[frase.length];
        }
        
        int i = 0, j = 0, k = 0, l = 0, flag = 0;
        while (k < frase.length) {
            if (frase[k] == chave[j]) {
                l = k;
                while (frase[l] == chave[j]) {
                    fraseAux.add(frase[l]);
                    flag = 1;
                    j++;
                    l++;
                    if (j == chave.length) {
                        for (int n = 0; n < tamanho; n++) {
                            frase2[i] = chave2[n];
                            i++;
                        }
                        flag = 0;
                        k += tam;
                        break;
                    }

                    if (l >= frase.length) {
                        for (int m = 0; m < fraseAux.size(); m++) {
                            frase2[i] = fraseAux.get(m);
                            i++;
                        }
                        fraseAux.clear();
                        flag = 0;
                        k += tam;
                        break;
                    }
                }
                if (flag == 1) {
                    for (int m = 0; m < fraseAux.size(); m++) {
                        frase2[i] = fraseAux.get(m);
                        i++;
                    }
                    k += fraseAux.size();
                }
            } else {
                frase2[i] = frase[k];
                k++;
                i++;
            }
            j = 0;
            fraseAux.clear();
        }
        saida(frase2, num);
    }
    
    public void saida(char[] frase2, int num){
        try {
            FileWriter saida = new FileWriter("saidaDif"+num+"Substituido.txt", true);
            BufferedWriter sai = new BufferedWriter(saida);

            //escreve no arquivo
            sai.write(frase2);
            sai.newLine();
            sai.flush();
            sai.close();

        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo saidaDif"+num+"Substituido.txt");
        }
    }
}
