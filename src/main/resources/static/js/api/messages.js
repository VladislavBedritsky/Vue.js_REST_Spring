import Vue from 'vue'

const messages = Vue.resource('/message{/id}')

export default {
    add: message => messages.save({id: message.id}, message),
    update: message => messages.update({id: message.id}, message),
    remove: id => messages.remove({id})
}