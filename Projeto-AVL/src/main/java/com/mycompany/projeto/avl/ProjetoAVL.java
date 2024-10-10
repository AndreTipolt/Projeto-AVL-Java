/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto.avl;

import com.mycompany.projeto.avl.entities.AVL;

/**
 *
 * @author andre
 */
public class ProjetoAVL {

    public static void main(String[] args) {
         AVL arvore = new AVL();

        arvore.adicionar(10);
        arvore.adicionar(20);
        arvore.adicionar(30);
        arvore.adicionar(40);
        arvore.adicionar(50);
        arvore.adicionar(25);

        System.out.print("Exibição pré-ordem: ");
        arvore.exibirPreOrdem(arvore.raiz);
        System.out.println();

        System.out.print("Exibição em ordem: ");
        arvore.exibirEmOrdem(arvore.raiz);
        System.out.println();

        System.out.print("Exibição pós-ordem: ");
        arvore.exibirPosOrdem(arvore.raiz);
        System.out.println();

        arvore.buscar(30);
        arvore.buscar(100);

        arvore.remover(40);
        System.out.print("Exibição em ordem após remoção: ");
        arvore.exibirEmOrdem(arvore.raiz);
        System.out.println();
    }
}
