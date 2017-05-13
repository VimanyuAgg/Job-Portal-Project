function checkPasswordMatch() {
    var password = $("#password").val();
    var confirmPassword = $("#confirmPassword").val();

    if (password != confirmPassword)
        $("#divCheckPasswordMatch").html("Passwords do not match!");
    else
        $("#divCheckPasswordMatch").html("Passwords matched!.");
}

$(document).ready(function () {
   $("#password, #confirmPassword").keyup(checkPasswordMatch);
});

