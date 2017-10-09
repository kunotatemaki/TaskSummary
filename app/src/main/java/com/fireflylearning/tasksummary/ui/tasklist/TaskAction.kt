package com.fireflylearning.tasksummary.ui.tasklist

import android.support.annotation.NonNull
import android.support.annotation.Nullable

/**
 * Created by Roll on 4/10/17.
 * A generic class that holds a value with its description status2.
 * @param <T>
 */
class TaskAction<out T>(@NonNull val taskDescription: TaskDescription, @Nullable val data: T?, @Nullable val message: String?) {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val taskAction = other as TaskAction<*>?

        if (taskDescription !== taskAction!!.taskDescription) {
            return false
        }
        if (if (message != null) message != taskAction!!.message else taskAction!!.message != null) {
            return false
        }
        return if (data != null) data == taskAction.data else taskAction.data == null
    }

    override fun hashCode(): Int {
        var result: Int = taskDescription.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "taskDescription=" + taskDescription +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}'
    }
    companion object {

        fun <T> reset(): TaskAction<T> {
            return TaskAction(TaskDescription.DO_NOTHING, null, null)
        }

        fun <T> noInfo(msg: String): TaskAction<T> {
            return TaskAction(TaskDescription.NO_INFO, null, msg)
        }

        fun <T> withInfo(data: T?): TaskAction<T> {
            return TaskAction(TaskDescription.LAUNCH_DESCRIPTION, data, null)
        }

        enum class TaskDescription constructor() {
            DO_NOTHING,
            NO_INFO,
            LAUNCH_DESCRIPTION
        }
    }
}

