import axios from 'axios';

const RentalAgreementService = {
    getAllRentalAgreements() {
        return axios.get('/rental_agreement');
    },
    getRentalAgreementsByPropertyId(propertyId) {
        return axios.get(`/rental_agreement/property/${propertyId}`)
    }
}

export default RentalAgreementService;