import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExampleTest {
    @Test
    fun test(){

        val x = 5;
        val y = 10;

        val result = x+y ;

        assertEquals(15, result)

    }

    @Test
    fun expense_model_list_test(){

        //GIVEN
        val expeseList = mutableListOf<Expense>()
        val expense = Expense(id=1, amount = 4.5, category = ExpenseCategory.CAR, description = "Combustible")
        //WHEN
        expeseList.add(expense)
        //THEN
        assertContains(expeseList,expense)
    }

    @Test
    fun expense_model_param_test_success(){
        //GIVEN
        val expeseList = mutableListOf<Expense>()
        val expense = Expense(id=1, amount = 4.5, category = ExpenseCategory.OTHER, description = "Combustible")
        val expense2 = Expense(id=2, amount = 14.5, category = ExpenseCategory.OTHER, description = "Limpieza")

        //WHEN
        expeseList.add(expense)
        expeseList.add(expense2)
        //THEN
        assertEquals(expense.category,expense2.category)
    }
    @Test
    fun expense_model_param_test_fail(){
        //GIVEN
        val expeseList = mutableListOf<Expense>()
        val expense = Expense(id=1, amount = 4.5, category = ExpenseCategory.OTHER, description = "Combustible")
        val expense2 = Expense(id=2, amount = 14.5, category = ExpenseCategory.CAR, description = "Limpieza")

        //WHEN
        expeseList.add(expense)
        expeseList.add(expense2)
        //THEN
        assertNotEquals(expeseList[0].category,expeseList[1].category)
    }

}