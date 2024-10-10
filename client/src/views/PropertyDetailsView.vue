<template>
  <div>
    <div class="loading" v-if="isLoading">
      <p>Loading...</p>
    </div>
    <div v-else>
      <nav>
        <router-link v-bind:to="{ name: 'home' }">Go Back to Home</router-link>
      </nav>


      <property-details-component v-bind:property="property" />

      <!-- Admin controls for editing/deleting property -->
      <!--{{ $store.state.user.role }}-->
      <div>
        <button @click="editProperty(property.propertyId)">Edit Property</button>
        <button @click="deleteProperty(property.propertyId)">Delete Property</button>
      </div>


      <div class="rental-agreements">
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
import PropertyDetailsComponent from '../components/PropertyDetailsComponent.vue';
import PropertyService from '../services/PropertyService.js';

export default {
  components: {
    PropertyDetailsComponent,
  },
  data() {
    return {
      property: {},
      rentalAgreements: [],
      isLoading: true,
      requiresAuth: false,
      showEditModal: false,
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

    openEditModal() {
      this.showEditModal = true; // Open the edit modal
    },

    updateProperty(propertyData) {
      PropertyService.updateProperty(this.property.id, propertyData)
        .then(() => {
          this.showEditModal = false; // Close the modal
          this.getProperty(this.property.id); // Refresh the property data
        })
        .catch((error) => {
          console.error("Error updating property:", error);
          alert("Failed to update the property.");
        });
    },

    deleteProperty(propertyId) {
      if (confirm("Are you sure you want to delete this property?")) {
        PropertyService.deletePropertyById(propertyId)
          .then(() => {
            alert("Property deleted successfully");
            // Redirect back to the property list or refresh the view
            this.$router.push({ name: 'home' });
          })
          .catch((error) => {
            console.error("Failed to delete property:", error);
            alert("Failed to delete the property.");
          });
      }
    }


  },
  created() {
    this.getProperty(this.$route.params.propertyId);
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
  