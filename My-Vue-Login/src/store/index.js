import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    globalVariable: {
      isAdmin: false
    }
  },
  mutations: {
    setAdmin(state, value) {
      state.globalVariable.isAdmin = value
    }
  },
  actions: {
    updateAdmin({ commit }, value) {
      commit('setAdmin', value)
    }
  },
  modules: {
    app,
    settings,
    user
  },
  getters
})

export default store
