package ru.mygames.gym_xml

// Функция расширения для преобразования Workout в FavoriteExercise
fun Workout.toFavoriteExercise(): FavoriteExercise = FavoriteExercise(
    id = this.id,
    name = this.name,
    equipment = this.equipment,
    secondaryMuscles = this.secondaryMuscles,
    gifUrl = this.gifUrl,
    bodyPart = this.bodyPart,
    target = this.target,
    instructions = this.instructions
)

// Функция расширения для преобразования FavoriteExercise в Workout
fun FavoriteExercise.toWorkout(): Workout = Workout(
    id = this.id,
    name = this.name,
    equipment = this.equipment,
    secondaryMuscles = this.secondaryMuscles,
    gifUrl = this.gifUrl,
    bodyPart = this.bodyPart,
    target = this.target,
    instructions = this.instructions
)