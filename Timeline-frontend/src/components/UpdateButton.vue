<template>
  <div class="row text-center">
    <div class="col-lg-8 mx-auto">
      <button type="button" class="btn btn-outline-dark" @click="$emit('updateTimeline')">Update</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UpdateButton'
}
</script>

<style scoped>
.button {
  background-color: rgb(255, 255, 255);
  color: balck;
}
</style>