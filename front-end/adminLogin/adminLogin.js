var badCredentials = document.getElementById("bad-credentials");
var greyBkg = document.getElementById("grey-bkg");

document.getElementById('login').addEventListener('click', checkAdminCredentials);
function checkAdminCredentials (){
        const Http = new XMLHttpRequest();
        const url='https://unwetw.herokuapp.com/rest/age';
       
        const username = document.getElementById("admin-username").value;
        const password = document.getElementById("admin-password").value;
        const data = { username: username, password: password};
        console.log(url);
        /* Http.open("POST", url);
        console.log('Aduc date intr o fericire');
        Http.send(data);
        Http.onreadystatechange = function() {
             if(Http.responseText == true){ */
                window.location.href = "../adminMode/adminMode.html";
            /*}
             else
             {
               badCredentials.classList.add("bad-credentials_shown");
               greyBkg.classList.add("bad-credentials_grey-bkg_shown");
               event.stopPropagation(); }
            } */
}
document.addEventListener("click", exitBadCredentialsWarning);
function exitBadCredentialsWarning(event){
    if(!badCredentials.contains(event.target)) 
    {greyBkg.classList.remove("bad-credentials_grey-bkg_shown");
    badCredentials.classList.remove("bad-credentials_shown");
  }
}