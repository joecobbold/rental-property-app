<template>
    <div>
      <h1>Update Property</h1>
      <UpdateComponent :propertyId="propertyId" />
    </div>
  </template>
  
  <script>
  import UpdateComponent from '../components/UpdateComponent.vue'; // Import the UpdateComponent
  
  export default {
    components: {
      UpdateComponent
    },
    computed: {
      propertyId() {
        return this.$route.params.propertyId; // Get the propertyId from the route params
      }
    }
  };
  </script>
  
  <style scoped>
  h1 {
    text-align: center;
  }
  </style>
  