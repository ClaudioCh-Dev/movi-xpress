const sidebar = document.querySelector(".sidebar");

document.addEventListener("click", e => {
    const toggle = e.target.closest("[data-sidebar-toggle]");
    if (!toggle) return;

    sidebar.classList.toggle("show");
}); 