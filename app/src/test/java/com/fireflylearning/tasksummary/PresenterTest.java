package com.fireflylearning.tasksummary;


import com.fireflylearning.tasksummary.model.CustomLiveData;
import com.fireflylearning.tasksummary.network.logic.NetworkManager;
import com.fireflylearning.tasksummary.persistence.PersistenceManager;
import com.fireflylearning.tasksummary.persistence.entities.Task;
import com.fireflylearning.tasksummary.ui.tasklist.presenters.TaskListPresenterAndroidImpl;
import com.fireflylearning.tasksummary.ui.tasklist.views.TaskListView;
import com.fireflylearning.tasksummary.utils.FireflyConstants;
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper;
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager;
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Roll on 1/9/17.
 */


@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {

    @Mock
    NetworkManager networkManager;

    @Mock
    CustomLiveData<List<Task>> liveTasks;

    @Mock
    WeakReference<TaskListView> mView;

    @Mock
    TaskListView myView;

    @Mock
    LoggerHelper log;

    @Mock
    PersistenceManager persistence;

    @Mock
    PreferencesManager preferences;

    @Mock
    ResourcesManager resourcesManager;

    private String token = "token";
    private String host = "host";

    @Spy
    @InjectMocks
    TaskListPresenterAndroidImpl presenter;

    private List<Task> list = new ArrayList<>();


    @Before
    public void setUp(){
        Task task = new Task(1,"task 1", "description", new Date(), new Date(),
                true, true, true, false,
                false, true);
        list.add(task);
        configureMocks();
    }


    @Test
    public void checkOnEmptyListCallDownloadUsers(){
        list.clear();
        presenter.loadTasksFromNetwork();
        verify(networkManager, times(1)).getListOfTasks(host, token, liveTasks);
    }

    @Test
    public void checkOnNonEmptyListDontCallDownload(){
        presenter.loadTasksFromNetwork();
        verify(networkManager, times(0)).getListOfTasks(host, token, liveTasks);
    }

    @Test
    public void checkIfLoadFromDatabase(){
        list.clear();
        presenter.handleChangesInObservedTasks(list, FireflyConstants.TaskOrigin.FROM_NETWORK);
        verify(presenter, times(1)).loadTasksFromDb();
    }


    private void configureMocks(){
        //mock users
        when(liveTasks.getLivedataValue()).thenReturn(list);
        when(mView.get()).thenReturn(myView);
        when(myView.getLiveTaks()).thenReturn(liveTasks);
        when(myView.getHostFromChache()).thenReturn(host);
        when(myView.getTokenFromChache()).thenReturn(token);

        //mock network
        doNothing().when(networkManager).getListOfTasks(host, token, liveTasks);


    }


}



