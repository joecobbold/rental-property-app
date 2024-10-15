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
            <input type="text" id="searchBar" v-model="searchQuery" placeholder="Enter a city, neighborhood, or address" />
            <button id="searchButton" @click="propertyFilter">Search</button>
          </div>
        </section>

    <div v-if="isLoading">
      <loading-spinner id="spinner" v-bind:spin="true"/>
    </div>

        
        <section class="property-featured" id="property-featured" v-else>
          <h2>Available rentals in Columbus, OH</h2>

          <!-- Dynamic data--> <!-- New container for dynamic content -->
          <div id="properties-container">
            <div v-for="property in filteredProperties" v-bind:key="property.propertyId" class="property-placard" v-on:mouseover="property.hover = true"
              @mouseleave="property.hover = false">
              
              <img v-bind:src="property.imageUrl" alt="Property Image" class="property-placard-image">
              <h3>{{ property.address }}</h3>
              <p>{{ property.description }}</p>

              <router-link v-bind:to="{ name: 'propertyDetailsView', params: {propertyId: property.propertyId}}">
                <button>View Details</button>
              </router-link>
              
              <p v-if="property.hover">Rent: ${{ property.rentPrice }}</p>
            </div>
          </div>
        </section>
    </div>
  </template>
  
  <script>
  //import RentalAgreementComponent from '../components/RentalAgreementComponent.vue';
  import PropertyService from '../services/PropertyService.js';
  import LoadingSpinner from './LoadingSpinner.vue';

 
  
  
  export default {
    data() {
    return {
      properties: [],  // Holds all property data
      selectedProperty: null,
      searchQuery: "",
      isLoading: true 
    };
  },

  components: {
    LoadingSpinner
  },

      created() {
    // Fetch properties using PropertyService when the component is created
    PropertyService.getAllProperties()
    .then((response) => {
      this.properties = response.data;
      this.isLoading = false;
    })
  },
    computed: {
      filteredProperties() {
      return this.properties.filter((property) => {
        const query = this.searchQuery.toLowerCase();
        const matchesCity = property.city.toLowerCase().includes(query);
        const matchesState = property.state.toLowerCase().includes(query);
        const matchesAddress = property.address.toLowerCase().includes(query);
        
        
        return matchesCity || matchesState || matchesAddress;
      });
    },
      createdDate() {
        let created = new Date(this.property.dateAdded);
        return created.toLocaleDateString();
      },
      createdTime() {
        let created = new Date(this.property.dateAdded);
        return created.toLocaleTimeString();
      },
      methods: {
    // This method can be expanded later for custom actions
    propertyFilter() {
      console.log(`Filtering by: ${this.searchQuery}`);
    },
  },
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
  