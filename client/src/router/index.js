import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import PropertyDetailsView from '../views/PropertyDetailsView.vue'
import AddProperty from '../views/AddPropertyView.vue'
import ManageRenterView from '../views/ManageRenterView.vue'
import UpdateView from '../views/UpdateView.vue'
import ManageAgreementView from '../views/ManageAgreementView.vue'


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/property/:propertyId",
      name: "propertyDetailsView",
      component: PropertyDetailsView,
      meta: {
        requiresAuth: false
      },
    },
    {
      path: '/property',
      name: 'addProperty',
      component: AddProperty,
      meta: {
        requiresAuth: true
      },
    },
    {
      path: '/renter',
      name: 'renter',
      component: ManageRenterView,
      meta: {
        requiresAuth: true,
        //isAdminFlag: true
      }
    },
    {
      path: '/rental_agreement',
      name: 'rental_agreement',
      component: ManageAgreementView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/property/:propertyId/update',
      name: 'updateProperty',
      component: UpdateView,
      meta: {
        requiresAuth: true, 
      },
    }
    
  ];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  //const isAdminFlag = to.matched.some(x => x.meta.isAdminFlag);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // if (isAdminFlag &&  (!this.$store.state.user.role || !this.$store.state.user.role.includes("ROLE_ADMIN"))) {
  //   alert("You do not have permission to access this page.");
  //   return {name: "login"};
    
  // }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
