package br.edu.univali.coletaseletiva.funcionalidades.pontos

/**
 * Gera a URL oficial do Google Maps para pesquisa por coordenadas geográficas.
 */
fun urlDaRota(latitude: Double, longitude: Double): String {
    return "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
}
