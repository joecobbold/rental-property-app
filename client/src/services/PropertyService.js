import axios from 'axios';


const PropertyService = {
  getAllProperties() {
    return axios.get('/property');
  },

  getPropertyById(propertyId) {
    return axios.get(`/property/${propertyId}`);
  },

  createProperty(propertyData) {
    return axios.post('/property', propertyData);
  },

  updateProperty(propertyId, propertyData) {
    return axios.put(`/property/${propertyId}`, propertyData);
  },

  deleteProperty(propertyId) {
    return axios.delete(`/property/${propertyId}`);
  }
};

export default PropertyService;
