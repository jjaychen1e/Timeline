<template>
  <div>
    <div class="row" style="float: right">
      <el-button
        type="primary"
        @click="$emit('setDialogVisible', true)"
        icon="el-icon-chat-dot-round"
      >NEW POST</el-button>
    </div>
    <div class="row text-center text-white mb-5" style="clear: both" >
      <div class="col-lg-8 mx-auto">
        <h1 class="display-4">Timeline</h1>
        <p class="lead mb-0">This is a simple timeline demo for software testing.</p>
        <p class="lead">Created by JJAYCHEN, mufanx, Haven Tong</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      dialogFormVisible: false
    }
  }
}
</script>

<style>
</style>