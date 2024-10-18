<template>
  <div class="rental-property-app">
    <header>
      <nav>
        <!-- Menu link -->
        <div class="menu">
          <a href="#" class="menu-toggle">Menu</a>

          <!-- Dropdown content (the links previously under li elements) -->
          <ul class="menu-items">
            <li>
              <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            </li>
            <li v-if="isAdmin">
              <router-link v-bind:to="{ name: 'addProperty' }"
                >Add a Property</router-link
              >
            </li>
            <li v-if="isAdmin || isUser">
              <router-link v-bind:to="{ name: 'renter' }"
                >Manage Renters</router-link
              >
            </li>
            <li v-if="isAdmin">
              <router-link v-bind:to="{ name: 'rental_agreement' }"
                >Manage Agreements</router-link
              >
            </li>
          </ul>
          <!-- <ul class="menu-items">
                  <li><a href="#home">Home</a></li>
                  <li><a href="#about">About</a></li>
                  <li><a href="#services">Services</a></li>
                  <li><a href="#contact">Contact</a></li>
              </ul> -->
        </div>
      </nav>

      <router-link v-bind:to="{ name: 'home' }" class="logo-link">
        <div class="logo" id="logo">
          <img src="./assets/BboldEnterprises.png" alt="Logo" />
        </div>
      </router-link>

      <router-link
        class="auth-links"
        v-bind:to="{ name: 'logout' }"
        v-if="this.$store.state.token"
      >
        <button>Logout</button>
      </router-link>
      <router-link class="auth-links" v-bind:to="{ name: 'login' }" v-else>
        <button>Login</button>
      </router-link>

      <!-- <div class="auth-links">
          <a id='Sign Up' href="#">Sign Up</a> / <a id='Sign In' href="#">Sign In</a>
      </div> -->
    </header>

    <main>
      <router-view />
    </main>

    <footer>
      <h3>
        Follow us on
        <a
          href="https://www.facebook.com/YourPage"
          target="_blank"
          rel="noopener noreferrer"
        >
          <font-awesome-icon
            :icon="['fab', 'facebook-square']"
            class="social-icon"
          />
        </a>
      </h3>
      <!--add icons with links next to this-->
      <!-- <p>
          <a href="https://www.facebook.com">Facebook</a> | 
          <a href="https://www.twitter.com">Twitter</a> | 
          <a href="https://www.linkedin.com">LinkedIn</a>
        </p> -->
      <p>
        <a href="#" @click.prevent="openModal('about')">About</a> |
        <a href="#" @click.prevent="openModal('services')">Services</a> |
        <a href="#" @click.prevent="openModal('contact')">Contact Us</a>
      </p>
      <p>
        Copyright ©2024 Bbold Enterprises - Excellence Through Simplicity. All
        rights reserved!
      </p>
    </footer>

    <div>
      <!-- Modal Component -->
      <modal-component
        :isVisible="isModalVisible"
        title="Bbold Enterprises Ltd."
        :content="modalContent"
        @close="isModalVisible = false"
      />
    </div>
  </div>
</template>
<script>
import ModalComponent from "./components/ModalComponent.vue";

export default {
  components: {
    ModalComponent,
  },
  data() {
    return {
      isModalVisible: false,
      modalContent: "",
      modalContentMap: {
        about: `
    This application is dedicated to my beloved mom, who taught me the importance of resilience and perseverance in the face of life's challenges. Your strength continues to inspire me every day, and I am forever grateful for the love and lessons you shared.

    To my wonderful wife, who has held the Cobbold fort during my long days and nights of study, your unwavering support and understanding have been my anchor. Thank you for your patience and encouragement; I could not have done this without you.

    And to my precious son, who has had to miss countless play nights and trail rides, I hope the time I've spent away will lead to a brighter future for us all. Your joy is my motivation, and I promise to make it all worth it.

    This journey is a tribute to each of you—my guiding lights. Thank you for your love and sacrifices.
  `,
        services: `
    The Property Rental Listing Application is a comprehensive platform designed to manage and streamline the rental property process. It serves as a central hub for property managers, renters, and administrators, offering a wide range of features to facilitate property listings, renter management, and rental agreement handling.
  `,
        contact: `
    Owner: Josalynn Cobbold
    Operations Manager: Joe Cobbold
    Property Manager: Joshua Cobbold
  `,
      },
    };
  },
  methods: {
    openModal(type) {
      this.isModalVisible = true;
      this.modalContent = this.modalContentMap[type]; // Set modal content based on type
    },
  },
  computed: {
    isAdmin() {
      return (
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
    isUser() {
      return (
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_USER")
      );
    },
  },
};
</script>

<style>
/* Global settings */

body {
  background-color: #ffffff;
}

.menu-items {
  margin-top: 30px;
}

#spinner {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  width: 50px;
  height: 50px;
  font-size: 24px;
  margin-top: 20vh;
  color: #414e58;
}

.rental-property-app {
  margin: 0;
  padding: 0;
  height: 100%;
}

/**************************************************************************************************/
/* HEADER GRID */
header {
  grid-area: header;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr; /* Left (menu), center (logo), right (auth-links) */
  align-items: center;
  background-color: #414e58;
  color: #ffffff;
  height: 76px;
  padding: 0 20px;
  box-sizing: border-box;
}

.logo-link {
  justify-self: center;
}

.auth-links {
  justify-self: end;
}

/*header styling*/
/*Menu styling*/
.menu-items {
  /* Hide the dropdown by default */
  display: none;
  list-style: none;
  margin: 10;
  padding: 0;
  background-color: #414e58; /* Match header background */
  position: absolute;
  top: 50px; /* Adjust this depending on your header height */
}

.menu:hover .menu-items {
  /* Show dropdown when the menu is hovered */
  display: block;
}

.menu-toggle {
  /* Style for the menu links */
  color: #ffffff;
  text-decoration: none;
  background-color: #e4853c;
  padding: 10px 20px;
  display: inline-block;
  border-radius: 5px;
}

.menu-toggle:hover {
  background-color: black;
}

.menu-items li {
  /* Dropdown item styling */
  padding: 5px;
}

.menu-items li a {
  color: #ffffff; /* Dropdown links color */
  text-decoration: none;
  text-align: center;
  padding: 10px;
  display: block;
}

.menu-items li a:hover {
  background-color: #e4853c;
}

/* Logo styling */
header .logo img {
  height: 80px; /* Set the height of the logo image */
  width: auto; /* Maintain aspect ratio */
  object-fit: contain; /* Ensure the image scales within given height */
}

/*Auth-links styling*/
.auth-links a {
  color: #ffffff;
  text-decoration: none;
}

.auth-links a:hover {
  color: #e4853c; /* change link to Orange */
  text-decoration: underline;
}

#li-links a {
  color: #ffffff; /* Set the text color to white */
  text-decoration: none; /* Remove underline */
}

