<?php include 'templates/header.php'; ?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Lembretes de Vacinação</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .send-button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .send-button:disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }
    </style>
</head>
<body>

<h1>Lembretes de Vacinação</h1>

<?php

$apiUrl = 'http://localhost:8000/agendamentos';
$options = [
    'http' => [
        'method'  => 'GET',
        'header'  => 'Content-type: application/json'
    ]
];

$response = file_get_contents($apiUrl, false, stream_context_create($options));

if ($response === FALSE) {
    die('Erro ao acessar a API');
}

$agendamentos = json_decode($response, true);

if (json_last_error() !== JSON_ERROR_NONE) {
    die('Erro ao decodificar a resposta JSON: ' . json_last_error_msg());
}

$data_atual = date('Y-m-d');

if (!empty($agendamentos)) {
    echo "<table>";
    echo "<tr><th>ID</th><th>Nome</th><th>Email</th><th>Data de Vacinação</th><th>Lembrete Enviado</th><th>Ação</th></tr>";
    foreach ($agendamentos as $agendamento) {
        echo "<tr>";
        echo "<td>{$agendamento['id']}</td>";
        echo "<td>{$agendamento['nome']}</td>";
        echo "<td>{$agendamento['email']}</td>";
        echo "<td>{$agendamento['data_vacinacao']}</td>";
        echo "<td>" . ($agendamento['lembrete_enviado'] ? 'Sim' : 'Não') . "</td>";
        echo "<td>";
        if (!$agendamento['lembrete_enviado']) {
            echo "<form method='POST' action='send_reminder.php'>";
            echo "<input type='hidden' name='id' value='{$agendamento['id']}'>";
            echo "<input type='hidden' name='email' value='{$agendamento['email']}'>";
            echo "<input type='hidden' name='nome' value='{$agendamento['nome']}'>";
            echo "<input type='hidden' name='data_vacinacao' value='{$agendamento['data_vacinacao']}'>";
            echo "<button type='submit' class='send-button'>Enviar Lembrete</button>";
            echo "</form>";
        } else {
            echo "<button class='send-button' disabled>Lembrete Enviado</button>";
        }
        echo "</td>";
        echo "</tr>";
    }
    echo "</table>";
} else {
    echo "<p>Nenhum lembrete para enviar hoje.</p>";
}
?>

</body>
</html>
<?php include 'templates/footer.php'; ?>