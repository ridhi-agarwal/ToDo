export function ToDos({todos}){
    return <div>
        {todos.map(function(todo){
            return <div>
                <h2 style={{padding:10,margin:10}}>{todo.title}</h2>
                <button style={{padding:10,margin:10}}>{todo.completed == true ? "Completed" : "Mark as Complete"}</button>
            </div>
        })}
    </div>
}