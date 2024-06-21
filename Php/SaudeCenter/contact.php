<?php include 'templates/header.php'; ?>

<div class="containercontact">

    <h1>Podemos te ajudar?</h1>
    <h4>Envie sua mensagem que em breve entraremos em contato com você!</h4>
    <form action="#" method="post" class="contact-form">

        <input type="text" id="name" name="name" required placeholder="Nome">

        <input type="email" id="email" name="email" required placeholder="Email">

        <textarea id="message" name="message" rows="5" required placeholder="Mensagem"></textarea>

        <button type="submit">Enviar</button>
    </form>
</div>

<?php include 'templates/footer.php'; ?>