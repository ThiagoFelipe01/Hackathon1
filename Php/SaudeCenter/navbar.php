<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar Exemplo</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    
    <nav class="navbar">
        <div class="logo">
            <a href="index.php"><img src="imagens/clinicasaude-logo-nova.png" alt="logo"></a>
            
        </div>
        <ul class="nav-links">
            <li><a href="index.php">Início</a></li>
            <li><a href="about.php">Sobre</a></li>
            <li><a href="services.php">Serviços</a></li>
            <li><a href="contact.php">Contato</a></li>
        </ul>
        <div class="menu-toggle">
            <input type="checkbox" id="menu-checkbox">
            <label for="menu-checkbox" class="menu-icon">&#9776;</label>
        </div>
    </nav>
    <script src="script.js"></script>
</body>
</html>
