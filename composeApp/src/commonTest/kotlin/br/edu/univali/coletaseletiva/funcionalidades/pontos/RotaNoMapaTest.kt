package br.edu.univali.coletaseletiva.funcionalidades.pontos

import kotlin.test.Test
import kotlin.test.assertEquals

class RotaNoMapaTest {

    @Test
    fun gera_url_do_google_maps_com_latitude_e_longitude() {
        val url = urlDaRota(-26.9, -48.7)
        assertEquals("https://www.google.com/maps/search/?api=1&query=-26.9,-48.7", url)
    }
}
