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
    alert("Function is called");

    let password = document.getElementById("password").val();
    let confirmPassword = document.getElementById("password_re").val();

    if (password !== confirmPassword) {
        $("#passwordMatchMessage").html("Passwords do not match!");
    } else {
        $("#passwordMatchMessage").html("Passwords match.");
    }
}
