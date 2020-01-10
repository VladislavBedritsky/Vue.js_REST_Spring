var messageApi = Vue.resource('/message{/id}');

Vue.component('message-form', {
    props: ['messages'],
    data:
        function () {
            return {
                text: ''
            }
        },
    template:
        '<div>' +
            '<input type="text" placeholder="vue" v-model="text" />' +
            '<input type="submit" value="Save" v-on:click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var message = { text: this.text };

            messageApi.save({}, message).then(result =>
            result.json().then(data => {
                this.messages.push(data);
                this.text="";
            }))
        }
    }
})

Vue.component('message-row', {
    props: ['message'],
    template: '<div>{{ message.id }}. {{ message.text }}</div>'
})

Vue.component('messages-list', {
  props: ['mes'],
  template:
    '<div>' +
        '<message-form v-bind:messages="mes" />' +
        '<message-row v-for="m in mes" v-bind:key="m.id" v-bind:message="m" />' +
    '</div>',
  created: function () {
        messageApi.get().then(result =>
                    result.json().then(data =>
                    data.forEach(temp => this.mes.push(temp))))
  }
})

var app = new Vue({
  el: '#app',
  template: '<messages-list v-bind:mes="messages" />',
  data: {
    messages: []
  }
})

var app2 = new Vue({
  el: '#app-2',
  data: {
    message: 'Вы загрузили эту страницу: ' + new Date().toLocaleString()
  }
})