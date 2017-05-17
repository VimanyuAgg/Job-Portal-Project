function encodeImageFileAsURL(element) {
    var file = element.files[0];
    var reader = new FileReader();
    reader.onloadend = function() {
        console.log('RESULT', reader.result)
        
        var a = document.getElementById("clogo");
        a.value = "";
        a.value = reader.result;
        
        console.log('RESULT', a.value)
        
        sessionStorage.setItem("imageSrc",reader.result);
        document.getElementById('defaultValue').src=sessionStorage.getItem('imageSrc');
    }
    reader.readAsDataURL(file);
}


function checkPasswordMatch() {
	//alert("passwordInput");
    var password = $("#password").val();
    var confirmPassword = $("#confirmPassword").val();
    if ($("#password").val() == "" || $("#password").val() == null)
    {
    	$("#divCheckPasswordMatch").html("");
    	//alert("psswordnull");
    }
    else if (password != confirmPassword){
        $("#divCheckPasswordMatch").html("Passwords do not match!");
    	$("#divCheckPasswordMatch").css("color","Red"); }
    else{
        $("#divCheckPasswordMatch").html("Passwords matched!");
    	$("#divCheckPasswordMatch").css("color","Green");
    }
}

$(document).ready(function () {
	   $("#password, #confirmPassword").keyup(checkPasswordMatch);
	   $("#file-input2").change(function(){readURL(this)});
});