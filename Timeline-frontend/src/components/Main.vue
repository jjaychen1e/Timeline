<template>
  <div class="container py-5">
    <!-- Page Header -->
    <Header />
    <!-- End -->

    <!-- Button -->
    <UpdateButton v-bind:buttonName="update" v-on:updateTimeline="updateNewerTimeline" />
    <!-- End -->

    <!-- Timeline -->
    <Timeline v-bind:timelineItems="timelineItems" />
    <!-- End -->

    <!-- Button -->
    <UpdateButton v-bind:buttonName="getMore" v-on:updateTimeline="updateOlderTimeline" />
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
      timelineItems: [],
      update: 'Update',
      getMore: 'More'
    }
  },
  methods: {
    updateNewerTimeline() {
      axios
        .get('http://127.0.0.1:8081/TimelineItems', {
          params: {
            type: 0,
            id: this.timelineItems[0].id,
            count: -1
          }
        })
        .then(res => (this.timelineItems = res.data.concat(this.timelineItems)))
        .catch(err => console.log(err))
    },
    updateOlderTimeline() {
      axios
        .get('http://127.0.0.1:8081/TimelineItems', {
          params: {
            type: 1,
            id: this.timelineItems[this.timelineItems.length - 1].id,
            count: 2
          }
        })
        .then(res => (this.timelineItems = this.timelineItems.concat(res.data)))
        .catch(err => console.log(err))
    }
  },
  created() {
    axios
      .get('http://127.0.0.1:8081/InitialTimelineItems', {
        params: {
          count: 3
        }
      })
      .then(res => (this.timelineItems = res.data))
      .catch(err => console.log(err))
  }
}
</script>