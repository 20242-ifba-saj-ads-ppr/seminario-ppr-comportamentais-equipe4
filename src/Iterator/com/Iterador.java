package com;

interface Iterador<T> {
    void primeiro();
    void proximo();
    boolean acabou();
    T itemAtual();
}