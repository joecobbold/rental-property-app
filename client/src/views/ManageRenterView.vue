<template>
  <div v-if="isLoading">
    <loading-spinner id="spinner" v-bind:spin="true" />
  </div>
  <div class="renterSearch" id="renterSearch">
    <input
      type="text"
      id="searchRenterBar"
      placeholder="Enter a renter id to see associated property and agreement..."
    />
    <button id="searchRenterButton">Search</button>
  </div>
  <div class="createButtons">
    <button @click="createRenter(property.propertyId)">Create Renter</button>
    <button @click="createRentalAgreement(property.propertyId)">Create Agreement</button>
  </div>

  <div v-if="isAdmin" class="renters-container">
    <div
      v-for="renter in renters"
      v-bind:key="renter.renter_id"
      class="renter-card"
    >
      <h3>{{ renter.first_name + " " + renter.last_name }}</h3>
      <p><strong>Renter Id: </strong>{{ renter.renter_id }}</p>
      <p><strong>Email: </strong>{{ renter.email }}</p>
      <p><strong>Profile Details: </strong>{{ renter.profile_details }}</p>
      <button @click="updateRenter(property.propertyId)">Update Renter</button>
      <button @click="deleteRenter(property.propertyId)">Delete Renter</button>
      <button @click="updateRentalAgreement(property.propertyId)">
        Update Rental Agreement
      </button>
      <button @click="deleteRentalAgreement(property.propertyId)">
        Delete Rental Agreement
      </button>
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
  background-color: #4a86c2ff; /* Green background */
  color: white; /* White text */
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Hover effect for buttons */
.renter-card button:hover {
  background-color: black;
}


#renterSearch {   /*Search bar styling*/
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
    margin: 0 auto; /* Center the container itself if needed */
  }
  
  #searchRenterBar {
    width: 100%; 
    max-width: 500px; /* Set a max-width to ensure it doesn't grow too large */
    padding: 10px;
    border: 3px solid #4a86c2ff;
    border-radius: 5px;
    margin-right: 10px;
    box-sizing: border-box; /* Include padding and border in the element's total width and height */
  }
  
  #searchRenterButton {
    padding: 10px 20px;
    background-color: #e4853c;
    color: #ffffff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  #searchRenterButton:hover {
    background-color: black;
  }

  .createButtons {
  display: flex;
  justify-content: space-between; 
  width: 400px;
  margin: 20px auto;
}

.createButtons button {
  padding: 10px 20px;
  background-color: #e4853c;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.createButtons button:hover {
  background-color: black;
}

  
</style>