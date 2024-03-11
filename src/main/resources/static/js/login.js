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

