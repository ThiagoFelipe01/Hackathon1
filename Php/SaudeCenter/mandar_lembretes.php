<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];
    $email = $_POST['email'];
    $nome = $_POST['nome'];
    $data_vacinacao = $_POST['data_vacinacao'];

    $to = $email;
    $subject = "Lembrete de Vacinação";
    $message = "Olá " . $nome . ",\n\nEste é um lembrete de que sua vacinação está agendada para " . $data_vacinacao . ".\n\nAtenciosamente,\nEquipe de Vacinação";
    $headers = "De: noreply@vacinacao.com";

    if (mail($to, $subject, $message, $headers)) {
        // Se o e-mail foi enviado com sucesso, fazemos uma requisição para atualizar o status na API
        $updateApiUrl = 'http://localhost:8000/agendamentos/' . $id;
        $updateOptions = [
            'http' => [
                'method'  => 'PUT',
                'header'  => 'Content-type: application/json',
                'content' => json_encode(['lembrete_enviado' => 1])
            ]
        ];

        $updateResponse = file_get_contents($updateApiUrl, false, stream_context_create($updateOptions));

        if ($updateResponse === FALSE) {
            echo "Erro ao atualizar lembrete para: " . $email . "\n";
        } else {
            echo "Lembrete enviado com sucesso para: " . $email . "\n";
        }
    } else {
        echo "Erro ao enviar lembrete para: " . $email . "\n";
    }
} else {
    echo "Método de requisição inválido.";
}
?>
