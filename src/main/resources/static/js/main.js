function changeLang(){
    var lang = document.getElementById('currentLang');
    if (lang.textContent == "RU") {
        lang.textContent = "KZ";
    } else {
        lang.textContent = "RU";
    }
}

var lastnews = document.getElementsByClassName('last-news')[0];
var popularnews = document.getElementsByClassName('popular-news')[0];

function last_active() {
    lastnews.style.color = "#005587";
    lastnews.style.background = "#fff";
    popularnews.style.color = "#fff";
    popularnews.style.background = "#005587";
}   

function popular_active() {
    lastnews.style.color = "#fff";
    lastnews.style.background = "#005587";
    popularnews.style.color = "#005587";
    popularnews.style.background = "#fff";
}   


// OPENING NAV MENU  
var menuIcon = document.querySelector(".menu-bar");
var navMenu = document.querySelector(".header-nav-block");
let menuOpen = false;

menuIcon.addEventListener("click", () => {
    if(!menuOpen){
      menuIcon.classList.add("open");
      navMenu.classList.add("open");
      document.body.style.overflow = 'hidden';
      menuOpen = true;
    } else {
      menuIcon.classList.remove("open");
      navMenu.classList.remove("open");
      document.body.style.overflow = 'visible';
      menuOpen = false;
    }
});