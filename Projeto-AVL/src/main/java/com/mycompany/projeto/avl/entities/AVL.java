/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.avl.entities;

/**
 *
 * @author andre
 */
public class AVL {

    public No raiz;

    public int obterAltura(No no) {
        if (no == null)
            return 0;
        return no.altura;
    }

    public int obterBalanceamento(No no) {
        if (no == null)
            return 0;
        return obterAltura(no.esquerdo) - obterAltura(no.direito);
    }

    public No rotacionarParaDireita(No noDesbalanceado) {
        No noEsquerdo = noDesbalanceado.esquerdo;
        No noEsquerdoDireito = noEsquerdo.direito;

        noEsquerdo.direito = noDesbalanceado;
        noDesbalanceado.esquerdo = noEsquerdoDireito;

        noDesbalanceado.altura = Math.max(obterAltura(noDesbalanceado.esquerdo), obterAltura(noDesbalanceado.direito)) + 1;
        noEsquerdo.altura = Math.max(obterAltura(noEsquerdo.esquerdo), obterAltura(noEsquerdo.direito)) + 1;

        return noEsquerdo;
    }

    public No rotacionarParaEsquerda(No noDesbalanceado) {
        No noDireito = noDesbalanceado.direito;
        No noDireitoEsquerdo = noDireito.esquerdo;

        noDireito.esquerdo = noDesbalanceado;
        noDesbalanceado.direito = noDireitoEsquerdo;

        noDesbalanceado.altura = Math.max(obterAltura(noDesbalanceado.esquerdo), obterAltura(noDesbalanceado.direito)) + 1;
        noDireito.altura = Math.max(obterAltura(noDireito.esquerdo), obterAltura(noDireito.direito)) + 1;

        return noDireito;
    }

    public No adicionarNo(No noAtual, int valor) {

        if (noAtual == null)
            return new No(valor);

        if (valor < noAtual.valor)
            noAtual.esquerdo = adicionarNo(noAtual.esquerdo, valor);
        else if (valor > noAtual.valor)
            noAtual.direito = adicionarNo(noAtual.direito, valor);
        else 
            return noAtual;

        noAtual.altura = 1 + Math.max(obterAltura(noAtual.esquerdo), obterAltura(noAtual.direito));

        int balanceamento = obterBalanceamento(noAtual);

        if (balanceamento > 1 && valor < noAtual.esquerdo.valor)
            return rotacionarParaDireita(noAtual);

        if (balanceamento < -1 && valor > noAtual.direito.valor)
            return rotacionarParaEsquerda(noAtual);

        if (balanceamento > 1 && valor > noAtual.esquerdo.valor) {
            noAtual.esquerdo = rotacionarParaEsquerda(noAtual.esquerdo);
            return rotacionarParaDireita(noAtual);
        }

        if (balanceamento < -1 && valor < noAtual.direito.valor) {
            noAtual.direito = rotacionarParaDireita(noAtual.direito);
            return rotacionarParaEsquerda(noAtual);
        }

        return noAtual;
    }

    public No buscarNo(No noAtual, int valor) {
        if (noAtual == null || noAtual.valor == valor)
            return noAtual;

        if (valor < noAtual.valor)
            return buscarNo(noAtual.esquerdo, valor);

        return buscarNo(noAtual.direito, valor);
    }

    public No obterMenorNo(No noAtual) {
        No atual = noAtual;

        while (atual.esquerdo != null)
            atual = atual.esquerdo;

        return atual;
    }

    public No removerNo(No noAtual, int valor) {
        if (noAtual == null)
            return noAtual;

        if (valor < noAtual.valor)
            noAtual.esquerdo = removerNo(noAtual.esquerdo, valor);
        else if (valor > noAtual.valor)
            noAtual.direito = removerNo(noAtual.direito, valor);
        else {

            if ((noAtual.esquerdo == null) || (noAtual.direito == null)) {
                No temp = null;
                if (temp == noAtual.esquerdo)
                    temp = noAtual.direito;
                else
                    temp = noAtual.esquerdo;

                if (temp == null) {
                    temp = noAtual;
                    noAtual = null;
                } else 
                    noAtual = temp;
            } else {

                No temp = obterMenorNo(noAtual.direito);
                noAtual.valor = temp.valor;
                noAtual.direito = removerNo(noAtual.direito, temp.valor);
            }
        }

        if (noAtual == null)
            return noAtual;

        noAtual.altura = Math.max(obterAltura(noAtual.esquerdo), obterAltura(noAtual.direito)) + 1;

        int balanceamento = obterBalanceamento(noAtual);

        if (balanceamento > 1 && obterBalanceamento(noAtual.esquerdo) >= 0)
            return rotacionarParaDireita(noAtual);

        if (balanceamento < -1 && obterBalanceamento(noAtual.direito) <= 0)
            return rotacionarParaEsquerda(noAtual);

        if (balanceamento > 1 && obterBalanceamento(noAtual.esquerdo) < 0) {
            noAtual.esquerdo = rotacionarParaEsquerda(noAtual.esquerdo);
            return rotacionarParaDireita(noAtual);
        }

        if (balanceamento < -1 && obterBalanceamento(noAtual.direito) > 0) {
            noAtual.direito = rotacionarParaDireita(noAtual.direito);
            return rotacionarParaEsquerda(noAtual);
        }

        return noAtual;
    }

    public void exibirEmOrdem(No noAtual) {
        if (noAtual != null) {
            exibirEmOrdem(noAtual.esquerdo);
            System.out.print(noAtual.valor + " ");
            exibirEmOrdem(noAtual.direito);
        }
    }

    public void exibirPreOrdem(No noAtual) {
        if (noAtual != null) {
            System.out.print(noAtual.valor + " ");
            exibirPreOrdem(noAtual.esquerdo);
            exibirPreOrdem(noAtual.direito);
        }
    }

    public void exibirPosOrdem(No noAtual) {
        if (noAtual != null) {
            exibirPosOrdem(noAtual.esquerdo);
            exibirPosOrdem(noAtual.direito);
            System.out.print(noAtual.valor + " ");
        }
    }

    public void adicionar(int valor) {
        raiz = adicionarNo(raiz, valor);
    }

    public void remover(int valor) {
        raiz = removerNo(raiz, valor);
    }

    public void buscar(int valor) {
        No resultado = buscarNo(raiz, valor);
        if (resultado != null) {
            System.out.println("Nó encontrado: " + resultado.valor);
        } else {
            System.out.println("Nó não encontrado.");
        }
    }
}