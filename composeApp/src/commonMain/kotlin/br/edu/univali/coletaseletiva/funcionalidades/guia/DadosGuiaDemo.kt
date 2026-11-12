package br.edu.univali.coletaseletiva.funcionalidades.guia

import br.edu.univali.coletaseletiva.funcionalidades.guia.Destino.COLETA_COMUM
import br.edu.univali.coletaseletiva.funcionalidades.guia.Destino.COLETA_SELETIVA
import br.edu.univali.coletaseletiva.funcionalidades.guia.Destino.PONTO_DE_DESCARTE

/**
 * Conteúdo estático do guia de separação (PB11, decisão D-08): funciona sem internet.
 * Orientações gerais de demonstração; confira sempre as regras da prefeitura do seu município.
 * RN04: pilhas, baterias e óleo vão para PONTO_DE_DESCARTE (protegido por DadosGuiaTest).
 */
object DadosGuiaDemo {

    val itens: List<GuiaItem> = listOf(
        // ----- Papel -----
        GuiaItem(
            id = 1L,
            nome = "Papel (folhas e cadernos)",
            destino = COLETA_SELETIVA,
            orientacao = "Coloque seco e limpo, sem dobrar em bolinhas. Papel molhado ou com gordura vai para a coleta comum.",
            sinonimos = listOf("folha", "caderno", "papel sulfite", "envelope"),
        ),
        GuiaItem(
            id = 2L,
            nome = "Papelão",
            destino = COLETA_SELETIVA,
            orientacao = "Desmonte as caixas para ocupar menos espaço e mantenha o papelão seco.",
            sinonimos = listOf("caixa de papelão", "caixa de mudança", "caixa de entrega"),
        ),
        GuiaItem(
            id = 3L,
            nome = "Caixa de leite (longa vida)",
            destino = COLETA_SELETIVA,
            orientacao = "Enxágue, deixe escorrer e amasse antes de colocar na coleta seletiva.",
            sinonimos = listOf("tetra pak", "caixa de suco", "embalagem longa vida"),
        ),
        GuiaItem(
            id = 4L,
            nome = "Jornal e revista",
            destino = COLETA_SELETIVA,
            orientacao = "Junte e amarre ou coloque em uma sacola. Não precisa tirar os grampos.",
            sinonimos = listOf("panfleto", "folheto", "encarte"),
        ),

        // ----- Plástico -----
        GuiaItem(
            id = 5L,
            nome = "Garrafa PET",
            destino = COLETA_SELETIVA,
            orientacao = "Esvazie, enxágue, amasse e coloque a tampa de volta.",
            sinonimos = listOf("refrigerante", "garrafa de água", "garrafa plástica"),
        ),
        GuiaItem(
            id = 6L,
            nome = "Embalagem de margarina",
            destino = COLETA_SELETIVA,
            orientacao = "Retire o excesso de gordura com papel e enxágue antes de descartar.",
            sinonimos = listOf("pote de margarina", "pote de sorvete", "pote plástico"),
        ),
        GuiaItem(
            id = 7L,
            nome = "Sacolas plásticas",
            destino = COLETA_SELETIVA,
            orientacao = "Junte várias sacolas limpas dentro de uma só. Reutilizar antes de descartar é ainda melhor.",
            sinonimos = listOf("sacola de mercado", "saco plástico"),
        ),
        GuiaItem(
            id = 8L,
            nome = "Isopor",
            destino = COLETA_SELETIVA,
            orientacao = "Só limpo e seco. Bandeja de isopor com restos de comida vai para a coleta comum.",
            sinonimos = listOf("bandeja de isopor", "poliestireno", "protetor de eletrodoméstico"),
        ),

        // ----- Metal -----
        GuiaItem(
            id = 9L,
            nome = "Lata de alumínio",
            destino = COLETA_SELETIVA,
            orientacao = "Esvazie e amasse para ocupar menos espaço.",
            sinonimos = listOf("latinha", "lata de refrigerante", "lata de cerveja"),
        ),
        GuiaItem(
            id = 10L,
            nome = "Lata de aço (conserva)",
            destino = COLETA_SELETIVA,
            orientacao = "Enxágue e, se der, coloque a tampa dentro da lata para evitar cortes.",
            sinonimos = listOf("lata de milho", "lata de ervilha", "lata de sardinha", "enlatado"),
        ),
        GuiaItem(
            id = 11L,
            nome = "Tampinhas",
            destino = COLETA_SELETIVA,
            orientacao = "Junte as tampinhas de metal e de plástico em uma garrafa ou pote para não se perderem na coleta.",
            sinonimos = listOf("tampa de garrafa", "tampinha de metal", "tampinha plástica"),
        ),

        // ----- Vidro -----
        GuiaItem(
            id = 12L,
            nome = "Garrafa de vidro",
            destino = COLETA_SELETIVA,
            orientacao = "Esvazie e enxágue. Se estiver quebrada, embrulhe em jornal para proteger quem coleta.",
            sinonimos = listOf("garrafa de cerveja", "garrafa de vinho", "long neck"),
        ),
        GuiaItem(
            id = 13L,
            nome = "Pote de vidro",
            destino = COLETA_SELETIVA,
            orientacao = "Enxágue e separe a tampa (tampa de metal também vai para a coleta seletiva).",
            sinonimos = listOf("vidro de conserva", "pote de geleia", "frasco"),
        ),
        GuiaItem(
            id = 14L,
            nome = "Espelho e vidro plano",
            destino = COLETA_COMUM,
            orientacao = "Não é reciclado junto com garrafas e potes. Embrulhe bem em jornal ou papelão e escreva 'vidro'.",
            sinonimos = listOf("vidro de janela", "box de banheiro", "vidro quebrado"),
        ),

        // ----- Ponto de descarte (RN04 e materiais especiais) -----
        GuiaItem(
            id = 15L,
            nome = "Pilhas",
            destino = PONTO_DE_DESCARTE,
            orientacao = "Têm metais tóxicos. Guarde em um pote fechado e leve a um ponto de descarte; nunca coloque na coleta porta a porta.",
            sinonimos = listOf("pilha AA", "pilha AAA", "pilha de relógio"),
        ),
        GuiaItem(
            id = 16L,
            nome = "Baterias de celular e notebook",
            destino = PONTO_DE_DESCARTE,
            orientacao = "Não fure nem amasse. Leve a um ponto de descarte ou à loja que recebe; nunca coloque na coleta porta a porta.",
            sinonimos = listOf("bateria", "carregador portátil", "power bank"),
        ),
        GuiaItem(
            id = 17L,
            nome = "Óleo de cozinha usado",
            destino = PONTO_DE_DESCARTE,
            orientacao = "Espere esfriar, guarde em garrafa PET fechada e leve a um ponto de descarte. Nunca jogue na pia.",
            sinonimos = listOf("óleo de fritura", "gordura de cozinha", "azeite usado"),
        ),
        GuiaItem(
            id = 18L,
            nome = "Lâmpadas",
            destino = PONTO_DE_DESCARTE,
            orientacao = "Lâmpadas fluorescentes têm mercúrio: não quebre e leve a um ponto de descarte que recebe lâmpadas.",
            sinonimos = listOf("lâmpada fluorescente", "lâmpada LED", "lâmpada queimada"),
        ),
        GuiaItem(
            id = 19L,
            nome = "Eletrônicos",
            destino = PONTO_DE_DESCARTE,
            orientacao = "Celulares, cabos e aparelhos quebrados vão para um ponto de descarte de lixo eletrônico.",
            sinonimos = listOf("celular", "cabo", "fone de ouvido", "computador", "lixo eletrônico"),
        ),
        GuiaItem(
            id = 20L,
            nome = "Roupas e tecidos",
            destino = PONTO_DE_DESCARTE,
            orientacao = "Peças em bom estado podem ser doadas. As demais vão para um ponto que recebe tecidos.",
            sinonimos = listOf("roupa velha", "pano", "lençol", "toalha"),
        ),

        // ----- Coleta comum (orgânicos e rejeitos) -----
        GuiaItem(
            id = 21L,
            nome = "Restos de comida (orgânicos)",
            destino = COLETA_COMUM,
            orientacao = "Coloque em saco bem fechado. Cascas de frutas e legumes também podem virar adubo em uma composteira.",
            sinonimos = listOf("orgânico", "casca de fruta", "borra de café", "resto de alimento"),
        ),
        GuiaItem(
            id = 22L,
            nome = "Papel higiênico usado",
            destino = COLETA_COMUM,
            orientacao = "Não é reciclável. Coloque no lixo do banheiro, em saco fechado.",
            sinonimos = listOf("lixo de banheiro", "rejeito sanitário"),
        ),
        GuiaItem(
            id = 23L,
            nome = "Fralda descartável",
            destino = COLETA_COMUM,
            orientacao = "Não é reciclável. Enrole, feche bem e coloque no lixo comum.",
            sinonimos = listOf("fralda", "absorvente"),
        ),
        GuiaItem(
            id = 24L,
            nome = "Guardanapo sujo",
            destino = COLETA_COMUM,
            orientacao = "Papel com gordura ou restos de comida não é reciclado: vai para a coleta comum.",
            sinonimos = listOf("guardanapo", "papel toalha usado", "lenço de papel"),
        ),
        GuiaItem(
            id = 25L,
            nome = "Caixa de pizza engordurada",
            destino = COLETA_COMUM,
            orientacao = "A parte com gordura vai para a coleta comum. Se a tampa estiver limpa, ela pode ir para a coleta seletiva.",
            sinonimos = listOf("caixa de pizza", "papelão sujo"),
        ),

        // ----- Orientações vindas da pesquisa do HOW IV (conteúdo, não regra de sistema) -----
        GuiaItem(
            id = 26L,
            nome = "Quem separa o lixo",
            destino = COLETA_SELETIVA,
            orientacao = "A separação começa em casa: cada morador separa os recicláveis secos do lixo orgânico e dos rejeitos antes da coleta.",
            sinonimos = listOf("separação", "responsabilidade", "como separar"),
        ),
        GuiaItem(
            id = 27L,
            nome = "Quando colocar na calçada",
            destino = COLETA_SELETIVA,
            orientacao = "Coloque o lixo na calçada só no dia da coleta do seu setor (veja o Calendário) e siga o horário informado pela prefeitura. Evite deixar de um dia para o outro.",
            sinonimos = listOf("horário", "calçada", "dia da coleta", "quando colocar o lixo"),
        ),
    )
}
