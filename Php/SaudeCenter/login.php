<?php include 'templates/header.php'; ?>

<div class="containerlogin">
    <div class="login">
        <h1 class="text-center">Efetuar Login</h1>
        <form method="POST">
            <label for="login">Login:</label>
            <input type="text" name="login" id="login" class="form-control" required placeholder="Por favor preencha este campo">

            <label for="senha">Senha:</label>
            <input type="password" name="senha" id="senha" class="form-control" required placeholder="Por favor preencha este campo">

            <button type="submit" class="btn btn-success w-100">Efetuar Login</button>
        </form>
    </div>
</div>

<?php
$apiUrl = 'http://localhost:8000/usuarios';

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

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = isset($_POST['login']) ? $_POST['login'] : ''; 
    $password = isset($_POST['senha']) ? $_POST['senha'] : '';
    var_dump($_POST);

    $senhaIncorreta = !password_verify($password, $responseData->senha);
    if($senhaIncorreta) {
        echo ('Senha incorreta');
    }

    echo "<script>location.href='index.php'</script>";
}


?>
<?php include 'templates/footer.php'; ?>
