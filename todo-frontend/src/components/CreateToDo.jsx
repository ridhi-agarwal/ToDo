import { useState } from "react";

export function CreateToDo(){
    const[title, setTitle] = useState("");
    return (
        <div>
            <input style={{padding:10,margin:10}}
            type = "text" placeholder="Title" onChange={function(e){
                const value = e.target.value;
                setTitle(value);
            }} ></input> <br></br>
            <button style={{padding:10,margin:10}} onClick={() => {
                fetch(`http://localhost:8080/todos/task?title=${encodeURIComponent(title)}`,{
                    method: "POST",
                    // body: JSON.stringify({
                    //     title:title,
                    // }),
                    headers:{
                        contenttype: "application/json"
                    }
                }).then(async function (res) {
                    const json = await res.json();
                    alert("todo added");

                    
                })
            }
            }>Add a ToDo</button>
        </div>
    )
    
}