var badCredentials = document.getElementById("bad-credentials");
var greyBkg = document.getElementById("grey-bkg");

document.getElementById('login').addEventListener('click', checkAdminCredentials);
function checkAdminCredentials (){
        const Http = new XMLHttpRequest();
        const url='http://localhost:8090/api/v1/admin';
       
        const username = document.getElementById("admin-username").value;
        const password = document.getElementById("admin-password").value;
        const data = { username: username, password: password};
        
        Http.open("POST", url, true);
        Http.setRequestHeader('Accept', 'application/json'); 

        Http.onload = function() {
             if(Http.readyState = 4 ){
                const ans = JSON.parse(Http.responseText);
                console.log(ans.response);
                if(ans.response != "Bad credentials!" ){
                localStorage.setItem("token", ans.response);
                window.location.href = "../adminMode/adminMode.html";
            }
             else
             {
               badCredentials.classList.add("bad-credentials_shown");
               greyBkg.classList.add("bad-credentials_grey-bkg_shown");
               event.stopPropagation(); 
              }
            } 
          }
        Http.send(JSON.stringify(data));
      }
document.addEventListener("click", exitBadCredentialsWarning);
function exitBadCredentialsWarning(event){
    if(!badCredentials.contains(event.target)) 
    {greyBkg.classList.remove("bad-credentials_grey-bkg_shown");
    badCredentials.classList.remove("bad-credentials_shown");
  }
}