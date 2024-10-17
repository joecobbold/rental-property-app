<template>
  <div v-if="isLoading">
    <loading-spinner id="spinner" v-bind:spin="true" />
  </div>

  <!-- Create Rental Agreement Form -->
  <div class="create-agreement" v-if="!isUpdating">
    <h2>Create Rental Agreement</h2>
    <form @submit.prevent="createRentalAgreement">
      <input
        type="number"
        v-model="newAgreement.renter_id"
        placeholder="Renter ID"
        required
      />
      <input
        type="number"
        v-model="newAgreement.property_id"
        placeholder="Property ID"
        required
      />
      <label for="start-date"><strong>Start Date: </strong></label>
      <input
        id="start-date"
        type="date"
        v-model="newAgreement.start_date"
        required
      />

      <label for="end-date"><strong>End Date: </strong></label>
      <input
        id="end-date"
        type="date"
        v-model="newAgreement.end_date"
        required
      />
      <input
        type="number"
        v-model="newAgreement.monthly_rent"
        placeholder="Monthly Rent"
        required
      />
      <input
        type="number"
        v-model="newAgreement.deposit_amount"
        placeholder="Deposit Amount"
        required
      />
      <button id="create-agreement-button" type="submit">
        Create Agreement
      </button>
    </form>
  </div>

  <!-- Update Rental Agreement Form -->
  <div class="update-agreement" v-if="editAgreementId !== null">
    <h2>Update Rental Agreement</h2>
    <form @submit.prevent="updateRentalAgreement">
      <input
        type="number"
        v-model="newAgreement.renter_id"
        placeholder="Renter ID"
        required
      />
      <input
        type="number"
        v-model="newAgreement.property_id"
        placeholder="Property ID"
        required
      />

      <label for="start-date"><strong>Start Date: </strong></label>
      <input
        id="start-date"
        type="date"
        v-model="newAgreement.start_date"
        required
      />

      <label for="end-date"><strong>End Date: </strong></label>
      <input
        id="end-date"
        type="date"
        v-model="newAgreement.end_date"
        required
      />

      <input
        type="number"
        v-model="newAgreement.monthly_rent"
        placeholder="Monthly Rent"
        required
      />
      <input
        type="number"
        v-model="newAgreement.deposit_amount"
        placeholder="Deposit Amount"
        required
      />
      <button id="update-agreement-form-button" type="submit">
        Update Agreement
      </button>
      <button id="cancel-form-button" type="button" @click="cancelUpdate">
        Cancel
      </button>
    </form>
  </div>

  <!-- Search bar for searching Rental Agreement -->
  <div class="agreementSearch" id="agreementSearch">
    <input
      type="text"
      id="searchAgreementBar"
      placeholder="Search agreement by ID"
      v-model="searchId"
    />
    <button id="searchAgreementButton" @click="searchAgreement">Search</button>
  </div>

  <div v-if="isAdmin" class="agreements-container">
    <div
      v-for="agreement in agreements"
      :key="agreement.rental_agreement_id"
      class="agreement-card"
    >
      <h3>Rental Agreement #{{ agreement.rental_agreement_id }}</h3>
      <p><strong>Renter ID: </strong>{{ agreement.renter_id }}</p>
      <p><strong>Property ID: </strong>{{ agreement.property_id }}</p>
      <p><strong>Start Date: </strong>{{ agreement.start_date }}</p>
      <p><strong>End Date: </strong>{{ agreement.end_date }}</p>
      <p><strong>Monthly Rent: </strong>{{ agreement.monthly_rent }}</p>
      <p><strong>Deposit: </strong>{{ agreement.deposit_amount }}</p>
      <button
        id="update-agreement-button"
        @click="openUpdateModal(agreement.rental_agreement_id)"
      >
        Update Agreement
      </button>
      <button
        id="delete-agreement-button"
        @click="deleteRentalAgreement(agreement.rental_agreement_id)"
      >
        Delete Agreement
      </button>
    </div>
  </div>
</template>
  
  <script>
import AgreementService from "../services/AgreementService.js";
import LoadingSpinner from "../components/LoadingSpinner.vue";

