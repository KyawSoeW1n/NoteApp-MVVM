package com.kurio.mvvmtesting.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(var title: String?, @field:ColumnInfo(name = "desc")
var description: String?, var priority: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
