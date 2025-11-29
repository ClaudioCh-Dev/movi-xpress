window.addEventListener("load", () => {
    const loader = document.getElementById("loader-overlay");

    loader.classList.add("loader-hidden");

    setTimeout(() => {
        loader.style.display = "none";
    }, 300);
});
