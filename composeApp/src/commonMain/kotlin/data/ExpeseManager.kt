package data

import androidx.compose.runtime.mutableStateListOf
import model.Expense
import model.ExpenseCategory
import kotlin.math.exp

object ExpeseManager{
    private var currentId = 1L;
     val fakeExpenseList = mutableStateListOf(
        Expense(
            id = currentId++,
            amount = 70.0,
            category = ExpenseCategory.GLOSERY,
            description = "Compra Semanal"
        ),
        Expense(
            id = currentId++,
            amount = 10.0,
            category = ExpenseCategory.SNACKS,
            description = "Oxxo"
        ),
        Expense(
            id = currentId++,
            amount = 150.50,
            category = ExpenseCategory.CAR,
            description = "Tesla model 3"
        ),
        Expense(
            id = currentId++,
            amount = 120.50,
            category = ExpenseCategory.PARTY,
            description = "Posada"
        ),
        Expense(
            id = currentId++,
            amount = 5.50,
            category = ExpenseCategory.HOUSE,
            description = "Cleaning"
        ),

        Expense(
            id = currentId++,
            amount = 20.50,
            category = ExpenseCategory.OTHER,
            description = "Services"
        )
    )

    fun addExpense(expense: Expense){
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }
        if (index != -1){
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }

    }


    fun deleteExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }
        fakeExpenseList.removeAt(index);
    }


    fun getCategory(): List<ExpenseCategory>{
        return listOf(
            ExpenseCategory.CAR,
            ExpenseCategory.GLOSERY,
            ExpenseCategory.PARTY,
            ExpenseCategory.OTHER,
            ExpenseCategory.SNACKS,
            ExpenseCategory.COFFFEE,
            ExpenseCategory.HOUSE,
        )

    }

}

