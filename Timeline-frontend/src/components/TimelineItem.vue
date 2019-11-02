<template>
  <li class="timeline-item bg-white rounded ml-3 p-4 shadow">
    <div class="timeline-arrow"></div>
    <h2 class="h5 mb-0">{{ timelineItem.title }}</h2>
    <span class="small text-gray">
      <i class="fa fa-clock-o mr-1"></i>
      <!-- {{ timelineItem.postDateTime + " " + timelineItem.userName}} -->
      {{ timelineItem.postDateTime }}
    </span>
    <p class="small text-gray">{{ timelineItem.userName }}</p>
    <p class="text-small mt-2 font-weight-light message-text">{{ timelineItem.text }}</p>
    <div>
      <img class="message-img" v-if="timelineItem.imageUrl" v-bind:src="timelineItem.imageUrl" />
    </div>
  </li>
</template>

<script>
export default {
  name: 'TimelineItem',
  props: ['timelineItem']
}
</script>

<style>
p {
  /* padding: 0px; */
  margin: 0px;
}
.message-img {
  padding: 10px;
  display: block;
  margin: auto;
  width: 100%;
}
</style>