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


function toSingup() {

}

function toHome() {

}
function checkPasswordMatch() {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("password_re").value;
    let passwordMatchMessage = document.getElementById("passwordMatchMessage");
    let registrationForm = document.getElementById("registrationForm");

    if (password !== confirmPassword) {
        passwordMatchMessage.innerHTML = "Passwords do not match!";
        passwordMatchMessage.style.display = "block";
        registrationForm.onsubmit = function () { return false; };
    } else {
        passwordMatchMessage.innerHTML = "Passwords match.";
        passwordMatchMessage.style.display = "none";
        registrationForm.onsubmit = function () { return true; };
    }
}

function displayErrorMessage() {
    var errorMessage = document.getElementById('username_is_used');

    if (errorMessage.innerHTML.trim() !== '') {
        // Create a new element to display the error message
        var errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.textContent = errorMessage.innerHTML.trim();

        // Append the new element to the body
        document.body.appendChild(errorDiv);
    }
}

document.addEventListener('DOMContentLoaded', displayErrorMessage);


