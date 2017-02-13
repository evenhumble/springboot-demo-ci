package io.hewig.cidemo.entity.todo;

public class ToDoItem {
    private long id;
    private String todoDesc;
    private boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
