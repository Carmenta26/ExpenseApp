package presentation

import moe.tlaster.precompose.viewmodel.ViewModel


import moe.tlaster.precompose.viewmodel.viewModelScope
import data.ExpeseManager
import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.viewmodel.viewModel

data class ExpensesUiState(
    val expenses:List<Expense> = emptyList(),
    val total: Double = 0.0
)

class ExpensesViewModel(private val repo: ExpenseRepository): ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()
    private val allExpense = repo.getAllExpenses()

    init {
        getAllExpenses()
    }

    private fun updateState(){
        _uiState.update {
                state ->
            state.copy(
                expenses = allExpense,
                total =  allExpense.sumOf { it.amount }
            )
        }
    }

    private fun getAllExpenses(){
       viewModelScope.launch {
           updateState()
       }
    }


    fun addExpense(expense: Expense){
        viewModelScope.launch {
            repo.addExpense(expense)
            updateState()
        }
    }

    fun editExpense(expense: Expense){
        viewModelScope.launch {
            repo.editExpense(expense)
            updateState()
        }
    }

    fun getexpenseWithID(id:Long):Expense{
        return allExpense.first{it.id == id}
    }

    fun deleteExpense(expense: Expense){
        viewModelScope.launch {
            repo.deleteExpense(expense)
            updateState()
        }
    }

    fun getCategories():List<ExpenseCategory>{
        return repo.getCategories()
    }
}