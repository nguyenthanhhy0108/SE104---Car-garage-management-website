//Variables
var index = 0;
var images = [
    '/images/carpic1.jpg',
    '/images/carpic2.jpg'
];

function showImage(index) {
    for (var i = 0; i < images.length; i++) {
        document.getElementById('image_' + i).style.opacity = i === index ? 1 : 0;
    }
}

function nextImage() {
    index = (index + 1) % images.length;
    showImage(index);
}

function prevImage() {
    index = (index - 1 + images.length) % images.length;
    showImage(index);
}

document.addEventListener("DOMContentLoaded", function () {
    showImage(index);
});


function showError() {
    var loginSuccess = document.getElementById('log-in');

    if (loginSuccess.textContent.trim() !== "") {
        loginSuccess.style.display = 'flex';
    }

}

function forgetPassword(event) {
    window.location.href = "/templates/password.html";
}

document.addEventListener('DOMContentLoaded', showError);

function showLoginMessage() {
    const urlParams = new URLSearchParams(window.location.search);
    const successful = urlParams.get('successful');
    if (successful === 'true') {
        document.getElementById("password-successMessage").style.display = "block";
    }
}

function checkLogOut() {
    const url = window.location.search;
    if (url.includes('?logout')) {
        window.location.replace('/login');
    }
}

function focus() {
    const url = window.location.search;
    if (!url.includes('?logout')) {
        let username = document.getElementById("username");
        username.focus();
    }
}

window.onload = showLoginMessage;