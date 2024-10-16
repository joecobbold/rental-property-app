<template>
  <!-- <div v-if="isLoading">
    <loading-spinner id="spinner" v-bind:spin="true"/>
  </div> -->
  <div id="main-div">
    <property-list-component id="property" />
  </div>
</template>

<script>
import PropertyListComponent from "../components/PropertyListComponent.vue";

import PropertyService from "../services/PropertyService.js";
import AgreementService from "../services/AgreementService.js";
import LoadingSpinner from "../components/LoadingSpinner.vue";

export default {
  components: {
    PropertyListComponent,
  },

  created() {   //not using getAllRentalAgreements on this page just for learning purposes.You sent the same ticket number
    this.isLoading = true;
    Promise.all([
      PropertyService.getAllProperties(),
      AgreementService.getAllRentalAgreements(),
    ])
      .then(([propertyResponse, rentalAgreementResponse]) => {
        this.$store.commit("SET_PROPERTIES", propertyResponse.data);
        this.$store.commit("SET_RENTAL_AGREEMENTS",rentalAgreementResponse.data);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        this.isLoading = false;
      });
  },
};
</script>



<style>
#main-div {
  /* main grid items */
  grid-area: main;
  background-color: #ffffff;
  overflow: auto;
}

@media screen and (max-width: 425px) {
  /* main app adjustments */
  .rental-property-app {
    grid-template-columns: 1fr;
    grid-template-rows: auto 1fr auto;
  }
}
</style>