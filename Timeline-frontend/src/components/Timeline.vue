<template>
  <div class="row">
    <div class="col-lg-7 mx-auto">
      <ul class="timeline">
        <transition-group name="list" tag="p">
          <div v-for="timelineItem in timelineItems" :key="timelineItem.id">
            <TimelineItem v-bind:timelineItem="timelineItem" />
          </div>
        </transition-group>
      </ul>
    </div>
  </div>
</template>

<script>
import TimelineItem from './TimelineItem.vue'

export default {
  name: 'Timeline',
  components: {
    TimelineItem
  },
  props: ['timelineItems']
}
</script>

<style>
.list-item {
  display: inline-block;
  margin-right: 10px;
}
.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter, .list-leave-to
/* .list-leave-active for below version 2.1.8 */ {
  opacity: 0;
  transform: translateY(30px);
}
</style>