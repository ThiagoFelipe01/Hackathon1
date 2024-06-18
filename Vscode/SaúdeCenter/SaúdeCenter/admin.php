<?php
include('db/connection.php');
include('templates/header.php');
?>

<div class="container">
    <h2>Administração - Agendamentos</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Tipo de Agendamento</th>
            <th>Data</th>
            <th>Horário</th>
        </tr>

        <?php
        $sql = "SELECT * FROM agendamentos";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                echo "<tr><td>" . $row["id"]. "</td><td>" . $row["nome"]. "</td><td>" . $row["email"]. "</td><td>" . $row["telefone"]. "</td><td>" . $row["tipo_agendamento"]. "</td><td>" . $row["data_agendamento"]. "</td><td>" . $row["horario"]. "</td></tr>";
            }
        } else {
            echo "<tr><td colspan='7'>Nenhum agendamento encontrado</td></tr>";
        }
        $conn->close();
        ?>
    </table>
</div>

<?php include('templates/footer.php'); ?>
