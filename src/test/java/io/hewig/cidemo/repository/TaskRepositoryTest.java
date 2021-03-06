package io.hewig.cidemo.repository;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import io.hewig.cidemo.CIDemoApplication;
import io.hewig.cidemo.entity.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Before
    public void clean(){
        taskRepository.deleteAll();
    }

    @Test(expected= ConstraintViolationException.class)
    public void shouldBeFailToSaveWithoutDescription() {
        try {
            taskRepository.save(new Task());
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(c -> {
                c.getPropertyPath().forEach(f -> Assert.assertEquals("description", f.getName()));
            });
            throw e;
        }
    }

    @Test
    public void shouldBeSuccessToSave() {
        Task task = new Task("Test");
        taskRepository.save(task);
        Assert.assertTrue(taskRepository.count() == 1);
    }

    @Test
    public void shouldListAll() {
        Task task = new Task("Test");
        taskRepository.save(task);
        Iterable<Task> tasks = taskRepository.findAll();
        Assert.assertTrue(tasks.iterator().hasNext());
        tasks.forEach(t -> Assert.assertEquals("Test", t.getDescription()));
    }

    @Test(expected= TransactionSystemException.class)
    public void shouldBeFailToUpdateWithoutDescription() {
        try {
            Task task = taskRepository.save(new Task("Test"));
            task.setDescription(null);
            taskRepository.save(task);
        } catch (TransactionSystemException e) {
            ((ConstraintViolationException)((RollbackException)e.getCause()).getCause()).getConstraintViolations().forEach(c -> {
                c.getPropertyPath().forEach(f -> Assert.assertEquals("description", f.getName()));
            });
            throw e;
        }
    }

    @Test
    public void shouldBeSuccessToUpdate() {
        Task task = taskRepository.save(new Task("Test"));
        task.setDescription("Buy Laptop");
        taskRepository.save(task);
        Task updated = taskRepository.findOne(task.getId());
        Assert.assertEquals("Buy Laptop", updated.getDescription());
    }

    @Test
    public void shouldBeSuccessToDelete() {
        Long id = taskRepository.save(new Task("Buy Sugar")).getId();
        taskRepository.save(new Task("Clear room"));
        Assert.assertTrue(taskRepository.count() == 2);
        taskRepository.delete(id);
        Assert.assertTrue(taskRepository.count() == 1);
    }
}
