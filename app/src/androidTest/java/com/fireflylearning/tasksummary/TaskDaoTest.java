package com.fireflylearning.tasksummary;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fireflylearning.tasksummary.persistence.daos.TaskDao;
import com.fireflylearning.tasksummary.persistence.databases.FireflyDatabase;
import com.fireflylearning.tasksummary.persistence.entities.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Roll on 22/8/17.
 */


@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {
    private TaskDao mTaskDao;
    private FireflyDatabase mDb;
    private Task task1, task2;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, FireflyDatabase.class).build();
        mTaskDao = mDb.taskDao();
        task1 = new Task(1,"task 1", "description 1", new Date(), new Date(),
                true, true, true, false,
                false, true);
        task2 = new Task(2,"task 2", "description 2", new Date(), new Date(),
                true, true, true, false,
                false, true);
        Task[] tasks = {task1, task2};
        mTaskDao.insertAll(tasks);

    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeTaskAndReadInList() throws Exception {
        List<Task> lTasks = mTaskDao.getAll();
        assertTrue(lTasks.size() == 2);
    }

    @Test
    public void deleteTask() throws Exception {
        mTaskDao.delete(task2);
        List<Task> lTasks = mTaskDao.getAll();
        assertTrue(lTasks.size() == 1);
    }
}