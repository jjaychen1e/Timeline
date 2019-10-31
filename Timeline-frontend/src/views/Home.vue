<template>
  <div id="app">
    <AddTodo v-on:add-todo="addTodo" />
    <Todos v-bind:todos="todos" v-on:del-todo="deleteTodo" />
  </div>
</template>

<script>
import Todos from '../components/Todos'
import AddTodo from '../components/AddTodo'
import axios from 'axios'

export default {
  name: 'Home',
  components: {
    Todos,
    AddTodo
  },
  data() {
    return {
      todos: []
    }
  },
  methods: {
    deleteTodo(id) {
      // 如果不在最外层处理，就会出问题.. 只会删除中间层的数据，JS 传的不是对象的引用吗？
      // 在最外层处理就可以同步到每一层.. 为什么?

      axios
        .delete('http://jsonplaceholder.typicode.com/todos/${id}')
        .then(res => (this.todos = this.todos.filter(todo => todo.id !== id)))
        .catch(err => console.log(err))
    },
    addTodo(newTodo) {
      const { title, completed } = newTodo

      axios
        .post('http://jsonplaceholder.typicode.com/todos', {
          title,
          completed
        })
        .then(res => (this.todos = [...this.todos, res.data]))
        .catch(err => console.log(err))
    }
  },
  created() {
    axios
      .get('http://jsonplaceholder.typicode.com/todos?_limit=5')
      .then(res => (this.todos = res.data))
      .catch(err => console.log(err))
  }
}
</script>

<style>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1.4;
}

.btn {
  display: inline-block;
  border: none;
  background: #555;
  color: #fff;
  padding: 7px 20px;
  cursor: pointer;
}
.btn:hover {
  background: #666;
}
</style>
