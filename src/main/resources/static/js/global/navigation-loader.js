document.addEventListener("click", e => {
    const a = e.target.closest("a");
    if (!a) return;

    const href = a.getAttribute("href");
    const blank = a.target === "_blank";
    const toggle = a.hasAttribute("data-bs-toggle");

    if (!href || href.startsWith("#") || blank || toggle) return;

    const loader = document.getElementById("loader-overlay");

    loader.classList.remove("loader-hidden");
    loader.style.display = "flex";
});