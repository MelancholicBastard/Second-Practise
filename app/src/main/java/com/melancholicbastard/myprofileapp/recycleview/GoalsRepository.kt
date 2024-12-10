package com.melancholicbastard.myprofileapp.recycleview

class GoalsRepository {
    private var goals: ArrayList<Goal> = arrayListOf(
        Goal("Пожарил рыбу с уксусом", "06.12.24"),
        Goal("Поднял на бицепс пару тонн", "02.12.24"),
        Goal("Родился", "27.06.01"),
        Goal("Оделся и причесался", "23.11.24"),
        Goal("Подорвал государственную целостность Южной Кореи", "03.12.24"),
        Goal("Призван по повестке в армию", "01.09.28"),
        Goal("Пожарил рыбу с уксусом", "06.12.24"),
        Goal("Поднял на бицепс пару тонн", "02.12.24"),
        Goal("Родился", "27.06.01"),
        Goal("Оделся и причесался", "23.11.24"),
        Goal("Подорвал государственную целостность Южной Кореи", "03.12.24"),
        Goal("Призван по повестке в армию", "01.09.28")
    )

    fun getGoals(): ArrayList<Goal> {
        return goals
    }

    fun removeGoal(item: Goal) {
        goals.remove(item)
    }

}