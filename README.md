
## 1. What makes Compose declarative?

Compose is declarative because you describe what the UI should look like for a given state, rather than how to change it.  

- In the `DashboardScreen`, we simply write: `Text("Points: $count")`.  
- When `count` changes, Compose automatically re-runs that function (recomposition).  
- You never have to write code to "find the TextView and update its string"—the UI is always a direct reflection of the data.

## 2. Where is state stored?

In this project, the state is stored in the Stateful Parent Composable (`DashboardScreen`):  

- It uses `remember { mutableIntStateOf(0) }` and `remember { mutableStateOf(true) }`.  
- `remember` stores the object in the Composition so it survives recomposition.  
- `mutableStateOf` creates an observable state object. When its value changes, Compose schedules a recomposition for any composables that read that value.

## 3. Which composables are stateful vs stateless?

- **Stateful Composable (`DashboardScreen`)**:  
  Holds the `count` and `isEnabled` variables and manages logic for how the state changes (e.g., `count++`).  

- **Stateless Composables (`CounterCard` and `SettingsToggle`)**:  
  Pure UI components that don’t own state. They receive data via parameters and notify the parent of user actions through lambda callbacks (e.g., `onIncrement`).  
  This makes them highly reusable and easy to test.

## 4. How does this differ from XML + View logic?

In **XML-based UI**, layout is defined separately in XML files and views are updated imperatively in the Activity or Fragment, often requiring manual `findViewById` calls, listeners, and state synchronization.  

In contrast, this Compose code uses **stateful and stateless composables**:  

- The UI is declarative, and changes in state (`count`, `isEnabled`) automatically trigger recomposition.  
- Event handling is passed via lambdas, keeping UI and logic separate.  
- This approach eliminates boilerplate, reduces errors, and ensures the UI always reflects the underlying state without manual updates.
