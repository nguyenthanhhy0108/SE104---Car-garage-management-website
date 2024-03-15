// source class "veri_box": https://www.youtube.com/watch?v=naVaJDYpptY&t=7s

//global variables
let inputs, buttons, button;

function handleVeriBox(){
    //duyet qua inputs
    inputs.forEach((input, index1) => {
        input.addEventListener("keyup", (e) => {
            const currentInput = input, //input hien tai
                nextInput = input.nextElementSibling, //input tiep theo
                prevInput = input.previousElementSibling; //input truoc do

            //neu so luong input nhap vao > 1 => clear
            if (currentInput.value.length > 1){
                currentInput.value = "";
                return
            }
            //neu input tiep theo "disabled" && input hien tai not empty
            // => cho phep nhap input tiep theo va focus
            if (nextInput && nextInput.hasAttribute("disabled") && currentInput.value !== ""){
                nextInput.removeAttribute("disabled");
                nextInput.focus();
            }

            //neu user xoa input (click backspace)
            if(e.key === "Backspace"){
                //duyet qua cac phan tu inputs
                inputs.forEach((input, index2) =>{
                    //neu index1 cua currInput <= index 2 cua loop ben ngoai && prevInput co ton tai => add disabled cho currInput va focus prevInput
                    if (index1 <= index2 && prevInput) {
                        input.setAttribute("disabled", true);
                        currentInput.value = "";
                        prevInput.focus();
                    }
                })
            }

            //neu input cuoi cung (index = 3) not empty va khong co disabled => add active class, nguoc lai xoa active class
            if(!inputs[3].disabled && inputs[3].value !== "") {
                button.classList.add("active");
                return;
            }
            button.classList.remove("active")
        });
    });
}


//neu click confirm vericode => kiểm tra giá trị trong inputs có giống với code đã gửi qua mail hay không
//nếu giống => thêm style display:flex cho id "reset-pass", chuyển style display:none cho "veri-box" và thực hiện logic của reset-pass
//nếu không => hiện cửa sổ thông báo và reset lại inputs
function checkOTP(){
    // code check gia tri trong mảng inputs (có thể xóa đoạn code này)
    inputs.forEach((input) => {
        console.log(input.value);
    });

    //...

    //nếu veri code đúng
    if (success){
        document.getElementById('veri-box').style.display='none';
        document.getElementById('reset-pass').style.display='flex';
        handleResetPass();
    }
    //nếu sai => xuất ra thông báo và reset lại giá trị inputs
    else {
        popupDialog("Error", "Your code is wrong, please try again.");
        inputs.forEach((input, i) => {
            if (i !== 0){
                input.setAttribute('disabled', true);
            }
            input.value = "";
        });
        document.getElementById('veri-box').style.display='flex';
        handleVeriBox();
    }
}

function checkPasswordMatch() {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("password_re").value;
    let passwordMatchMessage = document.getElementById("passwordMatchMessage");
    let button = document.getElementById("confirm");

    if (password !== confirmPassword) {
        passwordMatchMessage.innerHTML = "Passwords do not match!";
        passwordMatchMessage.style.display = "block";
        button.style.display='none';
    } else {
        passwordMatchMessage.style.display = "none";
        button.style.display = 'block';
    }
}


// hàm lấy giá trị input => thay đổi password trong db
function resetPassword(){

}

function showLoading() {
    $('.loading-overlay').fadeIn(); // Hiển thị hiệu ứng loading
}

function hideLoading() {
    $('.loading-overlay').fadeOut(); // Ẩn hiệu ứng loading
}


//Đã đem hàm sendcode qua đây
//hàm sendCode: click button => tiến hành gửi mail có mã code (4 số) qua mail user đã nhập => xuất ra cửa sổ thông báo đã gửi mail
// => chuyển display của class "mail_box" thành none và chuyển display của "veri_box" thành flex
$(document).ready(() => {
    $("#verification_email_form").submit(function(event) {
        event.preventDefault();
        // Gửi yêu cầu POST bằng AJAX
        $.ajax({
            type: "POST",
            url: "/password",
            data: $("#verification_email_form").serialize(),
            success: function(data) {
                document.querySelector('.loading-overlay').style.display = 'block';
                hideLoading();
                //If sent successfully
                popupDialog("Success", "We had sent you a verification code, please enter it for reseting password");
                document.getElementById('verification_email_form').style.display='none';
                document.getElementById('veri-box').style.display = 'flex';
                // Hiển thị kết quả trên trang
                $("#email").text(data.email);
                $("#username_sent").text(data.username);

                // Lấy inputs và button sau khi veri_box được hiển thị
                inputs = document.querySelectorAll('#veri-box input[type="number"]');
                button = document.querySelector('#veri-box button');
                inputs[0].focus();
                handleVeriBox();
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
});

