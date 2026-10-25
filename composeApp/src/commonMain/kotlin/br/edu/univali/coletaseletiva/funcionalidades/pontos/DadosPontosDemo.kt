package br.edu.univali.coletaseletiva.funcionalidades.pontos

// dados de demonstração, fictícios
object DadosPontosDemo {

    val lista = listOf(
        // Itajaí (município id = 1)
        PontoDeDescarte(
            id = 101L,
            municipioId = 1L,
            nome = "Ecoponto Centro - Itajaí",
            endereco = "Rua Lauro Müller, 150 - Centro, Itajaí - SC",
            horario = "Segunda a Sexta, das 8h às 17h",
            latitude = -26.9085,
            longitude = -48.6612,
            materiais = listOf(
                MaterialAceito(Material.PILHAS_BATERIAS),
                MaterialAceito(Material.ELETRONICOS, limite = "até 5 kg"),
                MaterialAceito(Material.OLEO, limite = "em garrafa PET de até 2 litros"),
                MaterialAceito(Material.PAPEL),
            ),
        ),
        PontoDeDescarte(
            id = 102L,
            municipioId = 1L,
            nome = "Ponto Verde Fazenda",
            endereco = "Av. Sete de Setembro, 800 - Fazenda, Itajaí - SC",
            horario = "Segunda a Sábado, das 8h às 19h",
            latitude = -26.9150,
            longitude = -48.6520,
            materiais = listOf(
                MaterialAceito(Material.ROUPAS),
                MaterialAceito(Material.OLEO),
                MaterialAceito(Material.VIDRO),
                MaterialAceito(Material.PLASTICO),
            ),
        ),
        PontoDeDescarte(
            id = 103L,
            municipioId = 1L,
            nome = "Coleta Especial Cordeiros",
            endereco = "Rua Dr. Reinaldo Schmithausen, 1200 - Cordeiros, Itajaí - SC",
            horario = "Segunda a Sexta, das 8h às 18h",
            latitude = -26.8870,
            longitude = -48.6800,
            materiais = listOf(
                MaterialAceito(Material.PILHAS_BATERIAS),
                MaterialAceito(Material.ELETRONICOS, limite = "até 10 kg"),
                MaterialAceito(Material.ROUPAS),
                MaterialAceito(Material.METAL),
            ),
        ),

        // Camboriú (município id = 2)
        PontoDeDescarte(
            id = 201L,
            municipioId = 2L,
            nome = "Ecoponto Central Camboriú",
            endereco = "Rua Gustavo Richard, 200 - Centro, Camboriú - SC",
            horario = "Segunda a Sexta, das 8h às 17h",
            latitude = -27.0240,
            longitude = -48.6530,
            materiais = listOf(
                MaterialAceito(Material.PILHAS_BATERIAS),
                MaterialAceito(Material.OLEO),
                MaterialAceito(Material.ELETRONICOS, limite = "até 5 kg"),
                MaterialAceito(Material.PAPEL),
            ),
        ),
        PontoDeDescarte(
            id = 202L,
            municipioId = 2L,
            nome = "Ponto Comunitário Santa Regina",
            endereco = "Rua Tarumã, 450 - Santa Regina, Camboriú - SC",
            horario = "Segunda a Sábado, das 9h às 18h",
            latitude = -27.0120,
            longitude = -48.6410,
            materiais = listOf(
                MaterialAceito(Material.ROUPAS),
                MaterialAceito(Material.PILHAS_BATERIAS),
                MaterialAceito(Material.VIDRO),
            ),
        ),
        PontoDeDescarte(
            id = 203L,
            municipioId = 2L,
            nome = "Posto de Reciclagem Monte Alegre",
            endereco = "Rua Monte Agulhas Negras, 300 - Monte Alegre, Camboriú - SC",
            horario = "Segunda a Sexta, das 8h às 17h30",
            latitude = -27.0050,
            longitude = -48.6280,
            materiais = listOf(
                MaterialAceito(Material.ELETRONICOS),
                MaterialAceito(Material.OLEO, limite = "até 5 litros por entrega"),
                MaterialAceito(Material.ROUPAS),
                MaterialAceito(Material.PLASTICO),
                MaterialAceito(Material.METAL),
            ),
        ),
    )
}
