<?php include 'templates/header.php'; ?>


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


        var_dump($responseData);
    } else {

        echo "Erro ao conectar na API Node";
    }
?>


<div class="containercadastro">
    <h1>Cadastro</h1>
    <form action="cadastro.php" method="POST">
        <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite seu nome" required>

            <label for="idade">Idade:</label>
            <input type="number" class="form-control" id="idade" name="idade" placeholder="Digite sua idade" required>

            <label for="cpf">CPF:</label>
            <input type="number" class="form-control" id="cpf" name="cpf" placeholder="Digite seu cpf" required>

            <label for="endereco">Endereço:</label>
            <input type="text" class="form-control" id="endereco" name="endereco" placeholder="Digite seu endereço" required>

            <label for="telefone">Telefone:</label>
            <input type="number" class="form-control" id="telefone" name="telefone" placeholder="Digite seu telefone" required>

            <label for="" class="">Tem Historico médico?</label>

            <div class="radioBotaoCadastro">
                <input type="radio" id="sim" name="historico_medico" value="sim" class="radio-button">
            </div>

            <div class="labelOpcaoCadastro">
                <label for="sim">Sim</label>
            </div>

            <div class="radioBotaoCadastro">
                <input type="radio" id="nao" name="historico_medico" value="nao" class="radio-button">
            </div>

            <div class="labelOpcaoCadastro">
                <label for="nao">Não</label>
            </div>

            <div class="label-input">
                <label for="historico_medico">Historico?</label>
                <input type="text" class="form-control" id="historico_medico" name="historico_medico">
            </div>

            <label for="" class="">Possui Alergia?</label>

            <div class="radioBotaoCadastro">
                <input type="radio" id="sim" name="alergia" value="sim" class="radio-button2">
            </div>

            <div class="labelOpcaoCadastro">
                <label for="sim">Sim</label>
            </div>

            <div class="radioBotaoCadastro">
                <input type="radio" id="nao" name="alergia" value="nao" class="radio-button2">
            </div>

            <div class="labelOpcaoCadastro">
                <label for="nao">Não</label>
            </div>

            <div class="label-input2">
                <label for="alergias">Alergia do que?</label>
                <input type="text" class="form-control" id="alergias" name="alergias">
            </div>

            <label for="" class="">Condições pré-existentes?</label>

            <div class="radioBotaoCadastro">
                <input type="radio" id="sim" name="pre_existentes" value="sim" class="radio-button3">
            </div>

            <div class="labelOpcaoCadastro">
                <label for="sim">Sim</label>
            </div>

            <div class="radioBotaoCadastro">
                <input type="radio" id="nao" name="pre_existentes" value="nao" class="radio-button3">
            </div>

            <div class="labelOpcaoCadastro">
                <label for="nao">Não</label>
            </div>

            <div class="label-input3">
                <label for="condicoes_preexistentes">Condições?</label>
                <input type="text" class="form-control" id="condicoes_preexistentes" name="condicoes_preexistentes">
            </div>

            <label for="observacoes">Observações:</label>
            <input type="text" class="form-control" id="observacoes" name="observacoes" placeholder="Digite suas observações">

            <script>
                const radioButtons = document.querySelectorAll('.radio-button');
                const labelInput = document.querySelector('.label-input');

                radioButtons.forEach(radioButton => {
                    radioButton.addEventListener('click', () => {
                        if (radioButton.value === 'sim') {
                            labelInput.style.display = 'block';
                        } else {
                            labelInput.style.display = 'none';
                        }
                    });
                });

                const radioButtons2 = document.querySelectorAll('.radio-button2');
                const labelInput2 = document.querySelector('.label-input2');

                radioButtons2.forEach(radioButton2 => {
                    radioButton2.addEventListener('click', () => {
                        if (radioButton2.value === 'sim') {
                            labelInput2.style.display = 'block';
                        } else {
                            labelInput2.style.display = 'none';
                        }
                    });
                });

                const radioButtons3 = document.querySelectorAll('.radio-button3');
                const labelInput3 = document.querySelector('.label-input3');

                radioButtons3.forEach(radioButton3 => {
                    radioButton3.addEventListener('click', () => {
                        if (radioButton3.value === 'sim') {
                            labelInput3.style.display = 'block';
                        } else {
                            labelInput3.style.display = 'none';
                        }
                    });
                });
            </script>
        </div>
        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>
</div>

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $nome = $_POST['nome'];
    $idade = $_POST['idade'];
    $cpf = $_POST['cpf'];
    $endereco = $_POST['endereco'];
    $telefone = $_POST['telefone'];
    $historico_medico = $_POST['historico_medico'];
    $alergias = $_POST['alergias'];
    $condicoes_preexistentes = $_POST['condicoes_preexistentes'];
    $observacoes = $_POST['observacoes'];
var_dump($_POST);


    $agendamentoDados = array(
        'nome' => $nome,
        'idade' => $idade,
        'cpf' => $cpf,
        'endereco' => $endereco,
        'telefone' => $telefone,
        'historico_medico' => $historico_medico,
        'alergias' => $alergias,
        'condicoes_preexistentes' => $condicoes_preexistentes,
       'observacoes' => $observacoes,
    );


    $jsonDados = json_encode($agendamentoDados);


    $url = 'http://localhost:8000/idosos';


    $ch = curl_init($url);


    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDados);


    $resposta = curl_exec($ch);


    if ($resposta === FALSE) {
        die('Erro na API: ' . curl_error($ch));
    }


    curl_close($ch);


    $respostaDados = json_decode($resposta, true);


    if (isset($respostaDados['success']) && $respostaDados['success'] == true) {
        echo "Agendamento realizado com sucesso!";
    } else {
        echo "Erro ao realizar agendamento: " . $respostaDados['message'];
    }
}
?>

<?php include 'templates/footer.php'; ?>