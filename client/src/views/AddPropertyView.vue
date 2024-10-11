<template>
    <div class="add-property">
      <h2>Add a Property</h2>
      <form @submit.prevent="submitProperty">
        <div>
          <label for="propertyAddress">Address:</label>
          <input type="text" v-model="property.address" id="propertyAddress" required />
        </div>
        <div>
          <label for="propertyPrice">City:</label>
          <input type="text" v-model="property.city" id="propertyCity" required />
        </div>
        <div>
          <label for="propertyName">State:</label>
          <input type="text" v-model="property.state" id="propertyState" required />
        </div>
        <div>
          <label for="propertyName">Zipcode:</label>
          <input type="text" v-model="property.zipCode" id="propertyZip" required />
        </div>
        <div>
          <label for="propertyName">Rent price:</label>
          <input type="text" v-model="property.rentPrice" id="propertyPrice" required />
        </div>
        <div>
          <label for="propertyName">No. of Bedroom(s):</label>
          <input type="text" v-model="property.bedrooms" id="propertyBedroom" required />
        </div>
        <div>
          <label for="propertyName">No. of Bathroom(s):</label>
          <input type="text" v-model="property.bathrooms" id="propertyBathroom" required />
        </div>
        <div>
          <label for="propertyName">Square Feet:</label>
          <input type="text" v-model="property.squareFeet" id="propertySqFt" required />
        </div>
        <div>
          <label for="propertyName">Description:</label>
          <input type="text" v-model="property.description" id="propertyDesc" required />
        </div>
        <div>
          <label for="propertyImageUrl">Image URL:</label>
          <input type="text" v-model="property.imageUrl" id="propertyImageUrl" required />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  </template>
  
  <script>
import PropertyService from '../services/PropertyService';
  export default {
    data() {
      return {
        property: {
          address: '',
          city: '',
          state: '',
          zipCode: '',
          rentPrice: '',
          bedrooms: '',
          bathrooms: '',
          squareFeet: '',
          description: '',
          imageUrl: ''
        }
      };
    },
    methods: {
      submitProperty() {
        PropertyService.createProperty(this.property)
        .then((response) => {
           alert("Property Submission Successful")
           this.$router.push({name: "home"})
        })
      }
    }
  };
  </script>
  
  <style scoped>
  .add-property {
    max-width: 600px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
  }
  
  h2 {
    text-align: center;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  label {
    margin-bottom: 5px;
  }
  
  input {
    margin-bottom: 15px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  button {
    background-color: #e4853c;
    color: white;
    border: none;
    padding: 10px;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: black;
  }
  </style>
  