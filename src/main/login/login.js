//Variables
let index = 0;
const images = document.querySelectorAll(".intro");
let accept = 0;

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
function seePass(idx) {
    if(idx==1){
        var passwordInput = document.getElementById("password");
        var iconPassword = document.getElementById("iconPassword1");
    }
    else if(idx==2){
        var passwordInput = document.getElementById("password_re");
        var iconPassword = document.getElementById("iconPassword2");
    }
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        iconPassword.textContent = "visibility_off";
    }else{
        passwordInput.type = "password";
        iconPassword.textContent = "visibility";
    }
}

function toSignup(){
    let mail_box = document.getElementById("signup_mail")
    let pass_box = document.getElementById("signup_pass")
    let confirm_signup = document.getElementById("btn-checkSignup");
    let opts_btns = document.getElementById("btn-2opts");

    mail_box.style.display="flex";
    pass_box.style.display="flex";  
    confirm_signup.style.display="flex";
    opts_btns.style.display = "none";
}

function checkSignup(){
    var phone_val = document.getElementById("phone").value
    var mail_val = document.getElementById("email").value
    var pass_val = document.getElementById("password").value
    var pass_val_re = document.getElementById("password_re").value
    let msg_phone = document.getElementById("phone_msg")
    let msg_pass = document.getElementById("pass_msg")
    if(pass_val != pass_val_re){
        msg_pass.style.display = "flex";
    }
    else if(pass_val == pass_val_re){
        msg_pass.style.display="none";
    }
    
}

function toHome(){

}
