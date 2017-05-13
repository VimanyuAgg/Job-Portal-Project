$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='jslogin']").validate({
    // Specify validation rules
    rules: {
    	username: "required",
        password: "required",
    },
  messages: {
      username: "Please enter your username",
      password: "Please enter your password",
  },
      submitHandler: function(form) {
          form.submit();
        }
     
    });
});