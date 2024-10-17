<template>
  <div v-if="isLoading">
    <loading-spinner id="spinner" v-bind:spin="true" />
  </div>

  <!-- Create Renter Form -->
  <div class="create-renter" v-if="!isUpdating">
    <h2>Create Renter</h2>
    <form @submit.prevent="createRenter">
      <input
        type="text"
        v-model="newRenter.first_name"
        placeholder="First Name"
        required
      />
      <input
        type="text"
        v-model="newRenter.last_name"
        placeholder="Last Name"
        required
      />
      <input
        type="email"
        v-model="newRenter.email"
        placeholder="Email"
        required
      />
      <input
        type="text"
        v-model="newRenter.phone"
        placeholder="Phone"
        required
      />
      <textarea
        v-model="newRenter.profile_details"
        placeholder="Profile Details"
      ></textarea>
      <button id="create-renter-button" type="submit">Create Renter</button>
    </form>
  </div>

  <!-- Update Renter Form -->
  <div class="update-renter" v-if="editRenterId !== null">
    <h2>Update Renter</h2>
    <form @submit.prevent="updateRenter">
      <input
        type="text"
        v-model="newRenter.first_name"
        placeholder="First Name"
        required
      />
      <input
        type="text"
        v-model="newRenter.last_name"
        placeholder="Last Name"
        required
      />
      <input
        type="email"
        v-model="newRenter.email"
        placeholder="Email"
        required
      />
      <input
        type="text"
        v-model="newRenter.phone"
        placeholder="Phone"
        required
      />
      <textarea
        v-model="newRenter.profile_details"
        placeholder="Profile Details"
      ></textarea>
      <button id="update-renter-form-button" type="submit">
        Update Renter
      </button>
      <button id="cancel-form-button" type="cancel-form" @click="cancelUpdate">
        Cancel
      </button>
    </form>
  </div>

  <!-- Search bar for searching Renter -->
  <div class="renterSearch" id="renterSearch">
    <input
      type="text"
      id="searchRenterBar"
      placeholder="Search renter by ID"
      v-model="searchId"
    />
    <button id="searchRenterButton" @click="searchRenter">Search</button>
  </div>

  <div v-if="isAdmin" class="renters-container">
    <div v-for="renter in renters" :key="renter.renter_id" class="renter-card">
      <h3>{{ renter.first_name + " " + renter.last_name }}</h3>
      <p><strong>Renter Id: </strong>{{ renter.renter_id }}</p>
      <p><strong>Email: </strong>{{ renter.email }}</p>
      <p><strong>Profile Details: </strong>{{ renter.profile_details }}</p>
      <button
        id="update-renter-button"
        @click="openUpdateModal(renter.renter_id)"
      >
        Update Renter
      </button>
      <button id="delete-renter-button" @click="deleteRenter(renter.renter_id)">
        Delete Renter
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
      isUpdating: false, // New property to track if in update mode
      editRenterId: null,
      newRenter: {
        first_name: "",
        last_name: "",
        email: "",
        phone: "",
        profile_details: "",
      },
      searchId: "",
    };
  },
  created() {
    RenterService.getAllRenters()
      .then((response) => {
        this.renters = response.data;
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
  methods: {
    createRenter() {
      RenterService.createRenter(this.newRenter)
        .then((response) => {
          alert("Renter created successfully.");
          this.renters.push(response.data); // Add the new renter to the list
          this.resetNewRenter(); // Reset form fields
        })
        .catch((error) => {
          console.error("Error creating renter:", error);
          alert("Failed to create renter.");
        });
    },

    openUpdateModal(renterId) {
      const renterToUpdate = this.renters.find(
        (renter) => renter.renter_id === renterId
      );
      this.newRenter = { ...renterToUpdate }; // Pre-fill with existing data
      this.editRenterId = renterId; // Store the ID of the renter to update
      this.isUpdating = true; // Set update mode
    },

    updateRenter() {
      RenterService.updateRenter(this.editRenterId, this.newRenter)
        .then((response) => {
          alert("Renter updated successfully.");
          const index = this.renters.findIndex(
            (renter) => renter.renter_id === this.editRenterId
          );
          // Directly update the renter in the list
          if (index !== -1) {
            this.renters[index] = response.data;
          }
          this.resetNewRenter(); // Reset form fields
          this.editRenterId = null; // Clear the edit renter ID
          this.isUpdating = false; // Reset update mode
        })
        .catch((error) => {
          console.error("Error updating renter:", error);
          alert("Failed to update renter.");
        });
    },

    deleteRenter(renterId) {
      if (confirm("Are you sure you want to delete this renter?")) {
        RenterService.deleteRenter(renterId)
          .then(() => {
            alert("Renter deleted successfully.");
            this.renters = this.renters.filter((r) => r.renter_id !== renterId); // Remove renter from list
          })
          .catch((error) => {
            console.error("Error deleting renter:", error);
            alert("Failed to delete renter.");
          });
      }
    },

    searchRenter() {
      const searchLower = this.searchId.toLowerCase();
      this.renters = this.renters.filter(
        (renter) =>
          renter.renter_id.toString().includes(this.searchId) ||
          renter.first_name.toLowerCase().includes(searchLower) ||
          renter.last_name.toLowerCase().includes(searchLower)
      );
    },

    resetNewRenter() {
      this.newRenter = {
        first_name: "",
        last_name: "",
        email: "",
        phone: "",
        profile_details: "",
      };
    },

    cancelUpdate() {
      this.editRenterId = null; // Clear the edit renter ID
      this.resetNewRenter(); // Reset form fields
      this.isUpdating = false; // Reset update mode
      this.$router.push({ name: "renter" });
    },
  },
};
</script>


<style>
/* General styles */
body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 20px;
}

h2 {
  color: #333;
}

button:hover {
  background-color: black;
}

button {
  padding: 10px 15px;
  background-color: #4a86c2ff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

/* Loader */
#spinner {
  display: block;
  margin: 0 auto;
}

/* Create Renter Form */
.create-renter,
.update-renter {
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin: 20px auto;
  width: 400px;
}

.create-renter form,
.update-renter form {
  display: flex;
  flex-direction: column;
}

.create-renter input,
.update-renter input,
.create-renter textarea,
.update-renter textarea {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

#create-renter-button {
  background-color: #28a745; /* Emerald Green */
  color: white; /* Text color */
}

#create-renter-button:hover {
  background-color: black;
}

