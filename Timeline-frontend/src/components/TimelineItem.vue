<template>
  <li class="timeline-item bg-white rounded ml-3 p-4 shadow">
    <div class="timeline-arrow"></div>
    <h2 class="h5 mb-0">{{ timelineItem.title }}</h2>
    <span class="small text-gray">
      <i class="fa fa-clock-o mr-1"></i>{{ timelineItem.postDateTime }}
    </span>
    <p class="text-small mt-2 font-weight-light">
      {{ timelineItem.text }}
    </p>
  </li>
</template>

<script>
export default {
    name: 'TimelineItem',
    props: ['timelineItem']
};
</script>

<style>
</style>