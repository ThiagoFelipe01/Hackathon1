// js/script.js
// Script placeholder, you can add interactive features later
document.addEventListener("DOMContentLoaded", function() {
    const menuCheckbox = document.getElementById('menu-checkbox');
    const navLinks = document.querySelector('.nav-links');

    menuCheckbox.addEventListener('change', function() {
        if (menuCheckbox.checked) {
            navLinks.classList.add('active');
        } else {
            navLinks.classList.remove('active');
        }
    });
});
