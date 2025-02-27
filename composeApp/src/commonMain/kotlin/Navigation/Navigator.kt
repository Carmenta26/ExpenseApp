package Navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseRepoImpl
import data.ExpeseManager
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpensesViewModel
import ui.ExpensesDetailScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator){
    val colors = getColorsTheme()
    val viewModel = viewModel (modelClass = ExpensesViewModel::class){
        ExpensesViewModel (ExpenseRepoImpl(ExpeseManager))
    }

    //skingle sign on
    NavHost(
        modifier = Modifier.background(color = colors.bagroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ){
        scene(route = "/home"){
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState = uiState){
                expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }

        scene(route = "/addExpenses/{id}?"){ backStackEntry->
            val idFromPath = backStackEntry.path<Long> ("id")
            val expenseToEditOrAdd = idFromPath?.let { id-> viewModel.getexpenseWithID(id) }

            ExpensesDetailScreen (expenseToEdit = expenseToEditOrAdd, categoryList = viewModel.getCategories()){ expense ->
                if(expenseToEditOrAdd  == null){
                    viewModel.addExpense(expense)
                }else{
                    viewModel.editExpense(expense)
                }
                navigator.popBackStack()
            }
        }
    }
}


fun main(){
    val car = Car(Engine(horsePower = 30))
    car.start()
}


class Car (private val engine: Engine) {


    fun start(){
        engine.start()
    }
}

class Engine ( private val horsePower: Int){

    fun start(){
        println("Se arranco el motor .. HP:  $horsePower")
    }

}
