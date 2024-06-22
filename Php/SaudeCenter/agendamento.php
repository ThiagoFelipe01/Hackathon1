<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Coletar dados do formulário
    $nome = $_POST['nome'];
    $email = $_POST['email'];
    $tipo = $_POST['tipo'];
    $data = $_POST['data'];
    $hora = $_POST['hora'];
    $observacoes = $_POST['observacoes'];

    // Dados para enviar para a API
    $agendamentoDados = array(
        'nome' => $nome,
        'email' => $email,
        'tipo' => $tipo,
        'data' => $data,
        'hora' => $hora,
        'observacoes' => $observacoes
    );

    // Converter dados para JSON
    $jsonDados = json_encode($agendamentoDados);

    // URL da API de agendamento
    $url = 'https://api.exemplo.com/agendar';

    // Inicializar cURL
    $ch = curl_init($url);

    // Configurar cURL para enviar uma requisição POST com JSON
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDados);

    // Executar a requisição
    $resposta = curl_exec($ch);

    // Verificar se houve erro na requisição
    if ($resposta === FALSE) {
        die('Erro na API: ' . curl_error($ch));
    }

    // Fechar cURL
    curl_close($ch);

    // Decodificar resposta da API
    $respostaDados = json_decode($resposta, true);

    // Exibir mensagem de sucesso ou erro
    if (isset($respostaDados['success']) && $respostaDados['success'] == true) {
        echo "Agendamento realizado com sucesso!";
    } else {
        echo "Erro ao realizar agendamento: " . $respostaDados['message'];
    }
}
?>
