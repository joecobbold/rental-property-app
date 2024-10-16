import axios from 'axios';

const RenterService = {
    getAllRenters() {
        return axios.get('/renter')
    },

    getRenterById(id) {
        return axios.get(`/renter/${id}`)
    },

    createRenter(newRenterData) {
        return axios.post('/renter', newRenterData)
    },

    updateRenter(renterId, updateRenterData) {
        return axios.put(`/renter/${renterId}`, updateRenterData)
    },

    deleteRenter(renterId) {
        return axios.delete(`/renter/${renterId}`)
    }
    
}

export default RenterService;