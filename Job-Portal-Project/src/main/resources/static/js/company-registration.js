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