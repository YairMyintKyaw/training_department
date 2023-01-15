let theme = localStorage.getItem("theme");

const themeIcon = document.querySelector(".theme");
const navbar = document.querySelector("nav");
const btn = document.querySelector(".btn");

if (theme === "light") {
  document.body.classList.replace("text-bg-dark", "text-bg-light");
  navbar.classList.remove("navbar-dark");
  btn.classList.replace("btn-outline-light", "btn-outline-dark");
}

themeIcon.addEventListener("click", function () {
  if (theme === "night") {
    //turn light
    document.body.classList.replace("text-bg-dark", "text-bg-light");
    navbar.classList.remove("navbar-dark");
    btn.classList.replace("btn-outline-light", "btn-outline-dark");
    this.classList.replace("bi-moon-stars", "bi-brightness-high");
    theme = "light";
    localStorage.setItem("theme", theme);
  } else {
    // turn night
    document.body.classList.replace("text-bg-light", "text-bg-dark");
    navbar.classList.add("navbar-dark");
    btn.classList.replace("btn-outline-dark", "btn-outline-light");
    this.classList.replace("bi-brightness-high", "bi-moon-stars");
    theme = "night";
    localStorage.setItem("theme", theme);
  }
});
