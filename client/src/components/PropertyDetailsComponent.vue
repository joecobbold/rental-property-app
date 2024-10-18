
  <template>
  <div>
    <section class="selected-property">
      <!-- Selected property details -->
      <div v-if="property" class="selected-property-details">
        <h2>Property Details for {{ property.address }}</h2>
        <img v-bind:src="'../' + property.imageUrl" alt="Selected Property Image" />
        <p><strong>Property Id:</strong> {{ property.propertyId }}</p>
        <p><strong>City:</strong> {{ property.city }}</p>
        <p><strong>State:</strong> {{ property.state }}</p>
        <p><strong>Zip Code:</strong> {{ property.zipCode }}</p>
        <p><strong>Rent Price:</strong> ${{ property.rentPrice }}</p>
        <p><strong>Bedrooms:</strong> {{ property.bedrooms }}</p>
        <p><strong>Bathrooms:</strong> {{ property.bathrooms }}</p>
        <p><strong>Square Feet:</strong> {{ property.squareFeet }}</p>
        <p>
          <strong>Available:</strong> {{ property.available ? "Yes" : "No" }}
        </p>
        <p><strong>Description:</strong> {{ property.description }}</p>
        <p><strong>Contact Us About This Property:</strong> 614-614-6144</p>
      </div>
    </section>
  </div>
</template>
  
  <script>
export default {
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
.selected-property h2 {
  /* property featured title */
  margin-bottom: 20px; /* Space between the heading and the property cards */
  text-align: center; /* Center the heading */
  width: 100%;
}

.selected-property-details {
  text-align: left;
  padding: 20px;
}

/* Styling the image to be responsive */
.selected-property-details img {
  width: 100%;
  height: auto;
  max-width: 500px;
  display: block;
  margin: 0 auto 20px;
}


#properties-container {
  /*properties-container*/
  display: flex;
  flex-wrap: wrap;
  align-content: space-around;
  padding: 20px; /* Padding inside the container */
  background-color: #ffffff;
  box-sizing: border-box; /* Ensure padding is included in the width */
}

#properties-container {
  background-color: #ffffff;
}

.property-placard {
  /*property placard styling*/
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #ffffff;
  padding: 10px;
  background-color: #ffffff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease;
  margin: 2px;
  flex-grow: 1;
}

.property-placard:hover {
  transform: scale(1.05); /* Slight zoom on hover */
}

.property-placard-image {
  width: 20em;
  height: auto; /* Maintain aspect ratio for images */
  display: block;
}

.property-placard h3 {
  font-size: 1.25em;
  margin: 10px 0 5px;
}

.property-placard p {
  margin: 5px 0;
  font-size: 1em;
  color: #666;
}

/*Mobile View Styling*/
@media (max-width: 425px) {
  .selected-property-details {
    padding: 10px;
  }

  .selected-property-details h2 {
    font-size: 1.25em; /* Adjust heading size for smaller screens */
  }

  .selected-property-details img {
    max-width: 100%; /* Ensure the image scales properly */
    height: auto;
  }

  .selected-property-details p {
    font-size: 0.9em; /* Smaller text size for readability */
    line-height: 1.4;
  }
}

</style>
  