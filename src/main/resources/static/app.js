
const onHandleListItemClick = async (id) => {
    console.log('click ', id)

    const options = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: {}
    }
    const response = await fetch(`/todos/${id}`, options)
    const data = await response.json()
    console.log('data', data)
    window.location.reload()
}
const deleteTodo = async (id) => {
    console.log('Deleting todo with id:', id);

    const options = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
    };

    const response = await fetch(`/todos/${id}`, options);
    if (response.ok) {
        window.location.reload(); // Recarrega a página após a exclusão
    } else {
        console.error('Failed to delete todo:', response.statusText);
    }
};

const editTodo = async (id) => {
    const newDescription = prompt("Digite a nova descrição da tarefa:");
    if (newDescription) {
        const options = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ description: newDescription })
        };
        const response = await fetch(`/todos/edit/${id}`, options);
        if (response.ok) {
            window.location.reload(); // Recarrega a página após a edição
        } else {
            console.error('Failed to edit todo:', response.statusText);
        }
    }
};


$(() => {
    console.log('jquery ready')
})