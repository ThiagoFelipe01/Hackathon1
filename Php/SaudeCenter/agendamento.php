<?php include 'templates/header.php'; ?>

<div class="containeragendamento">
    <h1>Agendamento</h1>

    <form action="agendamento.php" method="POST">
        <div class="form-group">

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="telefone">Telefone:</label>
        <input type="text" id="telefone" name="telefone" required>

        <label for="tipo_agendamento">Tipo de Agendamento:</label>
        <select id="tipo_agendamento" name="tipo_agendamento" required>
            <option value="vacina">Vacina</option>
            <option value="visita">Visita</option>
        </select>

        <label for="data_agendamento">Data:</label>
        <input type="date" id="data_agendamento" name="data_agendamento" required>

        <label for="horario">Horário:</label>
        <input type="time" id="horario" name="horario" required>
        <br>
        <label></label>
        <input class="btn btn-primary" type="submit" value="Agendar">
    
        </div>
    </form>
</div>

<?php

    $apiUrl = 'http://localhost:8000/agendamentos';
    

    $options = [
        'http' => [
            'header' => [
                'Content-Type: application/json' 
            ]
        ]
    ];

    
    $response = file_get_contents($apiUrl, false, stream_context_create($options));

    
    if ($response !== false) {
        
        $responseData = json_decode($response, true); 

        // Exibir os dados da resposta
        //var_dump($responseData);
    } else {
        // Erro ao fazer a requisição
        echo "Erro ao conectar na API Node";
    }
?>

<?php include 'templates/footer.php'; ?>