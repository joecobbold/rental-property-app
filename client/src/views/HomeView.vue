<template>
  <div id="main-div">
    <property-component />
    <rental-agreement-component/>
    <renter-component/>

  </div>
</template>

<script>
import PropertyComponent from '../components/PropertyComponent.vue';
import RentalAgreementComponent from '../components/RentalAgreementComponent.vue';
import RenterComponent from '../components/RenterComponent.vue';

export default {
  components: {
    PropertyComponent,
    RentalAgreementComponent,
    RenterComponent
}

}
</script>



<style>
#main-div {  /* main grid items */
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