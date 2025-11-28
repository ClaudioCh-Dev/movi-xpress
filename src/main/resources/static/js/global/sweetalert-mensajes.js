document.addEventListener("DOMContentLoaded", () => {
    const mensaje = document.body.dataset.mensaje;
    const tipo = document.body.dataset.tipo;

    if (!mensaje || mensaje.includes("[[")) return;

    Swal.fire({
        icon: tipo || "info",
        title: "Mensaje",
        text: mensaje,
        confirmButtonColor: "#dc3545",
    });
});