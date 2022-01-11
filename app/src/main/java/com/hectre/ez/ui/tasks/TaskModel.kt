package com.hectre.ez.ui.tasks

data class TaskModel (
    val taskType: String,
    val staff: Staff,
    val orchardName: String,
    val blockName: String,
    var rate: Double,
    val listRows: List<Row>

)

data class Row(
    var maxTrees: Int,
    val currentTrees: Int,
    var prunedTrees: Int
)

data class Staff(
    val firstName: String,
    val lastName: String
)