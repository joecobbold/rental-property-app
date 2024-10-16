<template>
  <div class="update-property-form">
    <!-- <h2>Update Property --</h2> -->
    <form @submit.prevent="submitUpdate">
      <!-- Form Fields -->
      <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" v-model="property.address" id="address" required />
      </div>

      <div class="form-group">
        <label for="city">City:</label>
        <input type="text" v-model="property.city" id="city" required />
      </div>

      <div class="form-group">
        <label for="state">State:</label>
        <input type="text" v-model="property.state" id="state" required />
      </div>

      <div class="form-group">
        <label for="zip_code">Zip Code:</label>
        <input type="text" v-model="property.zipCode" id="zip_code" required />
      </div>

      <div class="form-group">
        <label for="rent_price">Rent Price ($):</label>
        <input
          type="number"
          v-model="property.rentPrice"
          id="rent_price"
          required
        />
      </div>

      <div class="form-group">
        <label for="bedrooms">Bedrooms:</label>
        <input
          type="number"
          step="0.1"
          v-model="property.bedrooms"
          id="bedrooms"
          required
        />
      </div>

      <div class="form-group">
        <label for="bathrooms">Bathrooms:</label>
        <input
          type="number"
          step="0.1"
          v-model="property.bathrooms"
          id="bathrooms"
          required
        />
      </div>

      <div class="form-group">
        <label for="square_feet">Square Feet:</label>
        <input
          type="number"
          v-model="property.squareFeet"
          id="square_feet"
          required
        />
      </div>

      <div class="form-group">
        <label for="available">Available:</label>
        <input type="checkbox" v-model="property.available" id="available" />
      </div>

      <div class="form-group">
        <label for="basement">Basement:</label>
        <input type="checkbox" v-model="property.basement" id="basement" />
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea
          v-model="property.description"
          id="description"
          required
        ></textarea>
      </div>

      <div class="form-group">
        <label for="imageUrl">Image URL:</label>
        <input type="text" v-model="property.imageUrl" id="imageUrl" />
      </div>

      <!-- Submit Button -->
      <button type="submit">Save Changes</button>
    </form>
  </div>
</template>
  
  <script>
import PropertyService from "../services/PropertyService"; // Import your PropertyService

export default {
  props: {
    propertyId: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      property: {
        address: "",
        city: "",
        state: "",
        zipCode: "",
        rentPrice: "",
        bedrooms: "",
        bathrooms: "",
        squareFeet: "",
        available: false,
        basement: false,
        description: "",
        imageUrl: "",
      },
      isLoading: true, // Optional to show loading state
    };
  },
  created() {
    // Get propertyId from route params
    const propertyId = this.$route.params.propertyId;
    if (propertyId) {
      this.fetchPropertyDetails(propertyId);
    } else {
      console.error("No propertyId provided in route parameters");
    }
  },
  methods: {
    fetchPropertyDetails() {
      const propertyId = this.$route.params.propertyId;
      PropertyService.getPropertyById(propertyId)
        .then((response) => {
          this.property = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          console.error("Failed to fetch property details:", error);
          alert("Failed to load property data.");
        });
    },
    submitUpdate() {
      const propertyId = this.$route.params.propertyId;
      PropertyService.updateProperty(propertyId, this.property)
        .then(() => {
          alert("Property updated successfully!");
          this.$router.push({ name: "propertyDetailsView" });
        })
        .catch((error) => {
          console.error("Failed to update property:", error);
          alert("Property update failed.");
        });
    },
  },
};
</script>
  
  <style scoped>
.update-property-form {
  max-width: 600px;
  margin: 40px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
}
.form-group {
  margin-bottom: 15px;
}
button {
  display: block;
  width: fit-content; /* Adjust the button width to fit its content */
  margin: 20px auto; /* Center the button by applying auto margin on sides */
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
}
button:hover {
  background-color: #45a049;
}

textarea {
  width: 50%;
  height: 75px;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  resize: vertical;
  box-sizing: border-box;
}
</style>
  