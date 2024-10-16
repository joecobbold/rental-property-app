import axios from 'axios';

const AgreementService = {
    getAllRentalAgreements() {
        return axios.get('/rental_agreement');
    },

    getRentalAgreementById(id) {
        return axios.get(`/rental_agreement/${id}`)
    },

    getRentalAgreementsByRenterId(renterId) {
        return axios.get(`/rental_agreement/renter/${renterId}`)
    },

    getRentalAgreementsByPropertyId(propertyId) {
        return axios.get(`/rental_agreement/property/${propertyId}`)
    },

    createRentalAgreement(rentalAgreementData) {
        return axios.post('/rental_agreement', rentalAgreementData)
    },

    updateRentalAgreement(rentalAgreementId, updateRentalAgreementData) {
        return axios.put(`/rental_agreement/${rentalAgreementId}`, updateRentalAgreementData)
    },

    deleteRentalAgreement(rentalAgreementId) {
        return axios.delete(`rental_agreement/${rentalAgreementId}`)
    }

}

export default AgreementService;