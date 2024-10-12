import axios from 'axios';


const PropertyService = {
  getAllProperties() {
    return axios.get('/property');
  },

  getPropertyById(propertyId) {
    return axios.get(`/property/${propertyId}`);
  },

  createProperty(newPropertyData) {
    return axios.post('/property', newPropertyData);
  },

  updateProperty(propertyId, updatePropertyData) {
    return axios.put(`/property/${propertyId}`, updatePropertyData);
  },

  deleteProperty(propertyId) {
    return axios.delete(`/property/${propertyId}`);
  }
};

export default PropertyService;
