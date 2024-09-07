const DeleteModal = document.getElementById('ModalDelete')
DeleteModal.addEventListener('show.bs.modal', event => {

    const button = event.relatedTarget

    console.log(button)
    const userId = button.getAttribute('data-bs-userId')
    const userName = button.getAttribute('data-bs-userName')
    console.log(button)

    const modalUserId = DeleteModal.querySelector('#userIdDelete')
    const modalUserName = DeleteModal.querySelector('#userNameDelete')


    modalUserId.value = userId
    modalUserName.value = userName


})

const formDelete = document.getElementById('formDelete')
formDelete.addEventListener('submit', e =>{
    e.preventDefault();
    const formData = new FormData(formDelete);
    fetch("api/admin/"+formData.get("id"), {
        method: "DELETE"
    })
        .then(() => getUsers());
    $("#ModalDelete").modal("hide");
    formDelete.reset();
})