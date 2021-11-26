console.log("in script")
const urlParams = new URLSearchParams(window.location.search);

console.log(urlParams);

function val() {
    select = document.getElementById("select-tri").value;
    console.log(select);
    if (select !== "NULL") {
        urlParams.set('tri', select);
        window.location.search = urlParams;
    }
}

function goTo(id) {
    urlParams.set('page', id);
    console.log("page = " + id);
    window.location.search = urlParams;
}