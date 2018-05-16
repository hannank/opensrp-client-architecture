package org.opensrp.mvp.taskscheduler.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import org.opensrp.mvp.taskscheduler.converter.DateConverter;
import org.opensrp.mvp.taskscheduler.dao.TaskDao;
import org.opensrp.mvp.taskscheduler.model.Task;
import org.opensrp.mvp.taskscheduler.utils.Constants;


/**
 * Created by samuelgithengi on 4/11/18.
 */
@Database(entities = {Task.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class TaskRepository extends RoomDatabase {

    public abstract TaskDao getTaskDao();

    private static TaskRepository taskRepository;

    public static TaskRepository getInstance(Context context) {
        if (null == taskRepository) {
            taskRepository = buildDatabaseInstance(context);
        }
        return taskRepository;
    }

    private static TaskRepository buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, TaskRepository.class, Constants.DB_NAME)/*.allowMainThreadQueries()*/.build();
    }

    public void cleanUp() {
        taskRepository = null;
    }
}
