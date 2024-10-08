<template>
    <div>
      <div class="loading" v-if="isLoading">
        <p>Loading...</p>
      </div>
      <div v-else>
        <nav>
          <router-link v-bind:to="{ name: 'HomeView' }">Back to Topic List</router-link>
        </nav>
        <property-details v-bind:property="property" />
        <div class="rental-agreements" v-if="isAuthenticated">
          <h2>Your Rental Agreements</h2>
          <ul>
            <li v-for="agreement in rentalAgreements" :key="agreement.id">
              <p><strong>Agreement ID:</strong> {{ agreement.id }}</p>
              <p><strong>Renter ID:</strong> {{ agreement.renterId }}</p>
              <p><strong>Status:</strong> {{ agreement.status }}</p>
              <p><strong>Start Date:</strong> {{ agreement.startDate }}</p>
              <p><strong>End Date:</strong> {{ agreement.endDate }}</p>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import PropertyDetails from '../components/PropertyComponent.vue';
  import PropertyService from '../services/PropertyService.js'; 
  
  export default {
    components: {
      PropertyDetails,
    },
    data() {
      return {
        property: {},
        rentalAgreements: [],
        isLoading: true,
        requiresAuth: false,
      };
    },
    methods: {
      getProperty(id) {
        PropertyService.getPropertyById(id)
          .then((response) => {
            this.isLoading = false;
            this.property = response.data;
            if (this.requiresAuth) {
              this.getRentalAgreements(id);
            }
          });
      },
      getRentalAgreements(propertyId) {
        PropertyService.getRentalAgreementsByPropertyId(propertyId)
          .then((response) => {
            this.rentalAgreements = response.data;
          });
      },
    },
    created() {
      this.getProperty(this.$route.params.id);
    },
  };
  </script>
  
  <style scoped>
  .loading {
    text-align: center;
    font-size: 20px;
    margin-top: 20px;
  }
  </style>
  