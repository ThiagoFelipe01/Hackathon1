<?php include 'templates/navbar.php'; ?>
<?php include 'templates/header.php'; ?>

<div class="containerini">
    <div class="content">
        <h1>Bem-vindo ao nosso site!</h1>
        <h4>Facilitar o agendamento de vacinas e visitas para idosos é essencial para garantir sua saúde e bem-estar.<br> 
            Este serviço permite que familiares e cuidadores marquem compromissos médicos de forma rápida e 
            eficiente, assegurando que os idosos recebam a atenção necessária sem complicações.</h4>
        <a class="btn btn-primary btn-lg" href="#" role="button">Agende Agora</a>
    </div>

    <div class="containerimg">
        <img src="imagens/vacinaidoso.png" alt="a">
    </div>
</div>

<div class="container">
        <h2>Agendamento de Vacina</h2>
        <form action="agendamento.php" method="post">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="telefone">Telefone:</label>
                <input type="text" id="telefone" name="telefone" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="tipo_agendamento">Tipo de Agendamento:</label>
                <select id="tipo_agendamento" name="tipo_agendamento" class="form-control" required>
                    <option value="visita">Visita</option>
                </select>
            </div>
            <div class="form-group">
                <label for="data_agendamento">Data:</label>
                <input type="date" id="data_agendamento" name="data_agendamento" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="horario">Horário:</label>
                <input type="time" id="horario" name="horario" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Agendar</button>
        </form>
    </div>
</div>

<?php include 'templates/footer.php'; ?>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

