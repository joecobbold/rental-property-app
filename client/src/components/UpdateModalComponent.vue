<!-- Modal.vue -->
<template>
  <div class="modal" v-if="isVisible">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <h2>{{ title }}</h2>

      <p v-html="formattedContent"></p>


            <!-- Property editing form -->
      <textarea v-model="editableContent" rows="10" cols="50"></textarea>
      
      <div class="modal-actions">
        <button @click="submitUpdate">Save Changes</button>
        <button @click="closeModal">Cancel</button>
      </div>

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
      default: 'Modal Title',
    },
    content: {
      type: String,
      default: 'Modal Content',
    },
  },
  computed: {
    formattedContent() {
      return this.content.replace(/\n/g, '<br />'); // Replace newlines with <br />
    },
  },
  methods: {
  closeModal() {
    this.$emit('close');
  },
  submitUpdate() {
      const updatedProperty = JSON.parse(this.editableContent); // Parse updated content back to object
      this.$emit('submit', updatedProperty); // Emit submit event to parent with updated data
    },
  },
  watch: {
    content(newContent) {
      this.editableContent = newContent; // Update editable content when the prop changes
    },
},

};
</script>
<style scoped>
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 90%; /* Increased width for a larger modal */
  max-width: 1000px; /* Limits maximum width */
  max-height: 90vh; /* Ensures it stays within the viewport */
  background-color: rgba(255, 255, 255, 0.95); /* Lighter background */
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #ccc; /* Optional border for a cleaner look */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Optional subtle shadow */
}

.modal-content {
  background-color: white;
  padding: 30px;
  border-radius: 8px;
  width: 100%; /* Ensure the content takes up full width of modal */
  position: relative;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}
</style>
