<!-- Modal.vue -->
<template>
  <div class="modal" v-if="isVisible">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <h2>{{ title }}</h2>
      <p v-html="formattedContent"></p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true,
    },
    title: {
      type: String,
      default: "Modal Title",
    },
    content: {
      type: String,
      default: "Modal Content",
    },
  },
  computed: {
    formattedContent() {
      return this.content.replace(/\n/g, "<br />"); // Replace newlines with <br />
    },
  },
  methods: {
    closeModal() {
      this.$emit("close");
      this.isModalVisible = false;
    },
  },
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  width: 700px;
  position: relative;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}
</style>
