<!-- <template>
    <header class="flex">
      <h1>{{ property.title }}</h1>
    </header>
    <div class="created">
      <label>Property added on:</label>&nbsp;
      <span>{{ createdDate }} {{ createdTime }}</span>
    </div>
    <div class="flex">
      <h2>Rental Agreements</h2>
    </div>
    <rental-agreement-summary
      v-for="agreement in property.rentalAgreements"
      v-bind:key="agreement.id"
      v-bind:agreement="agreement"
    />
  </template> -->

  <template>
    <div>


        
        <section class="property-featured" id="property-featured">
         
                    <!-- Selected property details (only displayed if a property is selected) -->
          <div v-if="selectedProperty" class="selected-property-details">
            <h2>Property Details for {{ selectedProperty.address }}</h2>
            <img v-bind:src="selectedProperty.imageUrl" alt="Selected Property Image" />
            <p><strong>Property Id:</strong> {{ property.propertyId }}</p>
            <p><strong>City:</strong> {{ property.city }}</p>
            <p><strong>State:</strong> {{ property.state }}</p>
            <p><strong>Zip Code:</strong> {{ property.zip_code }}</p>
            <p><strong>Rent Price:</strong> ${{ property.rent_price }}</p>
            <p><strong>Bedrooms:</strong> {{ property.bedrooms }}</p>
            <p><strong>Bathrooms:</strong> {{ property.bathrooms }}</p>
            <p><strong>Square Feet:</strong> {{ property.square_feet }}</p>
            <p><strong>Available:</strong> {{ property.available ? 'Yes' : 'No' }}</p>
            <p><strong>Description:</strong> {{ property.description }}</p>
          </div>
        </section>
    </div>
  </template>
  
  <script>
  //import RentalAgreementComponent from '../components/RentalAgreementComponent.vue';
  import PropertyService from '../services/PropertyService.js';
  import ResourceService from '../services/ResourceService.js';

 
  
  
  export default {
    data() {
    return {
      properties: [],  // Holds all property data
      selectedProperty: this.property, 
    };
  },
    props: {
      property: {
        type: Object,
        required: true,
      },
    },
      created() {
    // Fetch properties using ResourceService when the component is created
    this.properties = ResourceService.getAllProperties();
  },
    computed: {
      createdDate() {
        let created = new Date(this.property.dateAdded);
        return created.toLocaleDateString();
      },
      createdTime() {
        let created = new Date(this.property.dateAdded);
        return created.toLocaleTimeString();
      }
    }

  
  };
  </script>
  
  <style scoped> 
  /* header {
    margin-bottom: 1rem;
  }
  h2 {
    font-size: large;
  }
  .created {
    margin-bottom: 1rem;
  } */

  
/***/
.property-featured h2 {  /* property featured title */
    margin-bottom: 20px; /* Space between the heading and the property cards */
    text-align: center; /* Center the heading */
    width: 100%;

}

#properties-container {  /*properties-container*/
    display: flex;
    flex-wrap: wrap;
    align-content: space-around;
    padding: 20px; /* Padding inside the container */
    background-color: #ffffff;
    box-sizing: border-box; /* Ensure padding is included in the width */
}

#properties-container {
    background-color:#ffffff;

}

.property-placard {   /*property placard styling*/  
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
  </style>
  