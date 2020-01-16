import Vue from 'vue'
import Vuex from 'vuex'
import messageApi from 'api/messages'
import { getIndex } from 'util/collections'

Vue.use(Vuex)

export default new Vuex.Store({

  state: {
    messages: frontendData.messages,
    profile: frontendData.profile
  },
  getters: {
    sortedMessages: state => state.messages.sort((a,b) => -(a.id - b.id))
  },
  mutations: {
    addMessageMutation(state, message) {
        state.messages = [
            ...state.messages,
            message
        ]
    },
    updateMessageMutation(state, message) {

        const index = getIndex(state.messages, message.id);

        state.messages = [
            ...state.messages.slice(0, index),
            message,
            ...state.messages.slice(index + 1)
        ]
    },
    removeMessageMutation(state, message) {

        const index = getIndex(state.messages, message.id);

        if (index > -1) {
            state.messages = [
                ...state.messages.slice(0, index),
                ...state.messages.slice(index + 1)
            ]
        }
    }
  },
  actions: {
    addMessageAction({commit}, message) {
                    messageApi.add(message)
                    .then(result =>
                        result.json().then(data => {

/*
                            if (index > -1) {
                                commit('updateMessageMutation', data, index)
                            } else {
                                commit('addMessageMutation', data)
                            }
*/

                    }))
    },
    updateMessageAction({commit}, message) {
                    messageApi.update(message).then(result =>
                    result.json().then(data => {

                        commit('updateMessageMutation', data);

                    }))
    },
    removeMessageAction({commit}, message) {
                    messageApi.remove(message.id);
    }
  }
})