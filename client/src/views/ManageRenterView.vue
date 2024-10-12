<template>
  <div v-if="isLoading">
    <loading-spinner id="spinner" v-bind:spin="true" />
  </div>
  <div v-if="isAdmin" class="renters-container">
    <div v-for="renter in renters" v-bind:key="renter.renter_id" class="renter-card">
    <h3>{{ renter.first_name + " " + renter.last_name }}</h3>
    <p><strong>Renter Id: </strong>{{ renter.renter_id }}</p>
    <p><strong>Email: </strong>{{ renter.email }}</p>
    <p><strong>Profile Details: </strong>{{ renter.profile_details }}</p>
    <button @click="updateRenter(property.propertyId)">Update Renter</button>
    <button @click="deleteRenter(property.propertyId)">Delete Renter</button>
    <button @click="updateRentalAgreement(property.propertyId)">Update Rental Agreement</button>
  </div>
  </div>
</template>

<script>
import RenterService from "../services/RenterService.js";
import LoadingSpinner from "../components/LoadingSpinner.vue";

export default {
  data() {
    return {
      renters: [],
      isLoading: true,
      requireAuth: false,
    };
  },
  created() {
    RenterService.getAllRenters()
      .then((response) => {
        this.renters = response.data;
        this.isLoading = false;
      })
      .catch((error) => {
        console.error("Error fetching renters:", error);
        alert("Failed to load renters.");
      })
      .finally(() => {
        this.isLoading = false;
      });
  },
  components: {
    LoadingSpinner,
  },
  computed: {
    isAdmin() {
      return (
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
  },
};
</script>

<style>
/* Container to center the renter cards */
.renters-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Align cards in the middle */
  gap: 20px; /* Space between each card */
  padding: 20px;
}

/* Style for each renter card */
.renter-card {
  background-color: #f9f9f9; /* Light background */
  border: 1px solid #ddd; /* Border around the card */
  border-radius: 10px; /* Curved corners */
  padding: 20px; /* Padding inside the card */
  width: 300px; /* Fixed width for uniformity */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Slight shadow for depth */
  text-align: center; /* Center the text */
}

/* Buttons styling inside the card */
.renter-card button {
  margin: 10px 0; /* Space around the buttons */
  padding: 8px 16px; /* Padding inside the button */
  background-color:#4a86c2ff; /* Green background */
  color: white; /* White text */
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Hover effect for buttons */
.renter-card button:hover {
  background-color: black;
}

</style>