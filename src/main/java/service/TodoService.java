package service;
import models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TodoRepository;

import java.util.List;
import java.util.Optional;
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setName(todoDetails.getName());
            todo.setType(todoDetails.getType());
            todo.setDescription(todoDetails.getDescription());
            todo.setTaskDescriptions(todoDetails.getTaskDescriptions());
            todo.setTasks(todoDetails.getTasks());
            return todoRepository.save(todo);
        } else {
            throw new RuntimeException("Todo not found with id " + id);
        }
    }
}