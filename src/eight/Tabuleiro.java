/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;


/**
 *
 * @author nicol
 */
public class Tabuleiro {

     int matriz[][] = {{2,4,3},{1,0,6},{7,5,8}};    ////Caso de teste (solução longa, requer 22 movimentos) = {{2,4,3},{1,0,6},{7,5,8}}
     Node raiz;
     
    public Tabuleiro (){
     //   matriz = new int [3][3];
        do {
          // initialize();
        } while(!(isPossible(matriz)));
        raiz = new Node(matriz, null);     
    }
    
    public void initialize(){
        //Cira ArrayList com números e os aleatoriza
        List <Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 9 ; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        
        //Insere na matriz de forma aleatória
        for (int i = 0; i<3 ; i++){
            for (int j = 0; j<3 ; j++){
                matriz[i][j] = numbers.get(3*i + j);
            }
        }
        
    }
    
    

    public boolean isPossible(int matriz[][]){
        //Testa se matriz foi instanciada
        if (matriz == null)
            return false;
        //Inteiro para contar numero de inversões
        int swap = 0;
        int test;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                test = 0;
                for (int l=j; l<3; l++){
                    if ( matriz[i][l] < matriz[i][j] && matriz[i][l] != 0)
                        test++;
                }
                for (int k=i+1; k<3; k++){
                    for (int l=0; l<3; l++){
                        if (matriz[k][l] < matriz[i][j] && matriz[k][l] != 0)
                            test++;
                    }                        
                }
                swap = swap + test;
            }                    
        }
        System.out.println(swap);
         return swap%2 == 0;
    }
    
    /**
     * Função que realiza busca em profundidade
     * @return returna true se encontrou solução e falso caso não encontre
     */
    public boolean profundidade(){
        ArrayList <int[][]> estadosVisitados = new ArrayList<>();
        int maxDepht = 25;
        int currentDepht = 0;
        int flag, right, left, up, down;
        int meta[][] = {{0,1,2},{3,4,5},{6,7,8}};       
        Node currentNode = raiz;      
        Node testNode;

        estadosVisitados.add(raiz.estado);
        
        while (true){        
           if (currentDepht < maxDepht){
                //escolhe nodo para abrir (esq,baixo,cima,direita)
                testNode = null;
                right = 0;
                left = 0;
                up = 0;
                down = 0;
                flag=0;
                while (testNode == null && flag == 0){
                    if (currentNode.Left != null)
                        left=1;
                    if (currentNode.Right != null)
                        right=1;
                    if (currentNode.Up != null)
                        up = 1;
                    if (currentNode.Down != null)
                        down = 1;
                    if (currentNode.Left == null && left == 0){
                       testNode = currentNode.moveLeft(estadosVisitados);
                       left++;
                    }
                    else if(currentNode.Down == null && down == 0){
                        testNode = currentNode.moveDown(estadosVisitados);
                        down++;
                    }
                    else if (currentNode.Up == null && up == 0){
                        testNode = currentNode.moveUp(estadosVisitados);
                        up++;
                    }
                    else if (currentNode.Right == null && right == 0){
                        testNode = currentNode.moveRight(estadosVisitados);
                        right ++;
                    }
                    else if (right != 0 && left !=0 && up!=0 && down!= 0){   //todos os nodos já foram abertos, então volta para o pai
                        if (currentNode == raiz)
                            return false;
                        testNode = currentNode.Father;
                        flag++;
                   }
               }
                currentNode = testNode;
                if (flag == 0){                   
                    estadosVisitados.add(currentNode.estado);
                    currentDepht++;
                }
                else{
                    currentDepht--;
                }
            }        //Verifica se movimento é possível

           else{    //já está no limite máximo, volta ao pai
               currentNode = currentNode.Father;
               currentDepht--;
           }
          
           //Testa se está no estado final desejado, caso esteja, chama função que salva caminho correto e retorna
           if (Arrays.deepEquals(currentNode.estado, meta)){
               savePath(currentNode);
               return true;
           }
        }              
    }
    
    /**
     * Função que realiza busca em amplitude
     * @return returna true se encontrou solução e falso caso não encontre
     */
    public boolean amplitude(){
        ArrayList <int[][]> estadosVisitados = new ArrayList<>();
      //  int maxDepht = 15;
      //  int currentDepht = 0;
       // int flag, right, left, up, down;
        int meta[][] = {{0,1,2},{3,4,5},{6,7,8}};
        Node currentNode;      
        Node testNode;
        
        
        Queue<Node> fila = new ArrayDeque<>();
        
        
        if (!fila.add(raiz))
            return false;
        
        while (!fila.isEmpty()){
            
            currentNode = (Node) fila.peek();
            if (Arrays.deepEquals(currentNode.estado, meta)){
                savePath(currentNode);
                return true;
            }
            
            estadosVisitados.add(currentNode.estado);
            
            //Adiciona esquerdo
            testNode = currentNode.moveLeft(estadosVisitados);
            if (testNode != null)
                fila.add(testNode);
            
            //Adiciona direito

            testNode = currentNode.moveRight(estadosVisitados);
            if (testNode != null)
                fila.add(testNode);
            
            //Adiciona cima
            testNode = currentNode.moveUp(estadosVisitados);
            if (testNode != null)
                fila.add(testNode);
            
            //Adiciona baixo
            testNode = currentNode.moveDown(estadosVisitados);
            if (testNode != null)
                fila.add(testNode);
            
            //Remove nodo já analisado
            fila.remove();
        }
        return false;
        
        /*
        while (true){        
            if (currentDepht < maxDepht){
                //escolhe nodo para abrir (esq,baixo,cima,direita)
                testNode = null;
                right = 0;
                left = 0;
                up = 0;
                down = 0;
                flag=0;
                //Abre todos os nodos possiveis e verifica se encontrou o estado meta
                testNode = currentNode.moveLeft(estadosVisitados);
                if (testNode != null){
                    //Deu para abrir
                    left = 1;
                    if (Arrays.deepEquals(testNode.estado,meta)){
                        savePath(testNode);
                        return true;
                    }
                }
                testNode = currentNode.moveDown(estadosVisitados);
                if (testNode != null){
                    //Deu para abrir
                    down = 1;
                    if (Arrays.deepEquals(testNode.estado,meta)){
                        savePath(testNode);
                        return true;
                    }
                }
                testNode = currentNode.moveUp(estadosVisitados);
                if (testNode != null){
                    //Deu para abrir
                    up = 1;
                    if (Arrays.deepEquals(testNode.estado,meta)){
                        savePath(testNode);
                        return true;
                    }
                }
                testNode = currentNode.moveRight(estadosVisitados);
                if (testNode != null){
                    //Deu para abrir
                    right = 1;
                    if (Arrays.deepEquals(testNode.estado,meta)){
                        savePath(testNode);
                        return true;
                    }
                }
                
                // Confere se não tem que abrir nodos que são irmaos (AMPLITUDE)
                if (testNode.Father == null){
                    //Esse nodo é a raiz
                    if(left == 1){
                        currentNode = currentNode.Left;
                    }
                    else if(down == 1){
                        currentNode = currentNode.Down;
                    }
                    else if (up == 1){
                        currentNode = currentNode.Up;
                    }
                    else if (right == 1){
                        currentNode = currentNode.Right;
                    }
                    estadosVisitados.add(currentNode.estado);
                    currentDepht++;
                }
                else if (right == 0 && left == 0 && up == 0 && down == 0){
                    //Esse nodo não é a raiz, e todos os nodos em amplitude já foram abertos
                    currentDepht++;
                    //ESCREVER CODIGO AQUI
                                                           
                }
                else{
                    //Algum nodo ainda pode ser aberto
                    currentDepht--;
                    //ESCREVER CODIGO AQUI
                    
                }    
            }
        }  
        */
    }
    
    /**
     * Função que remove referencias que não são do caminho correto, deixando apenas ele.
     * @param nodoFinal 
     */
    void savePath(Node nodoFinal){
        Node anterior, atual;
        atual = nodoFinal;
        //Remove todas as referencias pros outros nodos, mantendo apenas o caminho correto
        while(atual != raiz){
            anterior = atual;
            atual = atual.Father;
            if(atual.Right != null && atual.Right != anterior)
                atual.Right = null;
            if(atual.Left != null && atual.Left != anterior)
                atual.Left = null;
            if(atual.Up != null && atual.Up != anterior)
              atual.Up = null;
            if(atual.Down != null && atual.Down != anterior)
                atual.Down = null;
        }       
    }
    
    
    /**
     * @param args the command line arguments
     *
    public static void main(String[] args) {
        // TODO code application logic here
        
        Tabuleiro game = new Tabuleiro ();
        game.initialize();
   /* //Teste pré-definido
    game.matriz[0][0] = 8;
    game.matriz[0][1] = 7;
    game.matriz[0][2] = 6;
    game.matriz[1][0] = 1;
    game.matriz[1][1] =  2;
    game.matriz[1][2] =  4;
    game.matriz[2][0] =  0;
    game.matriz[2][1] =  3;
    game.matriz[2][2] =  5;
    
    if (game.isPossible(game.matriz))
            System.out.println("Possível.");
        else
           System.out.println("Impossível."); 
        
      
    } */
    
}