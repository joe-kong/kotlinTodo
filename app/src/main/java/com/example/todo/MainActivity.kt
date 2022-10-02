package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.Priority
import com.amplifyframework.datastore.generated.model.Todo
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*  try {
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)

            Log.i("Tutorial", "Initialized Amplify")
        } catch (failure: AmplifyException) {
            Log.e("Tutorial", "Could not initialize Amplify", failure)
        }*/

/*        val item =  Todo.builder()
            .name("Build Android application")
            .priority(Priority.NORMAL)
            .build()

        Amplify.DataStore.save(item,
            { Log.i("Tutorial", "Saved item: ${item.name}") },
            { Log.e("Tutorial", "Could not save item to DataStore", it) })*/

      /*  val date = Date()
        val offsetMillis = TimeZone.getDefault().getOffset(date.time).toLong()
        val offsetSeconds = TimeUnit.MILLISECONDS.toSeconds(offsetMillis).toInt()
        val temporalDateTime = Temporal.DateTime(date, offsetSeconds)
        val item2 = Todo.builder()
            .name("Finish quarterly taxes")
            .priority(Priority.HIGH)
            .completedAt(temporalDateTime)
            .build()
        Amplify.DataStore.save(item2,
            { Log.i("Tutorial", "Saved item: ${item2.name}") },
            { Log.e("Tutorial", "Could not save item to DataStore", it) })*/

      /*  Amplify.DataStore.query(Todo::class.java,
            {
                todos ->
                while (todos.hasNext()){
                    val todo: Todo = todos.next()
                    Log.i("Tutorial", "==== Todo ====")
                    Log.i("Tutorial", "Name: ${todo.name}")
                    Log.i("Tutorial", "Priority: ${todo.priority}")
                    Log.i("Tutorial", "CompletedAt: ${todo.completedAt}")
                }
            },
            {
                Log.e("Tutorial","Could not query DataSource ",it)
            })*/

        //query high priority items
       /* Amplify.DataStore.query(
            Todo::class.java,Where.matches(Todo.PRIORITY.eq(Priority.HIGH)),
            {
                todos ->
                while (todos.hasNext()){
                    val todo: Todo = todos.next()
                    Log.i("Tutorial", "==== Todo ====")
                    Log.i("Tutorial", "Name: ${todo.name}")
                    Log.i("Tutorial", "Priority: ${todo.priority}")
                    Log.i("Tutorial", "CompletedAt: ${todo.completedAt}")
                }
            },{ failure -> Log.e("Tutorial", "Could not query DataStore", failure) }
        )*/

        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)
            Log.i("Tutorial", "Initialized Amplify")
        } catch (failure: AmplifyException) {
            Log.e("Tutorial", "Could not initialize Amplify", failure)
        }
        Amplify.DataStore.observe(Todo::class.java,
            { Log.i("Tutorial", "Observation began.") },
            { Log.i("Tutorial", it.item().toString()) },
            { Log.e("Tutorial", "Observation failed.", it) },
            { Log.i("Tutorial", "Observation complete.") }
        )
    }
}