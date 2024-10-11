<template>
  <div>
    <div v-if="isLoading">
    <loading-spinner id="spinner" v-bind:spin="true"/>
  </div>
    <div v-else>
      <nav>
        <router-link v-bind:to="{ name: 'home' }">Go Back to Home</router-link>
      </nav>


      <property-details-component v-bind:property="property" />

      <!-- Admin controls for editing/deleting property -->
      <div v-if="isAdmin">
        <button @click="editProperty(property.propertyId)">Edit Property</button>
        <button @click="deleteProperty(property.propertyId)">Delete Property</button>
      </div>


      <div class="rental-agreements">
        <h2>Your Rental Agreement(s)</h2>
        <ul>
          <li v-for="agreement in rentalAgreements" :key="agreement.id">
            <p><strong>Agreement ID:</strong> {{ agreement.rental_agreement_id }}</p>
            <p><strong>Renter ID:</strong> {{ agreement.renter_id }}</p>
            <p><strong>Start Date:</strong> {{ agreement.start_date }}</p>
            <p><strong>End Date:</strong> {{ agreement.end_date }}</p>
            <p><strong>Deposit amount:</strong> {{ agreement.deposit_amount }}</p>
            <p><strong>Agreement:</strong> {{ agreement.agreement }}</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
  
<script>
import PropertyDetailsComponent from '../components/PropertyDetailsComponent.vue';
import PropertyService from '../services/PropertyService.js';
import LoadingSpinner from '../components/LoadingSpinner.vue';
import RentalAgreementService from '../services/RentalAgreementService';

export default {
  components: {
    PropertyDetailsComponent,
    LoadingSpinner
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
  computed: {
    isAdmin() {
      
      return this.$store.state.user.role && this.$store.state.user.role.includes("ROLE_ADMIN");
    },
  },

  methods: {
    getProperty(id) {
      PropertyService.getPropertyById(id)
        .then((response) => {
          this.isLoading = false;
          this.property = response.data;
        });
    },

    getRentalAgreements(propertyId) {
      RentalAgreementService.getRentalAgreementsByPropertyId(propertyId)
        .then((response) => {
          this.rentalAgreements = response.data;
          this.isLoading = false; // Set isLoading to false after fetching rental agreements
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
    this.getRentalAgreements(this.$route.params.propertyId);
  }
}

</script>
  
<style scoped>
#spinner {
  display: flex;              
  justify-content: center;    
  align-items: center;         
  margin: 0 auto;             
  width: 50px;                 
  height: 50px;                
  font-size: 24px;             
  margin-top: 20vh;
  color: #414e58;            
}
</style>


  