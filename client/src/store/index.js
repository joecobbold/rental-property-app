import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      properties: [],
      rentalAgreements: [],
      currentPropertyId: 1,
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_CURRENT_PROPERTY_ID(state, id) {
        state.currentPropertyId = id;
      },
      SET_PROPERTIES(state, property) {
        state.currentPropertyId = property;
      },
      //Not sure if the mutation for rental agreement is correct.Ask Tom about this on Saturday.
      SET_RENTAL_AGREEMENTS(state, rentalAgreements) {
        state.rentalAgreements = rentalAgreements;
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
        if (user.roles && user.roles.length > 0) {
          state.user.authoritiesString = user.roles.join(','); // added this may delete ltr
        }
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },

  })
  return store;
}