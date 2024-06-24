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
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = isset($_POST['login']) ? $_POST['login'] : ''; 
    $password = isset($_POST['senha']) ? $_POST['senha'] : '';
    var_dump($_POST);
}
?>
<?php include 'templates/footer.php'; ?>
