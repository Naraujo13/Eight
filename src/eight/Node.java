package eight;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;

/**
 *
 * @author nicol
 */
public class Node {
    
    Node Up, Down, Left, Right, Father;     //Up, Down, Left, Right - Estados filhos para cada movimento (peça da esq, dir...); Father - Nodo que representa o pai (estado anterior)
    int estado[][];                         //Matriz que representa o estado atual
    int iVazio, jVazio;
    
    /**
     * Construtor da classe nodo
     * @param estado - matriz que representa o estado atual
     * @param Father - referência para o nodo pai
     */
    public Node(int estado[][], Node Father){
       this.estado = estado;
       Up = null;
       Down = null;
       Left = null;
       Right = null;      
       this.Father = Father;
       for (int i=0; i<3; i++){
           for (int j=0; j<3; j++){
               if (estado[i][j] == 0){
                   iVazio = i;
                   jVazio = j;
               }                 
           }
       }
    }
    
    
     /**
     * Função que troca vazio com nodo acima
     * @return retorna referência para o nodo que representa o novo estado
     */
    public Node moveUp(){
        int proxEstado[][] = new int[3][3];     //cria novo estado como clone do atual
        Node newNode;
        
        //Verifica se movimento é possível
        if (iVazio == 0) 
            return null;
        
        //Cria novo estado como clone do antigo
        for (int i=0; i<3; i++){
            System.arraycopy(estado[i], 0, proxEstado[i], 0, 3);
        }        
        //Faz movimento
        proxEstado[iVazio][jVazio] = estado[iVazio-1][jVazio];
        proxEstado[iVazio-1][jVazio] = 0; 
        
        //Evita ciclo
        if(this.Father != null) {
            if (Arrays.deepEquals(this.Father.estado, proxEstado))
                return null;
        }
        
        newNode = new Node(proxEstado, this);
        Up = newNode;
        return newNode;
    }
    
     /**
     * Função que troca vazio com nodo abaixo
     * @return retorna referência para o nodo que representa o novo estado
     */
    public Node moveDown(){
        int proxEstado[][] = new int[3][3];     //cria novo estado como clone do atual
        Node newNode;
        
        //Verifica se movimento é possível
        if (iVazio == 2) 
            return null;
        
        //Cria novo estado como clone do antigo
        for (int i=0; i<3; i++){
            System.arraycopy(estado[i], 0, proxEstado[i], 0, 3);
        }  
        
        //Faz movimento
        proxEstado[iVazio][jVazio] = estado[iVazio+1][jVazio];
        proxEstado[iVazio+1][jVazio] = 0; 
        
         //Evita ciclo
        if(this.Father != null) {
            if (Arrays.deepEquals(this.Father.estado, proxEstado))
                return null;
        }
        
        newNode = new Node(proxEstado, this);
        Down = newNode;
        return newNode;
    }
    
    /**
     * Função que troca vazio com nodo a sua esquerda
     * @return retorna referência para o nodo que representa o novo estado
     */
    public Node moveLeft(){
        int proxEstado[][] = new int[3][3];     
        Node newNode;
        
        //Verifica se movimento é possível
        if (jVazio == 0) 
            return null;
        
        //Cria novo estado como clone do antigo
          for (int i=0; i<3; i++){
            System.arraycopy(estado[i], 0, proxEstado[i], 0, 3);
        }  
        
        
        //Faz movimento
        proxEstado[iVazio][jVazio] = estado[iVazio][jVazio-1];
        proxEstado[iVazio][jVazio-1] = 0; 
        
        //Evita ciclo
        if(this.Father != null) {
            if (Arrays.deepEquals(this.Father.estado, proxEstado))
                return null;
        }
        
        newNode = new Node(proxEstado, this);
        Left = newNode;
        return newNode;
    }
    
    /**
     * Função que troca vazio com nodo a sua direita
     * @return retorna referência para o nodo que representa o novo estado
     */
    public Node moveRight(){
        int proxEstado[][] = new int[3][3];     
        Node newNode;
        
        //Verifica se movimento é possível
        if (jVazio == 2) 
            return null;
        
        //Cria novo estado como clone do antigo
        for (int i=0; i<3; i++){
            System.arraycopy(estado[i], 0, proxEstado[i], 0, 3);
        }  
        
        //Faz movimento
        proxEstado[iVazio][jVazio] = estado[iVazio][jVazio+1];
        proxEstado[iVazio][jVazio+1] = 0; 
        
         //Evita ciclo
        if(this.Father != null) {
            if (Arrays.deepEquals(this.Father.estado, proxEstado))
                return null;
        }
        
        newNode = new Node(proxEstado, this);
        Right = newNode;
        return newNode;
    }
}
