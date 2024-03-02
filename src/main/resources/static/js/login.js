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

// Hàm khởi tạo
document.addEventListener("DOMContentLoaded", function() {
    showImage(index);  // Hiển thị ảnh đầu tiên khi trang được tải
});


// ---------password visibility---------//
function seePass() {
    var passwordInput = document.getElementById("password");
    var iconPassword = document.getElementById("iconPassword");
    
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        iconPassword.textContent = "visibility_off";
    }else{
        passwordInput.type = "password";
        iconPassword.textContent = "visibility";
    }
}

function toSingup(){

}

function toHome(){

}
