package br.edu.univali.coletaseletiva.util

// Letras acentuadas do português e a letra sem acento (já em minúsculas).
private val semAcento = mapOf(
    'á' to 'a', 'à' to 'a', 'â' to 'a', 'ã' to 'a', 'ä' to 'a',
    'é' to 'e', 'è' to 'e', 'ê' to 'e', 'ë' to 'e',
    'í' to 'i', 'ì' to 'i', 'î' to 'i', 'ï' to 'i',
    'ó' to 'o', 'ò' to 'o', 'ô' to 'o', 'õ' to 'o', 'ö' to 'o',
    'ú' to 'u', 'ù' to 'u', 'û' to 'u', 'ü' to 'u',
    'ç' to 'c',
)

/**
 * Prepara um texto para a busca do guia (PB11): tira espaços das pontas,
 * passa para minúsculas e troca letras acentuadas pela letra sem acento.
 * Ex.: " CAIXÁ de Leite " vira "caixa de leite".
 */
fun normalizarTexto(texto: String): String =
    texto.trim()
        .lowercase()
        .map { letra -> semAcento[letra] ?: letra }
        .joinToString("")
