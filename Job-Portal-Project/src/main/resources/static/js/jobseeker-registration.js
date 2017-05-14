function checkPasswordMatch() {
    var password = $("#password").val();
    var confirmPassword = $("#confirmPassword").val();

    if (password != confirmPassword){
        $("#divCheckPasswordMatch").html("Passwords do not match!");
    	$("#divCheckPasswordMatch").css("color","Red"); }
    else{
        $("#divCheckPasswordMatch").html("Passwords matched!");
    	$("#divCheckPasswordMatch").css("color","Green");
    }
}

$(document).ready(function () {
   $("#password, #confirmPassword").keyup(checkPasswordMatch);
});

function encodeImageFileAsURL(element) {
    var file = element.files[0];
    var reader = new FileReader();
    reader.onloadend = function() {
        console.log('RESULT', reader.result)
        sessionStorage.setItem("imageSrc",reader.result);
        document.getElementById('defaultValue').src=sessionStorage.getItem('imageSrc');
    }
    reader.readAsDataURL(file);
}
