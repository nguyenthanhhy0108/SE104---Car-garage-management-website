//Variables
let index = 0;
const images = document.querySelectorAll(".intro");

//------- image changing--------//
function showImage(imageIndex){
    images.forEach((image, i) => {
        image.style.opacity = i === imageIndex ? 1 : 0;
    });
}

function nextImage(){
    index = (index + 1) % images.length;
    showImage(index);
}

function prevImage(){
    index = (index - 1 + images.length) % images.length;
    showImage(index);
}

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