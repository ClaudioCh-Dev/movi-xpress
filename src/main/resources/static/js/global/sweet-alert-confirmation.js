document.querySelectorAll(".btn-eliminar").forEach(btn => {
    btn.addEventListener("click", e => {
        e.preventDefault();

        const id = btn.dataset.id;
        const form = document.getElementById(`formEliminar-${id}`);

        Swal.fire({
            title: btn.dataset.title || "¿Eliminar?",
            text: btn.dataset.text || "Esta acción no se puede deshacer.",
            icon: btn.dataset.icon || "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Sí, eliminar",
        }).then(result => {
            if (result.isConfirmed) form?.submit();
        });
    });
});