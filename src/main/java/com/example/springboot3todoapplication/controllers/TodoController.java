package com.example.springboot3todoapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot3todoapplication.models.StatusType;
import com.example.springboot3todoapplication.models.Todo;
import com.example.springboot3todoapplication.services.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TodoController {

    final TodoService todoService;

    @PutMapping("/todos/{id}")
    public Todo editTodoItem(@PathVariable("id") long id) {
        Todo updatedTodo = todoService.getById(id);
        if (updatedTodo.getStatus() == StatusType.BACKLOG) {
            updatedTodo.setStatus(StatusType.DOING);
        } else if (updatedTodo.getStatus() == StatusType.DOING) {
            updatedTodo.setStatus(StatusType.DONE);
        }

        return todoService.save(updatedTodo);
    }

    // Novo método para deletar
    @DeleteMapping("/todos/{id}")
public ResponseEntity<Void> deleteTodoItem(@PathVariable("id") long id) {
    todoService.deleteById(id);
    return ResponseEntity.noContent().build(); // Retorna um ResponseEntity<Void>
}

}