/* Search bar styles */
.renterSearch {
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
}

#searchRenterBar {
  padding: 10px;
  width: 300px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

#searchRenterButton {
  padding: 10px 15px;
  margin-left: 10px;
  background-color: #4a86c2ff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

#searchRenterButton:hover {
  background-color: black;
}

/* Renters container */
.renters-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Center the renter cards horizontally */
  gap: 20px; /* Space between cards */
  margin-top: 20px;
  margin-bottom: 20px;
}

/* button styles for renter cards */
.renter-card button {
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin: 5px 0; /* Add margin between buttons */
  display: inline-block; /* Make buttons inline-block */
  width: 100%; /* Make buttons take full width */
}

.renter-card .create-renter-button {
  background-color: #28a745; /* Green color for Create Renter button */
  color: white;
}

.renter-card .create-renter-button:hover {
  background-color: #218838; /* Darker green on hover */
}

.renter-card .update-renter-button {
  background-color: #4a86c2ff; /* Match the search button color */
  color: white;
}

.renter-card .update-renter-button:hover {
  background-color: black; /* Change color on hover */
}

.renter-card {
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 300px; /* Set the desired width for renter cards */
  text-align: center; /* Center text inside the renter card */
}

#update-renter-card,
#delete-renter-card {
  background-color: #4a86c2ff;
  color: white; /* Sets text color for both elements */
}

/**Update form & cancel button */
#update-renter-form-button {
  background-color: #28a745; /* Medium Sea Green */
  color: white; /* Text color */
}

#update-renter-form-button:hover {
  background-color: black;
}

#cancel-form-button {
  background-color: #dc3545; /* Bootstrap red */
  color: white; /* Text color */
  margin: 7px 0;
}

#cancel-form-button:hover {
  background-color: black;
}


/* Mobile view 425px or smaller */
@media (max-width: 425px) {
  body {
    padding: 10px; 
  }

  /* Adjust form container widths */
  .create-renter, 
  .update-renter {
    width: 100%; 
    padding: 15px; 
    margin: 10px auto;
  }

  /* Input fields on mobile */
  .create-renter input, 
  .update-renter input, 
  .create-renter textarea, 
  .update-renter textarea {
    width: 100%; 
    font-size: 14px; 
  }

  /* Buttons full width for mobile */
  button {
    width: 100%; 
    margin-bottom: 10px; 
    font-size: 14px; 
  }

  /* Search bar on mobile */
  .renterSearch {
    flex-direction: column; 
    align-items: stretch; 
  }

  #searchRenterBar {
    width: 100%; 
    margin-bottom: 10px; 
  }

  #searchRenterButton {
    width: 100%; /* Full width button */
    padding: 10px;
  }

  /* Renters container */
  .renters-container {
    flex-direction: column; 
    gap: 10px; /* Reduce gap between cards */
  }

  /* Renter cards on mobile */
  .renter-card {
    width: 100%; 
    padding: 15px; /* Adjust padding */
  }
}

</style>
