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


    } else {
        echo "Erro ao conectar na API Node";
    }
?>

<?php include 'templates/footer.php'; ?>