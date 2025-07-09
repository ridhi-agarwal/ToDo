import { useState } from 'react'
import './App.css'
import { CreateToDo } from './components/CreateToDo'
import { ToDos } from './components/ToDos'

function App() {
  const [todos,setTodos] = useState([]);

 fetch("http://localhost:8080/todos")
  .then(async function (res) {
    const json = await res.json();
    setTodos(json.todos);
  });

  return (
    <div>
      <CreateToDo></CreateToDo>
      <ToDos todos={todos}></ToDos>
    </div>
  )
}

export default App
