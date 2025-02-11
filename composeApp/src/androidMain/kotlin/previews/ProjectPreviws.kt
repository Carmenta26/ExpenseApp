package previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.ExpeseManager
import model.Expense
import model.ExpenseCategory
import presentation.ExpensesUiState
import ui.AllExpensesHeader
import ui.ExpensesItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
fun ExpesesTotalHeaderPreview() {
    ExpensesTotalHeader(total = 1028.9)
}


@Preview(showBackground = true)
@Composable
private fun AllExpensesPreview() {
    AllExpensesHeader()
}

@Preview(showBackground = true)
@Composable
private fun ExpensesItems() {
    ExpensesItem(
        expense = ExpeseManager.fakeExpenseList [0],
        onExpeseClick = {})
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPreview() {
    ExpensesScreen(uiState = ExpensesUiState(expenses = ExpeseManager.fakeExpenseList, total = 10.5), onExpeseClick = {})


}