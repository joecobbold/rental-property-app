<template>
  <div>
    <!-- Loading Spinner -->
    <div v-if="isLoading">
      <loading-spinner id="spinner" :spin="true" />
    </div>

    <!-- Property Details and Admin Controls -->
    <div v-else>
      <nav>
        <router-link :to="{ name: 'home' }">Go Back to Home</router-link>
      </nav>

      <!-- Property Details Card -->
      <div class="card">
        <property-details-component :property="property" />

        <!-- Admin Controls for Editing/Deleting Property -->
        <div v-if="isAdmin" class="admin-controls">
          <router-link
            :to="{
              name: 'updateProperty',
              params: { propertyId: property.propertyId },
            }"
          >
            <button>Update Property</button> <!-- Removed @click="submitUpdate" -->
          </router-link>

          <button
            @click="deleteProperty(property.propertyId)"
            :disabled="isLoading"
          >
            Delete Property
          </button>
        </div>
      </div>

      <!-- Rental Agreement Details Card -->
      <div class="card rental-agreements">
        <h2>Your Rental Agreement(s)</h2>
        <ul>
          <li
            v-for="agreement in rentalAgreements"
            :key="agreement.id"
            class="agreement-details"
          >
            <p><strong>Agreement ID:</strong> {{ agreement.rental_agreement_id }}</p>
            <p><strong>Renter ID:</strong> {{ agreement.renter_id }}</p>
            <p><strong>Start Date:</strong> {{ agreement.start_date }}</p>
            <p><strong>End Date:</strong> {{ agreement.end_date }}</p>
            <p><strong>Deposit Amount:</strong> {{ agreement.deposit_amount }}</p>
            <p><strong>Agreement:</strong> {{ agreement.agreement }}</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import PropertyDetailsComponent from "../components/PropertyDetailsComponent.vue";
import PropertyService from "../services/PropertyService.js";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import AgreementService from "../services/AgreementService.js";

export default {
  components: {
    PropertyDetailsComponent,
    LoadingSpinner,
  },
  data() {
    return {
      property: {},
      rentalAgreements: [],
      isLoading: true,
      showEditModal: false,
      modalContent: "",
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
          this.property = response.data;
        })
        .finally(() => {
          this.isLoading = false; // Make sure loading ends after the request
        });
    },
    getRentalAgreements(propertyId) {
      AgreementService.getRentalAgreementsByPropertyId(propertyId)
        .then((response) => {
          this.rentalAgreements = response.data;
        })
        .finally(() => {
          this.isLoading = false; // Adjust this to handle multiple loading states
        });
    },
    deleteProperty(propertyId) {
      if (confirm("Are you sure you want to delete this property?")) {
        this.isLoading = true;
        PropertyService.deleteProperty(propertyId)
          .then(() => {
            alert("Property deleted successfully");
            this.$router.push({ name: "home" });
          })
          .catch((error) => {
            console.error("Failed to delete property:", error);
            alert("Failed to delete the property.");
          })
          .finally(() => {
            this.isLoading = false;
          });
      }
    },
  },
  created() {
    const propertyId = this.$route.params.propertyId;
    this.getProperty(propertyId);
    this.getRentalAgreements(propertyId);
  },
};
</script>

<style scoped>
.card {
  background-color: #f9f9f9;
  border-radius: 15px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin: 20px auto;
  max-width: 600px;
  text-align: left;
}

.rental-agreements ul {
  list-style: none;
  padding-left: 0;
}

.agreement-details {
  padding: 10px 0;
  border-bottom: 1px solid #ddd;
}

.agreement-details:last-child {
  border-bottom: none;
}

.admin-controls {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

button {
  background-color: #306ba8;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 8px;
  cursor: pointer;
}

button:hover {
  background-color: #4a86c2;
}
</style>
