<?php include 'templates/header.php'; ?>

<div class="containeragendamento">
    <h1>Agendamento</h1>

    <form action="agendamento.php" method="POST">
        <div class="form-group">

            <label for="nome">Nome:</label>
            <select name="nome" id="nome" class="form-control"></select>

            <label for="data">Data:</label>
            <select name="data" id="data" class="form-control"></select>

            <label for="hora">Hora:</label>
            <select name="hora" id="hora" class="form-control"></select>
        </div>
</div>

<?php
    // URL da API Node
    $apiUrl = 'http://localhost:8000/agendamentos';
    
    // Opções da requisição (opcional)
    $options = [
        'http' => [
            'header' => [
                'Content-Type: application/json' // Definir cabeçalho Content-Type
            ]
        ]
    ];

    // Fazer a requisição HTTP
    $response = file_get_contents($apiUrl, false, stream_context_create($options));

    // Verificar se a requisição foi bem-sucedida
    if ($response !== false) {
        // Processar a resposta da API
        $responseData = json_decode($response, true); // Assumindo que a resposta é JSON

        // Exibir os dados da resposta
        //var_dump($responseData);
    } else {
        // Erro ao fazer a requisição
        echo "Erro ao conectar na API Node";
    }
?>

<?php include 'templates/footer.php'; ?>