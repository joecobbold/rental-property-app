<template>
    <header class="flex">
      <h1>{{ property.title }}</h1>
    </header>
    <div class="created">
      <label>Property added on:</label>&nbsp;
      <span>{{ createdDate }} {{ createdTime }}</span>
    </div>
    <div class="flex">
      <h2>Rental Agreements</h2>
    </div>
    <rental-agreement-summary
      v-for="agreement in property.rentalAgreements"
      v-bind:key="agreement.id"
      v-bind:agreement="agreement"
    />
  </template>
  
  <script>
  //import RentalAgreementComponent from '../components/RentalAgreementComponent.vue';
  import PropertyService from '../services/PropertyService.js'; 
  
  export default {
    components: {
      //RentalAgreementComponent
    },
    props: {
      property: {
        type: Object,
        required: true,
      },
    },
    computed: {
      createdDate() {
        let created = new Date(this.property.dateAdded);
        return created.toLocaleDateString();
      },
      createdTime() {
        let created = new Date(this.property.dateAdded);
        return created.toLocaleTimeString();
      },
    },
  };
  </script>
  
  <style scoped>
  header {
    margin-bottom: 1rem;
  }
  h2 {
    font-size: large;
  }
  .created {
    margin-bottom: 1rem;
  }
  </style>
  