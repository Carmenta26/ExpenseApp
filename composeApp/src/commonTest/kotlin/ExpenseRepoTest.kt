import data.ExpenseRepoImpl
import data.ExpeseManager
import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExpenseRepoTest {
    private val expenseManager = ExpeseManager
    private val repo = ExpenseRepoImpl(expenseManager)

    @Test
    fun expense_list_not_empty() {
        //GIVEN
        val expenseList = mutableListOf<Expense>()

        //WHEN
        expenseList.addAll(repo.getAllExpenses())

        //THEN
        assertTrue(expenseList.isNotEmpty())
    }


    @Test
    fun add_new_expense() {
        //GIVEN
        val expenseList = repo.getAllExpenses()

        //WHEN

        repo.addExpense(
            Expense(
                amount = 4.5,
                category = ExpenseCategory.CAR,
                description = "Combustible"
            )
        )

        //THEN
        assertContains(expenseList, expenseList.find { it.id == 7L })

    }

    @Test
    fun edit_expenses() {
        //GIVEN
        val expenseListBefore = repo.getAllExpenses()

        //WHEN
        val newExpenseId = 7L
        repo.addExpense(
            Expense(
                amount = 4.5,
                category = ExpenseCategory.CAR,
                description = "Combustible"
            )
        )
        assertNotNull(expenseListBefore.find { it.id == newExpenseId })
        val updatedExpense = Expense(
            id = newExpenseId,
            amount = 8.5,
            category = ExpenseCategory.OTHER,
            description = "ROPA"
        )

        repo.editExpense(updatedExpense)

        //THEN
        val expenseListAfter = repo.getAllExpenses()
        assertEquals(updatedExpense, expenseListAfter.find { it.id == newExpenseId })
    }

    @Test
    fun get_all_expenses() {
        //GIVEN
        val expenseList = mutableListOf<ExpenseCategory>()

        //WHEN
        expenseList.addAll(repo.getCategories())

        //THEN
        assertTrue(expenseList.isNotEmpty())

    }

    @Test
    fun check_all_categories() {
        //GIVEN
        val allCateries=listOf(
        ExpenseCategory.CAR,
        ExpenseCategory.GLOSERY,
        ExpenseCategory.PARTY,
        ExpenseCategory.OTHER,
        ExpenseCategory.SNACKS,
        ExpenseCategory.COFFFEE,
        ExpenseCategory.HOUSE,
        )

        //WHEN
        val repoCategories = repo.getCategories()

        //THEN
        assertEquals(allCateries,repoCategories)

    }
}