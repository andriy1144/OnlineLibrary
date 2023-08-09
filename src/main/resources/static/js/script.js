const fileInputer = document.getElementById("fileInputer");
let counter = 2;

function addMoreInputer() {
    fileInputer.innerHTML += " <div class=\"mb-3\">" +
        "                           <label for=\"formFileMultiple\" class=\"form-label\">Зображення " + counter + "</label>" +
        "                           <input class=\"form-control\" type=\"file\" name=\"files\" id=\"formFileMultiple\" multiple>" +
        "                       </div>"
    counter++;
}