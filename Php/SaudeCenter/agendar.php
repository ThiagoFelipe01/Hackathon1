<?php include 'templates/header.php'; ?>

<div class="container">
        <h2>Agendamento de Vacina e Visita</h2>
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