package com.fireflylearning.tasksummary.persistence.entities


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import com.fireflylearning.tasksummary.network.model.TaskElementResponse
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Roll on 31/8/17.
 */

@Entity(tableName = "task", indices = arrayOf(Index(value = *arrayOf("name"), unique = true)))
class Task constructor(@PrimaryKey
                       val id: Int?,
                       @ColumnInfo(name = "name")
                       val title: String?,
                       @ColumnInfo(name = "descriptionPageUrl")
                       val descriptionPageUrl: String?,
                       @ColumnInfo(name = "set")
                       val set: Date?,
                       @ColumnInfo(name = "due")
                       val due: Date?,
                       @ColumnInfo(name = "archived")
                       val archived: Boolean?,
                       @ColumnInfo(name = "draft")
                       val draft: Boolean?,
                       @ColumnInfo(name = "showInMarkbook")
                       val showInMarkbook: Boolean?,
                       @ColumnInfo(name = "highlightInMarkbook")
                       var highlightInMarkbook: Boolean?,
                       @ColumnInfo(name = "showInParentPortal")
                       val showInParentPortal: Boolean?,
                       @ColumnInfo(name = "hideAddressees")
                       val hideAddressees: Boolean?) {

    @Ignore
    constructor(task: TaskElementResponse) : this(
            id = task.id,
            title = task.title,
            descriptionPageUrl = task.descriptionPageUrl,
            set = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(task.set),
            due= if (task.due!=null) SimpleDateFormat("yyyy-MM-dd").parse(task.due) else  null,
            archived = task.archived,
            draft = task.draft,
            showInMarkbook = task.showInMarkbook,
            highlightInMarkbook = task.highlightInMarkbook,
            showInParentPortal = task.showInParentPortal,
            hideAddressees = task.hideAddressees
    )

}