export default {
  data() {
    return {
      agreements: [],
      isLoading: true,
      isUpdating: false,
      editAgreementId: null,
      newAgreement: {
        renter_id: "",
        property_id: "",
        start_date: "",
        end_date: "",
        monthly_rent: "",
        deposit_amount: "",
      },
      searchId: "",
    };
  },
  created() {
    AgreementService.getAllRentalAgreements()
      .then((response) => {
        this.agreements = response.data;
      })
      .catch((error) => {
        console.error("Error fetching agreements:", error);
        alert("Failed to load agreements.");
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
    createRentalAgreement() {
      AgreementService.createRentalAgreement(this.newAgreement)
        .then((response) => {
          alert("Agreement created successfully.");
          this.agreements.push(response.data); // Add the new agreement to the list
          this.resetNewAgreement(); // Reset form fields
        })
        .catch((error) => {
          console.error("Error creating agreement:", error);
          alert("Failed to create agreement.");
        });
    },

    openUpdateModal(agreementId) {
      const agreementToUpdate = this.agreements.find(
        (agreement) => agreement.rental_agreement_id === agreementId
      );
      this.newAgreement = { ...agreementToUpdate }; // Pre-fill with existing data
      this.editAgreementId = agreementId; // Store the ID of the agreement to update
      this.isUpdating = true; // Set update mode
    },

    updateRentalAgreement() {
      AgreementService.updateRentalAgreement(
        this.editAgreementId,
        this.newAgreement
      )
        .then((response) => {
          alert("Agreement updated successfully.");
          const index = this.agreements.findIndex(
            (agreement) =>
              agreement.rental_agreement_id === this.editAgreementId
          );
          // Directly update the agreement in the list
          if (index !== -1) {
            this.agreements[index] = response.data;
          }
          this.resetNewAgreement(); // Reset form fields
          this.editAgreementId = null; // Clear the edit agreement ID
          this.isUpdating = false; // Reset update mode
        })
        .catch((error) => {
          console.error("Error updating agreement:", error);
          alert("Failed to update agreement.");
        });
    },

    deleteRentalAgreement(agreementId) {
      if (confirm("Are you sure you want to delete this agreement?")) {
        AgreementService.deleteRentalAgreement(agreementId)
          .then(() => {
            alert("Agreement deleted successfully.");
            this.agreements = this.agreements.filter(
              (a) => a.rental_agreement_id !== agreementId
            ); // Remove agreement from list
          })
          .catch((error) => {
            console.error("Error deleting agreement:", error);
            alert("Failed to delete agreement.");
          });
      }
    },

    searchAgreement() {
      const searchLower = this.searchId.toLowerCase();
      this.agreements = this.agreements.filter(
        (agreement) =>
          agreement.rental_agreement_id.toString().includes(this.searchId) ||
          agreement.renter_id.toString().includes(searchLower) ||
          agreement.property_id.toString().includes(searchLower)
      );
    },

    resetNewAgreement() {
      this.newAgreement = {
        renter_id: "",
        property_id: "",
        start_date: "",
        end_date: "",
        monthly_rent: "",
        deposit_amount: "",
      };
    },

    cancelUpdate() {
      this.editAgreementId = null;
      this.resetNewAgreement();
      this.isUpdating = false;
      this.$router.push({ name: "agreement" });
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

/* Create Agreement Form */
.create-agreement,
.update-agreement {
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin: 20px auto;
  width: 400px;
}

.create-agreement form,
.update-agreement form {
  display: flex;
  flex-direction: column;
}

.create-agreement input,
.update-agreement input,
.create-agreement textarea,
.update-agreement textarea {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

#create-agreement-button {
  background-color: #28a745;
  color: white;
}

#create-agreement-button:hover {
  background-color: black;
}

/* Search Bar Styles */
.agreementSearch {
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
}

#searchAgreementBar {
  padding: 10px;
  width: 300px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

#searchAgreementButton {
  padding: 10px 15px;
  margin-left: 10px;
  background-color: #4a86c2ff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

#searchAgreementButton:hover {
  background-color: black;
}

/* Agreement Container */
.agreements-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Center the renter cards horizontally */
  gap: 20px; /* Space between cards */
  margin-top: 20px;
  margin-bottom: 20px;
}

/* button styles for agreement cards */
.agreement-card button {
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin: 5px 0; /* Add margin between buttons */
  display: inline-block; /* Make buttons inline-block */
  width: 100%; /* Make buttons take full width */
}

.agreement-card .create-agreement-button {
  background-color: #28a745; /* Green color for Create Renter button */
  color: white;
}

.agreement-card .create-agreement-button:hover {
  background-color: #218838; /* Darker green on hover */
}

.agreement-card .update-agreement-button {
  background-color: #4a86c2ff; /* Match the search button color */
  color: white;
}

.agreement-card .update-agreement-button:hover {
  background-color: black; /* Change color on hover */
}

.agreement-card {
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 300px; /* Set the desired width for renter cards */
  text-align: center; /* Center text inside the renter card */
}

#update-agreement-card,
#delete-agreement-card {
  background-color: #4a86c2ff;
  color: white; /* Sets text color for both elements */
}

/**Update form & cancel button */
#update-agreement-form-button {
  background-color: #28a745;
  color: white;
}

#update-agreement-form-button:hover {
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


/* Mobile view  425px or smaller */
@media (max-width: 425px) {
  body {
    padding: 10px;
  }

  /* Create/Update Agreement Form */
  .create-agreement,
  .update-agreement {
    width: 100%; 
    padding: 15px; 
    margin: 10px 0; 
  }

  /* Input fields in form */
  .create-agreement input,
  .update-agreement input {
    width: 100%; 
    padding: 8px; 
  }

  /* Button sizes */
  button {
    width: 100%; 
    padding: 12px; 
    font-size: 14px; 
  }

  /* Search bar adjustments */
  .agreementSearch {
    flex-direction: column; /* Stack the input and button */
    align-items: stretch;
  }

  #searchAgreementBar {
    width: 100%; 
    margin-bottom: 10px; 
  }

  /* Agreement Cards */
  .agreements-container {
    flex-direction: column;
  }
}

</style>
  