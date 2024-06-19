<?php
include('db/connection.php');

$nome = $_POST['nome'];
$email = $_POST['email'];
$telefone = $_POST['telefone'];
$tipo_agendamento = $_POST['tipo_agendamento'];
$data_agendamento = $_POST['data_agendamento'];
$horario = $_POST['horario'];

$sql = "INSERT INTO agendamentos (nome, email, telefone, tipo_agendamento, data_agendamento, horario) VALUES ('$nome', '$email', '$telefone', '$tipo_agendamento', '$data_agendamento', '$horario')";

if ($conn->query($sql) === TRUE) {
    echo "Agendamento realizado com sucesso!";
} else {
    echo "Erro: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
