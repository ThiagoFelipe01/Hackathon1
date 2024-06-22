<?php include 'templates/header.php'; ?>


    <div class="containeragend">
        <h1>Agendar</h1>
        <form action="agendar.php" method="POST">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome" required>
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
                <label for="tipo">Tipo de Agendamento:</label>
                <select class="form-control" id="tipo" name="tipo" required>
                    <option value="consulta">Consulta</option>
                    <option value="visita">Visita</option>
                </select>
                <label for="data">Data:</label>
                <input type="date" class="form-control" id="data" name="data" required>
                <label for="hora">Hora:</label>
                <input type="time" class="form-control" id="hora" name="hora" required>
                <label for="observacoes">Observações:</label>
                <textarea class="form-control" id="observacoes" name="observacoes"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Agendar</button>
        </form>
    </div>



    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<?php include 'templates/footer.php'; ?>