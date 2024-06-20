<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendamento de Vacina e Visita</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <header>
        <a href="index.php" title="Saúde Center" class="header-logo">
            <img src="imagens/clinicasaude-logo-nova.png" alt="Saude">
        </a>

        <a href="javascript:mostrarMenu()" class="header-menu" title="Menu">
            <img src="imagens/menu.webp" alt="Menu">
        </a>

        <nav class="header-nav">
            <ul>
                <li>
                    <a href="index.php" title="Home">Ínicio</a>
                </li>
                <li>
                    <a href="about.php" title="Home">Sobre</a>
                </li>
                <li>
                    <a href="services.php" title="Service">Serviços</a>
                </li>
                <li>
                    <a href="contact.php" title="Contato">Contatos</a>
                </li>
            </ul>
        </nav>
    </header>
</body>

<script>

    function mostrarMenu() {
        var menu = document.querySelector(".header-nav");
        menu.classList.toggle("show");
    }

</script>



</html>
