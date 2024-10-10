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
      <section class="homepageContent" id="contentWrapper">
          <div class="mainSearch" id="searchWrap">
            <h1 class = "mainTitle" id = "quickSearchMainTitle">Locate Your Perfect Home</h1>
            <p>Connecting renters with their perfect home</p>
          </div>

          <div class = "quickSearch" id = "quickSearch">
            <input type="text" id="searchBar" placeholder="Enter a city, neighborhood, or address" />
            <button id="searchButton">Search</button>
          </div>
        </section>

        
        <section class="property-featured" id="property-featured">
          <h2>Available rentals in Columbus, OH</h2>

          <!-- Dynamic data--> <!-- New container for dynamic content -->
          <div id="properties-container">
            <div v-for="property in properties" v-bind:key="property.propertyId" class="property-placard" >
              <img v-bind:src="property.imageUrl" alt="Property Image" class="property-placard-image">
              <h3>{{ property.address }}</h3>
              <p>{{ property.description }}</p>
              <p>Rent: ${{ property.rentPrice }}</p>
            </div>
          </div>
          
                    <!-- Selected property details (only displayed if a property is selected) -->
          <div v-if="selectedProperty" class="selected-property-details">
            <h2>Property Details for {{ selectedProperty.address }}</h2>
            <img v-bind:src="selectedProperty.imageUrl" alt="Selected Property Image" />
            <p><strong>Description:</strong> {{ selectedProperty.description }}</p>
            <p><strong>Rent:</strong> ${{ selectedProperty.rentPrice }}</p>
            <p><strong>City:</strong> {{ selectedProperty.city }}</p>
            <p><strong>State:</strong> {{ selectedProperty.state }}</p>
          </div>
        </section>
    </div>
  </template>
  
  <script>
  //import RentalAgreementComponent from '../components/RentalAgreementComponent.vue';
  import PropertyService from '../services/PropertyService.js';
  import { ResourceService } from '../services/ResourceService.js';
 
  
  
  export default {
    data() {
    return {
      properties: [],  // Holds all property data
      selectedProperty: null,  // Holds the details of the selected property
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
    display: inline-block;
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
  