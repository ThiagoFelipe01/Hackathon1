<?php include 'navbar.php'; ?>
<?php include('templates/header.php'); ?>

<div class="cardscont">
<div class="row">
    <div class="col-sm-6 mb-3 sm-0">
            <img src="imagens/doc.jpg" class="card-img-top" alt="vacina">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
    </div>
    <div class="col-sm-6">
            <img src="imagens/visita.png" class="card-img-top" alt="vacina">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
    </div>
</div>
</div>

<div class="content">
    <h1>Bem-vindo ao nosso site!</h1>
    <p>Conteúdo da página inicial.</p>
</div>
<div class="container">
    <h2>Agendamento de Vacina e Visita</h2>
    <form action="agendamento.php" method="post">
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

        <input type="submit" value="Agendar">
    </form>
</div>
<?php include('templates/footer.php'); ?>
