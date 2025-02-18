import Navigation.Navigation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.ExpenseRepoImpl
import data.ExpeseManager
import org.jetbrains.compose.ui.tooling.preview.Preview

import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpensesUiState
import presentation.ExpensesViewModel
import ui.ExpensesScreen
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import data.TitleTopBarTypes
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.imageResource

import kotlin.contracts.Returns

@Composable
@Preview
fun App() {
    PreComposeApp {


        val colors = getColorsTheme()

        AppTheme {
            val navigator = rememberNavigator()
            val titleToBar = getTitleToAppBar(navigator)
            val isEditOrAddExpenses = titleToBar != TitleTopBarTypes.DASHBOART.value
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        elevation = 0.dp, title = {
                            Text(titleToBar, fontSize = 25.sp, color = colors.textColor)
                        },
                        navigationIcon = {

                            if (isEditOrAddExpenses) {
                                IconButton(
                                    onClick = {
                                        navigator.popBackStack()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(
                                            start = 16.dp
                                        ),
                                        imageVector = Icons.Default.ArrowBack,
                                        tint = colors.textColor,
                                        contentDescription = "back arrow icon"
                                    )
                                }
                            } else {
                                Icon(
                                    modifier = Modifier.padding(
                                        start = 16.dp
                                    ),
                                    imageVector = Icons.Default.Apps,
                                    tint = colors.textColor,
                                    contentDescription = "Dashboard Icon"
                                )
                            }

                        },
                        backgroundColor = colors.bagroundColor
                    )
                },

                floatingActionButton = {
                    if (!isEditOrAddExpenses) {
                        FloatingActionButton(
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                navigator.navigate("/addExpenses")
                            },
                            shape = RoundedCornerShape(50.dp),
                            backgroundColor = colors.addIconColor,
                            contentColor = Color.White
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                tint = Color.White,
                                contentDescription = "FLoating icon"


                            )
                        }
                    }
                }

            ) {
                Navigation(navigator)
            }
        }

    }

}


@Composable
fun getTitleToAppBar(navigator: Navigator): String {
    var titleTopBar = TitleTopBarTypes.DASHBOART

    var isOnAddExpenses =
        navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if (isOnAddExpenses) {
        titleTopBar = TitleTopBarTypes.ADD
    }

    val isOnEditExpense = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpense?.let {
        titleTopBar = TitleTopBarTypes.EDIT
    }

    return titleTopBar.value
}