#li-links a:hover {
  color: #e4853c; /* change link to Orange */
  text-decoration: underline;
}

#li-links {
  /* Utility for ul */
  display: flex;
  gap: 10px;
  list-style: none;
  padding: 0;
  margin: 0;
}

/**************************************************************************************************/
/*MAIN GRID*/
/*Search area styling*/
.mainSearch {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  text-align: center;
  padding: 20px;
  margin-top: 0;
}

#searchWrap h1,
#searchWrap p {
  color: #ffffff;
}

.mainTitle {
  margin-bottom: 2px; /* Adjust the space between h1 and p */
}

#quickSearch {
  /*Search bar styling*/
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  margin: 0 auto;
}

#searchBar {
  width: 100%;
  max-width: 500px;
  padding: 10px;
  border: 1px solid #ffffff;
  border-radius: 5px;
  margin-right: 10px;
  box-sizing: border-box;
}

#searchButton {
  padding: 10px 20px;
  background-color: #e4853c;
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

#searchButton:hover {
  background-color: black;
}

/* Main content styling*/
.rental-property-app {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: auto 1fr auto;
  grid-template-areas:
    "header "
    "main"
    "footer";
  height: 100vh;
}

#main-div {
  /* main grid items */
  grid-area: main;
  background-color: #ffffff;
  overflow: auto;
}

.homepageContent {
  background-color: #306ba8;
}

/******************************************************************************/
/* FOOTER GRID*/
footer {
  grid-area: footer;
  background-color: #e4853c;
  color: #ffffff;
  font-size: 14px;
  text-align: center;
  height: 76px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 5px 10px;
  box-sizing: border-box;
}

footer h3 {
  margin: 20px 0 0 0;
}

footer p {
  margin: 2px 2px; /* Reduce margins around paragraphs */
}

footer p:last-of-type {
  margin-bottom: 20px; /* Add bottom margin */
}

footer a {
  color: #ffffff;
  text-decoration: none;
  margin: 0 5px;
}

footer a:hover {
  text-decoration: underline;
}

.social-icon {
  font-size: 24px;
  margin-left: 10px;
}

/* MOBILE STYLING */
/* Mobile View (425px and smaller) */
@media screen and (max-width: 425px) {
  /* main app adjustments */
  .rental-property-app {
    grid-template-columns: 1fr;
    grid-template-rows: auto 1fr auto;
  }

  /* Menu styling */
  .menu {
    display: block;
  }

  .menu-items {
    display: none;
    list-style-type: none;
    padding: 0;
    margin: 0;
    background-color: #414e58; /* Same as header background */
  }

  .menu-toggle {
    display: block;
    color: white;
    background-color: #414e58;
    padding: 10px;
    text-align: center;
    cursor: pointer;
  }

  .menu-toggle:hover {
    background-color: #e4853c;
  }

  .menu.active .menu-items {
    /* Show the menu on click */
    display: block;
  }

  .menu-items li a {
    display: block;
    padding: 10px;
    color: #ffffff;
    text-decoration: none;
    text-align: center;
  }

  .menu-items li a:hover {
    background-color: #e4853c;
  }

  .logo img {
    /* Logo adjustments */
    height: 60px;
    width: auto;
  }

  .auth-links {
    justify-self: center;
  }

  #quickSearch {
    /* Search bar */
    flex-direction: column;
  }

  #searchBar {
    width: 100%;
    max-width: none;
    margin: 10px 0;
  }

  #searchButton {
    width: 100%;
  }

  /*Footer*/
  footer {
    font-size: 10px; /* Smaller font for mobile */
    padding: 10px;
  }

  footer h3 {
    margin: 10px 0;
  }

  footer p {
    margin: 5px 0;
  }
}
</style>