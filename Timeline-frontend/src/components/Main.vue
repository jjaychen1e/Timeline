<template>
  <div class="container py-5">
    <!-- Page Header -->
    <Header />
    <!-- End -->

    <!-- Buttons -->
    <UpdateButton v-on:updateTimeline="updateTimeline" />
    <!-- End -->

    <!-- Timeline -->
    <Timeline v-bind:timelineItems="timelineItems" />
    <!-- End -->
  </div>
</template>

<script>
import Timeline from './Timeline.vue'
import Header from './Header.vue'
import UpdateButton from './UpdateButton.vue'
import axios from 'axios'

export default {
  name: 'Main',
  components: {
    Timeline,
    UpdateButton,
    Header
  },
  data() {
    return {
      timelineItems: []
    }
  },
  methods: {
    updateTimeline() {
      axios
        .get('http://127.0.0.1:8081/TimelineItems')
        .then(res => (this.timelineItems = [res.data, ...this.timelineItems]))
        // .then(
        //   res =>
        //     (this.timelineItems.push(res.data[0]))
        // )
        .catch(err => console.log(err))
    }
  }
}
</script>