
async function getUsers() {

    const response = await fetch("myapp/api/admin/users");

    if (response.ok) {
        const data = await response.json();
        replaceTable(data);
    } else {
        alert("Ошибка HTTP: " + response.status);
    }

    function replaceTable(data) {

        const placement = document.getElementById("usersTablePlacement")
        placement.innerHTML = "";
        data.forEach(({id, name, roles}) => {
            let userRoles = "";
            roles.forEach((role) => {
                userRoles = userRoles + role.role.replace("ROLE_", "") + " ";
            })
            const element = document.createElement("tr");

            element.innerHTML = `
            <th scope="row">${id}</th>
            <td>${name}</td>
            <td>${userRoles}</td>
            <td>
                <button type="button" class="btn btn-info text-white" 
                    data-bs-userId=${id}
                    data-bs-userName=${name} 
                    data-bs-toggle="modal"
                    data-bs-target="#ModalEdit">Edit</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger" 
                    data-bs-userId=${id}
                    data-bs-userName=${name} 
                    data-bs-toggle="modal"
                    data-bs-target="#ModalDelete">Delete</button>
            </td>            
            `
            placement.append(element);
        })
    }
}