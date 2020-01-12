
function getIndex (list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

function lastIndexId(list) {
  return list[list.length - 1];
}

var messageApi = Vue.resource('/message{/id}');

Vue.component('message-form', {
    props: ['messages','messageAttr'],
    data:
        function () {
            return {
                text: '',
                id: ''
            }
        },
    watch: {
        messageAttr: function(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="vue" v-model="text" />' +
            '<input type="submit" value="Save" v-on:click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var message = {id: this.id, text: this.text };

            if (this.text != 0) {
                if (this.id <= lastIndexId(this.messages).id && this.id > 0) {
                    messageApi.update( {id: this.id}, message).then(result =>
                    result.json().then(data => {
                         var index = getIndex(this.messages, data.id);
                         this.messages.splice(index, 1, data);
                         this.text="";
                         this.id="";
                }
                ))
            } else {
                    messageApi.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.text="";
                }))
                }
            }
        }
    }
})

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template:
    '<div>' +
        '{{ message.id }}. {{ message.text }}' +
        '<span style="position: absolute; right: 0">' +
            '<input type="submit" value="Edit" v-on:click="edit" />' +
            '<input type="submit" value="X" v-on:click="del" />' +
        '</span>' +
    '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.message);
        },
        del: function() {
            messageApi.remove({id: this.message.id}).then(result => {
                if ( result.ok ) {
                    this.messages.splice(this.messages.indexOf(this.message),1)
                }
            })
        }
    }
})

Vue.component('messages-list', {
  props: ['mes'],
  data:
    function() {
        return {
            m1: null
        }
    },
  template:
    '<div style="position:relative; width: 300px">' +
        '<message-form v-bind:messages="mes" v-bind:messageAttr="m1"/>' +
        '<message-row v-for="m in mes" v-bind:key="m.id" v-bind:message="m" v-bind:editMethod="editMethod" v-bind:messages="mes"/>' +
    '</div>',
  created: function () {
        messageApi.get().then(result =>
                    result.json().then(data =>
                    data.forEach(temp => this.mes.push(temp))))
  },
  methods: {
    editMethod: function(message) {
        this.m1 = message;
    }
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