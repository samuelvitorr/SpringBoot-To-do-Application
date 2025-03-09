package com.example.springboot3todoapplication.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.springboot3todoapplication.models.StatusType;
import com.example.springboot3todoapplication.models.Todo;
import com.example.springboot3todoapplication.services.TodoService;

class TodoControllerTests {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    public TodoControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEditTodoItem() {
        var existingTodo = new Todo();
        existingTodo.setId(1L);
        existingTodo.setDescription("Old Todo");
        existingTodo.setStatus(StatusType.BACKLOG);
        
        var updatedTodo = new Todo();
        updatedTodo.setId(1L);
        updatedTodo.setDescription("Updated Todo");
        updatedTodo.setStatus(StatusType.BACKLOG);
        when(todoService.getById(1L)).thenReturn(existingTodo);
        when(todoService.save(eq(existingTodo))).thenReturn(updatedTodo);

        var result = todoController.editTodoItem(1L);
        assertEquals("Updated Todo", result.getDescription());
    }

    @Test
    void testDeleteTodoItem() {
        doNothing().when(todoService).deleteById(1L);
        ResponseEntity<Void> response = todoController.deleteTodoItem(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    
}
