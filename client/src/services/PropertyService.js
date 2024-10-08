import axios from 'axios';

const PropertyService = {
  getAllProperties() {
    return axios.get('/properties');
  },

  getPropertyById(propertyId) {
    return axios.get(`/properties/${propertyId}`);
  },

  createProperty(propertyData) {
    return axios.post('/properties', propertyData);
  },

  updateProperty(propertyId, propertyData) {
    return axios.put(`/properties/${propertyId}`, propertyData);
  },

  deleteProperty(propertyId) {
    return axios.delete(`/properties/${propertyId}`);
  }
};

export default PropertyService;
