package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoImpl (private val expenseManayer: ExpeseManager) : ExpenseRepository {
    override fun getAllExpenses(): List<Expense> {
       return  expenseManayer.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManayer.addExpense(expense);
    }

    override fun editExpense(expense: Expense) {
        expenseManayer.editExpense(expense);
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManayer.getCategory();
    }

    override fun deleteExpense(expense: Expense) {
        expenseManayer.deleteExpense(expense)
    }
}